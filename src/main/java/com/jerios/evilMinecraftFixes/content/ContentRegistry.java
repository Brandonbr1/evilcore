package com.jerios.evilMinecraftFixes.content;

import com.jerios.evilMinecraftFixes.content.tile.BlockUnlockerAnvil;
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

    public static Item knockback3;
    public static Item knockback4;
    public static Item knockback5;
    public static Item knockback6;
    public static Item knockback7;
    public static Item knockback8;
    public static Item knockback9;
    public static Item knockback10;

    public static Item u5;
    public static Item u6;
    public static Item u7;
    public static Item u8;
    public static Item u9;
    public static Item u10;

    public static Item prot5;
    public static Item prot6;
    public static Item prot7;
    public static Item prot8;
    public static Item prot9;
    public static Item prot10;

    public static Block unlocker;

    public static Item baseBook;



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


        knockback3 = new DiamondEnchantmentBook("Knockback",Enchantment.knockback.effectId, 3);
        registerItm(knockback3, "Knockback 3");

        knockback4 = new DiamondEnchantmentBook("Knockback",Enchantment.knockback.effectId, 4);
        registerItm(knockback4, "Knockback 4");
        reci(knockback3, knockback4);

        knockback5 = new DiamondEnchantmentBook("Knockback",Enchantment.knockback.effectId, 5);
        registerItm(knockback5, "Knockback 5");

        reci(knockback4, knockback5);

        knockback6 = new DiamondEnchantmentBook("Knockback",Enchantment.knockback.effectId, 6);
        registerItm(knockback6, "Knockback 6");
        reci(knockback5, knockback6);

        knockback7 = new DiamondEnchantmentBook("Knockback",Enchantment.knockback.effectId, 7);
        registerItm(knockback7, "Knockback 7");
        reci(knockback6, knockback7);

        knockback8 = new DiamondEnchantmentBook("Knockback",Enchantment.knockback.effectId, 8);
        registerItm(knockback8, "Knockback 8");
        reci(knockback7, knockback8);

        knockback9 = new DiamondEnchantmentBook("Knockback",Enchantment.knockback.effectId, 9);
        registerItm(knockback9, "Knockback 9");
        reci(knockback8, knockback9);

        knockback10 = new DiamondEnchantmentBook("Knockback",Enchantment.knockback.effectId, 10);
        registerItm(knockback10, "Knockback 10");
        reci(knockback9, knockback10);


        prot5 =  new DiamondEnchantmentBook("Protection",Enchantment.protection.effectId, 5);
        registerItm(prot5, "Prot 5");


        prot6 = new DiamondEnchantmentBook("Protection",Enchantment.protection.effectId, 6);
        registerItm(prot6, "Prot 6");

        reci(prot5, prot6);

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

        u5 = new DiamondEnchantmentBook("Unbreaking",Enchantment.unbreaking.effectId, 5);
        registerItm(u5, "Unbreaking 5");

        u6 = new DiamondEnchantmentBook("Unbreaking",Enchantment.unbreaking.effectId, 6);
        registerItm(u6, "Unbreaking 6");
        reci(u5, u6);

        u7 = new DiamondEnchantmentBook("Unbreaking",Enchantment.unbreaking.effectId, 7);
        registerItm(u7, "Unbreaking 7");
        reci(u6, u7);

        u8 = new DiamondEnchantmentBook("Unbreaking",Enchantment.unbreaking.effectId, 8);
        registerItm(u8, "Unbreaking 8");
        reci(u7, u8);

        u9 = new DiamondEnchantmentBook("Unbreaking",Enchantment.unbreaking.effectId, 9);
        registerItm(u9, "Unbreaking 9");
        reci(u8, u9);

        u10 = new DiamondEnchantmentBook("Unbreaking",Enchantment.unbreaking.effectId, 10);
        registerItm(u10, "Unbreaking 10");
        reci(u9, u10);

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
