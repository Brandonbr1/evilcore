package com.jerios.evilMinecraftFixes;

import com.jerios.evilMinecraftFixes.evilOres.RenderEvilOres;
import com.jerios.evilMinecraftFixes.evilOres.mob.EntityGlowstone;
import com.jerios.evilMinecraftFixes.evilOres.mob.EntityGoldNugget;
import com.jerios.evilMinecraftFixes.evilOres.mob.EntityNetheriteOre;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class Isolated {


    public static void isolated() {
        RenderingRegistry.registerEntityRenderingHandler(EntityNetheriteOre.class, new RenderEvilOres(new fr.elias.fakeores.client.ModelOre()));
        RenderingRegistry.registerEntityRenderingHandler(EntityGoldNugget.class, new RenderEvilOres(new fr.elias.fakeores.client.ModelOre()));
        RenderingRegistry.registerEntityRenderingHandler(EntityGlowstone.class, new RenderEvilOres(new fr.elias.fakeores.client.ModelOre()));
    }
}
