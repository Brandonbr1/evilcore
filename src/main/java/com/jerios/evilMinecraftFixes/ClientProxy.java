package com.jerios.evilMinecraftFixes;

import com.jerios.evilMinecraftFixes.cfg.ConfigASM;
import com.jerios.evilMinecraftFixes.client.rendering.RenderAcidicSlime;
import com.jerios.evilMinecraftFixes.client.rendering.RenderCorruptedWolf;
import com.jerios.evilMinecraftFixes.client.rendering.RenderCryingSoul;
import com.jerios.evilMinecraftFixes.content.entity.nether.AcidicSlime;
import com.jerios.evilMinecraftFixes.content.entity.nether.CorruptedWolf;
import com.jerios.evilMinecraftFixes.content.entity.nether.EntityCryingSoul;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.CryptManager;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        registerRender(EntityCryingSoul.class, new RenderCryingSoul());
        registerRender(CorruptedWolf.class, new RenderCorruptedWolf());
        registerRender(AcidicSlime.class, new RenderAcidicSlime());
    }

    private void registerRender(Class<? extends Entity> clazz, Render render){
        RenderingRegistry.registerEntityRenderingHandler(clazz,render);
    }

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
