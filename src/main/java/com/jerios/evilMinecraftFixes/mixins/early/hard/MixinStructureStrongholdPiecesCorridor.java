package com.jerios.evilMinecraftFixes.mixins.early.hard;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureStrongholdPieces;
import net.minecraftforge.common.DungeonHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.Random;

@Mixin(StructureStrongholdPieces.Corridor.class)
public class MixinStructureStrongholdPiecesCorridor extends StructureStrongholdPieces.Stronghold {


    protected void evil$placeBlockAtCurrentPosition(World p_151550_1_, int p_151550_3_, int p_151550_4_, int p_151550_5_, int p_151550_6_, StructureBoundingBox p_151550_7_)
    {
        int i1 = this.getXWithOffset(p_151550_4_, p_151550_6_);
        int j1 = this.getYWithOffset(p_151550_5_);
        int k1 = this.getZWithOffset(p_151550_4_, p_151550_6_);

        if (p_151550_7_.isVecInside(i1, j1, k1))
        {
            p_151550_1_.setBlock(i1, j1, k1, Blocks.mob_spawner, p_151550_3_, 2);
            TileEntityMobSpawner tms = (TileEntityMobSpawner) p_151550_1_.getTileEntity(i1,j1,k1);
            if (tms != null) {
                tms.func_145881_a().setEntityName(pickMobSpawner(new Random()));

            }
        }
    }


   @Shadow private int field_74993_a;
    /**
     * @author Jerios
     * @reason
     */
    @Overwrite
    public boolean addComponentParts(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
    {
        if (this.isLiquidInStructureBoundingBox(p_74875_1_, p_74875_3_))
        {
            return false;
        }
        else
        {
            for (int i = 0; i < this.field_74993_a; ++i)
            {
                this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 0, 0, i, p_74875_3_);
                this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 1, 0, i, p_74875_3_);
                this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 2, 0, i, p_74875_3_);
                this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 3, 0, i, p_74875_3_);
                this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 4, 0, i, p_74875_3_);
                if (i == 3) {
                    evil$placeBlockAtCurrentPosition(p_74875_1_, 0, 4, 0, i, p_74875_3_);
                }

                for (int j = 1; j <= 3; ++j)
                {
                    this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 0, j, i, p_74875_3_);
                    this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 1, j, i, p_74875_3_);
                    this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 2, j, i, p_74875_3_);
                    this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 3, j, i, p_74875_3_);
                    this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 4, j, i, p_74875_3_);
                }

                this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 0, 4, i, p_74875_3_);
                this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 1, 4, i, p_74875_3_);
                this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 2, 4, i, p_74875_3_);
                this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 3, 4, i, p_74875_3_);
                this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.stonebrick, 0, 4, 4, i, p_74875_3_);
            }

            return true;
        }
    }

    @Unique
    private String pickMobSpawner(Random p_76543_1_)
    {
        return DungeonHooks.getRandomDungeonMob(p_76543_1_);
    }

}
