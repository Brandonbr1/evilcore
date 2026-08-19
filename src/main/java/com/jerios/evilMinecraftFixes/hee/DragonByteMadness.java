package com.jerios.evilMinecraftFixes.hee;

import chylex.hee.entity.boss.EntityBossDragon;
import chylex.hee.entity.boss.dragon.attacks.special.DragonSpecialAttackBase;
import chylex.hee.entity.boss.dragon.attacks.special.event.CollisionEvent;
import chylex.hee.entity.boss.dragon.attacks.special.event.DamageTakenEvent;
import chylex.hee.entity.boss.dragon.attacks.special.event.TargetPositionSetEvent;
import chylex.hee.entity.boss.dragon.attacks.special.event.TargetSetEvent;
import chylex.hee.mechanics.compendium.KnowledgeRegistrations;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class DragonByteMadness extends DragonSpecialAttackBase {
    private EntityPlayer target;


    private byte biteCooldown = 0;

    public DragonByteMadness(EntityBossDragon dragon, int attackId, int weight) {
        super(dragon, attackId, weight);
    }
    @Override
    public void update() {
        super.update();
        if (this.target == null) {
            this.target = this.dragon.attacks.getWeakPlayer();
            this.dragon.target = this.target;
        } else if (this.target.isDead) {
            this.tick = 999;
        } else if (this.target.getHealth() < 7.0F) {
            this.tick--;
        }
        if (this.target != null) {
            if (this.dragon.target != null && this.biteCooldown == 0) {
                this.biteCooldown = (byte)(this.dragon.attacks.biteClosePlayers() ? 6 : 3);
                System.out.println("OM NOM NOM");
              //  if (this.dragon.target instanceof EntityPlayer) {
                    //    KnowledgeRegistrations.ENDER_DRAGON((EntityPlayer)this.dragon.target, 0.14F, new short[] { 7, 10 });
              //  }
            }
            if (this.biteCooldown > 0)
                this.biteCooldown = (byte)(this.biteCooldown - 1);
        }
    }

    @Override
    public void end() {
        super.end();
        if (this.target != null && this.target.getHealth() < 8.0F) {
            this.dragon.trySetTarget(this.target);
        }
    }

    @Override
    public boolean canStart() {
        return (this.dragon.getHealth() > 40.0F);
    }

    @Override
    public boolean hasEnded() {
        return ((this.tick > 40 && this.target == null) || this.tick > 500 - rand.nextInt(70));
    }

    @Override
    public void onDamageTakenEvent(DamageTakenEvent event) {
        event.damage = Math.max(event.damage * 0.5F, 1.0F);
        this.tick = (int)(this.tick + 10.0F * event.damage);
    }

    @Override
    public void onTargetSetEvent(TargetSetEvent event) {
        event.newTarget = this.target;
    }

    @Override
    public void onTargetPositionSetEvent(TargetPositionSetEvent event) {
        event.cancel();
    }

    @Override
    public void onCollisionEvent(CollisionEvent event) {
        event.velocityX *= 0.04D;
        event.velocityY *= 0.05D;
        event.velocityZ *= 0.04D;
    }
}
