package com.jerios.evilMinecraftFixes.mixins.late.hee;

import chylex.hee.entity.boss.EntityBossDragon;
import chylex.hee.entity.boss.dragon.attacks.special.DragonSpecialAttackBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(DragonSpecialAttackBase.class)
public abstract class DragonSpecialAttackBaseMixin {


    @Shadow(remap = false)
    protected EntityBossDragon dragon;
    @Shadow(remap = false)
    protected Random rand;

    /**
     * @author
     * @reason
     */
    @Overwrite(remap = false)
    public int getNextAttackTimer() {
        if (this.dragon.attacks.getHealthPercentage() <= 40) {
            return 60;
        }
        return Math.max(140, 220 + this.rand.nextInt(140) + (4 - this.getDifficulty()) * 30 - Math.min(60, this.dragon.worldObj.playerEntities.size() * 10));
    }

    @Shadow(remap = false)
    protected final int getDifficulty() {
        return 3;
    }



}
