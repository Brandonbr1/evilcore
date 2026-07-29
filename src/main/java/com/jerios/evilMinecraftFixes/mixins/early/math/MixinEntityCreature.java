package com.jerios.evilMinecraftFixes.mixins.early.math;

import net.minecraft.entity.EntityCreature;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.jerios.evilMinecraftFixes.fasterMath.FastTrigno;

@Mixin(EntityCreature.class)
public class MixinEntityCreature {

    @Redirect(method = "updateEntityActionState", at = @At(value = "INVOKE", target = "Ljava/lang/Math;atan2(DD)D"))
    private double redirectAtan2onUpdate(double d0, double d1) {
        return FastTrigno.fastAtan2(d0, d1);
    }
}
