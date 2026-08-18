package com.jerios.evilMinecraftFixes;

import com.thetorine.thirstmod.core.network.NetworkHandler;
import com.thetorine.thirstmod.core.network.PacketUpdateClient;
import com.thetorine.thirstmod.core.player.PlayerContainer;
import com.thetorine.thirstmod.core.player.ThirstLogic;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class ThirstEvents {

   @SubscribeEvent
    public void entityConstruct(EntityEvent.EntityConstructing e) {
        if (e.entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.entity;
            if (e.entity.getExtendedProperties(IThirst.PROP) == null) {
                e.entity.registerExtendedProperties(IThirst.PROP, (IExtendedEntityProperties) new PlayerContainer(player, new ThirstLogic(player)));
            }
        }
    }

    @SubscribeEvent
    public void onClonePlayer(PlayerEvent.Clone e) {
        if(e.wasDeath) {
            PlayerContainer.getPlayer(e.original).respawnPlayer();
        }
    }

    @SubscribeEvent
    public void entityJoinWorld(EntityJoinWorldEvent e) {
        if (e.entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.entity;
            PlayerContainer data = PlayerContainer.getPlayer(player);
            if (data != null) {
                if (player instanceof EntityPlayerMP) {
                    EntityPlayerMP playerMP = (EntityPlayerMP) player;
                    NetworkHandler.networkWrapper.sendTo(new PacketUpdateClient(PlayerContainer.getPlayer(playerMP).getStats()), playerMP);
                }
            }
        }
    }



}
