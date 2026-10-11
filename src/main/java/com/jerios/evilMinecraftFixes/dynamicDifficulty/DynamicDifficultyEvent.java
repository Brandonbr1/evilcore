package com.jerios.evilMinecraftFixes.dynamicDifficulty;

import com.jerios.evilMinecraftFixes.savedata.EvilData;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import hostileworlds.entity.monster.ZombieClawer;
import hostileworlds.entity.monster.ZombieClimber;
import hostileworlds.entity.monster.ZombieHungry;
import hostileworlds.entity.monster.ZombieMiner;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

public class DynamicDifficultyEvent {

    @SubscribeEvent
    public void getWorldTicks(TickEvent.WorldTickEvent tickEvent) {
            if (tickEvent.side == Side.SERVER)
            {
                if (tickEvent.phase == TickEvent.Phase.END) {
                    if (!tickEvent.world.isRemote)
                    {
                        EvilData data = EvilData.get(tickEvent.world);
                        if (data != null) {
                        data.increaseTicks();
                        }
                    }
                }
            }
    }

    @SubscribeEvent
    public void graceWhereNoSpawn(LivingSpawnEvent.CheckSpawn spawnEvent) {
        World world = spawnEvent.world;
        if (!world.isRemote) {
            EvilData data = EvilData.get(world);
            // only spawn after invasion
            if (data!= null&&data.worldTicks <= 143999)
            {
                EntityLivingBase ent = spawnEvent.entityLiving;

                if (ent instanceof ZombieMiner || ent instanceof ZombieHungry || ent instanceof ZombieClimber || ent instanceof ZombieClawer)
                {
                spawnEvent.setResult(Event.Result.DEFAULT);
                }
            }
        }
    }


}
