package com.jerios.evilMinecraftFixes.content;

import com.jerios.evilMinecraftFixes.content.tile.BlockUnlockerAnvil;
import com.jerios.evilMinecraftFixes.hee.ItemEndermenRelicJerios;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContentRegistry {

    public static Item starFragment;

    public static Item sharpness6;
    public static Item sharpness7;
    public static Item sharpness8;
    public static Item sharpness9;
    public static Item sharpness10;

    public static Item knockback10;

    public static Item prot6;
    public static Item prot7;
    public static Item prot8;
    public static Item prot9;
    public static Item prot10;

    public static Block unlocker;

    public static Item baseBook;

    public static Item endermenRelicSpawner;

    public static void register() {
        starFragment = new ItemNetherStarFragment().setCreativeTab(CreativeTabs.tabRedstone);
        registerItm(starFragment, "Stat Fragment");

        baseBook = new BaseDiamondBook();
        registerItm(baseBook, "Base Diamond Book");

        sharpness6 = new DiamondEnchantmentBook("Sharpness", Enchantment.sharpness.effectId, 6);
        registerItm(sharpness6, "Sharpness 6");


        sharpness7 = new DiamondEnchantmentBook("Sharpness",Enchantment.sharpness.effectId, 7);
        registerItm(sharpness7, "Sharpness 7");
        reci(sharpness6, sharpness7);


        sharpness8 = new DiamondEnchantmentBook("Sharpness",Enchantment.sharpness.effectId, 8);
        registerItm(sharpness8, "Sharpness 8");
        reci(sharpness7, sharpness8);


        sharpness9 = new DiamondEnchantmentBook("Sharpness",Enchantment.sharpness.effectId, 9);
        registerItm(sharpness9, "Sharpness 9");

        reci(sharpness8, sharpness9);

        sharpness10 = new DiamondEnchantmentBook("Sharpness",Enchantment.sharpness.effectId, 10);
        registerItm(sharpness10, "Sharpness 10");

        reci(sharpness9, sharpness10);

        knockback10 = new DiamondEnchantmentBook("Knockback",Enchantment.knockback.effectId, 10);
        registerItm(knockback10, "Knockback 10");



        prot6 = new DiamondEnchantmentBook("Protection",Enchantment.protection.effectId, 6);
        registerItm(prot6, "Prot 6");

        reci(prot6, prot6);

        prot7 = new DiamondEnchantmentBook("Protection",Enchantment.protection.effectId, 7);
        registerItm(prot7, "Prot 7");

        reci(prot6, prot7);

        prot8 = new DiamondEnchantmentBook("Protection",Enchantment.protection.effectId, 8);
        registerItm(prot8, "Prot 8");
        reci(prot7, prot8);

        prot9 = new DiamondEnchantmentBook("Protection",Enchantment.protection.effectId, 9);
        registerItm(prot9, "Prot 9");
        reci(prot8, prot9);


        prot10 = new DiamondEnchantmentBook("Protection",Enchantment.protection.effectId, 10);
        registerItm(prot10, "Prot 10");
        reci(prot9, prot10);


        endermenRelicSpawner = new ItemEndermenRelicJerios();
        registerItm(endermenRelicSpawner, "Endemen Deamon Relic Spawner");



        unlocker = new BlockUnlockerAnvil();
        GameRegistry.registerBlock(unlocker, "Unlocker Anvil");

        GameRegistry.addShapelessRecipe(new ItemStack(Items.nether_star), starFragment, starFragment, starFragment, starFragment);
    }

    public static void reci(Item prevI, Item currI) {
        GameRegistry.addShapedRecipe(new ItemStack(currI), new Object[]{"OOO", "OOO", "OOO", 'O', prevI});
    }

    private static void registerItm(Item i , String s) {
        GameRegistry.registerItem(i, s);
    }

}
