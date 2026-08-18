package com.jerios.evilMinecraftFixes.ironBackPacks;

import com.jerios.evilMinecraftFixes.cfg.Config;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import main.ironbackpacks.items.backpacks.IBackpack;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class IronBackPacksLimitAmmountEvent {

    @SubscribeEvent
    public void onPlayerTick(EntityItemPickupEvent event) {
        EntityPlayer player = event.entityPlayer;
        World world = player.worldObj;
        if (!world.isRemote) {

            EntityItem itemE = event.item;
            ItemStack itemStackE = itemE.getEntityItem();
            if (itemStackE != null) {
                Item gotItemE = itemStackE.getItem();

                ItemStack[] inv = player.inventory.mainInventory;


                int currentBackpackAmmount = 0;
                int bp = 0;
                int finalBp = 0;

                for (int i = 0; i < inv.length; i++) {
                    ItemStack stack = inv[i];

                    if (stack != null) {
                        Item item = stack.getItem();

                        if (item instanceof IBackpack) {
                            currentBackpackAmmount += stack.stackSize;
                        }
                    }
                }


                finalBp += currentBackpackAmmount += itemStackE.stackSize ;

                if (currentBackpackAmmount > Config.maxBP && gotItemE instanceof IBackpack) {
                    event.setCanceled(true);
                    event.setResult(Event.Result.DENY);
                }
            }

        }


    }


    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        World world = player.worldObj;
        if (!world.isRemote) {
            ItemStack[] inv = player.inventory.mainInventory;

            int backpackAmm = 0;

            for (int i = 0; i < inv.length; i++) {
                ItemStack stack = inv[i];

                if (stack != null) {
                    Item item = stack.getItem();

                    if (item instanceof IBackpack) {
                        backpackAmm += stack.stackSize;


                        if (backpackAmm >= Config.maxBP) {
                            player.entityDropItem(stack.copy(), 0.0f);
                            player.inventory.mainInventory[i] = null;
                            player.addChatMessage(new ChatComponentText("You can not have more than " + Config.maxBP + " Backpacks"));
                        }
                    }




                }



            }




        }


    }



}
