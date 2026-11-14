package com.jerios.evilMinecraftFixes.race.impl;

import com.jerios.evilMinecraftFixes.race.BaseRace;
import cpw.mods.fml.common.gameevent.TickEvent;
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

public class TurtleRace extends BaseRace {

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
    public void onHarvestDrop(BlockEvent.HarvestDropsEvent harvest)
    {

    }

    @Override
    public void onPlayerTick(TickEvent.PlayerTickEvent tickEvent)
    {

    }

    @Override
    public void onHurt(LivingHurtEvent hurtEvent) {

    }

    @Override
    public void onBreakSpeed(PlayerEvent.BreakSpeed breakSpeed) {

    }

    @Override
    public void onHeal(LivingHealEvent healEvent)
    {

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

    @Override
    public void onStruckByLighting(EntityStruckByLightningEvent lightningEvent) {

    }

    @Override
    public String getRaceName() {
        return "race.turtle";
    }
}
