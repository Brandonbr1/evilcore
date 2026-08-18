package com.jerios.evilMinecraftFixes.content.tile;

import com.jerios.evilMinecraftFixes.Evil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockFalling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockUnlockerAnvil extends BlockFalling {

    public static final String[] anvilDamageNames = new String[] {"intact", "slightlyDamaged", "veryDamaged"};
    private static final String[] anvilIconNames = new String[] {"anvil_top_damaged_0", "anvil_top_damaged_1", "anvil_top_damaged_2"};
    @SideOnly(Side.CLIENT)
    public int anvilRenderSide;
    @SideOnly(Side.CLIENT)
    private IIcon[] anvilIcons;


    public BlockUnlockerAnvil()
    {
        super();
       // super(Material.anvil);
        this.setLightOpacity(0);
        this.setCreativeTab(CreativeTabs.tabCombat);
        this.setBlockName("anvil_breaker");
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if (this.anvilRenderSide == 3 && side == 1)
        {
            int k = (meta >> 2) % this.anvilIcons.length;
            return this.anvilIcons[k];
        }
        else
        {
            return this.blockIcon;
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        this.blockIcon = reg.registerIcon("anvil_base");
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World worldIn, int x, int y, int z, EntityLivingBase placer, ItemStack itemIn)
    {
        int l = MathHelper.floor_double((double)(placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int i1 = worldIn.getBlockMetadata(x, y, z) >> 2;
        ++l;
        l %= 4;

        if (l == 0)
        {
            worldIn.setBlockMetadataWithNotify(x, y, z, 2 | i1 << 2, 2);
        }

        if (l == 1)
        {
            worldIn.setBlockMetadataWithNotify(x, y, z, 3 | i1 << 2, 2);
        }

        if (l == 2)
        {
            worldIn.setBlockMetadataWithNotify(x, y, z, 0 | i1 << 2, 2);
        }

        if (l == 3)
        {
            worldIn.setBlockMetadataWithNotify(x, y, z, 1 | i1 << 2, 2);
        }
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            player.openGui(Evil.INSTANCE, 90, worldIn,x,y,z);
          //  player.displayGUIAnvil(x, y, z);
            return true;
        }
    }

    /**
     * The type of render function that is called for this block

    public int getRenderType()
    {
        return 35;
    }
     */

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int meta)
    {
        return meta >> 2;
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, int x, int y, int z)
    {
        int l = worldIn.getBlockMetadata(x, y, z) & 3;

        if (l != 3 && l != 1)
        {
            this.setBlockBounds(0.125F, 0.0F, 0.0F, 0.875F, 1.0F, 1.0F);
        }
        else
        {
            this.setBlockBounds(0.0F, 0.0F, 0.125F, 1.0F, 1.0F, 0.875F);
        }
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */

    protected void func_149829_a(EntityFallingBlock p_149829_1_)
    {
        p_149829_1_.func_145806_a(true);
    }

    public void func_149828_a(World p_149828_1_, int p_149828_2_, int p_149828_3_, int p_149828_4_, int p_149828_5_)
    {
        p_149828_1_.playAuxSFX(1022, p_149828_2_, p_149828_3_, p_149828_4_, 0);
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess worldIn, int x, int y, int z, int side)
    {
        return true;
    }
}
