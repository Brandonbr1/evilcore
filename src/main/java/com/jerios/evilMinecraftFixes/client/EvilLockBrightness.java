package com.jerios.evilMinecraftFixes.client;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderWorldLastEvent;

public class EvilLockBrightness {


    @SubscribeEvent
    public void renderLastWorld(RenderWorldLastEvent event) {
        Minecraft mc = FMLClientHandler.instance().getClient();

        if (mc != null) {
            mc.gameSettings.gammaSetting = 0;
        }
    }

}
