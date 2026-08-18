package com.jerios.evilMinecraftFixes.content.tile;

import com.jerios.evilMinecraftFixes.content.ContentRegistry;
import com.jerios.evilMinecraftFixes.content.DiamondEnchantmentBook;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.Map;

public class ContainerRepairUnlocker extends Container {

    public static final int APPENDED_HARDCODED_XP_BUFFER = 35;


    private static final Logger logger = LogManager.getLogger();
    /** Here comes out item you merged and/or renamed. */
    private IInventory outputSlot = new InventoryCraftResult();
    /** The 2slots where you put your items in that you want to merge and/or rename. */
    private IInventory inputSlots = new InventoryBasic("Repair", true, 2)
    {
        /**
         * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think
         * it hasn't changed and skip it.
         */
        public void markDirty()
        {
            super.markDirty();
            ContainerRepairUnlocker.this.onCraftMatrixChanged(this);
        }
    };
    private World theWorld;
    private int field_82861_i;
    private int field_82858_j;
    private int field_82859_k;
    /** The maximum cost of repairing/renaming in the anvil. */
    public int maximumCost;
    /** determined by damage of input item and stackSize of repair materials */
    public int stackSizeToBeUsedInRepair;
    private String repairedItemName;
    /** The player that has this container open. */
    private final EntityPlayer thePlayer;

    public ContainerRepairUnlocker(InventoryPlayer p_i1800_1_, final World p_i1800_2_, final int p_i1800_3_, final int p_i1800_4_, final int p_i1800_5_, EntityPlayer p_i1800_6_)
    {
        this.theWorld = p_i1800_2_;
        this.field_82861_i = p_i1800_3_;
        this.field_82858_j = p_i1800_4_;
        this.field_82859_k = p_i1800_5_;
        this.thePlayer = p_i1800_6_;
        this.addSlotToContainer(new Slot(this.inputSlots, 0, 27, 47));
        this.addSlotToContainer(new Slot(this.inputSlots, 1, 76, 47));
        this.addSlotToContainer(new Slot(this.outputSlot, 2, 134, 47)
        {
            /**
             * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
             */
            public boolean isItemValid(ItemStack stack)
            {
                return false;
            }
            /**
             * Return whether this slot's stack can be taken from this slot.
             */
            public boolean canTakeStack(EntityPlayer p_82869_1_)
            {
                return (p_82869_1_.capabilities.isCreativeMode || p_82869_1_.experienceLevel >= ContainerRepairUnlocker.this.maximumCost) && ContainerRepairUnlocker.this.maximumCost > 0 && this.getHasStack();
            }
            public void onPickupFromSlot(EntityPlayer p_82870_1_, ItemStack p_82870_2_)
            {
                if (!p_82870_1_.capabilities.isCreativeMode)
                {
                    p_82870_1_.addExperienceLevel(-ContainerRepairUnlocker.this.maximumCost);
                }

                float breakChance = ForgeHooks.onAnvilRepair(p_82870_1_, p_82870_2_, ContainerRepairUnlocker.this.inputSlots.getStackInSlot(0), ContainerRepairUnlocker.this.inputSlots.getStackInSlot(1));

                ContainerRepairUnlocker.this.inputSlots.setInventorySlotContents(0, (ItemStack)null);

                if (ContainerRepairUnlocker.this.stackSizeToBeUsedInRepair > 0)
                {
                    ItemStack itemToUse = ContainerRepairUnlocker.this.inputSlots.getStackInSlot(1);

                    if (itemToUse != null && itemToUse.stackSize > ContainerRepairUnlocker.this.stackSizeToBeUsedInRepair)
                    {
                        itemToUse.stackSize -= ContainerRepairUnlocker.this.stackSizeToBeUsedInRepair;
                        ContainerRepairUnlocker.this.inputSlots.setInventorySlotContents(1, itemToUse);
                    }
                    else
                    {
                        ContainerRepairUnlocker.this.inputSlots.setInventorySlotContents(1, (ItemStack)null);
                    }
                }
                else
                {
                    ContainerRepairUnlocker.this.inputSlots.setInventorySlotContents(1, (ItemStack)null);
                }

                ContainerRepairUnlocker.this.maximumCost = 0;

                if (!p_82870_1_.capabilities.isCreativeMode && !p_i1800_2_.isRemote && p_i1800_2_.getBlock(p_i1800_3_, p_i1800_4_, p_i1800_5_) == Blocks.anvil && p_82870_1_.getRNG().nextFloat() < breakChance)
                {
                    int i1 = p_i1800_2_.getBlockMetadata(p_i1800_3_, p_i1800_4_, p_i1800_5_);
                    int k = i1 & 3;
                    int l = i1 >> 2;
                    ++l;

                    if (l > 2)
                    {
                        p_i1800_2_.setBlockToAir(p_i1800_3_, p_i1800_4_, p_i1800_5_);
                        p_i1800_2_.playAuxSFX(1020, p_i1800_3_, p_i1800_4_, p_i1800_5_, 0);
                    }
                    else
                    {
                        p_i1800_2_.setBlockMetadataWithNotify(p_i1800_3_, p_i1800_4_, p_i1800_5_, k | l << 2, 2);
                        p_i1800_2_.playAuxSFX(1021, p_i1800_3_, p_i1800_4_, p_i1800_5_, 0);
                    }
                }
                else if (!p_i1800_2_.isRemote)
                {
                    p_i1800_2_.playAuxSFX(1021, p_i1800_3_, p_i1800_4_, p_i1800_5_, 0);
                }
            }
        });
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(p_i1800_1_, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(p_i1800_1_, i, 8 + i * 18, 142));
        }
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void onCraftMatrixChanged(IInventory p_75130_1_)
    {
        super.onCraftMatrixChanged(p_75130_1_);

        if (p_75130_1_ == this.inputSlots)
        {
            this.updateRepairOutput();
        }
    }

