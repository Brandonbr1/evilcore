package com.jerios.evilMinecraftFixes.evilOres;

import com.jerios.evilMinecraftFixes.evilOres.mob.EntityGlowstone;
import com.jerios.evilMinecraftFixes.evilOres.mob.EntityGoldNugget;
import com.jerios.evilMinecraftFixes.evilOres.mob.EntityNetheriteOre;
import com.jerios.evilMinecraftFixes.evilOres.block.EvilBlockFakeOres;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.elias.fakeores.common.FakeOres;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class OresInteg {
    public static Block fakeNetherrite;
    public static Block fakeGlowstone;
    public static Block fakeNetherGold;
    public static void init(FMLInitializationEvent event) {
        fakeNetherrite = new EvilBlockFakeOres().setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypePiston).setBlockTextureName("dextersnether:netherite_ore").setBlockName("netherite_ore").setCreativeTab(CreativeTabs.tabBrewing);
        GameRegistry.registerBlock(fakeNetherrite, "Fake Netherite");

        fakeGlowstone = new EvilBlockFakeOres().setHardness(0.3F).setStepSound(Block.soundTypeGlass).setLightLevel(1.0F).setBlockName("lightgem").setBlockTextureName("glowstone").setCreativeTab(CreativeTabs.tabBrewing);
        GameRegistry.registerBlock(fakeGlowstone, "Fake Glowstone");

        fakeNetherGold = new EvilBlockFakeOres().setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypePiston).setBlockTextureName("dextersnether:gold_ore_nether").setBlockName("gold_ore_nether").setCreativeTab(CreativeTabs.tabBrewing);
        GameRegistry.registerBlock(fakeNetherGold, "Fake Gold Nether");

        EntityRegistry.registerModEntity(EntityNetheriteOre.class, "fakeNetherite", 548554, FakeOres.instance, 40, 1, true);
        EntityRegistry.registerModEntity(EntityGlowstone.class, "fakeGlowstone", 974, FakeOres.instance, 40, 1, true);
        EntityRegistry.registerModEntity(EntityGoldNugget.class, "fakeNetherGold", 674, FakeOres.instance, 40, 1, true);

    }

}
