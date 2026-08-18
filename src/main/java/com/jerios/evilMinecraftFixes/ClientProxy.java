package com.jerios.evilMinecraftFixes;

import com.jerios.evilMinecraftFixes.cfg.ConfigASM;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        if (Loader.isModLoaded("fakeores")) {
            if (ConfigASM.ORE) {
                Isolated.isolated();
            }
        }

    }




}
