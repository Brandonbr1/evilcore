package com.jerios.evilMinecraftFixes.mixins.late.damageInd;

import DamageIndicatorsMod.client.DIClientProxy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(targets = {"DamageIndicatorsMod/client/DIClientProxy$1"})
public class MixinG {


    /**
     * @author Jerios
     * @reason Disable update checker fully.
     */
    @Overwrite(remap = false)
    public void run() {

    }

}
