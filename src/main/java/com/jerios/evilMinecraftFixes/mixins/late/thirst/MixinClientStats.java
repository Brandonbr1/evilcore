package com.jerios.evilMinecraftFixes.mixins.late.thirst;

import com.thetorine.thirstmod.core.client.player.ClientStats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientStats.class)
public class MixinClientStats {

    @Inject(method = "getInstance", at=@At("HEAD"), remap = false)
    private static void evil$crash(CallbackInfoReturnable<ClientStats> cir) {
        //TODO: MIGRATE MOVEMENT FROM THIS CLIENT STATIC GLOBAL STATE
        throw new RuntimeException("HEY IF YOU SEE THIS VORTEX MESSED SOMETHING UP! CONTACT HIM AND END THE LOGS LOL");
    }


}
