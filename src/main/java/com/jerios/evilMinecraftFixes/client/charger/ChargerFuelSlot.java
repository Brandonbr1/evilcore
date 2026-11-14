package com.jerios.evilMinecraftFixes.client.charger;

import chylex.hee.init.ItemList;
import com.jerios.evilMinecraftFixes.CQAdditions.CQInteg;
import com.jerios.evilMinecraftFixes.content.ContentRegistry;
import com.rwtema.extrautils.ExtraUtils;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ChargerFuelSlot extends Slot {
    public ChargerFuelSlot(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
        super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() == Items.feather ||
            stack.getItem() == CQInteg.goldFeather ||
            stack.getItem() == ItemList.essence ||
            stack.getItem() == Items.fireworks
            || stack.getItem() == ExtraUtils.unstableIngot
            ;
    }
}
