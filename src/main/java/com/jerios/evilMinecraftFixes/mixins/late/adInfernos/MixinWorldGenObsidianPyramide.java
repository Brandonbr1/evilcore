package com.jerios.evilMinecraftFixes.mixins.late.adInfernos;

import com.superdextor.dextersnether.worldgen.WorldGenObsidianPyramide;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WorldGenObsidianPyramide.class)
public class MixinWorldGenObsidianPyramide {

    @Redirect(method = "generate", at= @At(value = "INVOKE", target = "Lnet/minecraft/tileentity/MobSpawnerBaseLogic;setEntityName(Ljava/lang/String;)V"))
    private void t(MobSpawnerBaseLogic instance, String p_98272_1_) {
        instance.setEntityName("dextersnether." + p_98272_1_);
    }
}
