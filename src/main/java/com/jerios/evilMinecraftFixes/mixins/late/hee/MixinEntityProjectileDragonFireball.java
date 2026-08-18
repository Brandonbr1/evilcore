package com.jerios.evilMinecraftFixes.mixins.late.hee;

import chylex.hee.entity.projectile.EntityProjectileDragonFireball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(EntityProjectileDragonFireball.class)
public class MixinEntityProjectileDragonFireball extends EntityFireball {


    public MixinEntityProjectileDragonFireball(World p_i1759_1_) {
        super(p_i1759_1_);
    }

    @Inject(method = "onImpact", at= @At(value = "INVOKE", target = "Lchylex/hee/system/util/DragonUtil;createMobExplosion(Lnet/minecraft/world/World;DDDFZ)V"), remap = false)
    private void evil$a(MovingObjectPosition mop, CallbackInfo ci) {

        if (!this.worldObj.isRemote) {
            List<EntityLiving> list = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, this.boundingBox.expand(4, 4, 4));

            for (int i = 0; i < list.size(); i++) {
                Entity e = list.get(i);

                if (e instanceof EntityLiving) {
                    EntityLiving living = (EntityLiving) e;
                    living.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200, 2));
                }



            }


        }

    }

    @Shadow
    protected void onImpact(MovingObjectPosition p_70227_1_) {

    }
}
