package com.jerios.evilMinecraftFixes.hee;

import chylex.hee.entity.boss.EntityBossDragon;
import chylex.hee.entity.boss.dragon.attacks.special.DragonSpecialAttackBase;
import chylex.hee.entity.mob.EntityMobAngryEnderman;
import chylex.hee.entity.weather.EntityWeatherLightningBoltSafe;
import chylex.hee.proxy.ModCommonProxy;
import chylex.hee.system.util.DragonUtil;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;

public class SuperLightingAttack extends DragonSpecialAttackBase {
    public SuperLightingAttack(EntityBossDragon dragon, int attackId, int weight) {
        super(dragon, attackId, weight);
    }

    private EntityPlayer target;
    boolean stop;

    @Override
    public void init() {
        super.init();
        this.target = null;
        stop = false;
    }

    @Override
    public void update() {
        super.update();

        if (this.target == null) {
            this.target = this.dragon.attacks.getRandomPlayer();
            this.dragon.target = this.target;

            if (target != null) {
                for (int i = 0; i < 25 + this.rand.nextInt(12) + this.dragon.worldObj.difficultySetting.getDifficultyId(); i++) {
                    int x = (int) target.posX + this.rand.nextInt(16);
                    int z = (int) target.posZ + this.rand.nextInt(16);
                    int y = (int) target.posY + this.rand.nextInt(4);
                    this.dragon.worldObj.addWeatherEffect(new EntityLightningBolt(this.dragon.worldObj, (double) x, (double) y, (double) z));
                    if (i == 14) {
                        stop = true;
                    }
                }
            }

        } else {
            stop = true;
        }
    }

    @Override
    public float overrideMovementSpeed() {
        return super.overrideMovementSpeed() + 0.5f;
    }


    @Override
    public boolean canStart() {
        return this.dragon.attacks.getHealthPercentage() < 40;
    }


    @Override
    public boolean hasEnded() {
        return stop;
    }
}
