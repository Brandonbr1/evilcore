package com.jerios.evilMinecraftFixes.mixins.late.specialMobs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import toast.specialMobs.entity.creeper.EntityEnderCreeper;
import toast.specialMobs.entity.creeper.Entity_SpecialCreeper;

@Mixin(Entity_SpecialCreeper.class)
public class MixinEntity_SpecialCreeper extends EntityCreeper {


    @Shadow(remap = false)
    public int fuseTime = 30;
    @Shadow(remap = false)
    public int timeSinceIgnited;

    @Shadow(remap = false)
    public int explosionRadius = 3;

    @Unique
    private boolean isRareCreeper;
    @Unique
    private boolean hasAlreadyActivatedLowHP;

    public MixinEntity_SpecialCreeper(World p_i1733_1_) {
        super(p_i1733_1_);
    }

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

    @Redirect(method = "onUpdate", at = @At(value = "INVOKE", target = "Ltoast/specialMobs/entity/creeper/Entity_SpecialCreeper;setDead()V"))
    private void evil$doNothing(Entity_SpecialCreeper instance)  {

    }

    @Inject(method = "onSpawnWithEgg", at=@At("HEAD"))
    private void evil$injectSpawn(IEntityLivingData data, CallbackInfoReturnable<IEntityLivingData> cir) {
        if (this.rand.nextInt(64) == 0) {
            this.dataWatcher.updateObject(17, (byte) 1);
            this.fuseTime = this.fuseTime - this.rand.nextInt(4) + 2;
            explosionRadius += 1;
            isRareCreeper = true;
        }
    }


    @Redirect(method = "explodeByType", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFZ)Lnet/minecraft/world/Explosion;", ordinal = 0))
    private Explosion evil$redirectCharged(World instance, Entity p_72876_1_, double p_72876_2_, double p_72876_4_, double p_72876_6_, float p_72876_8_, boolean p_72876_9_) {
        boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");


        if (!(((Entity_SpecialCreeper) (Object) this) instanceof EntityEnderCreeper)) {

            timeSinceIgnited = -20;
        }

        return this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ,(float)this.explosionRadius * 2, flag, flag);
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





}
