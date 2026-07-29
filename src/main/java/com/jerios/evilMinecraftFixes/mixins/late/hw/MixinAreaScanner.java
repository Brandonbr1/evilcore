package com.jerios.evilMinecraftFixes.mixins.late.hw;

import hostileworlds.ai.AreaScanner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.PrintStream;

@Mixin(AreaScanner.class)
public class MixinAreaScanner {

    @Redirect(method = "areaScan", at= @At(value = "INVOKE", target = "Ljava/io/PrintStream;println(Ljava/lang/String;)V"), remap = false)
    private void disablePrintSpam(PrintStream instance, String x) {

    }
}
