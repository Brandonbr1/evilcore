package com.jerios.evilMinecraftFixes.mixins.early.hard;

import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayer.class)
public class MixinLongerSleepTime {

    @Shadow public int sleepTimer;

    @Inject(method =  "wakeUpPlayer", at=@At("TAIL"))
    private void evil$setNewTimer(boolean p_70999_1_, boolean updateWorldFlag, boolean setSpawn, CallbackInfo ci) {
        sleepTimer = -550;
    }

    @Inject(method = "sleepInBedAt", at=@At("TAIL"))
    private void evil$SleepBedTimer(int x, int y, int z, CallbackInfoReturnable<EntityPlayer.EnumStatus> cir) {
        sleepTimer = -550;
    }
}
