package com.jerios.evilMinecraftFixes.mixins.late.thirst;


import com.thetorine.thirstmod.core.player.PoisonLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = PoisonLogic.class)
public interface PoisionLogicAccessor {

    @Accessor("isPlayerPoisoned")
    void evil$setPlayerPosioned(boolean val);



}
