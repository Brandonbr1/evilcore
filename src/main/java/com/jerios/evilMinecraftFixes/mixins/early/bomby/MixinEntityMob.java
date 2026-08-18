package com.jerios.evilMinecraftFixes.mixins.early.bomby;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import thehippomaster.MutantCreatures.CreeperMinion;

@Mixin(EntityMob.class)
public class MixinEntityMob extends EntityCreature {
    public MixinEntityMob(World p_i1602_1_) {
        super(p_i1602_1_);
    }

    @WrapOperation(method = "onUpdate",at= @At(value = "INVOKE", target = "Lnet/minecraft/entity/monster/EntityMob;setDead()V"))
    private void evil$doNotDespawnBomby(EntityMob instance, Operation<Void> original) {
      if (instance instanceof CreeperMinion) {
          CreeperMinion minion = (CreeperMinion) instance;
          if (!minion.getTamed()) {
              original.call(instance);
          }
      } else {
          original.call(instance);
      }

    }

}
