package com.jerios.evilMinecraftFixes.CQAdditions;

import com.chocolate.chocolateQuest.ChocolateQuest;
import com.superdextor.dextersnether.init.NetherItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CQInteg {

    public static Item goldFeather;

    public static void register() {
        goldFeather = new ItemGoldenFeathers();
        GameRegistry.registerItem(goldFeather, "Golden Feathers");
        GameRegistry.addShapedRecipe(new ItemStack(goldFeather), "GIG", "IFI", "GIG", 'G', Blocks.gold_block, 'I', Items.gold_ingot, 'F', Items.feather);
        GameRegistry.addShapedRecipe(new ItemStack(ChocolateQuest.cloudBoots) ,"FDF", "FBF", "QQQ", 'F', goldFeather, 'D', Items.diamond_boots, 'Q', Blocks.quartz_block, 'B', NetherItems.quartz_boots);
    }

}
