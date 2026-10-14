package com.jerios.evilMinecraftFixes.content;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockContainerBase extends BlockContainer {
    public BlockContainerBase(Material materialIn, String blockName, String textureName, float hardness, CreativeTabs tabs) {
        super(materialIn);
        setBlockName(blockName);
        setHardness(hardness);
        setBlockTextureName(textureName);
        setCreativeTab(tabs);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        throw new RuntimeException("EXTEND BRUH");
    }
}
