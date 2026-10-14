package com.jerios.evilMinecraftFixes.mixins.late.extraUtils;

import com.jerios.evilMinecraftFixes.ducks.IAngelRing;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.rwtema.extrautils.helper.XUHelper;
import com.rwtema.extrautils.item.ItemAngelRing;
import com.rwtema.extrautils.network.NetworkHandler;
import com.rwtema.extrautils.network.packets.PacketAngelRingNotifier;
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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemAngelRing.class)
public class MixinAngelRing extends Item implements IAngelRing {


    int ringTimer = 0;
    boolean cachedHasKeyRingPower = false;
    boolean cachedHasTimerKey= false;

    /**
     * @author Jerios
     * @reason Power
     */
    @Overwrite
    public void onUpdate(ItemStack itemstack, World world, Entity entity, int slot, boolean par5) {
        super.onUpdate(itemstack, world, entity, slot, par5);
        if (!world.isRemote) {
            if (entity instanceof EntityPlayerMP) {
                ringTimer++;
                NBTTagCompound nbt = XUHelper.getPersistantNBT(entity);
                if (getPower(itemstack) > 0) {
                    nbt.setByte("XU|Flying", (byte) 20);
                } else {
                    nbt.setByte("XU|Flying", (byte) 1);
                }
                // SEEMS LIKE THIS SHOULD NOT BE HERE
             //   addPlayer((EntityPlayerMP)entity, itemstack.getItemDamage(), par5);


              //  System.out.println("RING TIMER  "+ringTimer);
              //  System.out.println("POWER  "+getPower(itemstack));

                if (ringTimer > 320)
                {
                    subPower(itemstack, 15);
                 //   System.out.println("Power Subtracted 1");
                    if (((EntityPlayerMP)entity).capabilities.isFlying) {
                        subPower(itemstack, 25);
                     //   System.out.println("Power Subtracted 2");
                    }
                    ringTimer = 0;
                }


                if (!((EntityPlayerMP)entity).capabilities.allowFlying || !nbt.hasKey("XU|FlyingDim") || nbt.getInteger("XU|FlyingDim") != world.provider.dimensionId) {
                    addPlayer((EntityPlayerMP)entity, itemstack.getItemDamage(), false);
                    ((EntityPlayerMP)entity).capabilities.allowFlying = true;
                    ((EntityPlayerMP)entity).sendPlayerAbilities();
                }

                nbt.setInteger("XU|FlyingDim", world.provider.dimensionId);
            }
        }
    }

    @Shadow(remap = false)
    public static void addPlayer(EntityPlayer player, int i, boolean override) {


    }


    public int getPower(ItemStack stack) {
        NBTTagCompound nbt = getNBTComp(stack);


        if (!cachedHasKeyRingPower) {
            if (!nbt.hasKey("RingPower")) {
                nbt.setInteger("RingPower", 0);
            }
            cachedHasKeyRingPower = true;
        }

        return nbt.getInteger("RingPower");
    }

    public int getRingTimer(ItemStack stack) {
        NBTTagCompound nbt = getNBTComp(stack);

        if (!cachedHasTimerKey) {
            if (!nbt.hasKey("RingTimer")) {
                nbt.setInteger("RingTimer", 0);
            }
            cachedHasTimerKey = true;
        }

         return ringTimer = nbt.getInteger("RingTimer");
    }

    public void addRingTimer(ItemStack stack, int toAdd) {
        NBTTagCompound nbt = getNBTComp(stack);

        nbt.setInteger("RingTimer", getRingTimer(stack) + toAdd );
    }

    public NBTTagCompound getNBTComp(ItemStack stack) {

        NBTTagCompound nbt;
        if (stack.hasTagCompound())
        {
            nbt = stack.getTagCompound();
        }
        else
        {
            nbt = new NBTTagCompound();
        }

        return nbt;

    }

    public void addPower(ItemStack stack, int powerToAdd) {
        int totalPower = getPower(stack) + powerToAdd;
        if (totalPower > 300000) {
            totalPower = 300000;
        }

        NBTTagCompound nbt = getNBTComp(stack);

        nbt.setInteger("RingPower", totalPower);
        stack.setTagCompound(nbt);
    }

    public void setPower(ItemStack stack, int setPower) {
        if (setPower < 0) {
            setPower = 0;
        }

        if (setPower > 300000) {
            setPower = 300000;
        }

        NBTTagCompound nbt = getNBTComp(stack);

        nbt.setInteger("RingPower", setPower);

        stack.setTagCompound(nbt);
    }

    public void subPower(ItemStack stack, int powerToSub) {
        int totalPower = getPower(stack) - powerToSub;
        if (totalPower < 0) {
            totalPower = 0;
        }

        NBTTagCompound nbt = getNBTComp(stack);
        nbt.setInteger("RingPower", totalPower);
        stack.setTagCompound(nbt);
    }



}
