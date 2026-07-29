package com.jerios.evilMinecraftFixes.mixins.early.math;

import net.minecraft.block.BlockLiquid;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.jerios.evilMinecraftFixes.fasterMath.FastTrigno;

@Mixin(BlockLiquid.class)
public class MixinBlockLiquid {

    @Redirect(
        method = "getFlowDirection(Lnet/minecraft/world/IBlockAccess;IIILnet/minecraft/block/material/Material;)D",
        at = @At(value = "INVOKE", target = "Ljava/lang/Math;atan2(DD)D"))
    private static double redirectAtan2InFlow(double y, double x) {
        return FastTrigno.fastAtan2(y, x);
    }
}
