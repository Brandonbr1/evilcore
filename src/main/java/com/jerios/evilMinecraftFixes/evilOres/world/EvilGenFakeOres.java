package com.jerios.evilMinecraftFixes.evilOres.world;

import com.jerios.evilMinecraftFixes.evilOres.OresInteg;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

public class EvilGenFakeOres implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.dimensionId) {
            case -1:
                this.generateNether(world, chunkX * 16, chunkZ * 16, random);
                break;
            default:
                break;

        }
    }


    public void generateNether(World world, int x, int z, Random rand) {
        fake$generateOre(OresInteg.fakeNetherrite, world, rand, x, z, 4, 8, 1, 0, 120, Blocks.netherrack);
    }

    public void fake$generateOre(Block block, World world, Random random, int chunkX, int chunkZ, int minVienSize, int maxVienSize, int chance, int minY, int maxY, Block generateIn) {
        int vienSize = minVienSize + random.nextInt(maxVienSize - minVienSize);
        int hightRange = maxY - minY;
        for (int i = 0; i < chance; i++) {
            int xRand = chunkX + random.nextInt(16);
            int yRand = random.nextInt(hightRange) + minY;
            int zRand = chunkZ + random.nextInt(16);
            (new WorldGenMinable(block, vienSize, generateIn)).generate(world, random, xRand, yRand, zRand);
        }
    }

}