    /**
     * called when the Anvil Input Slot changes, calculates the new result and puts it in the output slot
     */
    public void updateRepairOutput()
    {
        ItemStack itemstack = this.inputSlots.getStackInSlot(0);
        this.maximumCost = 0;
        int i = 0;
        byte b0 = 0;
        int j = 0;

        int appended = 0;


        if (itemstack == null)
        {
            this.outputSlot.setInventorySlotContents(0, (ItemStack)null);
            this.maximumCost = 0;
        }
        else
        {
            ItemStack itemToUse = itemstack.copy();
            ItemStack itemstack2 = this.inputSlots.getStackInSlot(1);
            Map mapCurrentEnchantmetsSlot1 = EnchantmentHelper.getEnchantments(itemToUse);
            boolean flag = false;


            if (itemstack2 != null && itemstack2.getItem() instanceof DiamondEnchantmentBook) {
                DiamondEnchantmentBook book = (DiamondEnchantmentBook) itemstack2.getItem();
                appended += book.level * book.level;
            }
            int k2 = appended + ContainerRepairUnlocker.APPENDED_HARDCODED_XP_BUFFER + b0 + itemstack.getRepairCost() + (itemstack2 == null ? 0 : itemstack2.getRepairCost());
            this.stackSizeToBeUsedInRepair = 0;
            int k;
            int l;
            int i1;
            int k1;
            int l1;
            Iterator iterator1;
            Enchantment enchantment;

            if (itemstack2 != null)
            {
                flag = isValidEnchBook(itemstack2.getItem()); // itemstack2.getItem() == Items.enchanted_book && Items.enchanted_book.func_92110_g(itemstack2).tagCount() > 0;

                if (itemToUse.isItemStackDamageable() && itemToUse.getItem().getIsRepairable(itemstack, itemstack2))
                {
                    k = Math.min(itemToUse.getItemDamageForDisplay(), itemToUse.getMaxDamage() / 4);

                    if (k <= 0)
                    {
                        this.outputSlot.setInventorySlotContents(0, (ItemStack)null);
                        this.maximumCost = 0;
                        return;
                    }

                    for (l = 0; k > 0 && l < itemstack2.stackSize; ++l)
                    {
                        i1 = itemToUse.getItemDamageForDisplay() - k;
                        itemToUse.setItemDamage(i1);
                        i += Math.max(1, k / 100) + mapCurrentEnchantmetsSlot1.size();
                        k = Math.min(itemToUse.getItemDamageForDisplay(), itemToUse.getMaxDamage() / 4);
                    }

                    this.stackSizeToBeUsedInRepair = l;
                }
                else
                {
                    if (!flag && (itemToUse.getItem() != itemstack2.getItem() || !itemToUse.isItemStackDamageable()))
                    {
                        this.outputSlot.setInventorySlotContents(0, (ItemStack)null);
                        this.maximumCost = 0;
                        return;
                    }

                    if (itemToUse.isItemStackDamageable() && !flag)
                    {
                        k = itemstack.getMaxDamage() - itemstack.getItemDamageForDisplay();
                        l = itemstack2.getMaxDamage() - itemstack2.getItemDamageForDisplay();
                        i1 = l + itemToUse.getMaxDamage() * 12 / 100;
                        int j1 = k + i1;
                        k1 = itemToUse.getMaxDamage() - j1;

                        if (k1 < 0)
                        {
                            k1 = 0;
                        }

                        if (k1 < itemToUse.getItemDamage())
                        {
                            itemToUse.setItemDamage(k1);
                            i += Math.max(1, i1 / 100);
                        }
                    }

                    Map map1 = EnchantmentHelper.getEnchantments(itemstack2);
                    iterator1 = map1.keySet().iterator();

                    while (iterator1.hasNext())
                    {
                        i1 = ((Integer)iterator1.next()).intValue();
                        enchantment = Enchantment.enchantmentsList[i1];
                        k1 = mapCurrentEnchantmetsSlot1.containsKey(Integer.valueOf(i1)) ? ((Integer)mapCurrentEnchantmetsSlot1.get(Integer.valueOf(i1))).intValue() : 0;
                        l1 = ((Integer)map1.get(Integer.valueOf(i1))).intValue();
                        int i3;

                        if (k1 == l1)
                        {
                            ++l1;
                            i3 = l1;
                        }
                        else
                        {
                            i3 = Math.max(l1, k1);
                        }

                        l1 = i3;
                        int i2 = l1 - k1;
                        boolean flag1 = enchantment.canApply(itemstack);

                        if (this.thePlayer.capabilities.isCreativeMode ||  isValidEnchBook(itemstack.getItem()) /**itemstack.getItem() == Items.enchanted_book**/)
                        {
                            flag1 = true;
                        }

                        Iterator iterator = mapCurrentEnchantmetsSlot1.keySet().iterator();

                        while (iterator.hasNext())
                        {
                            int j2 = ((Integer)iterator.next()).intValue();

                            Enchantment e2 = Enchantment.enchantmentsList[j2];
                            if (j2 != i1 && !(enchantment.canApplyTogether(e2) && e2.canApplyTogether(enchantment))) //Forge BugFix: Let Both enchantments veto being together
                            {
                                flag1 = false;
                                i += i2;
                            }
                        }

                        if (flag1)
                        {
                           /** if (l1 > enchantment.getMaxLevel())
                            {
                                l1 = enchantment.getMaxLevel();
                            }
                            **/

                            mapCurrentEnchantmetsSlot1.put(Integer.valueOf(i1), Integer.valueOf(l1));
                            int l2 = 0;

                            switch (enchantment.getWeight())
                            {
                                case 1:
                                    l2 = 8;
                                    break;
                                case 2:
                                    l2 = 4;
                                case 3:
                                case 4:
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                default:
                                    break;
                                case 5:
                                    l2 = 2;
                                    break;
                                case 10:
                                    l2 = 1;
                            }

                            if (flag)
                            {
                                l2 = Math.max(1, l2 / 2 );
                            }

                            i += l2 * i2;
                        }
                    }
                }
            }

            if (StringUtils.isBlank(this.repairedItemName))
            {
                if (itemstack.hasDisplayName())
                {
                    j = itemstack.isItemStackDamageable() ? 7 : itemstack.stackSize * 5;
                    i += j;
                    itemToUse.func_135074_t();
                }
            }
            else if (!this.repairedItemName.equals(itemstack.getDisplayName()))
            {
                j = itemstack.isItemStackDamageable() ? 7 : itemstack.stackSize * 5;
                i += j;

                if (itemstack.hasDisplayName())
                {
                    k2 += j / 2;
                }

                itemToUse.setStackDisplayName(this.repairedItemName);
            }

            k = 0;

            for (iterator1 = mapCurrentEnchantmetsSlot1.keySet().iterator(); iterator1.hasNext(); k2 += k + k1 * l1)
            {
                i1 = ((Integer)iterator1.next()).intValue();
                enchantment = Enchantment.enchantmentsList[i1];
                k1 = ((Integer)mapCurrentEnchantmetsSlot1.get(Integer.valueOf(i1))).intValue();
                l1 = 0;
                ++k;

                switch (enchantment.getWeight())
                {
                    case 1:
                        l1 = 8;
                        break;
                    case 2:
                        l1 = 4;
                    case 3:
                    case 4:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    default:
                        break;
                    case 5:
                        l1 = 2;
                        break;
                    case 10:
                        l1 = 1;
                }

                if (flag)
                {
                    l1 = Math.max(1, l1 / 2);
                }
            }

            if (flag)
            {
                k2 = Math.max(1, k2 / 2);
            }

            if (flag && !itemToUse.getItem().isBookEnchantable(itemToUse, itemstack2)) itemToUse = null;

            this.maximumCost = k2 + i;

            if (i <= 0)
            {
                itemToUse = null;
            }

            if (j == i && j > 0 && this.maximumCost >= 900000000)
            {
                this.maximumCost = 899999999;
            }

            if (this.maximumCost >= 900000000 && !this.thePlayer.capabilities.isCreativeMode)
            {
                itemToUse = null;
            }

            if (itemToUse != null)
            {
                l = itemToUse.getRepairCost();

                if (itemstack2 != null && l < itemstack2.getRepairCost())
                {
                    l = itemstack2.getRepairCost();
                }

                if (itemToUse.hasDisplayName())
                {
                    l -= 9;
                }

                if (l < 0)
                {
                    l = 0;
                }

                l += 2;
                itemToUse.setRepairCost(l);
                EnchantmentHelper.setEnchantments(mapCurrentEnchantmetsSlot1, itemToUse);
            }

            this.outputSlot.setInventorySlotContents(0, itemToUse);
            this.detectAndSendChanges();
        }
    }

