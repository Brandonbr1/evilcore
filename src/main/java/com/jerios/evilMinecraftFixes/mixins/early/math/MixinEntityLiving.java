package com.jerios.evilMinecraftFixes.mixins.early.math;

import net.minecraft.entity.EntityLiving;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.jerios.evilMinecraftFixes.fasterMath.FastTrigno;

@Mixin(EntityLiving.class)
public class MixinEntityLiving {

    @Redirect(
        method = "faceEntity(Lnet/minecraft/entity/Entity;FF)V",
        at = @At(value = "INVOKE", target = "Ljava/lang/Math;atan2(DD)D"))
    private double redirectAtan2InFaceEntity(double y, double x) {
        return FastTrigno.fastAtan2(y, x);
    }
}
