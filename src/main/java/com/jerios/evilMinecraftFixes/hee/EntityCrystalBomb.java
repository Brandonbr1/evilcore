package com.jerios.evilMinecraftFixes.hee;

import chylex.hee.entity.boss.EntityBossDragon;
import chylex.hee.proxy.ModCommonProxy;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.List;

public class EntityCrystalBomb extends EntityThrowable {

    public EntityCrystalBomb(World world) {
        super(world);
    }

    public EntityCrystalBomb(World world, double x, double y, double z, EntityLivingBase target) {
        super(world, x, y, z);
        this.yOffset = 0.0F;
        float f = 0.4F;
        Vec3 vec = Vec3.createVectorHelper(target.posX - x, 1.0D, target.posZ - z).normalize();
        double d = Math.sqrt(Math.pow(target.posX - x, 2.0D) + Math.pow(target.posZ - z, 2.0D)) / 44.0D;
        this.motionX = vec.xCoord * 0.0165D * d;
        this.motionY = 0.0175D;
        this.motionZ = vec.zCoord * 0.0165D * d;
        setThrowableHeading(this.motionX, this.motionY, this.motionZ, func_70182_d(), 1.0F);
    }


    @Override
    protected void onImpact(MovingObjectPosition p_70184_1_) {

        if (!this.worldObj.isRemote) {

            float explosionSize =  2.5f;

            if (ModCommonProxy.opMobs) {
                explosionSize += 1.9f;
            }

            List<EntityBossDragon> list = this.worldObj.getEntitiesWithinAABB(EntityBossDragon.class, this.boundingBox.expand(256, 256, 256));

            for (int i = 0; i < list.size(); i++) {
                EntityBossDragon dragon = list.get(i);

                if (dragon.isAngry()) {
                    explosionSize += 1.2f;
                }
            }

            this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, explosionSize, false, false);

            setDead();

        }

    }

    @Override
    protected float getGravityVelocity() {
        return 0.07F;
    }

    @Override
    public boolean isBurning() {
        return false;
    }
}
