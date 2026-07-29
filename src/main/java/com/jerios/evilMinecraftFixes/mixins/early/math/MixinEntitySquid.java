package com.jerios.evilMinecraftFixes.mixins.early.math;

import com.jerios.evilMinecraftFixes.fasterMath.FastTrigno;
import net.minecraft.entity.passive.EntitySquid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntitySquid.class)
public class MixinEntitySquid {

    @Redirect(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Ljava/lang/Math;atan2(DD)D"))
    private double redirectAtan2onUpdate(double d0, double d1) {
        return FastTrigno.fastAtan2(d0, d1);
    }
}
