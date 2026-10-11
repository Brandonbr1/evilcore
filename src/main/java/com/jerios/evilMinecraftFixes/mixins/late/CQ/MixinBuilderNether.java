package com.jerios.evilMinecraftFixes.mixins.late.CQ;

import com.chocolate.chocolateQuest.API.BuilderBase;
import com.chocolate.chocolateQuest.API.BuilderBlockData;
import com.chocolate.chocolateQuest.ChocolateQuest;
import com.chocolate.chocolateQuest.builder.BuilderHelper;
import com.chocolate.chocolateQuest.builder.BuilderNether;
import com.chocolate.chocolateQuest.builder.Perlin3D;
import com.chocolate.chocolateQuest.builder.schematic.Schematic;
import com.chocolate.chocolateQuest.entity.EntitySchematicBuilder;
import com.jerios.evilMinecraftFixes.Vortex;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(BuilderNether.class)
public class MixinBuilderNether extends BuilderBase {

    @Shadow
    private String folder;
    @Shadow
  int rows = 4;
  @Shadow  int posY = 31;
    @Shadow  BuilderBlockData floorBlock;
    @Shadow   BuilderBlockData lavaBlock;
    @Shadow  boolean replaceBanners;

    @Shadow
    public void generate(Random random, World world, int i, int i1, int i2) {

    }


    /**
     * @author
     * @reason
     */
    @Overwrite(remap = false)
    public void generate(Random random, World world, int i, int j, int k, int mob) {
        Vortex.ensureBlockExists(world,i, j , k);
        Perlin3D p = new Perlin3D(world.getSeed(), 8, random);
        Perlin3D p2 = new Perlin3D(world.getSeed(), 32, random);
        int rowSize = 21;
        int size = this.rows * rowSize;
        int height = 32;
        int wallSize = 4;

        for(int x = 0; x < size; ++x) {
            for(int y = 0; y < height; ++y) {
                for(int z = 0; z < size; ++z) {
                    float noiseVar = Math.max(0.0F, 2.0F - (float)(height - y) / 4.0F);
                    noiseVar += Math.max(0.0F, (float)wallSize - (float)x / 2.0F);
                    noiseVar += Math.max(0.0F, (float)wallSize - (float)(size - x) / 2.0F);
                    noiseVar += Math.max(0.0F, (float)wallSize - (float)z / 2.0F);
                    noiseVar += Math.max(0.0F, (float)wallSize - (float)(size - z) / 2.0F);
                    if (p.getNoiseAt(x + i, y + j, z + k) * p2.getNoiseAt(x + i, y + j, z + k) * (double)noiseVar < (double)0.5F) {
                        Vortex.ensureBlockExists(world,i + x, j + y, k + z);
                        world.setBlockToAir(i + x, j + y, k + z);
                    }
                }
            }
        }

        BuilderHelper b = BuilderHelper.builderHelper;
        Schematic s = new Schematic(size, 1, size);

        for(int x = 0; x < s.width; ++x) {
            for(int z = 0; z < s.length; ++z) {
                if (x % 21 > 2 && x % 21 < 18 && z % 21 > 2 && z % 21 < 18 || x % 21 > 8 && x % 21 < 12 || z % 21 > 8 && z % 21 < 12) {
                    Vortex.ensureBlockExists(world,x,0,z);
                    this.floorBlock.placeBlock(s, x, 0, z, random);
                } else {
                    Vortex.ensureBlockExists(world,x,0,z);
                    this.lavaBlock.placeBlock(s, x, 0, z, random);
                }
            }
        }

        if (ChocolateQuest.config.useInstantDungeonBuilder) {
            b.putSchematicInWorld(random, world, s, i, j, k, mob, this.replaceBanners);

            for(int x = 0; x < s.width; x += 21) {
                for(int z = 0; z < s.length; z += 21) {
                    b.putSchematicInWorld(random, world, BuilderHelper.getRandomNBTMap(this.folder, random, this.getDungeonName()), x + 3 + i, j + 1, z + 3 + k, mob, this.replaceBanners);
                }
            }
        } else {
            EntitySchematicBuilder builder = new EntitySchematicBuilder(world);
            builder.setMobID(mob, this.replaceBanners);
            builder.setPosition((double)i, (double)j, (double)k);
            builder.addBuildingPlans(s, i, j, k);

            for(int x = 0; x < s.width; x += 21) {
                for(int z = 0; z < s.length; z += 21) {
                    builder.addBuildingPlans(BuilderHelper.getRandomNBTMap(this.folder, random, this.getDungeonName()), x + 3 + i, j + 1, z + 3 + k);
                }
            }

            world.spawnEntityInWorld(builder);
        }

    }

    @Shadow
    public String getName() {
        return "";
    }


}
