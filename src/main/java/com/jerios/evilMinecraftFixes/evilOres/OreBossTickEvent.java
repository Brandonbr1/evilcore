package com.jerios.evilMinecraftFixes.evilOres;

import com.jerios.evilMinecraftFixes.evilOres.mob.EntityNetheriteOre;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.elias.fakeores.common.EntityOresBoss;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

import java.util.Random;

public class OreBossTickEvent {

    @SubscribeEvent
    public void mobTickEvent(LivingHurtEvent event) {
      //  if (!event.entity.worldObj.isRemote) {

            if (event.entity instanceof EntityOresBoss) {
                EntityOresBoss oresBoss = (EntityOresBoss) event.entityLiving;
                Entity entity = event.source.getEntity();

                EntityPlayer globalPlayer = null;
                if (entity instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) entity;
                    globalPlayer = player;
                }


                Random rand = oresBoss.worldObj.rand;
                if (oresBoss.phase == 2) {

                    if (rand.nextInt(240) == 0) {
                        EntityNetheriteOre ore = new EntityNetheriteOre(oresBoss.worldObj);
                        ore.setPosition(oresBoss.posX + rand.nextInt(5), oresBoss.posY + 3.0D, oresBoss.posZ + rand.nextInt(5));
                        oresBoss.worldObj.spawnEntityInWorld(ore);
                    }
                }

                // phase 4


                // phase 4 end

            }


     //   }
    }
}
