package com.jerios.evilMinecraftFixes.hee;

import chylex.hee.entity.boss.EntityBossEnderDemon;
import chylex.hee.entity.mob.EntityMobAngryEnderman;
import com.jerios.evilMinecraftFixes.Evil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemEndermenRelicJerios extends Item {

    public ItemEndermenRelicJerios() {
        this.setUnlocalizedName("endermenRelicEnderDeamon");
        setTextureName(Evil.PREFIX2 + "endermenRelicEnderDeamon");
        setCreativeTab(CreativeTabs.tabCombat);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        if (p_77648_3_.isRemote)
        {
            return true;
        }
        else
        {
            p_77648_2_.addPotionEffect(new PotionEffect(Potion.weakness.id, 900, 1));
            p_77648_2_.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 900, 1));
            p_77648_2_.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 900, 1));
            p_77648_2_.addChatMessage(new ChatComponentText(
                EnumChatFormatting.DARK_PURPLE+ "You feel a sudden Sickness, like your energy and power has been drained"
            ));


            Block block = p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_);
            p_77648_4_ += Facing.offsetsXForSide[p_77648_7_];
            p_77648_5_ += Facing.offsetsYForSide[p_77648_7_];
            p_77648_6_ += Facing.offsetsZForSide[p_77648_7_];
            double d0 = 0.0D;

            if (p_77648_7_ == 1 && block.getRenderType() == 11)
            {
                d0 = 0.5D;
            }

            Entity entity = spawnCreature(p_77648_3_, p_77648_1_.getItemDamage(), (double)p_77648_4_ + 0.5D, (double)p_77648_5_ + d0, (double)p_77648_6_ + 0.5D);

            for (int l = 0; l < 3; l++) {
                spawnAngryEndermen(p_77648_3_, p_77648_1_.getItemDamage(), (double)p_77648_4_ + 0.5D, (double)p_77648_5_ + d0, (double)p_77648_6_ + 0.5D);
            }

            if (entity != null)
            {
                if (entity instanceof EntityLivingBase && p_77648_1_.hasDisplayName())
                {
                    ((EntityLiving)entity).setCustomNameTag(p_77648_1_.getDisplayName());
                }

                if (!p_77648_2_.capabilities.isCreativeMode)
                {
                    --p_77648_1_.stackSize;
                }
            }

            return true;
        }
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player)
    {
        if (worldIn.isRemote)
        {
            return itemStackIn;
        }
        else
        {
            player.addPotionEffect(new PotionEffect(Potion.weakness.id, 900, 1));
            player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 900, 1));
            player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 900, 1));
            player.addChatMessage(new ChatComponentText(
                EnumChatFormatting.DARK_PURPLE+ "You feel a sudden Sickness, like your energy and power has been drained"
            ));
            MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(worldIn, player, true);

            if (movingobjectposition == null)
            {
                return itemStackIn;
            }
            else
            {
                if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
                {
                    int i = movingobjectposition.blockX;
                    int j = movingobjectposition.blockY;
                    int k = movingobjectposition.blockZ;

                    for (int l = 0; l < 3; l++) {
                        spawnAngryEndermen(worldIn, itemStackIn.getItemDamage(), (double)i, (double)j, (double)k);
                    }

                    if (!worldIn.canMineBlock(player, i, j, k))
                    {
                        return itemStackIn;
                    }

                    if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, itemStackIn))
                    {
                        return itemStackIn;
                    }

                    if (worldIn.getBlock(i, j, k) instanceof BlockLiquid)
                    {
                        Entity entity = spawnCreature(worldIn, itemStackIn.getItemDamage(), (double)i, (double)j, (double)k);

                        if (entity != null)
                        {
                            if (entity instanceof EntityLivingBase && itemStackIn.hasDisplayName())
                            {
                                ((EntityLiving)entity).setCustomNameTag(itemStackIn.getDisplayName());
                            }

                            if (!player.capabilities.isCreativeMode)
                            {
                                --itemStackIn.stackSize;
                            }
                        }
                    }
                }

                return itemStackIn;
            }
        }
    }

    /**
     * Spawns the creature specified by the egg's type in the location specified by the last three parameters.
     * Parameters: world, entityID, x, y, z.
     */
    public static Entity spawnCreature(World p_77840_0_, int p_77840_1_, double p_77840_2_, double p_77840_4_, double p_77840_6_)
    {
     //   if (!EntityList.entityEggs.containsKey(Integer.valueOf(p_77840_1_)))
     //   {
           // return null;
     //   }
     //   else
     //   {
            Entity entity = null;

            for (int j = 0; j < 1; ++j)
            {
                entity = new EntityBossEnderDemon(p_77840_0_); //EntityList.createEntityByID(p_77840_1_, p_77840_0_);

                if (entity != null && entity instanceof EntityLivingBase)
                {
                    EntityLiving entityliving = (EntityLiving)entity;
                    entity.setLocationAndAngles(p_77840_2_, p_77840_4_, p_77840_6_, MathHelper.wrapAngleTo180_float(p_77840_0_.rand.nextFloat() * 360.0F), 0.0F);
                    entityliving.rotationYawHead = entityliving.rotationYaw;
                    entityliving.renderYawOffset = entityliving.rotationYaw;
                    entityliving.onSpawnWithEgg((IEntityLivingData)null);
                    p_77840_0_.spawnEntityInWorld(entity);
                    entityliving.playLivingSound();
                }
            }

            return entity;
      //  }
    }

    public static Entity spawnAngryEndermen(World p_77840_0_, int p_77840_1_, double p_77840_2_, double p_77840_4_, double p_77840_6_)
    {
        //   if (!EntityList.entityEggs.containsKey(Integer.valueOf(p_77840_1_)))
        //   {
        // return null;
        //   }
        //   else
        //   {
        Entity entity = null;

        for (int j = 0; j < 1; ++j)
        {
            entity = new EntityMobAngryEnderman(p_77840_0_); //EntityList.createEntityByID(p_77840_1_, p_77840_0_);

            if (entity != null && entity instanceof EntityLivingBase)
            {
                EntityLiving entityliving = (EntityLiving)entity;
                entity.setLocationAndAngles(p_77840_2_, p_77840_4_, p_77840_6_, MathHelper.wrapAngleTo180_float(p_77840_0_.rand.nextFloat() * 360.0F), 0.0F);
                entityliving.rotationYawHead = entityliving.rotationYaw;
                entityliving.renderYawOffset = entityliving.rotationYaw;
                entityliving.onSpawnWithEgg((IEntityLivingData)null);
                p_77840_0_.spawnEntityInWorld(entity);
                entityliving.playLivingSound();
            }
        }

        return entity;
        //  }
    }


}
