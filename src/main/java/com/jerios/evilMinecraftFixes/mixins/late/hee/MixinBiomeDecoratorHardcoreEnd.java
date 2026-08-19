package com.jerios.evilMinecraftFixes.mixins.late.hee;

import chylex.hee.system.logging.Log;
import chylex.hee.system.logging.Stopwatch;
import chylex.hee.system.util.MathUtil;
import chylex.hee.world.biome.BiomeDecoratorHardcoreEnd;
import chylex.hee.world.feature.WorldGenEndiumOre;
import com.jerios.evilMinecraftFixes.cfg.Config;
import com.jerios.evilMinecraftFixes.hee.DecoratorEndDeamon;
import net.minecraft.world.biome.BiomeEndDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BiomeDecoratorHardcoreEnd.class)
public class MixinBiomeDecoratorHardcoreEnd extends BiomeEndDecorator {

    @Unique public DecoratorEndDeamon deamonStructure;

    @Inject(method = "<init>", at=@At("TAIL"), remap = false)
    private void evil$inject(CallbackInfo ci) {
        deamonStructure = new DecoratorEndDeamon();
    }

    // sadly, alot of errors are cuased when trying to generate this!
    @Unique
    private boolean tryGenerateSlient(WorldGenerator generator, int x, int y, int z) {
        try {
            return generator.generate(this.currentWorld, this.randomGenerator, x, y, z);
        } catch (RuntimeException var6) {
          //  Log.warn("Failed generating " + generator.getClass().getSimpleName() + " at " + (this.chunk_X + x) + "," + (this.chunk_Z + z) + ", there might be an empty chunk.", new Object[0]);
            return false;
        }
    }
    @Shadow(remap = false)
    private boolean tryGenerate(WorldGenerator generator, int x, int y, int z) {
        try {
            return generator.generate(this.currentWorld, this.randomGenerator, x, y, z);
        } catch (RuntimeException var6) {
            Log.warn("Failed generating " + generator.getClass().getSimpleName() + " at " + (this.chunk_X + x) + "," + (this.chunk_Z + z) + ", there might be an empty chunk.", new Object[0]);
            return false;
        }
    }

    // This causes lots of decoration errors, sorry!!!
    @Inject(method = "genDecorations", at= @At(value = "INVOKE", target = "Lchylex/hee/system/logging/Stopwatch;finish(Ljava/lang/String;)V", ordinal = 5))
    private void evil$in(BiomeGenBase biome, CallbackInfo ci) {

        double distFromCenter2 = MathUtil.distance((this.chunk_X >> 4), (double)(this.chunk_Z >> 4)) * (double)16.0F;
        if (distFromCenter2 > (double) Config.distanceToSpawn && this.randomGenerator.nextInt(Config.chanceToSpawnIsland) == 0 && Math.abs(this.randomGenerator.nextGaussian()) < 0.285) {
          //  Stopwatch.timeAverage("EnderDeamonStructure", 64);
            int xx = randX();
            int zz= randZ();
            // if enriched enabled, get possible block as x and z, else random height
            int height = Config.trySpawnOnEnrichedIslandOnly ? this.currentWorld.getTopSolidOrLiquidBlock(xx, zz) : 32 + this.randomGenerator.nextInt(40);
            this.tryGenerate(this.deamonStructure, xx, height, zz);
          //  Stopwatch.finish("EnderDeamonStructure");
        }
    }

    private int randX() {
        return this.chunk_X + this.randomGenerator.nextInt(16) + 8;
    }

    private int randZ() {
        return this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
    }
}
