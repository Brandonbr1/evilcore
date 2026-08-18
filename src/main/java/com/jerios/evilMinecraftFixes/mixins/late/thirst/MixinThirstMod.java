package com.jerios.evilMinecraftFixes.mixins.late.thirst;

import com.thetorine.thirstmod.core.main.ThirstMod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.PrintStream;

@Mixin(ThirstMod.class)
public class MixinThirstMod {

    @Unique private static final Logger THIRST_LOGGER = LogManager.getLogger("ThirstMod");

    @Redirect(method = "print", at= @At(value = "INVOKE", target = "Ljava/io/PrintStream;println(Ljava/lang/String;)V"), remap = false)
    private static void evil$redirectLogger(PrintStream instance, String x) {
        THIRST_LOGGER.info(x);
    }


}
