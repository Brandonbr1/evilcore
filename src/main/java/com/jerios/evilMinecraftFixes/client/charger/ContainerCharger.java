package com.jerios.evilMinecraftFixes.client.charger;

import com.jerios.evilMinecraftFixes.content.tile.charger.TileEntityCharger;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerCharger extends Container {

    private TileEntityCharger tileEntityCharger;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerCharger(InventoryPlayer invPlayer, TileEntityCharger tileEntityCharger)
    {
        this.tileEntityCharger = tileEntityCharger;
        this.addSlotToContainer(new ChargerRingSlot(tileEntityCharger, 0, 56, 17));
        this.addSlotToContainer(new ChargerFuelSlot(tileEntityCharger, 1, 56, 53));
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting p_75132_1_)
    {
        super.addCraftingToCrafters(p_75132_1_);
        p_75132_1_.sendProgressBarUpdate(this, 0, this.tileEntityCharger.chargerTickTimeCookedFor);
        p_75132_1_.sendProgressBarUpdate(this, 1, this.tileEntityCharger.chargerBurnTime);
        p_75132_1_.sendProgressBarUpdate(this, 2, this.tileEntityCharger.chargerRemainingTime);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCookTime != this.tileEntityCharger.chargerTickTimeCookedFor)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileEntityCharger.chargerTickTimeCookedFor);
            }

            if (this.lastBurnTime != this.tileEntityCharger.chargerBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileEntityCharger.chargerBurnTime);
            }

            if (this.lastItemBurnTime != this.tileEntityCharger.chargerRemainingTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileEntityCharger.chargerRemainingTime);
            }
        }

        this.lastCookTime = this.tileEntityCharger.chargerTickTimeCookedFor;
        this.lastBurnTime = this.tileEntityCharger.chargerBurnTime;
        this.lastItemBurnTime = this.tileEntityCharger.chargerRemainingTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int p_75137_1_, int p_75137_2_)
    {
        if (p_75137_1_ == 0)
        {
            this.tileEntityCharger.chargerTickTimeCookedFor = p_75137_2_;
        }

        if (p_75137_1_ == 1)
        {
            this.tileEntityCharger.chargerBurnTime = p_75137_2_;
        }

        if (p_75137_1_ == 2)
        {
            this.tileEntityCharger.chargerRemainingTime = p_75137_2_;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return this.tileEntityCharger.isUseableByPlayer(player);
    }


    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 1 && index != 0)
            {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityFurnace.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (index >= 3 && index < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }
}
