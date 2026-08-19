package com.jerios.evilMinecraftFixes.mixins.late.mutantCreatures;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import thehippomaster.MutantCreatures.MutantZombie;
import thehippomaster.MutantCreatures.Zombie;

@Mixin(MutantZombie.class)
public class MixinMutantZombie extends EntityMob {

    public MixinMutantZombie(World p_i1738_1_) {
        super(p_i1738_1_);
    }

        @Shadow(remap = false)
        public int getLives() {
        return this.dataWatcher.getWatchableObjectByte(20);
    }


    public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_) {
        MutantZombie mutantZombie = ((MutantZombie)(Object)this);
        this.dataWatcher.updateObject(20,  (byte)((byte) this.getLives() + (byte) mutantZombie.worldObj.difficultySetting.getDifficultyId( )));
        return super.onSpawnWithEgg(p_110161_1_);
    }

    @WrapOperation(method = "onDeathUpdate",at = @At(value = "INVOKE", target = "Lthehippomaster/MutantCreatures/MutantZombie;setHealth(F)V"))
    private void evil$strongerHeals(MutantZombie instance, float v, Operation<Void> original) {
                original.call(instance, v);
                instance.heal(v);

        for (int i = 0; i < 4 + instance.worldObj.difficultySetting.getDifficultyId() + getTotalArmorValue(); i++) {
           if (!instance.worldObj.isRemote) {
               Zombie zombie = new Zombie(instance.worldObj);
               zombie.setPosition(instance.posX, instance.posY, instance.posZ);
               instance.worldObj.spawnEntityInWorld(zombie);
           }

        }
    }

    @ModifyArgs(method = "interact", at= @At(value = "INVOKE", target = "Lthehippomaster/MutantCreatures/MutantZombie;setFire(I)V"))
    private void evil$injected(Args args) {
        args.set(0, 2);
    }


    @Override
    public int getTotalArmorValue() {
        return 4 + getLives();
    }
}
