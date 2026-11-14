package com.jerios.evilMinecraftFixes.extraUtilsEvents;


import com.jerios.evilMinecraftFixes.content.tile.charger.TileEntityCharger;
import com.jerios.evilMinecraftFixes.ducks.IAngelRing;
import com.rwtema.extrautils.item.ItemAngelRing;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
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

              int charge = 0;

              if (stack != null) {
                  if (TileEntityCharger.getItemFuelReplishAmm(stack) > 0) {
                      charge = TileEntityCharger.getItemFuelReplishAmm(stack);
                  }
                  if (stack.getItem() instanceof ItemAngelRing) {
                      ItemAngelRing ring = (ItemAngelRing) stack.getItem();

                      ((IAngelRing) ring).addPower(stack, 5);

                      if (player.isPotionActive(Potion.jump)) {
                          ((IAngelRing) ring).addPower(stack, 50);
                          ((IAngelRing) ring).addPower(stack, (int) Math.abs(player.motionX + player.motionY + player.motionY));
                      }

                   //   ((IAngelRing) ring).addPower(stack, charge);


                  }
              }

           }



       }

    }



}
