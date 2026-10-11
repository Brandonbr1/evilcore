package com.jerios.evilMinecraftFixes.mixins.late.CQ;

import com.chocolate.chocolateQuest.builder.schematic.Schematic;
import com.jerios.evilMinecraftFixes.Vortex;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(value = com.chocolate.chocolateQuest.builder.BuilderHelper.class, remap = false)
public class MixinBuildHelper {


    @Inject(method = "putSchematicInWorld", at=@At("HEAD"))
    private void evil$manuallyLoadChunk3(Random random, World world, Schematic schematic, int x, int y, int z, int idMob, boolean replaceFlags, CallbackInfo ci) {
        Vortex.ensureBlockExists(world,x,y,z);
    }


    @Inject(method = "putBlockWithLoot", at=@At("HEAD"))
    private void evil$manLoadChunk2(Random random, World world, int x, int y, int z, Block block, int metadata, int mobType, CallbackInfo ci) {
        Vortex.ensureBlockExists(world,x,y,z);
    }

    @Inject(method = "putBlock", at=@At("HEAD"))
    private void evil$manuallyLoadChunk(World world, int x, int y, int z, Block block, int metadata, CallbackInfo ci) {
        Vortex.ensureBlockExists(world,x,y,z);

    }

    @Inject(method = "putTileEntityWithLoot", at=@At("HEAD"), cancellable = true)
    private void evil$manuallyLoadChunk2(World world, TileEntity t, int idMob, boolean replaceFlags, Random random, CallbackInfo ci)
    {
        if (t == null) ci.cancel();
        Vortex.ensureBlockExists(world,t.xCoord,t.yCoord,t.zCoord);

    }

    @Inject(method = "addTreasure", at=@At("HEAD"))
    private static void evil$treasureSafetyCheck(Random random, World world, int x, int y, int z, int amount, CallbackInfoReturnable<Boolean> cir) {
        Vortex.ensureBlockExists(world,x,y,z);
    }

    @Inject(method = "addFoodChest",at=@At("HEAD"))
    private static void evil$FC(Random random, World world, int x, int y, int z, int amount, CallbackInfoReturnable<Boolean> cir) {
        Vortex.ensureBlockExists(world,x,y,z);
    }

    @Inject(method = "addMineralChest",at=@At("HEAD"))
    private static void evil$MC(Random random, World world, int x, int y, int z, int amount, CallbackInfoReturnable<Boolean> cir) {
        Vortex.ensureBlockExists(world,x,y,z);
    }


    @Inject(method = "addFoodChest",at=@At("HEAD"))
    private static void evil$WC(Random random, World world, int x, int y, int z, int amount, CallbackInfoReturnable<Boolean> cir) {
        Vortex.ensureBlockExists(world,x,y,z);
    }

    @Inject(method = "addChest",at=@At("HEAD"))
    private static void evil$chest(Random random, World world, int x, int y, int z, int amount, CallbackInfoReturnable<Boolean> cir) {
        Vortex.ensureBlockExists(world,x,y,z);
    }


    /**
     * @author Jerios
     * @reason better safety
     */
    @Overwrite(remap = false)
    public static void addSpawner(Random random, World world, int x, int y, int z, int idMob) {
        Vortex.ensureBlockExists(world,x,y,z);

        world.setBlock(x, y, z, Blocks.mob_spawner, 0, 0);
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityMobSpawner) {
            TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner) world.getTileEntity(x,y,z);
            if(tileentitymobspawner != null) {
                setMobForSpawner(tileentitymobspawner, idMob, x, y, z, random);
            }
        }


    }


    @Shadow(remap = false)
    public static void setMobForSpawner(TileEntityMobSpawner spawner, int idMob, int x, int y, int z, Random random) {

    }

}
