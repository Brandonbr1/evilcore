package com.jerios.evilMinecraftFixes.mixins.late.hee;

import chylex.hee.entity.boss.EntityBossEnderDemon;
import chylex.hee.entity.mob.EntityMobAngryEnderman;
import chylex.hee.entity.mob.EntityMobEnderGuardian;
import chylex.hee.entity.weather.EntityWeatherLightningBoltSafe;
import chylex.hee.system.collections.WeightedList;
import chylex.hee.system.util.MathUtil;
import chylex.hee.world.util.SpawnEntry;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityBossEnderDemon.class)
public class MixinEnderDeamon extends EntityFlying {

    private static final WeightedList<SpawnEntry> spawnList = new WeightedList<>(
        new SpawnEntry(EntityEnderman.class,32,100),
        new SpawnEntry(EntityMobAngryEnderman.class,13,23),
        new SpawnEntry(EntityMobEnderGuardian.class,6,5)
    );

    public MixinEnderDeamon(World p_i1587_1_) {
        super(p_i1587_1_);
    }


    @Inject(method = "onDeathUpdate", at=@At("HEAD"))
    private void evil$forceSetNightAgain(CallbackInfo ci) {
        if (!this.worldObj.isRemote) {
            this.worldObj.getWorldInfo().setWorldTime(13333L);
        }
    }

    @Redirect(method = "onSpawnWithEgg", at= @At(value = "INVOKE", target = "Lchylex/hee/entity/boss/EntityBossEnderDemon;setDead()V"))
    private void redirectNull(EntityBossEnderDemon instance){
       if (!instance.worldObj.isRemote) {
           this.worldObj.getWorldInfo().setWorldTime(13333L);

           for (int i = 0; i < 15; i++) {
               SpawnEntry spawnEntry = spawnList.getRandomItem(instance.worldObj.rand);
               EntityLiving entity = spawnEntry.createMob(instance.worldObj);
               entity.addPotionEffect(new PotionEffect(Potion.resistance.id, 60, 1, false));
               entity.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 600, 1, false));
               entity.setLocationAndAngles(instance.posX,instance.posY,instance.posZ,instance.worldObj.rand.nextFloat()*360F,0F);
               entity.func_110163_bv();
               entity.hurtResistantTime = 120;
               instance.worldObj.spawnEntityInWorld(entity);
           }

           // hardcoded spawns
           for (int i = 0; i < 5; i++) {
               EntityLiving entity = new EntityMobAngryEnderman(instance.worldObj);
               entity.addPotionEffect(new PotionEffect(Potion.resistance.id, 60, 1, false));
               entity.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 600, 1, false));
               entity.setLocationAndAngles(instance.posX,instance.posY,instance.posZ,instance.worldObj.rand.nextFloat()*360F,0F);
               entity.func_110163_bv();
               entity.hurtResistantTime = 120;
               instance.worldObj.spawnEntityInWorld(entity);
           }

           for (int i = 0; i < 2; i++) {
               EntityLiving entity = new EntityMobEnderGuardian(instance.worldObj);
               entity.addPotionEffect(new PotionEffect(Potion.resistance.id, 60, 1, false));
               entity.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 600, 1, false));
               entity.setLocationAndAngles(instance.posX,instance.posY,instance.posZ,instance.worldObj.rand.nextFloat()*360F,0F);
               entity.func_110163_bv();
               entity.hurtResistantTime = 120;
               instance.worldObj.spawnEntityInWorld(entity);
           }

       }

        MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(
            EnumChatFormatting.DARK_PURPLE+ "A creature from a different world is ready to fight, bringing an army with it..."
        ));

    }

}
