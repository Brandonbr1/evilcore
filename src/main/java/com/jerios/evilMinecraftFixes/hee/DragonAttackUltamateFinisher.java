package com.jerios.evilMinecraftFixes.hee;

import chylex.hee.entity.boss.EntityBossDragon;
import chylex.hee.entity.boss.dragon.attacks.special.DragonSpecialAttackBase;
import chylex.hee.entity.boss.dragon.attacks.special.event.DamageTakenEvent;
import chylex.hee.entity.boss.dragon.attacks.special.event.TargetPositionSetEvent;
import chylex.hee.entity.boss.dragon.attacks.special.event.TargetSetEvent;
import chylex.hee.entity.boss.dragon.managers.DragonShotManager;
import chylex.hee.proxy.ModCommonProxy;
import chylex.hee.system.util.MathUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

public class DragonAttackUltamateFinisher extends DragonSpecialAttackBase {
    private Entity target;
    private byte shootTimer;
    private byte shotAmount;
    private byte runCounter;
    private byte waitTimer;
    private boolean ended;

    public DragonAttackUltamateFinisher(EntityBossDragon dragon, int attackId, int weight) {
        super(dragon, attackId, weight);
    }

    public void init() {
        super.init();
        this.target = null;
        this.shootTimer = this.shotAmount = this.runCounter = this.waitTimer = 0;
        this.ended = false;
        this.dragon.target = null;
    }

    @Override
    public void update() {
        super.update();
        if (this.target != null && !this.target.isDead) {
            this.dragon.targetX = this.target.posX;
            this.dragon.targetY = this.target.posY + (double)10.0F;
            this.dragon.targetZ = this.target.posZ;
            double dist = MathUtil.distance(this.dragon.targetX - this.dragon.posX, this.dragon.targetZ - this.dragon.posZ);
            boolean stopShooting = false;
            if (dist < (double)90.0F && (this.waitTimer <= 0 || --this.waitTimer <= 0)) {
                if (dist < (double)30.0F) {
                    stopShooting = true;
                } else if (++this.shootTimer > 13 - this.getDifficulty() * 2 - (ModCommonProxy.opMobs ? 3 : 0)) {
                    System.out.println("Douglas should be obliterating your ass");
                    this.dragon.shots.createNew(DragonShotManager.ShotType.FIREBALL).setTarget(this.target).setRandom().shoot();
                    this.shootTimer = 0;
                    if (shotAmount % 2 == 0) {
                        this.dragon.attacks.biteClosePlayers();
                    }
                    if (++this.shotAmount > 17 + this.rand.nextInt(6) + this.getDifficulty()) {
                        stopShooting = true;
                    }
                }
            }

            if (stopShooting) {
                this.waitTimer = 110;
                this.shootTimer = this.shotAmount = 0;
                this.target = null;
                if (++this.runCounter > 12 + Math.min(4, this.dragon.attacks.getViablePlayers().size())) {
                    this.ended = true;
                }
            }
        } else if (this.waitTimer <= 0 || --this.waitTimer <= 0) {
            if ((this.target = this.dragon.attacks.getRandomPlayer()) == null) {
                this.ended = true;
            } else if (MathUtil.distance(this.dragon.targetX - this.dragon.posX, this.dragon.targetZ - this.dragon.posZ) < (double)60.0F) {
                this.target = null;
                this.waitTimer = 60;
                double dist = (double)10.0F;
                Vec3 vec = Vec3.createVectorHelper(this.dragon.motionX, (double)0.0F, this.dragon.motionZ).normalize();

                for(int attempt = 0; attempt < 10; ++attempt) {
                    this.dragon.targetX = this.dragon.posX + vec.xCoord * dist + (this.rand.nextDouble() - (double)0.5F) * (double)4.0F;
                    this.dragon.targetZ = this.dragon.posZ + vec.zCoord * dist + (this.rand.nextDouble() - (double)0.5F) * (double)4.0F;
                    if (MathUtil.distance(this.dragon.targetX - this.dragon.posX, this.dragon.targetZ - this.dragon.posZ) > (double)65.0F) {
                        break;
                    }

                    dist += (double)5.0F;
                }
            } else {
                this.waitTimer = 8;
            }
        }

    }

    @Override
    public float overrideMovementSpeed() {
        return super.overrideMovementSpeed() + 0.4f;
    }

    @Override
    public boolean hasEnded() {
        return this.ended;
    }

    @Override
    public void onTargetSetEvent(TargetSetEvent event) {
        event.newTarget = null;
    }

    @Override
    public void onTargetPositionSetEvent(TargetPositionSetEvent event) {
        if (this.target != null) {
            event.cancel();
        }
    }

    @Override
    public boolean canStart() {
        return this.dragon.attacks.getHealthPercentage() < 40;
    }

}
