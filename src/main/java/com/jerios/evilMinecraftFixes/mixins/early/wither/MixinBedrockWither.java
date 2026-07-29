package com.jerios.evilMinecraftFixes.mixins.early.wither;

import com.jerios.evilMinecraftFixes.fasterMath.FastTrigno;
import com.jerios.evilMinecraftFixes.athena.WitherProps;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// replaced attan2 math with FastTringo
@Mixin(EntityWither.class)
public class MixinBedrockWither extends EntityMob {
    public MixinBedrockWither(World p_i1738_1_) {
        super(p_i1738_1_);
        // func_82212_n() // whatever number it needs to be
    }

    @Shadow private float[] field_82220_d = new float[2];
    @Shadow private float[] field_82221_e = new float[2];
    @Shadow  private float[] field_82217_f = new float[2];
    @Shadow  private float[] field_82218_g = new float[2];

    @Shadow
    public void func_82215_s(int p_82215_1_)
    {
        this.dataWatcher.updateObject(20, Integer.valueOf(p_82215_1_));
    }

    /**
     * @author Vortex
     * @reason Give Bedrock Wither HP
     */
    @Overwrite
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(600.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6000000238418579D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
    }


    /**
     * @author _
     * @reason _
     */
    @Overwrite
    private void func_82209_a(int p_82209_1_, double p_82209_2_, double p_82209_4_, double p_82209_6_, boolean p_82209_8_)
    {
        this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1014, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
        double d3 = this.func_82214_u(p_82209_1_);
        double d4 = this.func_82208_v(p_82209_1_);
        double d5 = this.func_82213_w(p_82209_1_);
        double d6 = p_82209_2_ - d3;
        double d7 = p_82209_4_ - d4;
        double d8 = p_82209_6_ - d5;

        if (this.getHealth() <= this.getMaxHealth() / 2.0F ) {
            EntityWitherSkull entitywitherskull2 = new EntityWitherSkull(this.worldObj, this, d6 - 0.5, d7 - 0.5, d8 - 0.5);
            entitywitherskull2.posY = d4;
            entitywitherskull2.posX = d3;
            entitywitherskull2.posZ = d5;

            if (p_82209_8_)
            {
                entitywitherskull2.setInvulnerable(true);
            }

            this.worldObj.spawnEntityInWorld(entitywitherskull2);

        }

        if (this.getHealth() <= this.getMaxHealth() / 2.0F ) {
            EntityWitherSkull entitywitherskull2 = new EntityWitherSkull(this.worldObj, this, d6 , d7 , d8);
            entitywitherskull2.posY = d4;
            entitywitherskull2.posX = d3;
            entitywitherskull2.posZ = d5;

            if (p_82209_8_)
            {
                entitywitherskull2.setInvulnerable(true);
            }

            this.worldObj.spawnEntityInWorld(entitywitherskull2);

        }

        WitherProps props = WitherProps.get(((EntityWither) (Object) this));

        if (this.getHealth() <= this.getMaxHealth() / 1.35f || props.forcePinchMode) {

            if (rand.nextInt(35) == 0 || props.forcePinchMode) {
                for (int i = 0; i < 8 + this.rand.nextInt(4); i++) {
                    EntityWitherSkull entitywitherskull3 = new EntityWitherSkull(this.worldObj, this, d6 + 0.5, d7 + 0.5, d8 + 0.5);
                    entitywitherskull3.posY = d4;
                    entitywitherskull3.posX = d3;
                    entitywitherskull3.posZ = d5;

                    if (p_82209_8_)
                    {
                        entitywitherskull3.setInvulnerable(true);
                    }


                }
            }

        }



        EntityWitherSkull entitywitherskull = new EntityWitherSkull(this.worldObj, this, d6, d7, d8);

        if (p_82209_8_)
        {
            entitywitherskull.setInvulnerable(true);
        }

        entitywitherskull.posY = d4;
        entitywitherskull.posX = d3;
        entitywitherskull.posZ = d5;
        this.worldObj.spawnEntityInWorld(entitywitherskull);
    }

  @Inject(method = "attackEntityFrom", at = @At("HEAD"), cancellable = true)
  private void evil$noExplosionDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
  {
      if (source.isExplosion()) {
          cir.setReturnValue(false);
      }
  }



  // TODO: CHECK
  /**
   * @author
   * @reason
   */
  @Overwrite
    private double func_82214_u(int p_82214_1_)
    {
        if (p_82214_1_ <= 0)
        {
            return this.posX;
        }
        else
        {
            float f = (this.renderYawOffset + (float)(180 * (p_82214_1_ - 1))) / 180.0F * (float)Math.PI;
            float f1 = MathHelper.cos(f);
            return this.posX + (double)f1 * 1.0D; //* 1.3D;
        }
    }

    //TODO
    /**
     * @author
     * @reason
     */
    @Overwrite
    private double func_82213_w(int p_82213_1_)
    {
        if (p_82213_1_ <= 0)
        {
            return this.posZ;
        }
        else
        {
            float f = (this.renderYawOffset + (float)(180 * (p_82213_1_ - 1))) / 180.0F * (float)Math.PI;
            float f1 = MathHelper.sin(f);
            return this.posZ + (double)f1 * 1.0D; //1.3D;
        }
    }

    @Shadow
    public boolean isArmored()
    {
        return this.getHealth() <= this.getMaxHealth() / 2.0F;
    }

    @Shadow
    private double func_82208_v(int p_82208_1_)
    {
        return p_82208_1_ <= 0 ? this.posY + 3.0D : this.posY + 2.2D;
    }


