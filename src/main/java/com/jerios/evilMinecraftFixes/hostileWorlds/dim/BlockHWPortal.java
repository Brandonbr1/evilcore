package com.jerios.evilMinecraftFixes.hostileWorlds.dim;

import com.jerios.evilMinecraftFixes.content.ContentRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHWPortal extends BlockPortal {


    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, int x, int y, int z)
    {
        int l = func_149999_b(worldIn.getBlockMetadata(x, y, z));

        if (l == 0)
        {
            if (worldIn.getBlock(x - 1, y, z) != this && worldIn.getBlock(x + 1, y, z) != this)
            {
                l = 2;
            }
            else
            {
                l = 1;
            }

            if (worldIn instanceof World && !((World)worldIn).isRemote)
            {
                ((World)worldIn).setBlockMetadataWithNotify(x, y, z, l, 2);
            }
        }

        float f = 0.125F;
        float f1 = 0.125F;

        if (l == 1)
        {
            f = 0.5F;
        }

        if (l == 2)
        {
            f1 = 0.5F;
        }

        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess worldIn, int x, int y, int z, int side)
    {
        int i1 = 0;

        if (worldIn.getBlock(x, y, z) == this)
        {
            i1 = func_149999_b(worldIn.getBlockMetadata(x, y, z));

            if (i1 == 0)
            {
                return false;
            }

            if (i1 == 2 && side != 5 && side != 4)
            {
                return false;
            }

            if (i1 == 1 && side != 3 && side != 2)
            {
                return false;
            }
        }

        boolean flag = worldIn.getBlock(x - 1, y, z) == this && worldIn.getBlock(x - 2, y, z) != this;
        boolean flag1 = worldIn.getBlock(x + 1, y, z) == this && worldIn.getBlock(x + 2, y, z) != this;
        boolean flag2 = worldIn.getBlock(x, y, z - 1) == this && worldIn.getBlock(x, y, z - 2) != this;
        boolean flag3 = worldIn.getBlock(x, y, z + 1) == this && worldIn.getBlock(x, y, z + 2) != this;
        boolean flag4 = flag || flag1 || i1 == 1;
        boolean flag5 = flag2 || flag3 || i1 == 2;
        return flag4 && side == 4 ? true : (flag4 && side == 5 ? true : (flag5 && side == 2 ? true : flag5 && side == 3));
    }


    @Override
    public boolean func_150000_e(World p_150000_1_, int p_150000_2_, int p_150000_3_, int p_150000_4_)
    {
        BlockHWPortal.Size size = new BlockHWPortal.Size(p_150000_1_, p_150000_2_, p_150000_3_, p_150000_4_, 1);
        BlockHWPortal.Size size1 = new BlockHWPortal.Size(p_150000_1_, p_150000_2_, p_150000_3_, p_150000_4_, 2);

        if (size.func_150860_b() && size.field_150864_e == 0)
        {
            size.func_150859_c();
            return true;
        }
        else if (size1.func_150860_b() && size1.field_150864_e == 0)
        {
            size1.func_150859_c();
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    @Override
    public void onNeighborBlockChange(World worldIn, int x, int y, int z, Block neighbor)
    {
        int l = func_149999_b(worldIn.getBlockMetadata(x, y, z));
        BlockHWPortal.Size size = new BlockHWPortal.Size(worldIn, x, y, z, 1);
        BlockHWPortal.Size size1 = new BlockHWPortal.Size(worldIn, x, y, z, 2);

        if (l == 1 && (!size.func_150860_b() || size.field_150864_e < size.field_150868_h * size.field_150862_g))
        {
            worldIn.setBlock(x, y, z, Blocks.air);
        }
        else if (l == 2 && (!size1.func_150860_b() || size1.field_150864_e < size1.field_150868_h * size1.field_150862_g))
        {
            worldIn.setBlock(x, y, z, Blocks.air);
        }
        else if (l == 0 && !size.func_150860_b() && !size1.func_150860_b())
        {
            worldIn.setBlock(x, y, z, Blocks.air);
        }
    }



    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    @Override
    public void onEntityCollidedWithBlock(World worldIn, int x, int y, int z, Entity entityIn) {
        if (entityIn.ridingEntity == null && entityIn.riddenByEntity == null && !worldIn.isRemote) {
          //  MinecraftServer server = MinecraftServer.getServer();

                if (entityIn instanceof EntityPlayerMP entityPlayerMP) {
                    if (entityPlayerMP.timeUntilPortal-- <= 10) {
                        if (entityPlayerMP.dimension != ContentRegistry.HW_ID) {
                            entityIn.timeUntilPortal = 200;
                            HWTeleportHelper.travelToDimension(entityPlayerMP, ContentRegistry.HW_ID);

                        } else {
                            entityIn.timeUntilPortal = 200;
                            HWTeleportHelper.travelToDimension(entityPlayerMP, 0);

                        }
                    }
                }

                // Teleport only entities, not player
             if (!(entityIn instanceof EntityPlayer) && entityIn instanceof Entity) {
                 if (entityIn.timeUntilPortal-- <= 10) {
                     if (entityIn.dimension != ContentRegistry.HW_ID) {
                         entityIn.timeUntilPortal = 200;
                         HWTeleportHelper.travelToDimension(entityIn, ContentRegistry.HW_ID);

                     } else {
                            entityIn.timeUntilPortal = 200;
                           HWTeleportHelper.travelToDimension(entityIn, 0);

                     }

                 }
             }






        }
    }


    public static class Size
    {
        private final World field_150867_a;
        private final int field_150865_b;
        private final int field_150866_c;
        private final int field_150863_d;
        private int field_150864_e = 0;
        private ChunkCoordinates field_150861_f;
        private int field_150862_g;
        private int field_150868_h;

        public Size(World p_i45415_1_, int p_i45415_2_, int p_i45415_3_, int p_i45415_4_, int p_i45415_5_)
        {
            this.field_150867_a = p_i45415_1_;
            this.field_150865_b = p_i45415_5_;
            this.field_150863_d = BlockHWPortal.field_150001_a[p_i45415_5_][0];
            this.field_150866_c = BlockHWPortal.field_150001_a[p_i45415_5_][1];

            for (int i1 = p_i45415_3_; p_i45415_3_ > i1 - 21 && p_i45415_3_ > 0 && this.func_150857_a(p_i45415_1_.getBlock(p_i45415_2_, p_i45415_3_ - 1, p_i45415_4_)); --p_i45415_3_)
            {
                ;
            }

            int j1 = this.func_150853_a(p_i45415_2_, p_i45415_3_, p_i45415_4_, this.field_150863_d) - 1;

            if (j1 >= 0)
            {
                this.field_150861_f = new ChunkCoordinates(p_i45415_2_ + j1 * Direction.offsetX[this.field_150863_d], p_i45415_3_, p_i45415_4_ + j1 * Direction.offsetZ[this.field_150863_d]);
                this.field_150868_h = this.func_150853_a(this.field_150861_f.posX, this.field_150861_f.posY, this.field_150861_f.posZ, this.field_150866_c);

                if (this.field_150868_h < 2 || this.field_150868_h > 21)
                {
                    this.field_150861_f = null;
                    this.field_150868_h = 0;
                }
            }

            if (this.field_150861_f != null)
            {
                this.field_150862_g = this.func_150858_a();
            }
        }

        protected int func_150853_a(int p_150853_1_, int p_150853_2_, int p_150853_3_, int p_150853_4_)
        {
            int j1 = Direction.offsetX[p_150853_4_];
            int k1 = Direction.offsetZ[p_150853_4_];
            int i1;
            Block block;

            for (i1 = 0; i1 < 22; ++i1)
            {
                block = this.field_150867_a.getBlock(p_150853_1_ + j1 * i1, p_150853_2_, p_150853_3_ + k1 * i1);

                if (!this.func_150857_a(block))
                {
                    break;
                }

                Block block1 = this.field_150867_a.getBlock(p_150853_1_ + j1 * i1, p_150853_2_ - 1, p_150853_3_ + k1 * i1);

                if (block1 != Blocks.mossy_cobblestone)
                {
                    break;
                }
            }

            block = this.field_150867_a.getBlock(p_150853_1_ + j1 * i1, p_150853_2_, p_150853_3_ + k1 * i1);
            return block == Blocks.mossy_cobblestone ? i1 : 0;
        }

        protected int func_150858_a()
        {
            int i;
            int j;
            int k;
            int l;
            label56:

            for (this.field_150862_g = 0; this.field_150862_g < 21; ++this.field_150862_g)
            {
                i = this.field_150861_f.posY + this.field_150862_g;

                for (j = 0; j < this.field_150868_h; ++j)
                {
                    k = this.field_150861_f.posX + j * Direction.offsetX[BlockHWPortal.field_150001_a[this.field_150865_b][1]];
                    l = this.field_150861_f.posZ + j * Direction.offsetZ[BlockHWPortal.field_150001_a[this.field_150865_b][1]];
                    Block block = this.field_150867_a.getBlock(k, i, l);

                    if (!this.func_150857_a(block))
                    {
                        break label56;
                    }

                    if (block == ContentRegistry.hwPortal)
                    {
                        ++this.field_150864_e;
                    }

                    if (j == 0)
                    {
                        block = this.field_150867_a.getBlock(k + Direction.offsetX[BlockHWPortal.field_150001_a[this.field_150865_b][0]], i, l + Direction.offsetZ[BlockHWPortal.field_150001_a[this.field_150865_b][0]]);

                        if (block != Blocks.mossy_cobblestone)
                        {
                            break label56;
                        }
                    }
                    else if (j == this.field_150868_h - 1)
                    {
                        block = this.field_150867_a.getBlock(k + Direction.offsetX[BlockHWPortal.field_150001_a[this.field_150865_b][1]], i, l + Direction.offsetZ[BlockHWPortal.field_150001_a[this.field_150865_b][1]]);

                        if (block != Blocks.mossy_cobblestone)
                        {
                            break label56;
                        }
                    }
                }
            }

            for (i = 0; i < this.field_150868_h; ++i)
            {
                j = this.field_150861_f.posX + i * Direction.offsetX[BlockHWPortal.field_150001_a[this.field_150865_b][1]];
                k = this.field_150861_f.posY + this.field_150862_g;
                l = this.field_150861_f.posZ + i * Direction.offsetZ[BlockHWPortal.field_150001_a[this.field_150865_b][1]];

                if (this.field_150867_a.getBlock(j, k, l) != Blocks.mossy_cobblestone)
                {
                    this.field_150862_g = 0;
                    break;
                }
            }

            if (this.field_150862_g <= 21 && this.field_150862_g >= 3)
            {
                return this.field_150862_g;
            }
            else
            {
                this.field_150861_f = null;
                this.field_150868_h = 0;
                this.field_150862_g = 0;
                return 0;
            }
        }

        protected boolean func_150857_a(Block p_150857_1_)
        {
            return p_150857_1_.getMaterial() == Material.air || p_150857_1_ == Blocks.fire || p_150857_1_ == ContentRegistry.hwPortal;
        }

        public boolean func_150860_b()
        {
            return this.field_150861_f != null && this.field_150868_h >= 2 && this.field_150868_h <= 21 && this.field_150862_g >= 3 && this.field_150862_g <= 21;
        }

        public void func_150859_c()
        {
            for (int i = 0; i < this.field_150868_h; ++i)
            {
                int j = this.field_150861_f.posX + Direction.offsetX[this.field_150866_c] * i;
                int k = this.field_150861_f.posZ + Direction.offsetZ[this.field_150866_c] * i;

                for (int l = 0; l < this.field_150862_g; ++l)
                {
                    int i1 = this.field_150861_f.posY + l;
                    this.field_150867_a.setBlock(j, i1, k, ContentRegistry.hwPortal, this.field_150865_b, 2);
                }
            }
        }
    }








}
