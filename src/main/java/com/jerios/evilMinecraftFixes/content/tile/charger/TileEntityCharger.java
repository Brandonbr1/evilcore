package com.jerios.evilMinecraftFixes.content.tile.charger;

import com.jerios.evilMinecraftFixes.client.charger.ChargerItems;
import com.jerios.evilMinecraftFixes.ducks.IAngelRing;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

public class TileEntityCharger extends TileEntity implements ISidedInventory {

    private static final int[] slotsTop = new int[] {0};

    private static final int[] slotsBottom = new int[] {2, 1};

    private static final int[] slotsSides = new int[] {1};

    /** // 1 is fuel, 0 is item, 2 is output, which we don't need anymore**/
    private ItemStack[] chargerSlots = new ItemStack[2];

    /** The number of ticks that the furnace will keep burning */
    public int chargerBurnTime;
    /** The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for */
    public int chargerRemainingTime;
    /** The number of ticks that the current item has been cooking for */
    public int chargerTickTimeCookedFor;

    private String chargerName;


   @Override
    public int getSizeInventory()
    {
        return this.chargerSlots.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIn)
    {
        return this.chargerSlots[slotIn];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int index, int count)
    {
        if (this.chargerSlots[index] != null)
        {
            ItemStack itemstack;

            if (this.chargerSlots[index].stackSize <= count)
            {
                itemstack = this.chargerSlots[index];
                this.chargerSlots[index] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.chargerSlots[index].splitStack(count);

                if (this.chargerSlots[index].stackSize == 0)
                {
                    this.chargerSlots[index] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int index)
    {
        if (this.chargerSlots[index] != null)
        {
            ItemStack itemstack = this.chargerSlots[index];
            this.chargerSlots[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        this.chargerSlots[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * Returns the name of the inventory
     */
    public String getInventoryName()
    {
        return this.hasCustomInventoryName() ? this.chargerName : "container.furnace";
    }

    /**
     * Returns if the inventory is named
     */
    public boolean hasCustomInventoryName()
    {
        return this.chargerName != null && !this.chargerName.isEmpty();
    }

                /** setChargerName**/
    public void func_145951_a(String p_145951_1_)
    {
        this.chargerName = p_145951_1_;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.chargerSlots = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.chargerSlots.length)
            {
                this.chargerSlots[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.chargerBurnTime = compound.getShort("BurnTime");
        this.chargerTickTimeCookedFor = compound.getShort("CookTime");
        this.chargerRemainingTime = getItemFuelReplishAmm(this.chargerSlots[1]);

        if (compound.hasKey("CustomName", 8))
        {
            this.chargerName = compound.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setShort("BurnTime", (short)this.chargerBurnTime);
        compound.setShort("CookTime", (short)this.chargerTickTimeCookedFor);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.chargerSlots.length; ++i)
        {
            if (this.chargerSlots[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.chargerSlots[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        compound.setTag("Items", nbttaglist);

        if (this.hasCustomInventoryName())
        {
            compound.setString("CustomName", this.chargerName);
        }
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Returns an integer between 0 and the passed value representing how close the current item is to being completely
     * cooked
     */
    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int p_145953_1_)
    {
        return this.chargerTickTimeCookedFor * p_145953_1_ / 200;
    }

    /**
     * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
     * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
     */
    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int p_145955_1_)
    {
        if (this.chargerRemainingTime == 0)
        {
            this.chargerRemainingTime = 200;
        }

        return this.chargerBurnTime * p_145955_1_ / this.chargerRemainingTime;
    }


    public boolean isBurning()
    {
        return this.chargerBurnTime > 0;
    }

    @Override
    public void updateEntity()
    {
        boolean flag = this.chargerBurnTime > 0;
        boolean flag1 = false;

        if (this.chargerBurnTime > 0)
        {
            --this.chargerBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.chargerBurnTime != 0 || this.chargerSlots[1] != null && this.chargerSlots[0] != null)
            {
                if (this.chargerBurnTime == 0 && this.canCharge())
                {
                    this.chargerRemainingTime = this.chargerBurnTime = getItemFuelReplishAmm(this.chargerSlots[1]);

                    if (this.chargerBurnTime > 0)
                    {
                        flag1 = true;

                        if (this.chargerSlots[1] != null)
                        {
                            --this.chargerSlots[1].stackSize;

                            if (this.chargerSlots[1].stackSize == 0)
                            {
                                this.chargerSlots[1] = chargerSlots[1].getItem().getContainerItem(chargerSlots[1]);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canCharge())
                {
                    ++this.chargerTickTimeCookedFor;

                    if (this.chargerTickTimeCookedFor == 200)
                    {
                        this.chargerTickTimeCookedFor = 0;
                        this.chargeItem();
                        flag1 = true;
                    }
                }
                else
                {
                    this.chargerTickTimeCookedFor = 0;
                }
            }

            if (flag != this.chargerBurnTime > 0)
            {
                flag1 = true;
               // BlockFurnace.updateFurnaceBlockState(this.chargerBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
    }

    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canCharge()
    {
     //   if (this.chargerSlots[0] == null)
     //   {
        //    return false;
       // }
       // else
      //  {
            ItemStack itemStack2 = chargerSlots[0];
            ItemStack itemstack = chargerSlots[1]; //FurnaceRecipes.smelting().getSmeltingResult(this.chargerSlots[0]);
            return itemstack != null && itemStack2!= null;
         /**   if (itemstack == null) return false;
            if (this.chargerSlots[2] == null) return true;
            if (!this.chargerSlots[2].isItemEqual(itemstack)) return false;
            int result = chargerSlots[2].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.chargerSlots[2].getMaxStackSize(); //Forge BugFix: Make it respect stack sizes properly.
          **/
       // }
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void chargeItem()
    {
        if (this.canCharge()) //Null checks
        {

            ItemStack stackFuel = chargerSlots[1];
            ItemStack stackRing = chargerSlots[0];

            Item itemFuel = stackFuel.getItem();
            Item ringItem = stackRing.getItem();



            if (ringItem != null && itemFuel != null) {

               // int fuelAmmount = getItemFuelReplishAmm(stackFuel);
                ((IAngelRing) ringItem).addPower(stackRing, getItemFuelReplishAmm(stackFuel));

            } else if (ringItem == null) {
                this.chargerSlots[0] = stackRing.copy();
            }

            --this.chargerSlots[1].stackSize;

            if (this.chargerSlots[1].stackSize <= 0)
            {
                this.chargerSlots[1] = null;
            }



        /**    ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.chargerSlots[0]);

            if (this.chargerSlots[2] == null)
            {
                this.chargerSlots[2] = itemstack.copy();
            }
            else if (this.chargerSlots[2].getItem() == itemstack.getItem())
            {
                this.chargerSlots[2].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
            }

            --this.chargerSlots[0].stackSize;

            if (this.chargerSlots[0].stackSize <= 0)
            {
                this.chargerSlots[0] = null;
            }
         **/
        }
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
     * fuel
     */
    public static int getItemFuelReplishAmm(ItemStack stack)
    {
        if (stack == null) { return 0;}
        else {
            Integer key = ChargerItems.ITEM_FUEL_VALUE.get(stack.getItem());
           if (key == null)
           {
                return 0;
           } else {
               return (int)key;
           }


        }
    }

    public static boolean isItemFuel(ItemStack p_145954_0_)
    {
        return getItemFuelReplishAmm(p_145954_0_) > 0;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openInventory() {}

    public void closeInventory() {}

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return index == 2 ? false : (index == 1 ? isItemFuel(stack) : true);
    }

    /**
     * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
     * block.
     */
    public int[] getAccessibleSlotsFromSide(int p_94128_1_)
    {
        return p_94128_1_ == 0 ? slotsBottom : (p_94128_1_ == 1 ? slotsTop : slotsSides);
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_)
    {
        return this.isItemValidForSlot(p_102007_1_, p_102007_2_);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_)
    {
        return false;
    }
}
