package com.jerios.evilMinecraftFixes.athena;

import com.jerios.evilMinecraftFixes.Evil;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.EntityEvent;

public class WitherProps implements IExtendedEntityProperties {
    public int ticks = 0;
    public boolean exploded = false;

    public boolean immuneToExplosions;
    public boolean forcePinchMode;
    public boolean witherIsDesperate;

    public static final String PROP_NAME = Evil.MODID + "_WITHER_BEDROCK_DATA";

    public static void register() {
        MinecraftForge.EVENT_BUS.register(new Handler());
    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {
        NBTTagCompound propertyData = new NBTTagCompound();
        propertyData.setInteger("tickEvil", this.ticks);
        propertyData.setBoolean("explodedEvil", this.exploded);
        propertyData.setBoolean("immuneTOExplsoionsEvil", immuneToExplosions);
        propertyData.setBoolean("EvilForcePinch", forcePinchMode);
        propertyData.setBoolean("EvilwitherIsDesperate", witherIsDesperate);

        // Write data to propertyData

        compound.setTag(PROP_NAME, propertyData);

    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        if(compound.hasKey(PROP_NAME, Constants.NBT.TAG_COMPOUND)) {
            NBTTagCompound propertyData = compound.getCompoundTag(PROP_NAME);

            propertyData.getInteger("tickEvil");
            propertyData.getBoolean("tickEvil");
            propertyData.getBoolean("immuneTOExplsoionsEvil");
            propertyData.getBoolean("EvilForcePinch");
            propertyData.getBoolean("EvilwitherIsDesperate");
        }

    }

    @Override
    public void init(Entity entity, World world) {

    }

    public static WitherProps get(Entity p) {
        return (WitherProps) p.getExtendedProperties(PROP_NAME);
    }

    // IExtendedEntityProperties methods go here

    public static class Handler {

        @SubscribeEvent
        public void entityConstruct(EntityEvent.EntityConstructing e) {
            if (e.entity instanceof EntityWither) {
                if (e.entity.getExtendedProperties(PROP_NAME) == null) {
                    e.entity.registerExtendedProperties(PROP_NAME, new WitherProps());
                }

            }


            if (e.entity instanceof EntitySkeleton) {
                if (e.entity.getExtendedProperties(PROP_NAME) == null) {
                    e.entity.registerExtendedProperties(PROP_NAME, new WitherProps());
                }
            }
        }
    }


}
