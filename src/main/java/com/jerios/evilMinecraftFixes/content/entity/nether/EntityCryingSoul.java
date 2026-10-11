package com.jerios.evilMinecraftFixes.content.entity.nether;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class EntityCryingSoul extends EntityMob {

    int armor = 0;

    public EntityCryingSoul(World p_i1738_1_) {
        super(p_i1738_1_);
        this.isImmuneToFire = true;
    }

    @Override
    public int getTotalArmorValue() {
        if (armor > 15) {
            armor = 15;
        }
        return armor;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.55D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.4D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 2; k++) {
                    worldObj.spawnParticle("flame", posX + i, posY +k, posZ + i, 0.2, 0, 0.4);
                    worldObj.spawnParticle("flame", posX + i, posY+k, posZ - i, 0.7, 0, 0.63);
                    worldObj.spawnParticle("flame", posX - i, posY+k, posZ + i, 0.5, 0.6, 0.6);
                    worldObj.spawnParticle("flame", posX - i, posY+j, posZ - i, 0.8, 0.8, 0.3);
                }

            }
        }

        if (ticksExisted % 240 == 0) {
            heal(1);
        }

        int x = MathHelper.floor_double(posX);
        int y = MathHelper.floor_double(posY);
        int z = MathHelper.floor_double(posZ);

        if (worldObj.getBlock(x,y,z) == Blocks.soul_sand) {
            heal(1);
        }

        if (worldObj.getBlock(x,y - 1,z) == Blocks.soul_sand) {
            heal(1);
        }

    }

    @Override
    protected void onDeathUpdate()
    {
        ++this.deathTime;


        if (deathTime > 60)
        {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    worldObj.spawnParticle("mobSpell", posX + i, posY, posZ + i,0,0,0);
                    worldObj.spawnParticle("mobSpell", posX + i, posY, posZ - i,0,0,0);
                    worldObj.spawnParticle("mobSpell", posX - i, posY, posZ + i,0,0,0);
                    worldObj.spawnParticle("mobSpell", posX - i, posY, posZ - i,0,0,0);
                    if (!worldObj.isRemote) {
                        AxisAlignedBB bb = this.boundingBox.expand(4, 4, 4);

                      List<Entity> entityList= this.worldObj.getEntitiesWithinAABBExcludingEntity(this, bb);

                        for (int k = 0; k < entityList.size(); k++) {
                            Entity e = entityList.get(k);

                            if (e instanceof EntityLivingBase) {
                                EntityLivingBase living = (EntityLivingBase) e;

                                living.addPotionEffect(new PotionEffect(Potion.confusion.id, 120, 1));
                                living.addPotionEffect(new PotionEffect(Potion.wither.id, 220, 1));
                                living.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 240, 1));
                                living.setFire(8 + worldObj.difficultySetting.getDifficultyId());
                            }
                        }
                    }
                }

            }

        }

        if (this.deathTime == 80)
        {
            int i;

            if (!this.worldObj.isRemote && (this.recentlyHit > 0 || this.isPlayer()) && this.func_146066_aG() && this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot"))
            {
                i = this.getExperiencePoints(this.attackingPlayer);

                while (i > 0)
                {
                    int j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
                }
            }

            this.setDead();

            for (i = 0; i < 20; ++i)
            {
                double d2 = this.rand.nextGaussian() * 0.02D;
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                this.worldObj.spawnParticle("explode", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d2, d0, d1);
            }
        }
    }


    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "mob.ghast.moan";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.ghast.scream";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.ghast.death";
    }

    @Override
    public boolean attackEntityAsMob(Entity ent) {
        if (ent instanceof EntityLivingBase) {
            EntityLivingBase living = (EntityLivingBase) ent;
            living.addPotionEffect(new PotionEffect(Potion.blindness.id, 300, 1));
        }

        if (getHealth() < 20) {
            heal(3);
        }

        double knock = this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue();

        if (knock < 2 ) {
            this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(knock + 0.1);
        }

        double s =this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();

        if (s < 0.85) {
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(s + 0.1);
        }

        double ad = this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();

        if (ad < 5) {
            this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(ad + 1);
        }

        double hp = this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue();
        if (hp < 150) {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(hp + 1);
        } else {
            armor++;

            if (armor > 15) {
                armor = 15;
            }
        }

        return super.attackEntityAsMob(ent);

    }

    // the more health you have, the weaker it gets... The less you have the stronger it gets.....
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
       Entity ent = source.getEntity();

       if (ent instanceof EntityLiving) {
           EntityLiving living = (EntityLiving) ent;

          float max = living.getMaxHealth();
          float current = living.getHealth();

          float curr = (max - current) * 0.5f;

           amount -= curr;
       }

       double knock = this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue();

       if (knock < 2 ) {
           this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(knock + 0.1);
       }

        double s =this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();

       if (s < 0.85) {
           this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(s + 0.1);
       }

        double ad = this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();

       if (ad < 5) {
           this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(ad + 1);
       }

        double hp = this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue();
       if ( hp < 150) {
           this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(hp + 1);
       } else {
           armor++;

           if (armor > 15) {
               armor = 15;
           }
       }

       if (source.isProjectile()) {
           amount = amount * 0.1f;
       }

        return super.attackEntityFrom(source, amount);
    }


    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund) {
        super.readEntityFromNBT(tagCompund);

        armor = tagCompund.getInteger("MobArmorFactor");

    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);

        tagCompound.setInteger("MobArmorFactor", armor);
    }
}
