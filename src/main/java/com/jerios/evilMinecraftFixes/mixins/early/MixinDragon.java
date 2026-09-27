package com.jerios.evilMinecraftFixes.mixins.early;

import chylex.hee.entity.boss.EntityBossDragon;
import com.jerios.evilMinecraftFixes.hee.JeanFireball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.List;

@Mixin(EntityDragon.class)
public class MixinDragon extends EntityLiving {
    public MixinDragon(World p_i1595_1_) {
        super(p_i1595_1_);
    }

   @Shadow
   public double targetX;
    @Shadow public double targetY;
    @Shadow  public double targetZ;
    @Shadow  public boolean forceNewTarget;
    @Shadow  private Entity target;

    @Shadow  public EntityDragonPart dragonPartHead;


    @Unique
    int evil$fireballCharge;

    @Unique
    int evil$healTimer;


    @Inject(method = "collideWithEntities", at= @At("TAIL"))
    private void evil$jeanHarder(List p_70970_1_, CallbackInfo ci) {
        Iterator it = p_70970_1_.iterator();

        while (it.hasNext())
        {
            Entity entity2 = (Entity)it.next();

            if (entity2 instanceof EntityLivingBase)
            {
                entity2.attackEntityFrom(DamageSource.magic, 1.0F);
            }
        }

    }

    @Inject(method = "attackEntitiesInList", at= @At("TAIL"))
    private void evil$jeanHarder2(List p_70970_1_, CallbackInfo ci) {
        Iterator it = p_70970_1_.iterator();

        while (it.hasNext())
        {
            Entity entity2 = (Entity)it.next();

            if (entity2 instanceof EntityLivingBase)
            {
                entity2.attackEntityFrom(DamageSource.magic, 1.0F);
            }
        }

    }


    public void moveEntity(double x, double y, double z) {
        x *= 1.25D;
        y *= 1.25D;
        z *= 1.25D;
        super.moveEntity(x, y, z);
    }

    @Inject(method = "onLivingUpdate", at=@At("HEAD"))
    private void evil$injectLogic(CallbackInfo ci) {
        if (!this.worldObj.isRemote) {
            EntityDragon dragon = ((EntityDragon)(Object)this);

           List<EntityBossDragon> dragonNearby = this.worldObj.getEntitiesWithinAABB(EntityBossDragon.class, boundingBox.expand(32,16,32));

            for (int i = 0; i < dragonNearby.size(); i++) {
                EntityBossDragon dragonHee = dragonNearby.get(i);

                if (dragonHee.isAngry()) {
                    evil$healTimer++;

                    if (evil$fireballCharge > 800) {
                        dragonHee.heal(10);
                        evil$fireballCharge = 0;
                    }



                }

            }

            if (target != null)  {


                if (target.getDistanceToEntity(dragon) < 4096.0D)
                {
                    if (dragon.canEntityBeSeen(target))
                    {
                        ++this.evil$fireballCharge;
                        Vec3 vec3d1 = (Vec3.createVectorHelper(this.target.posX - dragon.posX, 0.0D, target.posZ - dragon.posZ)).normalize();
                        Vec3 vec3d = (Vec3.createVectorHelper((double)MathHelper.sin(dragon.rotationYaw * 0.017453292F), 0.0D, (double)(-MathHelper.cos(dragon.rotationYaw * 0.017453292F)))).normalize();
                        float f1 = (float)vec3d.dotProduct(vec3d1);
                        float f = (float)(Math.acos((double)f1) * (180D / Math.PI));
                        f = f + 0.5F;
                                    // we may wanna change this timer to be less insane! Yeah was 5 not 15 because that was too insane // can now shoot even closer I think... 1 means she can shoot any distance
                        if (this.evil$fireballCharge >= 5 && f >= 0.0F /**&& f < 1.0F 10.0F**/) {
                            double d14 = 1.0D;

                            Vec3 vec3d2 = dragon.getLook(1.0F);
                            double d6 = dragon.dragonPartHead.posX - vec3d2.xCoord * 1.0D;
                            double d7 = dragon.dragonPartHead.posY + (double) (dragon.dragonPartHead.height / 2.0F) + 0.5D;
                            double d8 = dragon.dragonPartHead.posZ - vec3d2.zCoord * 1.0D;
                            double d9 = target.posX - d6;
                            double d10 = target.posY + (double) (target.height / 2.0F) - (d7 + (double) (dragon.dragonPartHead.height / 2.0F));
                            double d11 = target.posZ - d8;
                            dragon.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1017, (int) posX, (int) posY, (int) posZ, 0);
                            JeanFireball entitydragonfireball = new JeanFireball(dragon.worldObj, dragon, d9, d10, d11);
                            entitydragonfireball.setLocationAndAngles(d6, d7, d8, 0.0F, 0.0F);
                            dragon.worldObj.spawnEntityInWorld(entitydragonfireball);
                            this.evil$fireballCharge = 0;
                        }

                    }
                    else if (this.evil$fireballCharge > 0)
                    {
                        --this.evil$fireballCharge;
                    }
                }
                else if (this.evil$fireballCharge > 0)
                {
                    --this.evil$fireballCharge;
                }

            }



        }

}





/**
 * @author Jerios
 * @reason Make Jean more agressive
 */
@Overwrite
    public boolean attackEntityFromPart(EntityDragonPart p_70965_1_, DamageSource p_70965_2_, float p_70965_3_)
    {
        if (p_70965_1_ != this.dragonPartHead)
        {
            p_70965_3_ = p_70965_3_ / 4.0F + 1.0F;
        }

        float f1 = this.rotationYaw * (float)Math.PI / 180.0F;
        float f2 = MathHelper.sin(f1);
        float f3 = MathHelper.cos(f1);
        this.targetX = this.posX + (double)(f2 * 5.0F) + (double)((this.rand.nextFloat() - 0.5F) * 2.0F);
        this.targetY = this.posY + (double)(this.rand.nextFloat() * 3.0F) + 1.0D;
        this.targetZ = this.posZ - (double)(f3 * 5.0F) + (double)((this.rand.nextFloat() - 0.5F) * 2.0F);
     //   this.target = null;

        if (p_70965_2_.getEntity() instanceof EntityPlayer || p_70965_2_.isExplosion())
        {
            this.func_82195_e(p_70965_2_, p_70965_3_);
        }

        return true;
    }


    @Override
    public void knockBack(Entity p_70653_1_, float p_70653_2_, double p_70653_3_, double p_70653_5_) {

    }

    @Shadow
    protected boolean func_82195_e(DamageSource p_82195_1_, float p_82195_2_)
    {
        return super.attackEntityFrom(p_82195_1_, p_82195_2_);
    }

    /**
     * @author Jerios
     * @reason HEE Dragon is the only one that can spawn in the portal
     */
    @Overwrite
    private void createEnderPortal(int p_70975_1_, int p_70975_2_)
    {
    }



}
