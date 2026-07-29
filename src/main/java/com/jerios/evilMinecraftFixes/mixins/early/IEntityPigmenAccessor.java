package com.jerios.evilMinecraftFixes.mixins.early;

import net.minecraft.entity.monster.EntityPigZombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EntityPigZombie.class)
public interface IEntityPigmenAccessor {

    @Accessor("angerLevel")
    void evil$setAnger(int anger);

    @Accessor("randomSoundDelay")
    void evil$soundDelay(int del);
}
