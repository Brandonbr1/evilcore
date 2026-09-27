package com.jerios.evilMinecraftFixes.hee;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class JeanFireball extends EntityLargeFireball {
    public JeanFireball(World p_i1759_1_) {
        super(p_i1759_1_);
    }

    public JeanFireball(World p_i1760_1_, double p_i1760_2_, double p_i1760_4_, double p_i1760_6_, double p_i1760_8_, double p_i1760_10_, double p_i1760_12_) {
        super(p_i1760_1_, p_i1760_2_, p_i1760_4_, p_i1760_6_, p_i1760_8_, p_i1760_10_, p_i1760_12_);
    }

    public JeanFireball(World p_i1761_1_, EntityLivingBase p_i1761_2_, double p_i1761_3_, double p_i1761_5_, double p_i1761_7_) {
        super(p_i1761_1_, p_i1761_2_, p_i1761_3_, p_i1761_5_, p_i1761_7_);
    }

    @Override
    protected void onImpact(MovingObjectPosition p_70227_1_) {
        if (!worldObj.isRemote) {
            if (p_70227_1_.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                EntityFireBallJean fireBall = new EntityFireBallJean(worldObj);
                fireBall.setLocationAndAngles(p_70227_1_.blockX, p_70227_1_.blockY, p_70227_1_.blockZ, 0.0F, 0.0F);
                worldObj.spawnEntityInWorld(fireBall);
            }
        }
        super.onImpact(p_70227_1_);

    }
}
