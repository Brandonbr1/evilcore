package com.jerios.evilMinecraftFixes.mixins.early.race;

import com.jerios.evilMinecraftFixes.IGolemAttacker;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityIronGolem.class)
public class MixinIronGolem extends EntityGolem {

    public MixinIronGolem(World p_i1686_1_) {
        super(p_i1686_1_);
    }

    @Redirect(method = "<init>", at= @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/EntityAITasks;addTask(ILnet/minecraft/entity/ai/EntityAIBase;)V", ordinal = 10))
    private void evil$(EntityAITasks instance, int p_75776_1_, EntityAIBase p_75776_2_) {
        instance.addTask(3, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, true, IGolemAttacker.mobSelector));
    }
}
