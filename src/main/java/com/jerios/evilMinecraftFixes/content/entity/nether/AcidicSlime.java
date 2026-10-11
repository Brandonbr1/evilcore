package com.jerios.evilMinecraftFixes.content.entity.nether;

import com.superdextor.dextersnether.NetherDamageSource;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class AcidicSlime extends EntitySlime {
    public AcidicSlime(World p_i1742_1_) {
        super(p_i1742_1_);
        this.isImmuneToFire = true;
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn)
    {
        if (this.canDamagePlayer())
        {
            int i = this.getSlimeSize();

            if (this.canEntityBeSeen(entityIn) && this.getDistanceSqToEntity(entityIn) < 0.6D * (double)i * 0.6D * (double)i && entityIn.attackEntityFrom(NetherDamageSource.acid, (float)this.getAttackStrength()))
            {
                this.playSound("random.fizz", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            }
        }
    }

    @Override
    public int getTotalArmorValue()
    {
        return this.getSlimeSize() * 3;
    }

    @Override
    protected String getSlimeParticle()
    {
        return "flame";
    }

    protected int getAttackStrength()
    {
        return super.getAttackStrength() + 2;
    }

    @Override
    protected EntitySlime createInstance()
    {
        return new AcidicSlime(this.worldObj);
    }

    @Override
    protected void alterSquishAmount()
    {
        this.squishAmount *= 0.9F;
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
    }

    protected boolean canDamagePlayer()
    {
        return true;
    }

    @Override
    protected void fall(float distance) {

    }


    @Override
    public boolean handleLavaMovement()
    {
        return false;
    }

    @Override
    protected boolean makesSoundOnLand()
    {
        return true;
    }

    /**
     * Causes this entity to do an upwards motion (jumping).
     */
    @Override
    protected void jump()
    {
        this.motionY = (double)(0.42F + (float)this.getSlimeSize() * 0.1F);
        this.isAirBorne = true;
        net.minecraftforge.common.ForgeHooks.onLivingJump(this);
    }

    @Override
    protected String getJumpSound()
    {
        return "random.fizz";
    }

    @Override
    protected int getJumpDelay()
    {
        return 40 + this.rand.nextInt(10);
    }

}
