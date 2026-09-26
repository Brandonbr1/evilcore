package com.jerios.evilMinecraftFixes.savedata;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;

public class EvilData extends WorldSavedData {

   public int athenaDeath = 0;

    public EvilData() {
        super("evil_mc_world_data");
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        nbt.getInteger("AthenaDeathCount");

    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("AthenaDeathCount", athenaDeath);
    }

    public void increaseAthenaDeath() {
        athenaDeath++;
        markDirty();
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
        // The IS_GLOBAL constant is there for clarity, and should be simplified into the right branch.
        MapStorage storage = world.mapStorage;
        EvilData instance = (EvilData) storage.loadData(EvilData.class, "evil_mc_world_data");

        if (instance == null) {
            instance = new EvilData();
            storage.setData("evil_mc_world_data", instance);
        }
        return instance;
    }

}
