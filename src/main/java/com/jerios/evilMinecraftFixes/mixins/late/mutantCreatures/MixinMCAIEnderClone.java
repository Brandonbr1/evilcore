package com.jerios.evilMinecraftFixes.mixins.late.mutantCreatures;

import chylex.hee.entity.mob.EntityMobAngryEnderman;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import thehippomaster.MutantCreatures.EndermanClone;
import thehippomaster.MutantCreatures.ai.MCAIEnderClone;

@Mixin(MCAIEnderClone.class)
public class MixinMCAIEnderClone extends EntityAIBase {
    @Shadow private EntityLivingBase attackTarget;

    @WrapOperation(method = "startExecuting", at= @At(value = "INVOKE", target = "Lthehippomaster/MutantCreatures/EndermanClone;setAttackTarget(Lnet/minecraft/entity/EntityLivingBase;)V"))
    private void evil$cloneSpawnAngryEndermen(EndermanClone instance, EntityLivingBase entityLivingBase, Operation<Void> original) {

                EntityMobAngryEnderman angryEnderman = new EntityMobAngryEnderman(instance.worldObj);
                double x = this.attackTarget.posX + (double)((angryEnderman.getRNG().nextFloat() - 0.5F) * 24.0F);
                double z = this.attackTarget.posZ + (double)((angryEnderman.getRNG().nextFloat() - 0.5F) * 24.0F);
                double y = this.attackTarget.posY + (double)8.0F;

                angryEnderman.setPosition(x, y, z);
                instance.worldObj.spawnEntityInWorld(angryEnderman);
                System.out.println("TESTING");
                if (entityLivingBase instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) entityLivingBase;
                    angryEnderman.setAttackTarget(player);
                    angryEnderman.setRevengeTarget(player);
                }




        original.call(instance, entityLivingBase);
    }


    @Shadow
    public boolean shouldExecute() {
        return false;
    }
}
