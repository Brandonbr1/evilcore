package com.jerios.evilMinecraftFixes.infernalMobs;

import atomicstryker.infernalmobs.common.MobModifier;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;

public class MM_SuperAttack extends MobModifier {

    private static final String[] suffix = { "ofRelentlessness", "quick", "theAttacker" };
    private static final String[] prefix = { "relentless", "superAttack", "superSpeed" };

    public MM_SuperAttack(EntityLivingBase mob) {
        this.modName = "SuperAttack";
        ((IInfernalBlacklist)this).addEmptyString();
    }

    public MM_SuperAttack(EntityLivingBase mob, MobModifier prevMod) {
        this.modName = "SuperAttack";
        this.nextMod = prevMod;
        ((IInfernalBlacklist)this).addEmptyString();
    }


    @Override
    public boolean onUpdate(EntityLivingBase mob) {
        mob.attackTime-=3;
        return super.onUpdate(mob);
    }
}
