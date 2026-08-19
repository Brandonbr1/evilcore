package com.jerios.evilMinecraftFixes.mixins.late.hee;

import chylex.hee.entity.boss.EntityBossDragon;
import chylex.hee.entity.boss.dragon.attacks.special.DragonAttackDefault;
import chylex.hee.entity.boss.dragon.attacks.special.DragonSpecialAttackBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DragonAttackDefault.class)
public class DragonAttackDefaultMixin extends DragonSpecialAttackBase {
    public DragonAttackDefaultMixin(EntityBossDragon dragon, int attackId, int weight) {
        super(dragon, attackId, weight);
    }

    @Shadow(remap = false)
    public boolean hasEnded() {
        return false;
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
        return 220 + this.rand.nextInt(70) + (4 - this.getDifficulty()) * 15;
    }
}
