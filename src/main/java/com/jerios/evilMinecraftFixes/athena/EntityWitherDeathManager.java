package com.jerios.evilMinecraftFixes.athena;

import com.jerios.evilMinecraftFixes.Evil;
import net.minecraft.entity.*;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class EntityWitherDeathManager extends Entity {
    public EntityWitherDeathManager(World worldIn) {
        super(worldIn);
    }
    int ticks = 0;

    // spawns on player, and gives custom new name now!
    @Override
    public void onUpdate() {
        super.onUpdate();
      //  EntityPlayer gloabalPlayer = null;

        List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(64, 8, 64));

        for(int i = 0; i < list.size(); i++) {
            Entity entity = (Entity) list.get(i);


            if (entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entity;
             //   gloabalPlayer = player;
                double dx = this.posX - player.posX;
                double dy = this.posY - player.posY;
                double dz = this.posZ - player.posZ;
                double dir = MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);
                double pull = 0.48D;
                player.motionX += dx / dir * pull * 0.10D;
                player.motionY += dy / dir * pull * 0.10D;
                player.motionZ += dz / dir * pull * 0.10D;
            }

            if (entity instanceof EntityEnderPearl) {
                EntityEnderPearl player = (EntityEnderPearl) entity;
                double dx = this.posX - player.posX;
                double dy = this.posY - player.posY;
                double dz = this.posZ - player.posZ;
                double dir = MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);
                double pull = 2.4D;
                player.motionX += dx / dir * pull * 0.08D;
                player.motionY += dy / dir * pull * 0.08D;
                player.motionZ += dz / dir * pull * 0.08D;
            }


        }



        if (!this.worldObj.isRemote) {
            ticks++;
            if (ticks == 120) {
                for (int i = 0; i < 10; i++) {
                    this.worldObj.newExplosion(this, this.posX - i, this.posY - i, this.posZ - i, 8.0F, true,true);
                    this.worldObj.newExplosion(this, this.posX + i, this.posY + i, this.posZ + i, 8.0F,true, true);
                }

            }
            if (ticks == 135) {
                for (int i = 0; i < 15; i++) {
              //   if (gloabalPlayer != null) {
               //      Evil.spawnSkeketonMinionNearPlayer(new EntitySkeleton(worldObj), worldObj, gloabalPlayer, null);
               //  } else {
                     Evil.spawnSkeketonMinionNearPlayer(new EntitySkeleton(worldObj), worldObj, null, this);
               //  }

                }
            }
            if (ticks == 140) {
                for (int j = 0; j < 15; j++) {
                  //  if (gloabalPlayer != null) {
                       // Evil.spawnSkeketonMinionNearPlayer(new EntityPigZombie(worldObj), worldObj, gloabalPlayer, null);
                 //   } else {
                        Evil.spawnSkeketonMinionNearPlayer(new EntityPigZombie(worldObj), worldObj, null, this);
                  //  }

                }
            }
            if (ticks == 145) {
                this.setDead();
            }

        }



    }

    private Vec3 findPossibleShelter(EntityCreature theCreature)
    {
        Random random = theCreature.getRNG();

            int j = MathHelper.floor_double(theCreature.posX + (double)random.nextInt(20) - 10.0D);
            int k = MathHelper.floor_double(theCreature.boundingBox.minY + (double)random.nextInt(6) - 3.0D);
            int l = MathHelper.floor_double(theCreature.posZ + (double)random.nextInt(20) - 10.0D);
            return Vec3.createVectorHelper(j, k, l);
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tagCompund) {
        tagCompund.getInteger("TICKS");

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tagCompound) {
        tagCompound.setInteger("TICKS", this.ticks);

    }
}
