package com.jerios.evilMinecraftFixes.content.tile.charger;

import com.jerios.evilMinecraftFixes.Evil;
import com.jerios.evilMinecraftFixes.content.BlockBase;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;

public class BlockRingCharger extends BlockContainer {
    public BlockRingCharger() {
        super(Material.tnt);
        setBlockName("ringCharger");
        setHardness(5.0f);
        setBlockTextureName("evilmc:ringCharger");
        setCreativeTab(CreativeTabs.tabDecorations);
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
          //  TileEntityCharger tileentityfurnace = (TileEntityCharger)worldIn.getTileEntity(x, y, z);

          //  if (tileentityfurnace != null)
           // {
            System.out.println("TEST3");
                player.openGui(Evil.INSTANCE, 23, worldIn, x,y,z);
         //   }

            return true;
        }
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityCharger();
    }
}
