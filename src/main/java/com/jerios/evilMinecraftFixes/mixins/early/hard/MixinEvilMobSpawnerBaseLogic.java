package com.jerios.evilMinecraftFixes.mixins.early.hard;

import com.jerios.evilMinecraftFixes.cfg.Config;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(MobSpawnerBaseLogic.class)
public abstract class MixinEvilMobSpawnerBaseLogic {

    @Shadow public int spawnDelay = 20;
    @Shadow private int spawnCount = 4;
    @Shadow private int maxNearbyEntities = 6;
    @Shadow private int activatingRangeFromPlayer = 16;
    @Shadow private int minSpawnDelay = 200;
    @Shadow private int maxSpawnDelay = 800;
    @Unique Random evil$rand;
    @Unique boolean rareSpawner;

    @Inject(method = "<init>", at=@At("TAIL"))
    private void evil$inject(CallbackInfo ci) {
        evil$rand = new Random();
        spawnDelay = Config.initalSpawnDelay;
        maxNearbyEntities = Config.maxMobs;
        activatingRangeFromPlayer = Config.playerActivationDistance;
        minSpawnDelay = Config.minTimer;
        maxSpawnDelay = Config.maxTimer;
        if (evil$rand.nextInt(12) == 0) {
            rareSpawner = true;
            minSpawnDelay -= (60 + evil$rand.nextInt(20));
            maxSpawnDelay -= (250 + evil$rand.nextInt(140));
        }
    }


    @Inject(method = "readFromNBT", at=@At("HEAD"))
    private void evil$readNbt(NBTTagCompound p_98270_1_, CallbackInfo ci) {
        rareSpawner = p_98270_1_.getBoolean("IsRareSpawner");
    }

    @Inject(method = "writeToNBT", at=@At("HEAD"))
    private void evil$writeNbt(NBTTagCompound p_98280_1_, CallbackInfo ci) {
        p_98280_1_.setBoolean("IsRareSpawner", rareSpawner);
    }
    @Shadow
    public abstract int getSpawnerX();
    @Shadow
    public abstract int getSpawnerY();
    @Shadow
    public abstract int getSpawnerZ();

    @Shadow
    public abstract World getSpawnerWorld();

    @Shadow
    public String getEntityNameToSpawn()
    {
        return null;
    }

    @Inject(method = "updateSpawner", at= @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnParticle(Ljava/lang/String;DDDDDD)V"))
    private void evil$addParticle(CallbackInfo ci) {
        if (rareSpawner) {
            double d2;
            double d0 = (double) ((float) this.getSpawnerX() + this.getSpawnerWorld().rand.nextFloat());
            double d1 = (double) ((float) this.getSpawnerY() + this.getSpawnerWorld().rand.nextFloat());
            d2 = (double) ((float) this.getSpawnerZ() + this.getSpawnerWorld().rand.nextFloat());
            this.getSpawnerWorld().spawnParticle("mobSpell", d0, d1, d2, 0.3D, 0.2D, 0.1D);
        }
    }

    @WrapOperation(method = "updateSpawner", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setLocationAndAngles(DDDFF)V"))
    private void evil$i(Entity instance, double x, double y, double z, float yaw, float pitch, Operation<Void> original) {
        original.call(instance, x,y,z,yaw,pitch);
        if (rareSpawner) {
            ((EntityLiving) instance).addPotionEffect(new PotionEffect(Potion.damageBoost.id, 5000, 0, false));
            ((EntityLiving) instance).addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 5000, 0, false));
            ((EntityLiving) instance).addPotionEffect(new PotionEffect(Potion.resistance.id, 5000, 0, false));
        }
    }



}
