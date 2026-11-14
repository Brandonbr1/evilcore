package com.jerios.evilMinecraftFixes;

import com.jerios.evilMinecraftFixes.race.RaceProps;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;

public interface IGolemAttacker extends IAnimals {

    /** Entity selector for IMob types. */
    IEntitySelector mobSelector = new IEntitySelector()
    {
        public boolean isEntityApplicable(Entity entity)
        {
            if (entity instanceof EntityPlayer) {
                EntityPlayer p = (EntityPlayer) entity;
                RaceProps props = RaceProps.get(entity);

                return props.raceName == "ORC";
            }

            return entity instanceof IMob;
        }
    };


}
