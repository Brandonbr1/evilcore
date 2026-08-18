package com.jerios.evilMinecraftFixes.mixins.late;

import atomicstryker.infernalmobs.client.RendererBossGlow;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RendererBossGlow.class)
public class MixinRendererBossGlow {

    @Inject(method = "renderBossGlow", at= @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;getMinecraft()Lnet/minecraft/client/Minecraft;", shift = At.Shift.AFTER), cancellable = true, remap = false)
    private void evil$inject(float renderTick, CallbackInfo ci) {

        Minecraft minecraft = Minecraft.getMinecraft();

        if (minecraft.isGamePaused()) {
            ci.cancel();
        }

    }


}
