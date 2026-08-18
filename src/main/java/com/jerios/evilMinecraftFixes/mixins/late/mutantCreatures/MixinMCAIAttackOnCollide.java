package com.jerios.evilMinecraftFixes.mixins.late.mutantCreatures;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.EnumDifficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import thehippomaster.MutantCreatures.MutantCreeper;
import thehippomaster.MutantCreatures.ai.MCAIAttackOnCollide;

@Mixin(MCAIAttackOnCollide.class)
public class MixinMCAIAttackOnCollide extends EntityAIBase {

   @Shadow EntityCreature attacker;

   @WrapOperation(method = "updateTask", at= @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityCreature;attackEntityAsMob(Lnet/minecraft/entity/Entity;)Z"))
   private boolean evil$explode(EntityCreature instance, Entity entity, Operation<Boolean> original) {
       if (instance instanceof MutantCreeper) {
           instance.worldObj.newExplosion(instance, entity.posX, entity.posY + 0.5, entity.posZ, 0.78f, true,  true);
       }
       return  original.call(instance, entity);
   }

    @Shadow
    public boolean shouldExecute() {
        return false;
    }
}
