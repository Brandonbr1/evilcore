package com.jerios.evilMinecraftFixes.race;

import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraftforge.common.MinecraftForge;
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
import java.util.List;
import java.util.Objects;

public abstract class BaseRace {

    protected ArrayList<String> descList = new ArrayList<>(64);


    public abstract List<String> getDesc();

    public abstract void onStartEat(PlayerUseItemEvent.Start useItemEvent);
    public abstract void onTickEat(PlayerUseItemEvent.Tick tick);
    public abstract void onFinishEat(PlayerUseItemEvent.Finish finish);
   public abstract void onHarvestDrop(BlockEvent.HarvestDropsEvent harvest);
    public abstract void onPlayerTick(TickEvent.PlayerTickEvent tickEvent);
    public abstract void onHurt(LivingHurtEvent hurtEvent);
    public abstract  void onBreakSpeed(PlayerEvent.BreakSpeed breakSpeed);
    public  abstract  void onHeal(LivingHealEvent healEvent);
    public  abstract  void onInteract(PlayerInteractEvent interactEvent);
    public  abstract  void onPotionBrew(PotionBrewEvent brewEvent);
    public  abstract   void onPotionBrewPre(PotionBrewEvent.Pre brewEvent);
    public  abstract  void onPotionBrewPost(PotionBrewEvent.Post brewEvent);
    public abstract  void onLivingJump(LivingEvent.LivingJumpEvent jumpEvent);
    public abstract  void onLivingFall(LivingFallEvent livingFallEvent);
    public  abstract  void onStruckByLighting(EntityStruckByLightningEvent lightningEvent);
    /** If anyone for whatever reason wants to create more races, append your modid for less conflicts **/
    public abstract String getRaceName();

    public boolean isSameRace(String race) {
        return Objects.equals(race, getRaceName());
    }

}
