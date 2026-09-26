package com.jerios.evilMinecraftFixes.mixins.late.animalPlus;

import clickme.animals.AnimalsPlus;
import cpw.mods.fml.common.eventhandler.EventBus;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = AnimalsPlus.class, remap = false)
public class MixinAnimalsPlus {

    @Redirect(method = "load", at= @At(value = "INVOKE", target = "Lcpw/mods/fml/common/eventhandler/EventBus;register(Ljava/lang/Object;)V"))
    private void evil$doNotRegister(EventBus instance, Object eventType) {

    }

    /**
     * @author Jerios
     * @reason Disable Update checker
     */
    @Overwrite
    protected void notificatePlayerInChat(EntityPlayer player) {

    }

    /**
     * @author Jerios
     * @reason Disable Update checker
     */
    @Overwrite
    private void checkForPromotions() {
    }
}