    public void addCraftingToCrafters(ICrafting p_75132_1_)
    {
        super.addCraftingToCrafters(p_75132_1_);
        p_75132_1_.sendProgressBarUpdate(this, 0, this.maximumCost);
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int p_75137_1_, int p_75137_2_)
    {
        if (p_75137_1_ == 0)
        {
            this.maximumCost = p_75137_2_;
        }
    }

    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(EntityPlayer p_75134_1_)
    {
        super.onContainerClosed(p_75134_1_);

        if (!this.theWorld.isRemote)
        {
            for (int i = 0; i < this.inputSlots.getSizeInventory(); ++i)
            {
                ItemStack itemstack = this.inputSlots.getStackInSlotOnClosing(i);

                if (itemstack != null)
                {
                    p_75134_1_.dropPlayerItemWithRandomChoice(itemstack, false);
                }
            }
        }
    }

    public boolean canInteractWith(EntityPlayer player)
    {
        return this.theWorld.getBlock(this.field_82861_i, this.field_82858_j, this.field_82859_k) != ContentRegistry.unlocker ? false : player.getDistanceSq((double)this.field_82861_i + 0.5D, (double)this.field_82858_j + 0.5D, (double)this.field_82859_k + 0.5D) <= 64.0D;
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer player, int index)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemToUse = slot.getStack();
            itemstack = itemToUse.copy();

