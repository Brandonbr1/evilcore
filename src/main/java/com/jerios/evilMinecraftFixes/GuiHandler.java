package com.jerios.evilMinecraftFixes;

import com.jerios.evilMinecraftFixes.content.ContentRegistry;
import com.jerios.evilMinecraftFixes.content.tile.ContainerRepairUnlocker;
import com.jerios.evilMinecraftFixes.content.tile.GuiRepairUnlocker;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
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

        }

        return null;
    }
}
