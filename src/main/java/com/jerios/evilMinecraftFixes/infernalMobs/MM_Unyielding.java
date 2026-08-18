package com.jerios.evilMinecraftFixes.infernalMobs;

import atomicstryker.infernalmobs.common.MobModifier;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraftforge.common.config.Configuration;

public class MM_Unyielding extends MobModifier {

    private static final String[] suffix = { "ofRelentlessness", "theUnYielding", "theUnstoppable" };
    private static final String[] prefix = { "relentless", "unyielding", "unstoppable" };

    public MM_Unyielding(EntityLivingBase mob) {
        this.modName = "Berserk";
        ((IInfernalBlacklist)this).addEmptyString();
    }

    public MM_Unyielding(EntityLivingBase mob, MobModifier prevMod) {
        this.modName = "Berserk";
        this.nextMod = prevMod;
        ((IInfernalBlacklist)this).addEmptyString();
    }


    @Override
    public boolean onUpdate(EntityLivingBase mob) {
        mob.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
            .setBaseValue(Double.MAX_VALUE);

        return super.onUpdate(mob);
    }

    @Override
    protected String[] getModNameSuffix() {
        return suffix;
    }

    @Override
    protected String[] getModNamePrefix() {
        return prefix;
    }


}
