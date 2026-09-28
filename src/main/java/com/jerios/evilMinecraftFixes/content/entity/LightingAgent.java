package com.jerios.evilMinecraftFixes.content.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class LightingAgent extends EntityLiving {
    int insideAgent;
    public LightingAgent(World p_i1595_1_) {
        super(p_i1595_1_);
    }


    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (!source.isProjectile()) return false;
        return super.attackEntityFrom(source, amount);
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn) {
        insideAgent++;
        if (insideAgent > 20) {
            entityIn.onStruckByLightning(null);
            insideAgent = 0;
        }
        super.onCollideWithPlayer(entityIn);
    }

}
