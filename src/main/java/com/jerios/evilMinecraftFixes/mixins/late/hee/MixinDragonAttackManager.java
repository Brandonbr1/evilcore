package com.jerios.evilMinecraftFixes.mixins.late.hee;

import chylex.hee.entity.boss.dragon.managers.DragonAttackManager;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(DragonAttackManager.class)
public class MixinDragonAttackManager {


    @Redirect(method = "biteClosePlayers", at= @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 0), remap = false)
    private int evil$biteAlwaysDoesEffects(Random instance, int i) {
        return 0;
    }

    @Redirect(method = "biteClosePlayers", at= @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 1), remap = false)
    private int evil$biteAlwaysDoesEffects2(Random instance, int i) {
        return 0;
    }
}
