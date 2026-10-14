package com.jerios.evilMinecraftFixes.content.evilMinecraftMod;

import com.jerios.evilMinecraftFixes.content.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.Random;

public class BlockQuicksand extends BlockBase {


    public BlockQuicksand() {
        super(Material.sand, "quickSand", "evilmc:quicksand", 1, CreativeTabs.tabDecorations);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, int x, int y, int z, Entity entityIn) {
        super.onEntityCollidedWithBlock(worldIn, x, y, z, entityIn);
        if (entityIn instanceof EntityLivingBase) {
            EntityLivingBase elb = (EntityLivingBase) entityIn;
            elb.motionY -= 0.7;
            if (!worldIn.isRemote) {
                elb.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120, 1));
            }
        }
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
