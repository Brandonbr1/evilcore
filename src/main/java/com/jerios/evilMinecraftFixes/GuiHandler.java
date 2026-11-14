package com.jerios.evilMinecraftFixes;

import com.jerios.evilMinecraftFixes.client.charger.ContainerCharger;
import com.jerios.evilMinecraftFixes.client.charger.GuiCharger;
import com.jerios.evilMinecraftFixes.content.ContentRegistry;
import com.jerios.evilMinecraftFixes.content.tile.ContainerRepairUnlocker;
import com.jerios.evilMinecraftFixes.content.tile.GuiRepairUnlocker;
import com.jerios.evilMinecraftFixes.content.tile.charger.TileEntityCharger;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if (ID == 90) {
            Block b = world.getBlock(x,y,z);
         //   System.out.println("Called");

            if (b == ContentRegistry.unlocker) {
                return new ContainerRepairUnlocker(player.inventory, world, x,y,z, player);
            }
        } else if (ID == 23) {
            Block b = world.getBlock(x,y,z);
            if (b == ContentRegistry.charger) {
                System.out.println("TEST2");
                TileEntityCharger charger = (TileEntityCharger) world.getTileEntity(x,y,z);
                return new ContainerCharger(player.inventory, charger);
            }
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if (ID == 90) {
            Block b = world.getBlock(x,y,z);
        //    System.out.println("Called server");

            if (b == ContentRegistry.unlocker) {
                return new GuiRepairUnlocker(player.inventory, world, x,y,z);
            }

        }else if (ID == 23) {
            Block b = world.getBlock(x,y,z);
            if (b == ContentRegistry.charger) {
                System.out.println("TEST2");
                TileEntityCharger charger = (TileEntityCharger) world.getTileEntity(x,y,z);
                return new GuiCharger(charger, player.inventory);
            }
        }

        return null;
    }
}
