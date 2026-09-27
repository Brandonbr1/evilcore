package com.jerios.evilMinecraftFixes.mixins.late.extraUtils;

import cofh.api.energy.IEnergyContainerItem;
import com.rwtema.extrautils.helper.XUHelper;
import com.rwtema.extrautils.item.ItemAngelRing;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ItemAngelRing.class)
public class MixinAngelRing extends Item implements IEnergyContainerItem {


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


    @Shadow(remap = false)
    public static void addPlayer(EntityPlayer player, int i, boolean override) {

    }

/**
 * @author Jerios
 * @reason REQUIRE RF
 */
    @Overwrite
    public void onUpdate(ItemStack itemstack, World world, Entity entity, int slot, boolean par5) {
        super.onUpdate(itemstack, world, entity, slot, par5);
        if (!world.isRemote) {
            if (entity instanceof EntityPlayerMP) {
                NBTTagCompound nbt = XUHelper.getPersistantNBT(entity);

                if (getEnergy(itemstack) <= 0) {
                    ItemAngelRing.removePlayer(((EntityPlayerMP)entity));
                    if (!((EntityPlayerMP)entity).capabilities.isCreativeMode) {
                        nbt.setByte("XU|Flying", (byte)1);
                        ((EntityPlayerMP)entity).capabilities.allowFlying = false;
                        ((EntityPlayerMP)entity).capabilities.isFlying = false;
                        ((EntityPlayerMP)entity).sendPlayerAbilities();
                    }

                } else {
                    nbt.setByte("XU|Flying", (byte)20);
                    addPlayer((EntityPlayerMP)entity, itemstack.getItemDamage(), par5);
                    if (!((EntityPlayerMP)entity).capabilities.allowFlying && getEnergy(itemstack) >= 0 || !nbt.hasKey("XU|FlyingDim") || nbt.getInteger("XU|FlyingDim") != world.provider.dimensionId) {
                        setEn(itemstack, getEnergy(itemstack) - 1);
                        addPlayer((EntityPlayerMP)entity, itemstack.getItemDamage(), false);
                        ((EntityPlayerMP)entity).capabilities.allowFlying = true;
                        ((EntityPlayerMP)entity).sendPlayerAbilities();

                    }
                }

                nbt.setInteger("XU|FlyingDim", world.provider.dimensionId);
            }
        }
    }




}
