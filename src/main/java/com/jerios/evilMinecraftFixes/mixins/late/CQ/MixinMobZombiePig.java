package com.jerios.evilMinecraftFixes.mixins.late.CQ;

import com.chocolate.chocolateQuest.entity.mob.registry.MobZombiePig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.PrintStream;

@Mixin(MobZombiePig.class)
public class MixinMobZombiePig {

    @Redirect(method = "setItemColor", at= @At(value = "INVOKE", target = "Ljava/io/PrintStream;println(Ljava/lang/Object;)V"), remap = false)
    private void evil$disableLogSpam(PrintStream instance, Object x) {

    }
}
