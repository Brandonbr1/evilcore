package com.jerios.evilMinecraftFixes.infernalMobs;

import atomicstryker.infernalmobs.client.InfernalMobsClient;
import atomicstryker.infernalmobs.common.InfernalMobsCore;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.event.world.WorldEvent;


public class InfernalMobsSaveHandler {

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event)
    {
      InfernalMobsCore.proxy.getRareMobs().entrySet().removeIf(entry -> entry.getKey().worldObj == event.world);
    }




}
