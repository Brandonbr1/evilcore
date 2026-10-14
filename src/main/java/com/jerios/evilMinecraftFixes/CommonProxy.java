package com.jerios.evilMinecraftFixes;

import com.jerios.evilMinecraftFixes.content.ContentRegistry;
import com.jerios.evilMinecraftFixes.content.tile.GuiRepairUnlocker;
import com.jerios.evilMinecraftFixes.hostileWorlds.dim.TeleporterHW;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(Evil.INSTANCE, new GuiHandler());
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {}

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {}

    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        MinecraftServer server = event.getServer();
        WorldServer worldServer = server.worldServerForDimension(ContentRegistry.HW_ID);
        worldServer.customTeleporters.add(new TeleporterHW(worldServer));

      //  MinecraftServer minecraftserver = MinecraftServer.getServer();

    }


}
