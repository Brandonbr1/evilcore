package com.jerios.evilMinecraftFixes.mixins.late.specialMobs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import toast.specialMobs.MobHelper;
import toast.specialMobs._SpecialMobs;

@Mixin(MobHelper.class)
public class MixinMobHelper {


    /**
     * @author
     * @reason
     */
    @Overwrite(remap = false)
    public static ItemStack removeHeldItem(EntityPlayer player) {
        int index = player.inventory.currentItem;
       // ItemStack heldItem = getCurrentItem(index, player.inventory.mainInventory);
       // ItemStack heldItem = player.inventory.mainInventory[index];
       ItemStack heldItem = player.inventory.getCurrentItem();

        if (heldItem != null) {
            if (heldItem.stackSize >= 1) {
                heldItem.stackSize--;
                player.clearItemInUse();
            }

            if (heldItem.stackSize <= 0) {
                player.clearItemInUse();

                player.inventory.mainInventory[index] = null;

        }



        }

        return heldItem;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite(remap = false)
    public static ItemStack removeRandomItem(EntityPlayer player) {
        int count = 0;

        for(int i = 0; i < player.inventory.getSizeInventory(); ++i) {
            if (player.inventory.getStackInSlot(i) != null) {
                ++count;
            }
        }

        if (count > 0) {
            count = _SpecialMobs.random.nextInt(count);

            for(int i = 0; i < player.inventory.getSizeInventory(); ++i) {
                ItemStack item = player.inventory.getStackInSlot(i);
                if (item != null) {
                    --count;
                    if (count < 0) {


                        if (item.stackSize >= 1) {
                            item.stackSize--;
                            player.clearItemInUse();
                        }

                        if (item.stackSize <= 0) {
                            player.clearItemInUse();
                            player.inventory.mainInventory[i] = null;

                        }


                      //  player.inventory.setInventorySlotContents(i, (ItemStack)null);
                        return item;
                    }
                }
            }
        }

        return null;
    }




}
