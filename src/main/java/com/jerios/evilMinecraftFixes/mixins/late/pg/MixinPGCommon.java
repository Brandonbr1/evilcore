package com.jerios.evilMinecraftFixes.mixins.late.pg;

import com.jerios.evilMinecraftFixes.CQAdditions.CQInteg;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import particleman.forge.CommonProxy;
import particleman.forge.ParticleMan;

@Mixin(CommonProxy.class)
public class MixinPGCommon {

    @Redirect(method = "init",at= @At(value = "INVOKE", target = "Lcpw/mods/fml/common/registry/GameRegistry;addRecipe(Lnet/minecraft/item/ItemStack;[Ljava/lang/Object;)V"), remap = false)
    private void evil$no(ItemStack output, Object[] params) {
        GameRegistry.addRecipe(new ItemStack(ParticleMan.itemGlove), new Object[]{"OLL", "LRR", "LRD", 'L', CQInteg.goldFeather, 'R', Blocks.redstone_block, 'D', Blocks.diamond_block, 'O', Blocks.lapis_block});
    }

}
