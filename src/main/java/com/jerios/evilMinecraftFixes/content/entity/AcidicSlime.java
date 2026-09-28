package com.jerios.evilMinecraftFixes.content.entity;

import com.superdextor.dextersnether.NetherDamageSource;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class AcidicSlime extends EntityMagmaCube {
    public AcidicSlime(World p_i1742_1_) {
        super(p_i1742_1_);
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn)
    {
        if (this.canDamagePlayer())
        {
            int i = this.getSlimeSize();

            if (this.canEntityBeSeen(entityIn) && this.getDistanceSqToEntity(entityIn) < 0.6D * (double)i * 0.6D * (double)i && entityIn.attackEntityFrom(NetherDamageSource.acid, (float)this.getAttackStrength()))
            {
                this.playSound("mob.attack", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            }
        }
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
    }

    @Override
    protected String getJumpSound()
    {
        return "random.fizz";
    }

    @Override
    protected int getJumpDelay()
    {
        return this.rand.nextInt(10);
    }

}
