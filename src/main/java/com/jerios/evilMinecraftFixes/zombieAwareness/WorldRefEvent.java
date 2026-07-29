package com.jerios.evilMinecraftFixes.zombieAwareness;

import ZombieAwareness.ZombieAwareness;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;

public class WorldRefEvent {

    @SubscribeEvent
    public void unstaticWorldReferences(WorldEvent.Unload worldEvent) {
        if (Loader.isModLoaded("ZAMod")) {
            ZombieAwareness.worldRef = null; // clear ZombieAwareness world reference
            ZombieAwareness.mc = null; // clear ZombieAwareness server reference
            ZombieAwareness.player = null; // clear player reference
        }
    }

}
