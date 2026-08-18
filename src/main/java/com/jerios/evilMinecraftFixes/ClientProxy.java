package com.jerios.evilMinecraftFixes;

import com.jerios.evilMinecraftFixes.evilOres.RenderEvilOres;
import com.jerios.evilMinecraftFixes.evilOres.mob.EntityGlowstone;
import com.jerios.evilMinecraftFixes.evilOres.mob.EntityGoldNugget;
import com.jerios.evilMinecraftFixes.evilOres.mob.EntityNetheriteOre;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        if (Loader.isModLoaded("fakeores")) {
            RenderingRegistry.registerEntityRenderingHandler(EntityNetheriteOre.class, new RenderEvilOres(new fr.elias.fakeores.client.ModelOre()));
            RenderingRegistry.registerEntityRenderingHandler(EntityGoldNugget.class, new RenderEvilOres(new fr.elias.fakeores.client.ModelOre()));
            RenderingRegistry.registerEntityRenderingHandler(EntityGlowstone.class, new RenderEvilOres(new fr.elias.fakeores.client.ModelOre()));
        }

    }




}
