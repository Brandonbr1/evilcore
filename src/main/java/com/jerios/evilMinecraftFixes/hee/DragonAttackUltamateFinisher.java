package com.jerios.evilMinecraftFixes.hee;

import chylex.hee.entity.boss.EntityBossDragon;
import chylex.hee.entity.boss.dragon.attacks.special.DragonSpecialAttackBase;
import chylex.hee.entity.boss.dragon.attacks.special.event.CollisionEvent;
import chylex.hee.entity.boss.dragon.attacks.special.event.DamageTakenEvent;
import chylex.hee.entity.boss.dragon.attacks.special.event.TargetPositionSetEvent;
import chylex.hee.entity.boss.dragon.attacks.special.event.TargetSetEvent;
import chylex.hee.entity.boss.dragon.managers.DragonShotManager;
import chylex.hee.entity.mob.EntityMobAngryEnderman;
import chylex.hee.entity.mob.EntityMobVampiricBat;
import chylex.hee.entity.weather.EntityWeatherLightningBoltSafe;
import chylex.hee.proxy.ModCommonProxy;
import chylex.hee.system.util.DragonUtil;
import chylex.hee.system.util.MathUtil;
import com.jerios.evilMinecraftFixes.cfg.ConfigASM;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;

// every single attack except divebomb and punch!
public class DragonAttackUltamateFinisher extends DragonSpecialAttackBase {
    private Entity target;
    private byte shootTimer;
    private byte shotAmount;
    private byte runCounter;
    private byte waitTimer;
    private boolean ended;
    private float speed;

    public DragonAttackUltamateFinisher(EntityBossDragon dragon, int attackId, int weight) {
        super(dragon, attackId, weight);
    }

    public void init() {
        super.init();
        this.target = null;
        this.shootTimer = this.shotAmount = this.runCounter = this.waitTimer = 0;
        this.ended = false;
        this.dragon.target = null;
        this.speed = 2.0F;
    }

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
                    this.dragon.shots.createNew(DragonShotManager.ShotType.FIREBALL).setTarget(this.target).setRandom().shoot();
                    this.dragon.shots.createNew(DragonShotManager.ShotType.FIREBALL).setTarget(this.target).setRandom().shoot();
                    this.dragon.shots.createNew(DragonShotManager.ShotType.FIREBALL).setTarget(this.target).setRandom().shoot();
                    this.dragon.shots.createNew(DragonShotManager.ShotType.FIREBALL).setTarget(this.target).setRandom().shoot();
                    this.dragon.shots.createNew(DragonShotManager.ShotType.FIREBALL).setTarget(this.target).setRandom().shoot();
                    this.shootTimer = 0;
                    if (++this.shotAmount > 7 + this.rand.nextInt(6) + this.getDifficulty()) {
                        stopShooting = true;
                    }
                }
            }

            if (stopShooting) {

                for (int i = 0; i < 25 + this.rand.nextInt(12) + this.dragon.worldObj.difficultySetting.getDifficultyId(); i++) {
                    System.out.println("Lightingg?");
                    int x = (int) target.posX + this.rand.nextInt(16);
                    int z = (int) target.posZ + this.rand.nextInt(16);
                    int y = (int) target.posY + this.rand.nextInt(4);
                    this.dragon.worldObj.addWeatherEffect(new EntityWeatherLightningBoltSafe(this.dragon.worldObj, (double) x, (double) y, (double) z));
                }


                for (int i = 0; i < 4; i++) {
                    this.dragon.attacks.biteClosePlayers();
                    this.target.attackEntityFrom(DamageSource.causeMobDamage(this.dragon), 10.0F + (float)this.getDifficulty());
                    if (target instanceof EntityPlayer) {
                        EntityPlayer player = (EntityPlayer) target;
                        spawnBatAt(target.posX, target.posY, target.posZ, player);
                    }

                }

                for(int a = 0; a < 3 + this.rand.nextInt(this.getDifficulty()); ++a) {
                    double x = target.posX + (this.rand.nextDouble() - (double)0.5F) * (double)13.0F;
                    double z = target.posZ + (this.rand.nextDouble() - (double)0.5F) * (double)13.0F;
                    int y = 1 + DragonUtil.getTopBlockY(this.dragon.worldObj, Blocks.end_stone, MathUtil.floor(x), MathUtil.floor(z), MathUtil.floor(target.posY + (double)8.0F));
                    EntityMobAngryEnderman enderman = new EntityMobAngryEnderman(this.dragon.worldObj);
                    enderman.setPosition(x, (double)y, z);
                    enderman.setTarget(target);
                    if ((this.getDifficulty() > 1 || ModCommonProxy.opMobs) && this.rand.nextInt(100) < 5 + this.getDifficulty() * 10 + (ModCommonProxy.opMobs ? 25 : 0)) {
                        enderman.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 2400, 0, true));
                    }

                    this.dragon.worldObj.addWeatherEffect(new EntityWeatherLightningBoltSafe(this.dragon.worldObj, x, (double)y, z));
                    this.dragon.worldObj.spawnEntityInWorld(enderman);
                }


                this.waitTimer = 110;
                this.shootTimer = this.shotAmount = 0;
                this.target = null;
                if (++this.runCounter > 3 + Math.min(4, this.dragon.attacks.getViablePlayers().size())) {
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

    private void spawnBatAt(double x, double y, double z, EntityPlayer target) {
        EntityMobVampiricBat bat = new EntityMobVampiricBat(this.dragon.worldObj);
        bat.setPosition(x, y, z);
        bat.target = target;
        this.dragon.worldObj.spawnEntityInWorld(bat);
    }

    @Override
    public void onDamageTakenEvent(DamageTakenEvent event) {
        event.damage = ConfigASM.maxDragonDamage;
    }

    @Override
    public int getNextAttackTimer() {
        return 3;
    }

    @Override
    public float overrideMovementSpeed() {
        return 1.5F;
    }

    @Override
    public float overrideWingSpeed() {
        return 1.0F;
    }


    @Override
    public void onCollisionEvent(CollisionEvent event) {
        event.velocityX *= 0.04D;
        event.velocityY *= 0.05D;
        event.velocityZ *= 0.04D;
    }

    public boolean hasEnded() {
        return this.ended;
    }

    public void onTargetSetEvent(TargetSetEvent event) {
        event.newTarget = null;
    }

    public void onTargetPositionSetEvent(TargetPositionSetEvent event) {
        if (this.target != null) {
            event.cancel();
        }

    }
}
