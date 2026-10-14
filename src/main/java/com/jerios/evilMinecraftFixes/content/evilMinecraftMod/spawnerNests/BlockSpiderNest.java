package com.jerios.evilMinecraftFixes.content.evilMinecraftMod.spawnerNests;

import com.jerios.evilMinecraftFixes.content.BlockContainerBase;
import com.jerios.evilMinecraftFixes.content.evilMinecraftMod.tile.TileEntitySpiderNest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockWeb;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import toast.specialMobs.entity.spider.EntityBabySpider;

import java.util.Random;

public class BlockSpiderNest extends BlockContainerBase {

    public BlockSpiderNest() {
        super(Material.web, "SpiderNest", "evilmc:spiderNest", 1000f, CreativeTabs.tabDecorations);
    }


    @Override
    public void onBlockDestroyedByPlayer(World worldIn, int x, int y, int z, int meta) {
        super.onBlockDestroyedByPlayer(worldIn, x, y, z, meta);

        if (!worldIn.isRemote)
        {
            EntityBabySpider spider = new EntityBabySpider(worldIn);
            spider.setLocationAndAngles(x,y,z,5,1);
            worldIn.spawnEntityInWorld(spider);
        }

    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntitySpiderNest();
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, int x, int y, int z, Entity entityIn)
    {
        entityIn.setInWeb();
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World worldIn, int x, int y, int z)
    {
        return null;
    }

    @Override
    public int getRenderType()
    {
        return 1;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune)
    {
        return Items.string;
    }

    @Override
    protected boolean canSilkHarvest()
    {
        return true;
    }
}
