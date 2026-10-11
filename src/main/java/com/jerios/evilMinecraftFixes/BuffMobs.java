package com.jerios.evilMinecraftFixes;

import atomicstryker.infernalmobs.common.InfernalMobsCore;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class BuffMobs {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onJoinNether(EntityJoinWorldEvent event) {
        if (!event.entity.worldObj.isRemote) {
            if (event.entity instanceof EntityLiving) {
                EntityLiving living = (EntityLiving) event.entity;
                if (living instanceof IMob) {


                if (living.worldObj.provider != null && living.worldObj.provider.dimensionId == -1) {

                    NBTTagCompound tag = living.getEntityData();

                    if (!tag.hasKey("Evil_Nether_Buffed")) {
                        tag.setBoolean("Evil_Nether_Buffed", true);
                        InfernalMobsCore.instance().processEntitySpawn(living);
                        if(living.worldObj.rand.nextInt(9) == 0) {
                            float currHp = living.getMaxHealth();
                            float finalc = currHp + (currHp * 0.3f) ;
                            living.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(finalc);
                            living.heal(finalc);
                        }


                    }


                }

            }
            }
        }
    }


}
