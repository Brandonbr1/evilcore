package com.jerios.evilMinecraftFixes.mixins.late.damageInd;

import DamageIndicatorsMod.client.DIClientProxy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(DIClientProxy.class)
public class MixinDIProxy {

    /**
     * @author Jerios
     * @reason Disable Update checker, we no longer need it
     */
    @Overwrite(remap = false)
    public void trysendmessage() {
    }

}
