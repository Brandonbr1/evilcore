package com.jerios.evilMinecraftFixes.evilOres;

import com.jerios.evilMinecraftFixes.evilOres.mob.EntityGlowstone;
import com.jerios.evilMinecraftFixes.evilOres.mob.EntityNetheriteOre;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.elias.fakeores.common.EntityCoalOre;
import fr.elias.fakeores.common.EntityDiamondOre;
import fr.elias.fakeores.common.EntityEmeraldOre;
import fr.elias.fakeores.common.EntityGoldOre;
import fr.elias.fakeores.common.EntityIronOre;
import fr.elias.fakeores.common.EntityNetherQuartzOre;
import fr.elias.fakeores.common.EntityRedstoneOre;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

public class OresAttackEvent {

    @SubscribeEvent
    public void attackMob(LivingAttackEvent event) {
        EntityLivingBase hurt = event.entityLiving;
        Entity attacker = event.source.getEntity();

        if (hurt instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) hurt;
            if (attacker instanceof EntityLiving) {
                EntityLiving ore = (EntityLiving) attacker;

                if (ore instanceof EntityCoalOre) {
                    player.setFire(4 + hurt.worldObj.difficultySetting.getDifficultyId());
                }

                if (ore instanceof EntityDiamondOre) {
                    player.addPotionEffect(new PotionEffect(Potion.weakness.id, 900, 1));
                    ore.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 9999, 1));
                }

                if (ore instanceof EntityEmeraldOre) {
                    player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 900, 3));
                }

                if (ore instanceof EntityRedstoneOre) {
                    player.setFire(2 + hurt.worldObj.difficultySetting.getDifficultyId());
                }

                if (ore instanceof EntityIronOre) {
                    player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 999, 1));
                }

                if (ore instanceof EntityNetherQuartzOre) {
                    player.setFire(6 + hurt.worldObj.difficultySetting.getDifficultyId());
                }

                if (ore instanceof EntityNetheriteOre) {
                    player.setFire(9 + hurt.worldObj.difficultySetting.getDifficultyId());
                    player.addPotionEffect(new PotionEffect(Potion.weakness.id, 900, 1));
                    player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 900, 2));
                }

                if (ore instanceof EntityGlowstone) {
                    player.setFire(7 + hurt.worldObj.difficultySetting.getDifficultyId());
                }

                if (ore instanceof EntityGoldOre) {
                    ore.addPotionEffect(new PotionEffect(Potion.regeneration.id, 600, 1));
                    ore.addPotionEffect(new PotionEffect(Potion.resistance.id, 6000, 0));
                    ore.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 6000, 0));
                }

            }




        }


    }


}
