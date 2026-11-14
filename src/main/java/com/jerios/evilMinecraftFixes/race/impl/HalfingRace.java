package com.jerios.evilMinecraftFixes.race.impl;

import com.jerios.evilMinecraftFixes.race.BaseRace;
import com.jerios.evilMinecraftFixes.race.RaceProps;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
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
import java.util.UUID;

public class HalfingRace extends BaseRace {


    private static final UUID attackingSpeedBoostModifierUUID = UUID.fromString("020E0DFB-87AE-4653-9556-K31010E291A0");
    private static final AttributeModifier sprintingSpeedBoostModifier = (new AttributeModifier(attackingSpeedBoostModifierUUID, "Hafling Speed Boost", 0.10000001192092896D, 2)).setSaved(true);


    @Override
    public List<String> getDesc() {
        descList.add("Is Hungry");
        descList.add("Expert Farmer");
        descList.add("1.1x faster speed");
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
        if (tickEvent.phase == TickEvent.Phase.END && tickEvent.side == Side.SERVER) {
            EntityPlayer player = tickEvent.player;
            RaceProps props = RaceProps.get(player);

            if (!player.worldObj.isRemote) {
                if (isSameRace(props.raceName)) {
                    player.getFoodStats().addExhaustion(0.3f);
                    player.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(sprintingSpeedBoostModifier);
                }

            }
        }

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
    public void onStruckByLighting(EntityStruckByLightningEvent lightningEvent)
    {

    }

    @Override
    public String getRaceName() {
        return "race.halfing";
    }
}
