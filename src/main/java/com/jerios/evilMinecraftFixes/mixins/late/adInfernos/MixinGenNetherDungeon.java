package com.jerios.evilMinecraftFixes.mixins.late.adInfernos;

import com.superdextor.dextersnether.worldgen.GenNetherDungeon;
import com.superdextor.dextersnether.worldgen.NetherDungeonHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(GenNetherDungeon.class)
public class MixinGenNetherDungeon {

    @Redirect(method = "pickMobSpawner", at= @At(value = "INVOKE", target = "Lcom/superdextor/dextersnether/worldgen/NetherDungeonHooks;getRandomDungeonMob(Ljava/util/Random;)Ljava/lang/String;"))
    public String f(Random rand) {
        return "dextersnether." + NetherDungeonHooks.getRandomDungeonMob(rand);
    }
}
