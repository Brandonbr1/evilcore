package com.jerios.evilMinecraftFixes.content.evilMinecraftMod;

import net.minecraft.block.BlockCactus;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class BlockNetherCactus extends BlockCactus {


    @Override
    public void onEntityCollidedWithBlock(World worldIn, int x, int y, int z, Entity entityIn) {
        super.onEntityCollidedWithBlock(worldIn, x, y, z, entityIn);
        if (!worldIn.isRemote) {
            if (entityIn instanceof EntityLivingBase) {
                EntityLivingBase elb = (EntityLivingBase) entityIn;
                elb.addPotionEffect(new PotionEffect(Potion.wither.id, 120, 1));
            }
        }
    }
}
