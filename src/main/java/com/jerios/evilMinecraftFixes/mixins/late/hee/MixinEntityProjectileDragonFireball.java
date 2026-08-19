package com.jerios.evilMinecraftFixes.mixins.late.hee;

import chylex.hee.entity.projectile.EntityProjectileDragonFireball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(EntityProjectileDragonFireball.class)
public class MixinEntityProjectileDragonFireball extends EntityFireball {


    public MixinEntityProjectileDragonFireball(World p_i1759_1_) {
        super(p_i1759_1_);
    }

    @Inject(method = "attackEntityFrom", at= @At(value = "INVOKE", target = "Lchylex/hee/system/util/DragonUtil;createMobExplosion(Lnet/minecraft/entity/Entity;DDDFZ)V"))
    private void evil$e(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {

        if (!this.worldObj.isRemote) {
            List<EntityPlayer> list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(8, 8, 8));

            for (int i = 0; i < list.size(); i++) {
                Entity e = list.get(i);

                if (e instanceof EntityPlayer) {
                    EntityPlayer living = (EntityPlayer) e;
                    living.addPotionEffect(new PotionEffect(Potion.hunger.id, 200, 3));
               //     living.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 200, 2));
                    living.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200, 2));
                }



            }


        }

    }


    @Inject(method = "attackEntityFrom", at= @At(value = "INVOKE", target = "Lchylex/hee/system/util/DragonUtil;createMobExplosion(Lnet/minecraft/world/World;DDDFZ)V"))
    private void evil$k(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {

        if (!this.worldObj.isRemote) {
            List<EntityPlayer> list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(8, 8, 8));

            for (int i = 0; i < list.size(); i++) {
                Entity e = list.get(i);

                if (e instanceof EntityPlayer) {
                    EntityPlayer living = (EntityPlayer) e;
                    living.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200, 2));
                }



            }


        }

    }



    @Inject(method = "onImpact", at= @At(value = "INVOKE", target = "Lchylex/hee/system/util/DragonUtil;createMobExplosion(Lnet/minecraft/entity/Entity;DDDFZ)V"))
   private void evil$b(MovingObjectPosition mop, CallbackInfo ci) {

       if (!this.worldObj.isRemote) {
           List<EntityPlayer> list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(8, 8, 8));

           for (int i = 0; i < list.size(); i++) {
               Entity e = list.get(i);

               if (e instanceof EntityPlayer) {
                   EntityPlayer living = (EntityPlayer) e;
                   living.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200, 2));
               }



           }


       }

   }


    @Inject(method = "onImpact", at= @At(value = "INVOKE", target = "Lchylex/hee/system/util/DragonUtil;createMobExplosion(Lnet/minecraft/world/World;DDDFZ)V"), remap = false)
    private void evil$a(MovingObjectPosition mop, CallbackInfo ci) {

        if (!this.worldObj.isRemote) {
            List<EntityPlayer> list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(8, 8, 8));

            for (int i = 0; i < list.size(); i++) {
                Entity e = list.get(i);

                if (e instanceof EntityPlayer) {
                    EntityPlayer living = (EntityPlayer) e;
                    living.addPotionEffect(new PotionEffect(Potion.hunger.id, 200, 9));
                    living.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 200, 2));
                    living.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200, 2));
                }



            }


        }

    }

    @Shadow
    protected void onImpact(MovingObjectPosition p_70227_1_) {

    }
}
