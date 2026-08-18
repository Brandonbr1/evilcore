package com.jerios.evilMinecraftFixes;

import com.jerios.evilMinecraftFixes.content.ContentRegistry;
import com.jerios.evilMinecraftFixes.content.tile.ContainerRepairUnlocker;
import com.jerios.evilMinecraftFixes.content.tile.GuiRepairUnlocker;
import com.jerios.evilMinecraftFixes.evilOres.RenderEvilOres;
import com.jerios.evilMinecraftFixes.evilOres.mob.EntityGlowstone;
import com.jerios.evilMinecraftFixes.evilOres.mob.EntityGoldNugget;
import com.jerios.evilMinecraftFixes.evilOres.mob.EntityNetheriteOre;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import fr.elias.fakeores.client.ModelOre;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy {
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        if (Loader.isModLoaded("dextersnether")) {
            RenderingRegistry.registerEntityRenderingHandler(EntityNetheriteOre.class, new RenderEvilOres(new ModelOre()));
            RenderingRegistry.registerEntityRenderingHandler(EntityGoldNugget.class, new RenderEvilOres(new ModelOre()));
        }
        RenderingRegistry.registerEntityRenderingHandler(EntityGlowstone.class, new RenderEvilOres(new ModelOre()));
    }




}
