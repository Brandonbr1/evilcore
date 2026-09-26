package com.jerios.evilMinecraftFixes.mixins.late.extraUtils;

import cofh.api.energy.IEnergyContainerItem;
import com.rwtema.extrautils.item.ItemAngelRing;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ItemAngelRing.class)
public class MixinAngelRing implements IEnergyContainerItem {


   @Unique private int evil$MAXCAPACITY = Integer.MAX_VALUE;
    @Unique private String evil$RP = "RingPower";

    @Unique
    private static int evil$maxGiveRec = 1000;

    @Override
    public int receiveEnergy(ItemStack itemStack, int maxReceive, boolean simulate) {

        int energy = getEnergy(itemStack);
        int energyReceived = Math.min(evil$MAXCAPACITY - energy, Math.min(evil$maxGiveRec, maxReceive));

        if (!simulate) {
            energy += energyReceived;
            setEn(itemStack, energy);
        }

        return energyReceived;
    }

    @Override
    public int extractEnergy(ItemStack itemStack, int maxExtract, boolean simulate) {

        if (getEnergy(itemStack) <= 0) return 0;

        int energy = getEnergy(itemStack);
        int energyExtracted = Math.min(energy, evil$maxGiveRec);

        if (!simulate) {
            energy -= energyExtracted;
           setEn(itemStack, energy);
        }


        return energyExtracted ;
    }

    @Override
    public int getEnergyStored(ItemStack itemStack) {
        return getEnergy(itemStack);
    }

    private NBTTagCompound getNbt(ItemStack stack) {
        NBTTagCompound nbt;
        if (stack.hasTagCompound())
        {
            nbt = stack.getTagCompound();
        }
        else
        {
            nbt = new NBTTagCompound();
        }

        stack.setTagCompound(nbt);

        return nbt;
    }


    private int getEnergy(ItemStack s) {
        return getNbt(s).getInteger(evil$RP);
    }

    private void setEn(ItemStack s, int en) {
        getNbt(s).setInteger(evil$RP,en);
    }

    @Override
    public int getMaxEnergyStored(ItemStack itemStack) {
        return evil$MAXCAPACITY;
    }
}
