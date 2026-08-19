package com.jerios.evilMinecraftFixes.hee;

import chylex.hee.init.BlockList;
import chylex.hee.system.util.BlockPosM;
import com.jerios.evilMinecraftFixes.EnderDeamonIsolator;
import com.jerios.evilMinecraftFixes.cfg.Config;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class DecoratorEndDeamon extends WorldGenerator {

    protected final void placeBlock(World world, Block block, int metadata, int x, int y, int z) {
            world.setBlock(x, y, z, (block == null) ? Blocks.air : block, metadata, 3);
    }

    public static final byte metaEnchanted = 2;

    public boolean generate(World world, Random rand, int x, int y, int z) {
      //  int sz = (int)Math.floor(48.0D), halfsz = sz >> 1;
       // x = x + rand.nextInt(sz) - halfsz;
      //  z = z + rand.nextInt(sz) - halfsz;
        BlockPosM tmpPos = BlockPosM.tmp();

        // if we are not set to spawn on enriched island, use algorithm that spawns own islands
        if (!Config.trySpawnOnEnrichedIslandOnly) {
            if (tmpPos.set(x - 7, y, z).getBlock(world) != Blocks.air && tmpPos.set(x + 7, y, z).getBlock(world) != Blocks.air && tmpPos.set(x, y, z - 7).getBlock(world) != Blocks.air && tmpPos.set(x, y, z + 7).getBlock(world) != Blocks.air && tmpPos.set(x, y - 7, z).getBlock(world) != Blocks.air && tmpPos.set(x, y + 7, z).getBlock(world) != Blocks.air && tmpPos.set(x, y - 15, z).getBlock(world) != Blocks.air && tmpPos.set(x, y + 15, z).getBlock(world) != Blocks.air) {
                return false;
            }
            if (world.getBlock(x, y, z) != Blocks.air && world.getBlock(x, y - 1, z) != Blocks.air && world.getBlock(x, y + 1, z) != Blocks.air) {
                return false;
            }
        } else {
            // enriched island algorithm
            if (world.getBlock(x,y,z) != BlockList.end_terrain
                || world.getBlock(x,y - 1, z) != BlockList.end_terrain
                || world.getBlock(x,y + 1, z) != BlockList.end_terrain
                && world.getBlockMetadata(x,y,z) != metaEnchanted
                || world.getBlockMetadata(x,y - 1, z) != metaEnchanted
                || world.getBlockMetadata(x,y + 1, z) != metaEnchanted
            ) {
                return false;
            }
        }

       // if (world.getBlock(x, y, z) != BlockList.end_terrain || world.getBlock(x, y, z) != Blocks.end_stone) {
      //      return false;
      //  }

        boolean canGenerate = true;
        for (int testX = x - 5; testX <= x + 5; testX++) {
            for (int testZ = z - 5; testZ <= z + 5; testZ++) {
           /**     int hy;
                if (Math.abs((hy = getHighestY(testX, testZ)) - y) > 1)
                    return false;
                if (hy > y)
                    y = hy;
            **/
            }
        }
        for (int xx = x - 5; xx <= x + 5; xx++) {
            for (int zz = z - 5; zz <= z + 5; zz++) {
                int j = (xx == x - 5 || xx == x + 5) ? 5 : ((xx == x - 4 || xx == x + 4) ? 4 : 0);
                int b = (zz == z - 5 || zz == z + 5) ? 5 : ((zz == z - 4 || zz == z + 4) ? 4 : 0);
                if ((j != 5 || b != 4) && (j != 4 || b != 5) && (j != 5 || b != 5)) {
                    for (int k = y; k < y + 2; ) {
                        placeBlock(world,null, 0, xx, k, zz);
                        k++;
                    }
                    placeBlock(world,BlockList.end_terrain, 2, xx, y - 1, zz);
                }
            }
        }
        for (int a = 0; a < 2; a++) {
            int b;
            for (b = 0; b < 2; b++) {
                for (int j = 0; j < 5; j++)
                    placeBlock(world,(j == 4) ? Blocks.obsidian : BlockList.obsidian_special, (j == 3) ? 1 : 0, x - 4 + 8 * a, y + j, z - 4 + 8 * b);
            }
            for (b = 0; b < 7; b++) {
                for (int j = 0; j < 5; j++) {
                    placeBlock(world,(j == 4) ? Blocks.obsidian : BlockList.obsidian_special, (j == 3) ? 1 : (((b == 2 || b == 4) && j < 3) ? 2 : 0), x - 3 + b, y + j, z - 5 + 10 * a);
                    placeBlock(world,(j == 4) ? Blocks.obsidian : BlockList.obsidian_special, (j == 3) ? 1 : (((b == 2 || b == 4) && j < 3) ? 2 : 0), x - 5 + 10 * a, y + j, z - 3 + b);
                }
            }
            for (b = 0; b < 7; b++) {
                int n = Math.min((b == 0 || b == 6) ? 5 : 4, (int)Math.floor(Math.abs(((b == 0 || b == 6) ? 1.8D : 1.0D) * rand.nextGaussian() + rand.nextGaussian() * 1.2D + rand.nextFloat() * 0.3D)));
                int j;
                for (j = 0; j < n; j++)
                    placeBlock(world,Blocks.bookshelf, 0, x - 4 + 8 * a, y + j, z - 3 + b);
                n = Math.min((b == 0 || b == 6) ? 5 : 4, (int)Math.floor(Math.abs(((b == 0 || b == 6) ? 1.8D : 1.0D) * rand.nextGaussian() + rand.nextGaussian() * 1.2D + rand.nextFloat() * 0.3D)));
                for (j = 0; j < n; j++)
                    placeBlock(world,Blocks.bookshelf, 0, x - 3 + b, y + j, z - 4 + 8 * a);
            }
        }
        int add = rand.nextBoolean() ? -5 : 5;
        (new byte[2])[0] = 0;
        (new byte[2])[1] = 1;
        (new byte[2])[0] = 1;
        (new byte[2])[1] = 0;
        byte[] mp = rand.nextBoolean() ? new byte[2] : new byte[2];
        int yy;
        for (yy = 0; yy < 3; yy++) {
            int j;
            for (j = -1; j <= 1; j++)
                placeBlock(world,null, 0, x + mp[0] * add + j * mp[1], y + yy, z + mp[1] * add + j * mp[0]);
            for (j = 0; j < 2; j++)
                placeBlock(world,BlockList.obsidian_special, (yy == 1) ? 1 : 2, x + mp[0] * add + ((j == 0) ? -2 : 2) * mp[1], y + yy, z + mp[1] * add + ((j == 0) ? -2 : 2) * mp[0]);
        }
        add = (add == -5) ? -4 : 4;
        for (yy = 0; yy < 5; yy++) {
            for (int j = -1; j <= 1; j++)
                placeBlock(world,null, 0, x + mp[0] * add + j * mp[1], y + yy, z + mp[1] * add + j * mp[0]);
        }
        for (int i = 0; i < 2; i++) {
            int b;
            for (b = 0; b < 3; b++) {
                placeBlock(world,BlockList.obsidian_stairs, 4 + ((i == 0) ? 1 : 0), x - 4 + 8 * i, y + 4, z - 1 + b);
                placeBlock(world,BlockList.obsidian_stairs, 4 + ((i == 0) ? 3 : 2), x - 1 + b, y + 4, z - 4 + 8 * i);
            }
            for (b = 0; b < 2; b++) {
                int metasame = 4 + ((i == 0) ? 1 : 0);
                int metadiff = 4 + ((b == 0) ? 3 : 2);
                placeBlock(world,BlockList.obsidian_stairs, metasame, x - 4 + 8 * i, y + 4, z - 2 + 4 * b);
                placeBlock(world,BlockList.obsidian_stairs, metasame, x - 2 + 4 * i, y + 4, z - 4 + 8 * b);
                placeBlock(world,BlockList.obsidian_stairs, metasame, x - 3 + 6 * i, y + 4, z - 3 + 6 * b);
                placeBlock(world,BlockList.obsidian_stairs, metadiff, x - 2 + 4 * i, y + 4, z - 3 + 6 * b);
                placeBlock(world,BlockList.obsidian_stairs, metadiff, x - 3 + 6 * i, y + 4, z - 2 + 4 * b);
                placeBlock(world,BlockList.obsidian_stairs, 0, x - 4 + 8 * i, y + 4, z - 3 + 6 * b);
                placeBlock(world,BlockList.obsidian_stairs, 0, x - 3 + 6 * i, y + 4, z - 4 + 8 * b);
            }
        }
        placeBlock(world, BlockList.obsidian_special_glow, 1, x, y, z);
        EntityItem item = new EntityItem(world, x, y + 2, z, new ItemStack(EnderDeamonIsolator.endermenRelicSpawner));
        item.lifespan = Integer.MAX_VALUE;
        world.spawnEntityInWorld(item);
       // getWorld().spawnEntityInWorld((Entity)new EntityItemEndermanRelic(getWorld(), x + 0.5D, (y + 1), z + 0.5D, 0));
        return true;
    }

}
