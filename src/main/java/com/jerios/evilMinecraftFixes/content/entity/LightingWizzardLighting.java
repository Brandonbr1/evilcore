package com.jerios.evilMinecraftFixes.content.entity;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class LightingWizzardLighting extends EntityLightningBolt {
    public LightingWizzardLighting(World p_i1703_1_, double p_i1703_2_, double p_i1703_4_, double p_i1703_6_) {
        super(p_i1703_1_, p_i1703_2_, p_i1703_4_, p_i1703_6_);
    }


    @Override
    public void setDead() {
        super.setDead();

        worldObj.newExplosion(this, posX, posY, posZ, 1.2f, true, true);

        double d0 = 3.0D;
        List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getBoundingBox(this.posX - d0, this.posY - d0, this.posZ - d0, this.posX + d0, this.posY + 6.0D + d0, this.posZ + d0));

        for (int l = 0; l < list.size(); ++l)
        {
            Entity entity = (Entity)list.get(l);

            if (entity instanceof EntityLiving) {
                EntityLiving living = (EntityLiving) entity;

                living.addPotionEffect(new PotionEffect(Potion.blindness.id, 150, 0));
                living.addPotionEffect(new PotionEffect(Potion.confusion.id, 150, 0));
                living.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 150, 0));
                living.addPotionEffect(new PotionEffect(Potion.weakness.id, 150, 0));
            }
        }
    }
}
