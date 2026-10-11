package com.jerios.evilMinecraftFixes;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Collection;

public class HungerChangesEvent {


    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        if (event.phase == TickEvent.Phase.END && !player.worldObj.isRemote) {
            player.getFoodStats().addExhaustion(0.01f);

            Collection<PotionEffect> p = player.getActivePotionEffects();

            ArrayList<PotionEffect> copiedPotionList = new ArrayList<>(p);

            for (int i = 0; i < copiedPotionList.size(); i++) {
                PotionEffect potionEffect = copiedPotionList.get(i);

                Potion potion = Potion.potionTypes[potionEffect.getPotionID()];

                if (potion.isBadEffect()) {
                    player.getFoodStats().addExhaustion(0.05f);
                }

                if (player.isPotionActive(Potion.hunger)) {
                    player.getFoodStats().addExhaustion(0.05f);
                }


                //    if (player.isPotionActive(Potion))

            }
        }

    }
}
