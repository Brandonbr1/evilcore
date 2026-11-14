package com.jerios.evilMinecraftFixes.mixins.early.hard;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureStrongholdPieces;
import net.minecraftforge.common.DungeonHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(StructureStrongholdPieces.ChestCorridor.class)
public class MixinStructureStrongholdPiecesChestCorridor extends StructureStrongholdPieces.Stronghold{

    @WrapOperation(method = "addComponentParts", at= @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/structure/StructureStrongholdPieces$ChestCorridor;placeBlockAtCurrentPosition(Lnet/minecraft/world/World;Lnet/minecraft/block/Block;IIIILnet/minecraft/world/gen/structure/StructureBoundingBox;)V"))
    private void evil$addSpawners(StructureStrongholdPieces.ChestCorridor instance, World world, Block block, int i, int i2, int i3, int i4, StructureBoundingBox structureBoundingBox, Operation<Void> original) {
        original.call(instance, world,block,i,i2,i3,i4,structureBoundingBox);
        evil$placeBlockAtCurrentPosition(world, i,i2,i3,i4,structureBoundingBox);
    }


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

    @Shadow
    public boolean addComponentParts(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
        return false;
    }


    @Unique
    private String pickMobSpawner(Random p_76543_1_)
    {
        return DungeonHooks.getRandomDungeonMob(p_76543_1_);
    }
}
