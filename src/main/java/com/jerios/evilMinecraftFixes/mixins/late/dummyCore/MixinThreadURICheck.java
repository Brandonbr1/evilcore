package com.jerios.evilMinecraftFixes.mixins.late.dummyCore;

import DummyCore.Utils.ModVersionChecker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ModVersionChecker.ThreadURICheck.class)
public class MixinThreadURICheck {


    /**
     * @author Jerios
     * @reason Remove All traces of Update Checker
     */
    @Overwrite(remap = false)
    public void run() {

    }
}