            if (index == 2)
            {
                if (!this.mergeItemStack(itemToUse, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemToUse, itemstack);
            }
            else if (index != 0 && index != 1)
            {
                if (index >= 3 && index < 39 && !this.mergeItemStack(itemToUse, 0, 2, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemToUse, 3, 39, false))
            {
                return null;
            }

            if (itemToUse.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemToUse.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, itemToUse);
        }

        return itemstack;
    }


    /** Kids, please never do this**/
    private boolean isValidEnchBook(Item i) {
        return i == ContentRegistry.sharpness6 ||
            i == ContentRegistry.sharpness7 ||
            i == ContentRegistry.sharpness8 ||
            i == ContentRegistry.sharpness9 ||
            i == ContentRegistry.sharpness10 ||
            i == ContentRegistry.prot6 ||
            i == ContentRegistry.prot7 ||
            i == ContentRegistry.prot8
            ||
            i == ContentRegistry.prot9
            ||
            i == ContentRegistry.prot10
            || i == ContentRegistry.knockback10


            ;
    }

    /**
     * used by the Anvil GUI to update the Item Name being typed by the player
     */
    public void updateItemName(String p_82850_1_)
    {
        this.repairedItemName = p_82850_1_;

        if (this.getSlot(2).getHasStack())
        {
            ItemStack itemstack = this.getSlot(2).getStack();

            if (StringUtils.isBlank(p_82850_1_))
            {
                itemstack.func_135074_t();
            }
            else
            {
                itemstack.setStackDisplayName(this.repairedItemName);
            }
        }

        this.updateRepairOutput();
    }
}
