package com.jerios.evilMinecraftFixes.evilerRecipiesManager;

import chylex.hee.init.BlockList;
import com.jerios.evilMinecraftFixes.content.ContentRegistry;
import com.rwtema.extrautils.ExtraUtils;
import com.superdextor.dextersnether.init.NetherItems;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.elias.fakeores.common.FakeOres;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class EvilerRecipiesManager {

    public static void add()
    {
        GameRegistry.addShapedRecipe(new ItemStack(FakeOres.fd_rainbow_block), "RGE", "DAM", "BLP", 'R', Blocks.redstone_block, 'G', Blocks.gold_block, 'E', Blocks.emerald_block, 'D', FakeOres.fd_daren_block, 'A', FakeOres.antiOreStone, 'M', Blocks.diamond_block, 'B', FakeOres.fd_blurk_block, 'L', Blocks.lapis_block, 'P', FakeOres.fd_purlight_block);
        GameRegistry.addShapedRecipe(new ItemStack(ExtraUtils.creativeBuildersWand), "OOB", "OWO", "BOO", 'O', BlockList.obsidian_special_glow, 'B', Items.blaze_powder, 'W', ExtraUtils.buildersWand);
        GameRegistry.addShapelessRecipe(new ItemStack(FakeOres.fd_rainbow_block), NetherItems.darkcore,NetherItems.darkcore,NetherItems.darkcore,NetherItems.darkcore,NetherItems.darkcore,NetherItems.darkcore,NetherItems.darkcore,NetherItems.darkcore,NetherItems.darkcore);
        GameRegistry.addShapelessRecipe(new ItemStack(ExtraUtils.unstableIngot, 1), new ItemStack(ExtraUtils.decorative1, 1,5));
        GameRegistry.addShapedRecipe(new ItemStack(ExtraUtils.decorative1, 1, 8), "GEG", "EBE", "GEG", 'G', Blocks.gold_block, 'E', Items.enchanted_book, 'B', Blocks.bookshelf);
        GameRegistry.addShapedRecipe(new ItemStack(ContentRegistry.unlocker, 1), "III","INI", "III", 'I', ExtraUtils.unstableIngot, 'N', Items.nether_star);

        //  GameRegistry.addShapedRecipe(new ItemStack(ContentRegistry.unlocker, 1), "UUU", "UNU", "UUU", 'U', ItemList.essence, 'N', Items.nether_star);
    }



}
