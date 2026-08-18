package com.jerios.evilMinecraftFixes.mixins.late.hee;

import chylex.hee.sound.CustomMusicTicker;
import chylex.hee.sound.EndMusicType;
import net.minecraft.client.audio.MusicTicker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CustomMusicTicker.class)
public class MixinCustomMusicTicker {

   @Shadow(remap = false)
   private EndMusicType playingEndMusicType = null;

   @Shadow(remap = false)
    private int endMusicTimer;

    @Inject(method = "updateEndMusic", at= @At(value = "INVOKE", target = "Lnet/minecraft/client/audio/MusicTicker$MusicType;func_148634_b()I", shift = At.Shift.BEFORE), remap = false)
    private void evil$injectFixedMusicTicker(CallbackInfo ci) {
        if (playingEndMusicType.isBossMusic) {
            endMusicTimer = 0;
        }

    }


}
