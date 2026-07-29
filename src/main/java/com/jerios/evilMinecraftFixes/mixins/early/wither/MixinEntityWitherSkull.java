package com.jerios.evilMinecraftFixes.mixins.early.wither;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityWitherSkull.class)
public class MixinEntityWitherSkull extends EntityFireball {
    public MixinEntityWitherSkull(World p_i1759_1_) {
        super(p_i1759_1_);
    }

    @Unique
    EntityWither evil$entityWither;

    /**
     * @author
     * @reason
     */
    @Overwrite
    protected float getMotionFactor()
    {
        // faster if wither
        if (evil$entityWither != null) {
            return this.isInvulnerable() ? 0.95F : super.getMotionFactor() + 0.20f;
        }
        return this.isInvulnerable() ? 0.73F : super.getMotionFactor();
    }

    @Shadow
    public boolean isInvulnerable()
    {
        return this.dataWatcher.getWatchableObjectByte(10) == 1;
    }

    @Inject(method = "<init>(Lnet/minecraft/world/World;Lnet/minecraft/entity/EntityLivingBase;DDD)V", at = @At("TAIL"))
    private void evil$inject(World p_i1794_1_, EntityLivingBase p_i1794_2_, double p_i1794_3_, double p_i1794_5_, double p_i1794_7_, CallbackInfo ci) {
        if (p_i1794_2_ instanceof EntityWither) {
            EntityWither wither = (EntityWither) p_i1794_2_;
            evil$entityWither = wither;
        }

    }
    int explodeCounter = 3;
    /**
     * @author Jerios
     * @reason Harder Wither skulls
     * @param p_70227_1_
     */
    @Overwrite
    protected void onImpact(MovingObjectPosition p_70227_1_)
    {
        if (!this.worldObj.isRemote)
        {
            if (p_70227_1_.entityHit != null)
            {
                if (this.shootingEntity != null)
                {
                    if (p_70227_1_.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), 8.0F) && !p_70227_1_.entityHit.isEntityAlive())
                    {
                        this.shootingEntity.heal(5.0F);
                    }
                }
                else
                {
                    p_70227_1_.entityHit.attackEntityFrom(DamageSource.magic, 5.0F);
                }

                if (p_70227_1_.entityHit instanceof EntityLivingBase)
                {
                    byte b0 = 0;

                    if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
                    {
                        b0 = 10;
                    }
                    else if (this.worldObj.difficultySetting == EnumDifficulty.HARD)
                    {
                        b0 = 40;
                    }

                    if (b0 > 0)
                    {
                        if (evil$entityWither == null) {
                            ((EntityLivingBase)p_70227_1_.entityHit).addPotionEffect(new PotionEffect(Potion.wither.id, 20 * b0, 2));
                        } else {
                            if (evil$entityWither.isArmored()) {
                                ((EntityLivingBase)p_70227_1_.entityHit).addPotionEffect(new PotionEffect(Potion.wither.id, 20 * b0, 3));
                            } else {
                                ((EntityLivingBase)p_70227_1_.entityHit).addPotionEffect(new PotionEffect(Potion.wither.id, 20 * b0, 2));
                            }
                        }
                      //  ((EntityLivingBase)p_70227_1_.entityHit).addPotionEffect(new PotionEffect(Potion.wither.id, 20 * b0, 2));
                    }
                }
            }

            if (evil$entityWither != null) {
                if (evil$entityWither.isArmored()) {
                    this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, 1.0F + this.rand.nextInt(2), true, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
                    explodeCounter = 3;
                }
                this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, 1.0F, true, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
            } else {
                this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, 1.0F, true, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
            }


            // create a repeat chain of explosions like the creepers if withers.
            if (evil$entityWither == null) {
                this.setDead();
            } else if (evil$entityWither.isArmored()) {
                    if (rand.nextInt(28) == 0) {
                        System.out.println("MY code here was ran");

                        for (int i = 0; i <= explodeCounter; i++) {
                            if (i >= explodeCounter)  {
                                this.setDead();
                            }
                        }

                    } else {
                        this.setDead();
                    }

                } else {
                    this.setDead();
                }




        }
    }
}
