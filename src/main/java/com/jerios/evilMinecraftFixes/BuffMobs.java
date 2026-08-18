package com.jerios.evilMinecraftFixes;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class BuffMobs {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onJoinNether(EntityJoinWorldEvent event) {
        if (!event.entity.worldObj.isRemote) {
            if (event.entity instanceof EntityLiving) {
                EntityLiving living = (EntityLiving) event.entity;
                if (living.worldObj.provider != null && living.worldObj.provider.dimensionId == -1) {
                    float currHp = living.getMaxHealth();
                    float finalc = currHp + 10;
                    living.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(finalc);
                    living.heal(20);
                }

            }
        }
    }


}
