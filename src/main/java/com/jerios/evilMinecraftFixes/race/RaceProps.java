package com.jerios.evilMinecraftFixes.race;

import com.jerios.evilMinecraftFixes.Evil;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class RaceProps implements IExtendedEntityProperties {


    public RaceProps() {
        MinecraftForge.EVENT_BUS.register(new RaceProps.Handler());
    }

    public String raceName = "";

    public static final String PROP_NAME = Evil.MODID + "PlayerRace";

    public static RaceProps get(Entity p) {
        return (RaceProps) p.getExtendedProperties(PROP_NAME);
    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {
        NBTTagCompound propertyData = new NBTTagCompound();

        propertyData.setString("RaceID", raceName);

        compound.setTag(PROP_NAME, propertyData);


    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {

        if(compound.hasKey(PROP_NAME, Constants.NBT.TAG_COMPOUND)) {
            NBTTagCompound propertyData = compound.getCompoundTag(PROP_NAME);

            raceName = propertyData.getString("RaceID");

        }

    }

    @Override
    public void init(Entity entity, World world)
    {

    }


    public static class Handler{

        @SubscribeEvent
        public void entityConstruct(EntityEvent.EntityConstructing e) {
            if (e.entity instanceof EntityPlayer) {
                if (e.entity.getExtendedProperties(PROP_NAME) == null) {
                    e.entity.registerExtendedProperties(PROP_NAME, new RaceProps());
                }
            }
        }




        @SubscribeEvent
        public void onClonePlayer(PlayerEvent.Clone e) {
                NBTTagCompound compound = new NBTTagCompound();
                RaceProps.get(e.original).saveNBTData(compound);
                RaceProps.get(e.entityPlayer).loadNBTData(compound);

        }



    }

}
