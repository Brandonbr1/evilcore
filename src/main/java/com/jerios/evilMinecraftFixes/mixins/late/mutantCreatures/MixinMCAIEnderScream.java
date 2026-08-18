package com.jerios.evilMinecraftFixes.mixins.late.mutantCreatures;

import chylex.hee.entity.mob.EntityMobAngryEnderman;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import thehippomaster.MutantCreatures.EndermanClone;
import thehippomaster.MutantCreatures.MutantEnderman;
import thehippomaster.MutantCreatures.ai.MCAIEnderScream;

import java.util.Random;

@Mixin(MCAIEnderScream.class)
public class MixinMCAIEnderScream extends EntityAIBase {

    @Shadow private MutantEnderman mutantEnderman;
    @Shadow private Random rand;
    @Shadow
    public boolean shouldExecute() {
        return false;
    }


    @WrapOperation(method = "updateTask", at= @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;attackEntityFrom(Lnet/minecraft/util/DamageSource;F)Z"))
    private boolean evil$spawnClonesOnScream(Entity instance, DamageSource source, float amount, Operation<Boolean> original) {
        if (instance instanceof EntityLivingBase) {
            EntityLivingBase base = (EntityLivingBase) instance;

            if (base instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) base;

                if (!instance.worldObj.isRemote) {
                    for (int i = 0; i < 8; i++) {
                        EndermanClone endermanClone = new EndermanClone(instance.worldObj);
                        EntityMobAngryEnderman angryEnderman = new EntityMobAngryEnderman(instance.worldObj);
                        endermanClone.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 9000, 1));
                        angryEnderman.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 9000, 1));
                        endermanClone.setPosition(instance.posX, instance.posY, instance.posZ);
                        angryEnderman.setPosition(instance.posX, instance.posY, instance.posZ);
                        instance.worldObj.spawnEntityInWorld(endermanClone);
                        instance.worldObj.spawnEntityInWorld(angryEnderman);


                        endermanClone.setAttackTarget(player);
                        endermanClone.setRevengeTarget(player);

                        angryEnderman.setAttackTarget(player);
                        angryEnderman.setRevengeTarget(player);


                    }
                }


            }
        }


        return original.call(instance, source, amount);
    }

/**  @WrapOperation(method = "updateTask",at= @At(value = "INVOKE", target = "Lthehippomaster/MutantCreatures/MutantEnderman;getDistanceSqToEntity(Lnet/minecraft/entity/Entity;)D"))
    private double evil$spawnAngryEndermenOnScream(MutantEnderman instance, Entity entity, Operation<Double> original) {

        if (!instance.worldObj.isRemote) {
            for (int i = 0; i < 8; i++) {
                EndermanClone endermanClone = new EndermanClone(instance.worldObj);
                endermanClone.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 9000, 1));
                endermanClone.setPosition(instance.posX, instance.posY, instance.posZ);
                instance.worldObj.spawnEntityInWorld(endermanClone);
                if (entity instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) entity;

                    endermanClone.setAttackTarget(player);
                    endermanClone.setRevengeTarget(player);

                }
            }
        }

        return original.call(instance, entity);
  }
**/

  /**
   * @author Jerios
   * @reason Make scream attack more common
   */
  @Overwrite(remap = false)
    private boolean getScreamChance() {
        int chance = 500;

      switch (mutantEnderman.worldObj.difficultySetting) {
          case EASY:
              chance -= 25;
              break;
          case NORMAL:
              chance -= 45;
              break;
          case HARD:
              chance -= 100;
              break;
          case PEACEFUL:
              break;
      }

        if (mutantEnderman.getHealth() <= 95) {
            chance -= 195;
        }

      if (this.mutantEnderman.isWet()) {
          chance -= 80;
      }


        return rand.nextInt(chance) == 0 || rand.nextInt(chance) == 1;
    }

    /**
     * @author Jerios
     * @reason Lower Delay
     */
    @Overwrite
    public void resetTask() {
        this.mutantEnderman.sendAttackPacket(0);
        this.mutantEnderman.screamDelayTick = 0;
    }
}
