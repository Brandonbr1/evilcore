package com.jerios.evilMinecraftFixes.packet;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class NetworkHandler {

    public static SimpleNetworkWrapper wrapper;

    public static void init() {
        wrapper = NetworkRegistry.INSTANCE.newSimpleChannel("Evil_MC_Core");
       // from client to server
        wrapper.registerMessage(PacketHunger.Handler.class, PacketHunger.class, 0, Side.SERVER);
    }

}
