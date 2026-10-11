package com.jerios.evilMinecraftFixes.infernalMobs;

import atomicstryker.infernalmobs.common.InfernalMobsCore;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import thehippomaster.MutantCreatures.CreeperMinion;

public class CreeperMinionNiceUnableToBecomeRare {


    @SubscribeEvent
    public void mobTickEvent(LivingEvent.LivingUpdateEvent event) {


        if (event.entityLiving instanceof CreeperMinion) {
            CreeperMinion minion = (CreeperMinion) event.entityLiving;

            // prevent Bomby from having rare effects.
            if (minion.getTamed()) {
                if(InfernalMobsCore.getMobModifiers(event.entityLiving) != null) {
                    InfernalMobsCore.removeEntFromElites(event.entityLiving);
                }
            }
        }
    }
}
