package com.jerios.evilMinecraftFixes.extraUtilsEvents;


import com.jerios.evilMinecraftFixes.ducks.IAngelRing;
import com.rwtema.extrautils.item.ItemAngelRing;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;

public class ExtraUtilitiesPowerEvent {

    @SubscribeEvent
    public void jumpRefuelPower(LivingEvent.LivingJumpEvent jumpEvent) {
       EntityLivingBase living = jumpEvent.entityLiving;

       if (living instanceof EntityPlayer) {
           EntityPlayer player = (EntityPlayer) living;

           ItemStack[] playerAllInv = player.inventory.mainInventory;

           for (int i = 0; i < playerAllInv.length; i++) {
              ItemStack stack =  playerAllInv[i];

              if ( stack != null && stack.getItem() instanceof ItemAngelRing) {
                  ItemAngelRing ring = (ItemAngelRing) stack.getItem();

                  ((IAngelRing)ring).addPower(stack, 5);

              }

           }



       }

    }



}
