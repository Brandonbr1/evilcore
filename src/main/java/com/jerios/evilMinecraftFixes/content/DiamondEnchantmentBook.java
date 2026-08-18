package com.jerios.evilMinecraftFixes.content;

import com.jerios.evilMinecraftFixes.Evil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DiamondEnchantmentBook extends Item {

    public int enchantmentID;
    public int level;

    public DiamondEnchantmentBook(String bType, int id, int lvl) {
        this.enchantmentID = id;
        this.level = lvl;
        this.setUnlocalizedName("diamond_enchantmentBook" + bType + lvl);
        setTextureName(Evil.PREFIX2 + "diamond_book");
        setCreativeTab(CreativeTabs.tabCombat);

    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack p_77636_1_)
    {
        return true;
    }

    /**
     * Checks isDamagable and if it cannot be stacked
     */
    @Override
    public boolean isItemTool(ItemStack p_77616_1_)
    {
        return false;
    }

    boolean cachedCheck = false;

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int p_77663_4_, boolean p_77663_5_) {
        super.onUpdate(stack, worldIn, entityIn, p_77663_4_, p_77663_5_);
        if (!worldIn.isRemote && !entityIn.worldObj.isRemote) {
            if (!cachedCheck) {
                if (EnchantmentHelper.getEnchantmentLevel(enchantmentID, stack) <= 0) {
                    Enchantment[] aenchantment = Enchantment.enchantmentsList;
                    Enchantment enchantment = aenchantment[enchantmentID];
                    stack.addEnchantment(enchantment, level);
                  //  cachedCheck = true;
                }
            }
        }


    }
}
