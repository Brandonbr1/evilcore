package com.jerios.evilMinecraftFixes.jean;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class JeanHarder {

    @SubscribeEvent
    public void jeanLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving instanceof EntityDragon) {
            EntityDragon dragon = (EntityDragon) event.entityLiving;
            if (!dragon.worldObj.isRemote) {
                dragon.slowed = false;
            }
        }

    }

    @SubscribeEvent
    public void jeanDamageCap(LivingHurtEvent event) {
        if (event.entityLiving instanceof EntityDragon) {
            EntityDragon dragon = (EntityDragon) event.entityLiving;
            if (event.ammount > 30) {
                event.ammount = 30;
            }
        }

    }


}
