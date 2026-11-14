package com.jerios.evilMinecraftFixes.race.impl;

import com.chocolate.chocolateQuest.ChocolateQuest;
import com.jerios.evilMinecraftFixes.race.BaseRace;
import com.jerios.evilMinecraftFixes.race.RaceProps;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.brewing.PotionBrewEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.world.BlockEvent;

import java.util.List;
import java.util.Objects;

public class WalkerRace extends BaseRace {
    @Override
    public List<String> getDesc() {
        descList.add("Placeholder");
        return descList;
    }

    @Override
    public void onStartEat(PlayerUseItemEvent.Start useItemEvent) {

    }

    @Override
    public void onTickEat(PlayerUseItemEvent.Tick tick) {

    }

    @Override
    public void onFinishEat(PlayerUseItemEvent.Finish finish) {

    }

    @Override
    public void onHarvestDrop(BlockEvent.HarvestDropsEvent harvest) {

    }

    @SubscribeEvent
    @Override
    public void onPlayerTick(TickEvent.PlayerTickEvent tickEvent) {
            EntityPlayer player = tickEvent.player;
             RaceProps props = RaceProps.get(player);

        if (Objects.equals(props.raceName, getRaceName())) {
            if (hasTheKingArmor(player)) {
                player.addPotionEffect(new PotionEffect(Potion.resistance.id, 50, 1));
            }
        }
    }

    private boolean hasTheKingArmor(EntityPlayer player) {
        ItemStack[] armorInventory = player.inventory.armorInventory;

        ItemStack armor = armorInventory[2];
        if (armor == null || armor.getItem() != ChocolateQuest.kingArmor) {
            return false;
        }
        return true;
    }

    @Override
    public void onHurt(LivingHurtEvent hurtEvent) {
       Entity entity = hurtEvent.source.getEntity();
       if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
           RaceProps props = RaceProps.get(player);
           if (Objects.equals(props.raceName, getRaceName())) {
               if (hasTheKingArmor(player)) {
                   hurtEvent.ammount *= 0.75f;
               } else {
                   hurtEvent.ammount *= 1.5f;
               }
           }
       }

    }

    @Override
    public void onBreakSpeed(PlayerEvent.BreakSpeed breakSpeed) {

    }

    @SubscribeEvent
    @Override
    public void onHeal(LivingHealEvent healEvent)
    {
        healEvent.setCanceled(true);
    }

    @Override
    public void onInteract(PlayerInteractEvent interactEvent) {

    }

    @Override
    public void onPotionBrew(PotionBrewEvent brewEvent) {

    }

    @Override
    public void onPotionBrewPre(PotionBrewEvent.Pre brewEvent) {

    }

    @Override
    public void onPotionBrewPost(PotionBrewEvent.Post brewEvent) {

    }

    @Override
    public void onLivingJump(LivingEvent.LivingJumpEvent jumpEvent) {

    }

    @Override
    public void onLivingFall(LivingFallEvent livingFallEvent) {

    }

    @SubscribeEvent
    @Override
    public void onStruckByLighting(EntityStruckByLightningEvent lightningEvent)
    {
     Entity entity = lightningEvent.entity;
     if (entity instanceof EntityPlayer) {
         EntityPlayer player= (EntityPlayer) entity;
        RaceProps props = RaceProps.get(player);

        if (Objects.equals(props.raceName, getRaceName()))
        {
            player.setHealth(player.getHealth() + 10);
        }


     }

    }

    @Override
    public String getRaceName() {
        return "race.walker";
    }
}
