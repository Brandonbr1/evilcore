package com.jerios.evilMinecraftFixes.pg;

import com.jerios.evilMinecraftFixes.CQAdditions.CQInteg;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import particleman.forge.ParticleMan;

public class PGI {

    public static void re() {
        GameRegistry.addRecipe(new ItemStack(ParticleMan.itemGlove), new Object[]{" LL", "LRR", "LRD", 'L', CQInteg.goldFeather, 'R', Blocks.redstone_block, 'D', Blocks.diamond_block});
    }

}
