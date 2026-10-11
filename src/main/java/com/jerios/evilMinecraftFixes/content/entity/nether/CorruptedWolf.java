package com.jerios.evilMinecraftFixes.content.entity.nether;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class CorruptedWolf extends EntityMob {
    public CorruptedWolf(World p_i1738_1_) {
        super(p_i1738_1_);
        this.isImmuneToFire = true;
        this.getNavigator().setAvoidsWater(true);
        setSize(1.5f, 1.5f);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.80000001192092896D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
    }

    @Override
    protected String getLivingSound()
    {
        return "mob.wolf.growl";
    }

    @Override
    protected String getHurtSound()
    {
        return "mob.wolf.hurt";
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.wolf.death";
    }

    @Override
    protected float getSoundVolume()
    {
        return 1.0F;
    }


    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!worldObj.isRemote) {
            if(getHealth() < 20) {
                addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 900, 1));
                attackTime-=20;
            }


            int x = MathHelper.floor_double(posX);
            int y = MathHelper.floor_double(posY);
            int z = MathHelper.floor_double(posZ);

            if (worldObj.getBlock(x, y, z) == Blocks.air) {
                worldObj.setBlock(x,y,z,Blocks.fire);
            }
        }

    }

    @Override
    protected void updateEntityActionState() {
        super.updateEntityActionState();
        if (!worldObj.isRemote) {

            AxisAlignedBB bb = boundingBox.expand(32, 32, 32);

            List<CorruptedWolf> mobs = worldObj.getEntitiesWithinAABB(CorruptedWolf.class, bb);

            if (mobs.size() > 3) {
                addPotionEffect(new PotionEffect(Potion.damageBoost.id, 800, 1));
            }
        }

    }


    @Override
    public boolean attackEntityAsMob(Entity p_70652_1_) {

        if (!worldObj.isRemote) {
            if (p_70652_1_ instanceof EntityLivingBase) {
                EntityLivingBase living = (EntityLivingBase) p_70652_1_;
                living.hurtTime-=20;
                setOnFireFromLava(living);
            }
        }
        return super.attackEntityAsMob(p_70652_1_);
    }

    protected void setOnFireFromLava(EntityLivingBase entityLivingBase)
    {
        if (!entityLivingBase.isImmuneToFire())
        {
            entityLivingBase.attackEntityFrom(DamageSource.lava, 4.0F);
            entityLivingBase.setFire(15);
        }
    }

}
