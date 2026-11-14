package com.jerios.evilMinecraftFixes.race.impl;

import com.jerios.evilMinecraftFixes.race.BaseRace;
import com.jerios.evilMinecraftFixes.race.RaceProps;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DruidRace extends BaseRace {

    // ANYONE CAN ADD TO IT IF NEEDED.
    public static Map<Item, PotionEffect> GOOD_ITEM_POTION_MAP = new HashMap<>();
    public static Map<Item, PotionEffect> BAD_ITEM_POTION_MAP = new HashMap<>();


    static  {
        GOOD_ITEM_POTION_MAP.put(Items.apple, new PotionEffect(new PotionEffect(Potion.regeneration.id, 160, 1)));
        GOOD_ITEM_POTION_MAP.put(Items.carrot, new PotionEffect(new PotionEffect(Potion.regeneration.id, 180, 1)));
        GOOD_ITEM_POTION_MAP.put(Items.golden_apple, new PotionEffect(new PotionEffect(Potion.resistance.id, 480, 2)));
        GOOD_ITEM_POTION_MAP.put(Items.golden_carrot, new PotionEffect(new PotionEffect(Potion.regeneration.id, 480, 2)));
        GOOD_ITEM_POTION_MAP.put(Items.melon, new PotionEffect(new PotionEffect(Potion.regeneration.id, 40, 1)));
        GOOD_ITEM_POTION_MAP.put(Items.baked_potato, new PotionEffect(new PotionEffect(Potion.damageBoost.id, 140, 3)));



    }

    @Override
    public List<String> getDesc() {
        descList.add("Is very weak to fire");
        descList.add("Vegetarian foods heals and gives better hunger stats");
        descList.add("Meat items cause debuffs and worse hunger status.");
        descList.add("Gains debuffs in the nether");
        descList.add("Attacking has a chance to give bad potion effects to the attacked in return of good ones to you.");
        return descList;
    }

    @Override
    public void onStartEat(PlayerUseItemEvent.Start useItemEvent) {

    }

    @Override
    public void onTickEat(PlayerUseItemEvent.Tick tick) {

    }

    @Override
    public void onFinishEat(PlayerUseItemEvent.Finish finish)
    {
        EntityPlayer player = finish.entityPlayer;
        if (!player.worldObj.isRemote)
        {
            RaceProps props = RaceProps.get(player);

            if (isSameRace(props.raceName))
            {
                ItemStack stack = finish.item;

               if (stack != null)
               {
                   Item item = stack.getItem();

               }

            }
        }

    }

    @Override
    public void onHarvestDrop(BlockEvent.HarvestDropsEvent harvest) {

    }

    @Override
    public void onPlayerTick(TickEvent.PlayerTickEvent tickEvent) {

    }

    @Override
    public void onHurt(LivingHurtEvent hurtEvent) {

    }

    @Override
    public void onBreakSpeed(PlayerEvent.BreakSpeed breakSpeed) {

    }

    @Override
    public void onHeal(LivingHealEvent healEvent) {

    }

    @Override
    public void onInteract(PlayerInteractEvent interactEvent) {

    }

    @Override
    public void onPotionBrew(PotionBrewEvent brewEvent)
    {

    }

    @Override
    public void onPotionBrewPre(PotionBrewEvent.Pre brewEvent)
    {

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

    @Override
    public void onStruckByLighting(EntityStruckByLightningEvent lightningEvent) {

    }

    @Override
    public String getRaceName() {
        return "race.druid";
    }


    public static class ItemEaten
    {
        public Item item;
        public PotionEffect effect;
        public float newHunger;
        public float newSaturation;

    }
}
