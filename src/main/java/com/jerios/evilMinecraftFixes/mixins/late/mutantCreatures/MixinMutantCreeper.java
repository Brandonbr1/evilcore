package com.jerios.evilMinecraftFixes.mixins.late.mutantCreatures;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thehippomaster.MutantCreatures.MutantCreeper;
import thehippomaster.MutantCreatures.MutantZombie;

import java.util.Random;

@Mixin(MutantCreeper.class)
public class MixinMutantCreeper extends EntityMob {


    public MixinMutantCreeper(World p_i1738_1_) {
        super(p_i1738_1_);
    }

    @Inject(method = "<init>", at=@At("TAIL"))
    private void evil$Init(World world, CallbackInfo ci) {
        this.isImmuneToFire = true;
    }

    /**
     * @author Jerios
     * @reason Give similar buffs from normal creeper
     */
    @Overwrite
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.35D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(180D);
    }

    @Shadow
    public void setCharged(boolean flag) {
        this.dataWatcher.updateObject(17, (byte)(flag ? 1 : 0));
    }

    @Inject(method = "attackEntityFrom", at=@At("HEAD"), cancellable = true)
    private void evil$noFireDamage(DamageSource source, float dmg, CallbackInfoReturnable<Boolean> cir) {
       if (source.isFireDamage()) {
           cir.setReturnValue(false);
       }
    }


    public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_) {
        MutantCreeper creeper = ((MutantCreeper)(Object)this);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0F + creeper.worldObj.difficultySetting.getDifficultyId());
        Random r = new Random();
        if (r.nextBoolean()) {
            setCharged(true);
        }
        return super.onSpawnWithEgg(p_110161_1_);
    }
}
