package com.jerios.evilMinecraftFixes.race;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;
import java.util.List;

public class RaceList {
    public static final List<BaseRace> RACE_LIST = new ArrayList<>();


    public static void addRace(BaseRace race) {
        MinecraftForge.EVENT_BUS.register(race);
        FMLCommonHandler.instance().bus().register(race);
        RACE_LIST.add(race);
    }

}
