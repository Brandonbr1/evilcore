package com.jerios.evilMinecraftFixes.ducks;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public interface IAngelRing {


    int getPower(ItemStack stack);

    void addPower(ItemStack stack, int powerToAdd);

   void setPower(ItemStack stack, int setPower);

    void subPower(ItemStack stack, int powerToAdd);



}
