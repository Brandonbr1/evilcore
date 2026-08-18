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
import org.spongepowered.asm.mixin.injection.Redirect;

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

    @Redirect(method = "onSpawnWithEgg", at= @At(value = "INVOKE", target = "Lchylex/hee/entity/boss/EntityBossEnderDemon;setDead()V"))
    private void redirectNull(EntityBossEnderDemon instance){
       if (!instance.worldObj.isRemote) {

           for (int i = 0; i < 15; i++) {
               SpawnEntry spawnEntry = spawnList.getRandomItem(instance.worldObj.rand);
               EntityLiving entity = spawnEntry.createMob(instance.worldObj);
               entity.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 600, 2, false));
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

    @Unique
    private void spawnEndermen(EntityLiving skeleton, World world, int x, int y, int z) {
        skeleton.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
        skeleton.rotationYawHead = skeleton.rotationYaw;
        skeleton.renderYawOffset = skeleton.rotationYaw;
        skeleton.onSpawnWithEgg(null);
        world.spawnEntityInWorld(skeleton);
    }

    @Unique
    private void doRandomLightning(World world, int chanceRange, int chance){
        if (world.rand.nextInt(chanceRange) >= chance || world.playerEntities.size() == 0)return;

        EntityPlayer randPlayer = (EntityPlayer)world.playerEntities.get(world.rand.nextInt(world.playerEntities.size()));
        double x = randPlayer.posX+world.rand.nextGaussian()*70D,
            z = randPlayer.posZ+world.rand.nextGaussian()*70D,
            y = world.getPrecipitationHeight((int)Math.floor(x),(int)Math.floor(z));

        if (world.rand.nextInt(5) != 0 && MathUtil.distance(x-randPlayer.posX,z-randPlayer.posZ) < 40D)return;

        if (world.rand.nextInt(8) == 0)world.addWeatherEffect(new EntityLightningBolt(world,x,y,z));
        else world.addWeatherEffect(new EntityWeatherLightningBoltSafe(world,x,y,z));
    }


}
