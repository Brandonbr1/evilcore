package com.jerios.evilMinecraftFixes.savedata;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;

public class EvilData extends WorldSavedData {

    private static String NAME = "evil_mc_world_data";

   public int athenaDeath = 0;
   public int worldTicks = 0;

    // Required constructors
    public EvilData() {
        super(NAME);
    }
    public EvilData(String s) {
        super(s);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        athenaDeath =   nbt.getInteger("AthenaDeathCount");
        worldTicks=nbt.getInteger("WorldTimeTick");

    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("AthenaDeathCount", athenaDeath);
        nbt.setInteger("WorldTimeTick", worldTicks);
    }

    public void increaseAthenaDeath() {
        athenaDeath++;
        markDirty();
    }

    public void increaseTicks() {
        if (worldTicks <= 144000) {
            worldTicks++;
            markDirty();
        }
    }

    public boolean isAgressiveStage1() {
        return athenaDeath >= 3;
    }

    public boolean isAgressiveStage2() {
        return athenaDeath >= 5;
    }

    public boolean isAgressiveStage3() {
        return athenaDeath >= 8;
    }

    public static EvilData get(World world) {
        if (world.isRemote) {
            throw new RuntimeException("DO NOT RUN ON CLIENT DUMBASS");
        }
        MapStorage storage = world.mapStorage;
        EvilData instance = (EvilData) storage.loadData(EvilData.class, NAME);

        if (instance == null) {
            instance = new EvilData();
            storage.setData(NAME, instance);
        }
        return instance;
    }

}
