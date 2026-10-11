package com.jerios.evilMinecraftFixes.mixins.late.CQ;

import com.chocolate.chocolateQuest.API.BuilderBlockData;
import com.chocolate.chocolateQuest.API.HelperReadConfig;
import com.chocolate.chocolateQuest.ChocolateQuest;
import com.chocolate.chocolateQuest.builder.Perlin3D;
import com.chocolate.chocolateQuest.builder.schematic.Schematic;
import com.chocolate.chocolateQuest.builder.support.PlateauBuilder;
import com.jerios.evilMinecraftFixes.Vortex;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Properties;
import java.util.Random;

@Mixin(PlateauBuilder.class)
public class MixinPlateauBuilder {


    @Shadow  BuilderBlockData structureBlock;
  @Shadow  BuilderBlockData structureTopBlock;

  @Shadow  public int wallSize;


  /**
   * @author
   * @reason
   */
  @Overwrite(remap = false)
    public void generate(Random random, World world, int i, int j, int k, int sizeX, int sizeZ) {
        Perlin3D p = new Perlin3D(world.getSeed(), 8, random);
        Perlin3D p2 = new Perlin3D(world.getSeed(), 32, random);
        int wallSize = 8;
        sizeX += wallSize * 2;
        sizeZ += wallSize * 2;
        int height = 32;
        i -= wallSize;
        k -= wallSize;

        for(int x = 0; x < sizeX; ++x) {
            for(int z = 0; z < sizeZ; ++z) {
                Vortex.loadChunk(world, x + i,z + k);
                int maxHeight = j - 1 - world.getTopSolidOrLiquidBlock(x + i, z + k);
                int posY = world.getTopSolidOrLiquidBlock(x + i, z + k);

                for(int y = 0; y <= maxHeight; ++y) {
                    if (x > wallSize && z > wallSize && x < sizeX - wallSize && z < sizeZ - wallSize) {
                        Vortex.ensureBlockExists(world, i + x , posY + y, k + z);
                        this.structureBlock.placeBlock(world, i + x, posY + y, k + z, random);
                    } else {
                        float noiseVar = (float)(y - maxHeight) / ((float)Math.max(1, maxHeight) * 1.5F);
                        noiseVar += Math.max(0.0F, (float)(wallSize - x) / 8.0F);
                        noiseVar += Math.max(0.0F, (float)(wallSize - (sizeX - x)) / 8.0F);
                        noiseVar += Math.max(0.0F, (float)(wallSize - z) / 8.0F);
                        noiseVar += Math.max(0.0F, (float)(wallSize - (sizeZ - z)) / 8.0F);
                        double value = (p.getNoiseAt(x + i, y, z + k) + p2.getNoiseAt(x + i, y, z + k) + (double)noiseVar) / (double)3.0F;
                        if (value < (double)0.5F) {
                            Vortex.ensureBlockExists(world, i + x, posY + y, k + z);
                            this.structureBlock.placeBlock(world, i + x, posY + y, k + z, random);
                        }
                    }
                }

                Vortex.loadChunk(world, x+i , z+k);
                maxHeight = world.getTopSolidOrLiquidBlock(x + i, z + k);
                if (maxHeight <= j) {
                    Vortex.ensureBlockExists(world, i + x , maxHeight - 1, k + z);
                    this.structureTopBlock.placeBlock(world, i + x, maxHeight - 1, k + z, random);
                }
            }
        }

    }


    /**
     * @author
     * @reason
     */
    @Overwrite(remap = false)
    public Schematic getSchematic(Random random, World world, int i, int j, int k, int sizeX, int sizeZ) {
        int wallSize = 8;
        sizeX += wallSize * 2;
        sizeZ += wallSize * 2;
        i -= wallSize;
        k -= wallSize;
        int schematicHeight = 0;

        for(int x = 0; x < sizeX; ++x) {
            for(int z = 0; z < sizeZ; ++z) {
                Vortex.loadChunk(world, x+i , z+k);
                int maxHeight = j - world.getTopSolidOrLiquidBlock(x + i, z + k);
                if (maxHeight > schematicHeight) {
                    schematicHeight = maxHeight;
                }
            }
        }

        schematicHeight = Math.max(1, schematicHeight);
        Schematic schematic = new Schematic(sizeX, schematicHeight, sizeZ, ChocolateQuest.emptyBlock);
        Perlin3D p = new Perlin3D(world.getSeed(), 8, random);
        Perlin3D p2 = new Perlin3D(world.getSeed(), 32, random);

        for(int x = 0; x < sizeX; ++x) {
            for(int z = 0; z < sizeZ; ++z) {
                Vortex.loadChunk(world, x+i , z+k);
                int maxHeight = j - world.getTopSolidOrLiquidBlock(x + i, z + k);
                int maxY = 0;

                for(int y = schematicHeight - maxHeight; y < schematicHeight; ++y) {
                    if (x > wallSize && z > wallSize && x < sizeX - wallSize && z < sizeZ - wallSize) {
                        Vortex.ensureBlockExists(world, x,y,z);
                        this.structureBlock.placeBlock(schematic, x, y, z, random);
                        maxY = y;
                    } else {
                        float noiseVar = (float)(y - maxHeight) / ((float)Math.max(1, maxHeight) * 1.5F);
                        noiseVar += Math.max(0.0F, (float)(wallSize - x) / (float)wallSize);
                        noiseVar += Math.max(0.0F, (float)(wallSize - (sizeX - x)) / (float)wallSize);
                        noiseVar += Math.max(0.0F, (float)(wallSize - z) / (float)wallSize);
                        noiseVar += Math.max(0.0F, (float)(wallSize - (sizeZ - z)) / (float)wallSize);
                        double value = (p.getNoiseAt(x + i, y, z + k) + p2.getNoiseAt(x + i, y, z + k) + (double)noiseVar) / (double)3.0F + (double)((float)y / (float)schematicHeight) * (double)0.25F;
                        if (value < (double)0.5F) {
                            Vortex.ensureBlockExists(world, x,y,z);
                            this.structureBlock.placeBlock(schematic, x, y, z, random);
                            maxY = y;
                        }
                    }
                }

                if (maxHeight <= j) {
                    Vortex.ensureBlockExists(world, x,maxY,z);
                    this.structureTopBlock.placeBlock(schematic, x, maxY, z, random);
                }
            }
        }

        schematic.setPosition(i, j - schematicHeight, k);
        return schematic;
    }

}