    /**
     * @author Vortex
     * @reason Give Bedrock Wither HP
     */
    @Overwrite
    public void func_82206_m()
    {
        this.func_82215_s(120); // set this to correct value
        this.setHealth(this.getMaxHealth() / 1.2f);
    }

    @Shadow
    public int getWatchedTargetId(int p_82203_1_)
    {
        return this.dataWatcher.getWatchableObjectInt(17 + p_82203_1_);
    }

    @Shadow
    public int func_82212_n()
    {
        return this.dataWatcher.getWatchableObjectInt(20);
    }

    @Shadow
    private float func_82204_b(float p_82204_1_, float p_82204_2_, float p_82204_3_)
    {
        float f3 = MathHelper.wrapAngleTo180_float(p_82204_2_ - p_82204_1_);

        if (f3 > p_82204_3_)
        {
            f3 = p_82204_3_;
        }

        if (f3 < -p_82204_3_)
        {
            f3 = -p_82204_3_;
        }

        return p_82204_1_ + f3;
    }

    /**
     * @author Vortex
     * @reason Make wither float higher
     */
    @Overwrite
    public void onLivingUpdate()
    {
        this.motionY *= 0.6000000238418579D;
        double d1;
        double d3;
        double d5;

        if (!this.worldObj.isRemote && this.getWatchedTargetId(0) > 0)
        {
            Entity entity = this.worldObj.getEntityByID(this.getWatchedTargetId(0));

            if (entity != null)
            {
                // rise up, hardly in player hit range if in desperate mode
                if (this.getHealth() <= 35 && this.posY < entity.posY + 2D || this.posY < entity.posY || !this.isArmored() && this.posY < entity.posY + 14.0D)
                {
                    if (this.motionY < 0.0D)
                    {
                        this.motionY = 0.0D;
                    }

                    this.motionY += (0.6D - this.motionY) * 0.6000000238418579D;
                }

                double d0 = entity.posX - this.posX;
                d1 = entity.posZ - this.posZ;
                d3 = d0 * d0 + d1 * d1;

                if (d3 > 9.0D)
                {
                    d5 = (double)MathHelper.sqrt_double(d3);
                    this.motionX += (d0 / d5 * 0.5D - this.motionX) * 0.6000000238418579D;
                    this.motionZ += (d1 / d5 * 0.5D - this.motionZ) * 0.6000000238418579D;
                }
            }
        }

        if (this.motionX * this.motionX + this.motionZ * this.motionZ > 0.05000000074505806D)
        {
            this.rotationYaw = (float) FastTrigno.fastAtan2(this.motionZ, this.motionX) * (180F / (float)Math.PI) - 90.0F;
        }

        super.onLivingUpdate();
        int i;

        for (i = 0; i < 2; ++i)
        {
            this.field_82218_g[i] = this.field_82221_e[i];
            this.field_82217_f[i] = this.field_82220_d[i];
        }

        int j;

        for (i = 0; i < 2; ++i)
        {
            j = this.getWatchedTargetId(i + 1);
            Entity entity1 = null;

            if (j > 0)
            {
                entity1 = this.worldObj.getEntityByID(j);
            }

            if (entity1 != null)
            {
                d1 = this.func_82214_u(i + 1);
                d3 = this.func_82208_v(i + 1);
                d5 = this.func_82213_w(i + 1);
                double d6 = entity1.posX - d1;
                double d7 = entity1.posY + (double)entity1.getEyeHeight() - d3;
                double d8 = entity1.posZ - d5;
                double d9 = (double)MathHelper.sqrt_double(d6 * d6 + d8 * d8);
                float f = (float)(FastTrigno.fastAtan2(d8, d6) * 180.0D / Math.PI) - 90.0F;
                float f1 = (float)(-(FastTrigno.fastAtan2(d7, d9) * 180.0D / Math.PI));
                this.field_82220_d[i] = this.func_82204_b(this.field_82220_d[i], f1, 40.0F);
                this.field_82221_e[i] = this.func_82204_b(this.field_82221_e[i], f, 10.0F);
            }
            else
            {
                this.field_82221_e[i] = this.func_82204_b(this.field_82221_e[i], this.renderYawOffset, 10.0F);
            }
        }

        boolean flag = this.isArmored();

        for (j = 0; j < 3; ++j)
        {
            double d10 = this.func_82214_u(j);
            double d2 = this.func_82208_v(j);
            double d4 = this.func_82213_w(j);
            this.worldObj.spawnParticle("smoke", d10 + this.rand.nextGaussian() * 0.30000001192092896D, d2 + this.rand.nextGaussian() * 0.30000001192092896D, d4 + this.rand.nextGaussian() * 0.30000001192092896D, 0.0D, 0.0D, 0.0D);

            if (flag && this.worldObj.rand.nextInt(4) == 0)
            {
                this.worldObj.spawnParticle("mobSpell", d10 + this.rand.nextGaussian() * 0.30000001192092896D, d2 + this.rand.nextGaussian() * 0.30000001192092896D, d4 + this.rand.nextGaussian() * 0.30000001192092896D, 0.699999988079071D, 0.699999988079071D, 0.5D);
            }
        }

        if (this.func_82212_n() > 0)
        {
            for (j = 0; j < 3; ++j)
            {
                this.worldObj.spawnParticle("mobSpell", this.posX + this.rand.nextGaussian() * 1.0D, this.posY + (double)(this.rand.nextFloat() * 3.3F), this.posZ + this.rand.nextGaussian() * 1.0D, 0.699999988079071D, 0.699999988079071D, 0.8999999761581421D);
            }
        }
    }

}
