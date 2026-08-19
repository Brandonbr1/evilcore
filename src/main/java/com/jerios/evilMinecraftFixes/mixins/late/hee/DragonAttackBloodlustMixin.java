package com.jerios.evilMinecraftFixes.mixins.late.hee;

import chylex.hee.entity.boss.EntityBossDragon;
import chylex.hee.entity.boss.dragon.attacks.special.DragonAttackBloodlust;
import chylex.hee.entity.boss.dragon.attacks.special.DragonSpecialAttackBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DragonAttackBloodlust.class)
public class DragonAttackBloodlustMixin extends DragonSpecialAttackBase {
    public DragonAttackBloodlustMixin(EntityBossDragon dragon, int attackId, int weight) {
        super(dragon, attackId, weight);
    }

    @Shadow(remap = false)
    public boolean hasEnded() {
        return false;
    }


    /**
     * @author Jerios
     * @reason Faster Attack Timer
     */
    @Overwrite(remap = false)
    public int getNextAttackTimer() {
        if (this.dragon.attacks.getHealthPercentage() <= 40) {
            return 60;
        }
        return super.getNextAttackTimer() + 70;
    }

}
