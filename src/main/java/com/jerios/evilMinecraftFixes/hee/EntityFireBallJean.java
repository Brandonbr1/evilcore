package com.jerios.evilMinecraftFixes.hee;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.List;

public class EntityFireBallJean extends Entity {
    public EntityFireBallJean(World worldIn) {
        super(worldIn);
    }

    int timer = 0;


    @Override
    public void onUpdate() {
        super.onUpdate();

        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 4; k++) {
                worldObj.spawnParticle("witchMagic", posX + i, posY , posZ + k, 0, 0, 0);
                worldObj.spawnParticle("witchMagic", posX - i, posY , posZ - k, 0, 0, 0);

                worldObj.spawnParticle("snowballpoof", posX + i, posY , posZ + k, 0, 0, 0);
                worldObj.spawnParticle("snowballpoof", posX - i, posY, posZ - k, 0, 0, 0);
            }

        }


        if (!worldObj.isRemote) {
            timer++;




            List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(4, 2, 4));

            for (int i = 0; i < list.size(); i++) {
                Entity e = list.get(i);

                if (e instanceof EntityLivingBase) {
                    EntityLivingBase living = (EntityLivingBase) e;
                    living.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20, 0));

                    this.worldObj.newExplosion((Entity)null, this.posX, this.posY, this.posZ, (float)1.2f, true, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));

                    if (timer % 20 == 0) {
                        living.attackEntityFrom(DamageSource.magic, 1);
                    }

                }

            }
            if (timer > 120) {
                setDead();
            }
        }
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tagCompund) {
        timer =  tagCompund.getInteger("FireBallTimer");

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tagCompound) {
        tagCompound.setInteger("FireBallTimer", timer);
    }

}
