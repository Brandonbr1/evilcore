package com.jerios.evilMinecraftFixes.infernalMobs;

import atomicstryker.infernalmobs.common.InfernalMobsCore;
import atomicstryker.infernalmobs.common.modifiers.MobModifier;
import com.jerios.evilMinecraftFixes.mixins.Config;
import com.jerios.evilMinecraftFixes.mixins.early.IEntityPigmenAccessor;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;

public class InfernalMobsMakeNeturalMobsAgressiveEvent {

    @SubscribeEvent
    public void mobTickEvent(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving.worldObj.isRemote) return;
        if (Config.pigmenAgressiveIfModifier) {
            if (event.entityLiving instanceof EntityPigZombie) {
                MobModifier mod = InfernalMobsCore.getMobModifiers(event.entityLiving);
                EntityPigZombie zombie = (EntityPigZombie) event.entityLiving;
                if (mod != null) {
                    ((IEntityPigmenAccessor)zombie).evil$setAnger(900);
                }
            }
        }


        if (Config.spiderAgressiveIfModifier) {
            if (event.entityLiving instanceof EntitySpider) {
                EntitySpider spider = (EntitySpider) event.entityLiving;
                MobModifier mod = InfernalMobsCore.getMobModifiers(event.entityLiving);

                if (mod != null) {
                    double d0 = 32.0D;
                    EntityPlayer playerToAttack = spider.worldObj.getClosestVulnerablePlayerToEntity(spider, d0);
                    spider.setAttackTarget(playerToAttack);
                }
            }
        }

        if (Config.endermenAgressiveIfModifier) {
            if (event.entityLiving instanceof EntityEnderman) {
                EntityEnderman enderman = (EntityEnderman) event.entityLiving;
                MobModifier mod = InfernalMobsCore.getMobModifiers(event.entityLiving);

                if (mod != null) {
                    double d0 = 32.0D;
                    EntityPlayer playerToAttack = enderman.worldObj.getClosestVulnerablePlayerToEntity(enderman, d0);
                    enderman.setAttackTarget(playerToAttack);
                }
            }
        }

    }
}
