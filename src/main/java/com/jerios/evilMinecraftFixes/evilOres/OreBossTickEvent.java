package com.jerios.evilMinecraftFixes.evilOres;

import com.jerios.evilMinecraftFixes.evilOres.mob.EntityNetheriteOre;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.elias.fakeores.common.EntityGoldOre;
import fr.elias.fakeores.common.EntityOresBoss;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

import java.util.Random;

public class OreBossTickEvent {

    @SubscribeEvent
    public void mobTickEvent(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase entity = event.entityLiving;

        if (entity instanceof EntityGoldOre) {
            if (entity.ticksExisted % 40 == 0) {
                entity.heal(1);
            }
            entity.addPotionEffect(new PotionEffect(Potion.resistance.id, 6000, 0));
            entity.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 6000, 0));
        }

    }
}
