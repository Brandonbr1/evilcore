package com.jerios.evilMinecraftFixes.mixins.early.hard;

import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityZombie.class)
public class MixinEntityZombie extends EntityMob {

    public MixinEntityZombie(World p_i1738_1_) {
        super(p_i1738_1_);
    }

    @Redirect(method = "<init>", at= @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/EntityAITasks;addTask(ILnet/minecraft/entity/ai/EntityAIBase;)V", ordinal = 9))
    private void evil$increasePlayerPirioty(EntityAITasks instance, int p_75776_1_, EntityAIBase p_75776_2_)
    {
        instance.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false));
    }

    @Redirect(method = "<init>", at= @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/EntityAITasks;addTask(ILnet/minecraft/entity/ai/EntityAIBase;)V", ordinal = 10))
    private void evil$decreaseVillagerPirioty(EntityAITasks instance, int p_75776_1_, EntityAIBase p_75776_2_)
    {
        instance.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, true));
    }

    @Redirect(method = "<init>", at= @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/EntityAITasks;addTask(ILnet/minecraft/entity/ai/EntityAIBase;)V", ordinal = 1))
    private void evil$playerIsZombieLongMemory(EntityAITasks instance, int p_75776_1_, EntityAIBase p_75776_2_) {
        instance.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
    }

    @Redirect(method = "<init>", at= @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/EntityAITasks;addTask(ILnet/minecraft/entity/ai/EntityAIBase;)V", ordinal = 2))
    private void evil$villagerIsShortMemory(EntityAITasks instance, int p_75776_1_, EntityAIBase p_75776_2_) {
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, false));
    }

}
