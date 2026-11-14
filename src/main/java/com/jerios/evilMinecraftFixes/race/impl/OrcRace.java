package com.jerios.evilMinecraftFixes.race.impl;

import com.jerios.evilMinecraftFixes.race.BaseRace;
import com.jerios.evilMinecraftFixes.race.RaceProps;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
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

public class OrcRace extends BaseRace {

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

    @SubscribeEvent
    @Override
    public void onHarvestDrop(BlockEvent.HarvestDropsEvent harvest) {
        EntityPlayer player = harvest.harvester;
        World world = player.worldObj;

        if (!world.isRemote) {

            RaceProps props = RaceProps.get(player);

            if (isSameRace(props.raceName) && world.rand.nextFloat() > 0.75f)
            {

                Block block = harvest.block;

                if (block == Blocks.wheat || block == Blocks.pumpkin || block  == Blocks.melon_block)
                {
                    block.dropBlockAsItem(world, harvest.x, harvest.y, harvest.z, harvest.blockMetadata, harvest.fortuneLevel);

                }

            }
        }


    }

    @SubscribeEvent
    @Override
    public void onPlayerTick(TickEvent.PlayerTickEvent tickEvent)
    {
        if (tickEvent.phase == TickEvent.Phase.END && tickEvent.side == Side.SERVER) {
            EntityPlayer player = tickEvent.player;
            RaceProps props = RaceProps.get(player);

            if (!player.worldObj.isRemote) {

                if (isSameRace(props.raceName)) {
                    if (player.isInWater()) {
                        player.setAir(player.getAir() - 1);
                    }
                }


            }
        }

    }

    @SubscribeEvent
    @Override
    public void onHurt(LivingHurtEvent hurtEvent)
    {



        Entity entity = hurtEvent.source.getEntity();
        boolean isProj = hurtEvent.source.isProjectile();
        if (entity instanceof EntityPlayer)
        {
            RaceProps props = RaceProps.get(entity);

            if (isSameRace(props.raceName))
            {
                if (isProj) {
                    hurtEvent.ammount *= 0.65F;
                } else {
                    hurtEvent.ammount *= 1.2F;
                }
            }




        }

    }

    @SubscribeEvent
    @Override
    public void onBreakSpeed(PlayerEvent.BreakSpeed breakSpeed)
    {
        RaceProps props = RaceProps.get(breakSpeed.entity);
        if (isSameRace(props.raceName))
        {
            breakSpeed.newSpeed = 1.5f;
        }

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
        return "race.orc";
    }
}
