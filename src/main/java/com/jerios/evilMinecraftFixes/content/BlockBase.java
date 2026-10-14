package com.jerios.evilMinecraftFixes.content;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockBase extends Block {
    public BlockBase(Material materialIn, String blockName, String textureName, float hardness, CreativeTabs tabs) {
        super(materialIn);
        setBlockName(blockName);
        setHardness(hardness);
        setBlockTextureName(textureName);
        setCreativeTab(tabs);
    }
}
