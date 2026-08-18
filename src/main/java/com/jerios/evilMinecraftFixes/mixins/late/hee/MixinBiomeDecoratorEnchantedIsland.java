package com.jerios.evilMinecraftFixes.mixins.late.hee;

import chylex.hee.world.structure.island.biome.IslandBiomeBase;
import chylex.hee.world.structure.island.biome.decorator.BiomeDecoratorEnchantedIsland;
import chylex.hee.world.structure.island.biome.decorator.IslandBiomeDecorator;
import chylex.hee.world.structure.island.biome.feature.island.StructureShadowOrchid;
import com.jerios.evilMinecraftFixes.hee.DecoratorEndDeamon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BiomeDecoratorEnchantedIsland.class)
public class MixinBiomeDecoratorEnchantedIsland extends IslandBiomeDecorator {

    @Unique
    private final DecoratorEndDeamon evil$endDeamon = new DecoratorEndDeamon();

    @Inject(method = "genHomeland", at=@At("HEAD"), remap = false)
    private void evil$f(CallbackInfo ci) {
        if (rand.nextInt(7) <= 3) {
            for (int i = 0; i < 5 &&
                !evil$endDeamon.generateInWorld(this.world, rand, getBiome()); i++);
        }
    }

    @Shadow(remap = false)
    protected IslandBiomeBase getBiome() {
        return null;
    }
}
