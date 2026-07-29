package com.jerios.evilMinecraftFixes.mixins.early.hard;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityCreeper.class)
public class MixinCreeperEntity extends EntityMob {

    @Shadow private int fuseTime = 30;
    @Shadow private int timeSinceIgnited;

    @Unique
    private boolean isRareCreeper;
    @Unique
    private boolean hasAlreadyActivatedLowHP;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void evil$inject(CallbackInfo ci) {
        this.getNavigator().setBreakDoors(true);
        this.isImmuneToFire = true;
    }

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void evil$injectOnUpdate(CallbackInfo ci) {
        if (this.getHealth() <= 12) {
            if (!hasAlreadyActivatedLowHP) {
                this.fuseTime = this.fuseTime - 5;

                hasAlreadyActivatedLowHP = true;
                double currentSpeed = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
                double newSpeed = currentSpeed + 0.05D;
                this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(newSpeed);

            }
        }

        if (isRareCreeper) {
            this.worldObj.spawnParticle("mobSpell", this.posX + this.rand.nextGaussian() * 1.0D, this.posY + (double)(this.rand.nextFloat() * 3.3F), this.posZ + this.rand.nextGaussian() * 1.0D, 0.699999988079071D, 0.699999988079071D, 0.8999999761581421D);
        }

    }

    @Shadow private int explosionRadius = 3;

    public MixinCreeperEntity(World p_i1738_1_) {
        super(p_i1738_1_);
    }

    /**
     * @author jerios
     * @reason Stronger creeper
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (source.isExplosion()) {
            return false;
        }
        return super.attackEntityFrom(source, amount);
    }


    /**
     * @author jerios
     * @reason Stronger creeper
     */
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_)
    {
        if (this.rand.nextInt(64) == 0) {
            this.dataWatcher.updateObject(17, (byte) 1);
            this.fuseTime = this.fuseTime - this.rand.nextInt(4) + 2;
            explosionRadius += 1;
            isRareCreeper = true;
        }

        return super.onSpawnWithEgg(p_110161_1_);
    }

    @Inject(method = "readEntityFromNBT", at = @At("TAIL"))
    private void evil$injectRead(NBTTagCompound tag, CallbackInfo callbackInfo) {
        tag.setBoolean("rareCreeperEvil", isRareCreeper);
        tag.setBoolean("rareAlreadyLowHp", hasAlreadyActivatedLowHP);

    }

    @Inject(method = "writeEntityToNBT", at = @At("TAIL"))
    private void evil$injectWrite(NBTTagCompound tag, CallbackInfo callbackInfo) {
        tag.getBoolean("rareCreeperEvil");
        tag.getBoolean("rareAlreadyLowHp");
    }

    /**
     * @author jerios
     *  @reason Stronger creeper
     * @return
     */

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.35D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60D);
    }

    @Redirect(method = "func_146077_cc", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/monster/EntityCreeper;setDead()V"))
    private void evil$dontDie(EntityCreeper instance) {

    }

    @Redirect(method = "func_146077_cc", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFZ)Lnet/minecraft/world/Explosion;", ordinal = 0))
    private Explosion evil$redirectCharged(World instance, Entity p_72876_1_, double p_72876_2_, double p_72876_4_, double p_72876_6_, float p_72876_8_, boolean p_72876_9_) {
        boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");

        timeSinceIgnited = -20;

        return this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ,(float)this.explosionRadius * 2, flag, flag);
    }

    @Redirect(method = "func_146077_cc", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFZ)Lnet/minecraft/world/Explosion;", ordinal = 1))
    private Explosion evil$redirectNonCharged(World instance, Entity p_72876_1_, double p_72876_2_, double p_72876_4_, double p_72876_6_, float p_72876_8_, boolean p_72876_9_) {
        boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");

        timeSinceIgnited = 0;

        return this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ,(float)this.explosionRadius, flag, flag);
    }

    @Override
    protected void dropRareDrop(int p_70600_1_) {
        this.dropItem(Items.diamond, 1);
    }

    @Override
    protected Item getDropItem() {
        return Items.gold_ingot;
    }

}
