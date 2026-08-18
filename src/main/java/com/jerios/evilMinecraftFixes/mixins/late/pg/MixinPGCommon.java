package com.jerios.evilMinecraftFixes.mixins.late.pg;

import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import particleman.forge.CommonProxy;

@Mixin(CommonProxy.class)
public class MixinPGCommon {

    @Redirect(method = "init",at= @At(value = "INVOKE", target = "Lcpw/mods/fml/common/registry/GameRegistry;addRecipe(Lnet/minecraft/item/ItemStack;[Ljava/lang/Object;)V"), remap = false)
    private void evil$no(ItemStack output, Object[] params) {

    }

}
