package com.jerios.evilMinecraftFixes.mixins.late.adInfernos;

import com.superdextor.dextersnether.NetherAchievements;
import com.superdextor.dextersnether.entity.NetherMob;
import com.superdextor.dextersnether.entity.monster.EntityObsidianSheepman;
import com.superdextor.dextersnether.event.NetherEvents;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(NetherEvents.class)
public class MixinNetherEvents {


    /**
     * @author Jerios
     * @reason Fix broken achievement granting code
     */
    @Overwrite(remap = false)
    @SubscribeEvent
    public void playerKillEntity(LivingDeathEvent event) {
        if (!event.entityLiving.worldObj.isRemote && event.source.getEntity() instanceof EntityLivingBase) {
            EntityLivingBase entityMob = event.entityLiving;
            if(event.source.getEntity() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) event.source.getEntity();
                if (entityMob instanceof NetherMob || entityMob instanceof EntityPigZombie || entityMob instanceof EntityBlaze || entityMob instanceof EntityGhast || entityMob instanceof EntityMagmaCube) {
                    player.triggerAchievement(NetherAchievements.demonSlayer);
                }

                if (entityMob instanceof EntityObsidianSheepman) {
                    player.triggerAchievement(NetherAchievements.obsidiancow);
                }
            }
        }

    }
}
