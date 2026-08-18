package com.jerios.evilMinecraftFixes;

import com.jerios.evilMinecraftFixes.hee.ItemEndermenRelicJerios;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class EnderDeamonIsolator {

    public static Item endermenRelicSpawner;


    public static void init() {
        endermenRelicSpawner = new ItemEndermenRelicJerios();
        registerItm(endermenRelicSpawner, "Endemen Deamon Relic Spawner");

    }

    private static void registerItm(Item i , String s) {
        GameRegistry.registerItem(i, s);
    }

}
