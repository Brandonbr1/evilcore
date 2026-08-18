package com.jerios.evilMinecraftFixes.mixins.late.thirst;

import com.thetorine.thirstmod.core.content.ItemLoader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemLoader.class)
public class MixinItemLoader {

    @Shadow(remap = false) public static Item goldCoin;

    @Redirect(method = "<init>", at= @At(value = "INVOKE", target = "Lcpw/mods/fml/common/registry/GameRegistry;addShapelessRecipe(Lnet/minecraft/item/ItemStack;[Ljava/lang/Object;)V", ordinal = 0), remap = false )
    public void evil$makeRecipeExpensive(ItemStack output, Object[] params) {
        GameRegistry.addShapelessRecipe(new ItemStack(goldCoin, 15), new Object[]{Items.gold_ingot, Items.iron_ingot});
    }
}
