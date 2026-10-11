package com.jerios.evilMinecraftFixes.mixins.late.infernalMob;

import atomicstryker.infernalmobs.common.EntityEventHandler;
import com.jerios.evilMinecraftFixes.savedata.EvilData;
import hostileworlds.config.ModConfigFields;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityEventHandler.class)
public class MixinEntityEventHandler {

    @Inject(method = "onEntityJoinedWorld", at=@At("HEAD"), cancellable = true, remap = false)
    private void evil$InjectOnJoinWorld(EntityJoinWorldEvent event, CallbackInfo ci) {
        if (!event.world.isRemote)
        {
            EvilData data = EvilData.get(event.world);
            // impossible for a mob to become rare within the 15 mins of the world as a grace.
            if (data != null && data.worldTicks <= 18000) {
                ci.cancel();
            }
        }



    }


}
