package com.jerios.evilMinecraftFixes.mixins.late.fakeOres;

import fr.elias.fakeores.common.FakeOres;
import fr.elias.fakeores.common.WorldGenFakeOres;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(WorldGenFakeOres.class)
public class MixinWorldGen {

    /**
     * @author Jerios
     * @reason Fix worldgen leaks.
     */
    @Overwrite(remap = false)
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.dimensionId) {
            case 0:
                this.generateSurface(world, chunkX * 16, chunkZ * 16, random);
                break;
            case -1:
                this.generateNether(world, chunkX * 16, chunkZ * 16, random);
                break;
            default:
                if (world.provider.dimensionId == FakeOres.dimID) {
                    this.generateCustom(world, chunkX * 16, chunkZ * 16, random);
                }
                break;

        }
    }

    @Shadow(remap = false)
    public void generateSurface(World world, int x, int z, Random rand) { }
    @Shadow(remap = false)
    public void generateNether(World world, int x, int z, Random rand) { }

    @Shadow(remap = false)
    public void generateCustom(World world, int x, int z, Random rand) { }
}
