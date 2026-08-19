package com.jerios.evilMinecraftFixes.mixins.late.hee;

import chylex.hee.entity.boss.EntityBossDragon;
import chylex.hee.entity.boss.dragon.attacks.special.DragonAttackSummoning;
import chylex.hee.entity.boss.dragon.attacks.special.DragonSpecialAttackBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DragonAttackSummoning.class)
public class DragonAttackSummoningMixin extends DragonSpecialAttackBase {
    public DragonAttackSummoningMixin(EntityBossDragon dragon, int attackId, int weight) {
        super(dragon, attackId, weight);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite(remap = false)
    public int getNextAttackTimer() {
        if (this.dragon.attacks.getHealthPercentage() <= 40) {
            return 60;
        }
        return super.getNextAttackTimer() + 100;
    }

    @Shadow(remap = false)
    public boolean hasEnded() {
        return false;
    }
}
