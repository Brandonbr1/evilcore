package com.jerios.evilMinecraftFixes.athena;

import com.jerios.evilMinecraftFixes.Evil;
import com.jerios.evilMinecraftFixes.mixins.Config;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class AthenaEvents {



    @SubscribeEvent
    public void witherDeath(LivingDeathEvent event) {
        if (!event.entityLiving.worldObj.isRemote) {
            if (event.entityLiving instanceof EntityWither) {
                EntityWither wither = (EntityWither) event.entityLiving;
                World world =  wither.worldObj;
                EntityWitherDeathManager deathManager = new EntityWitherDeathManager(world);
                deathManager.setLocationAndAngles(wither.posX + 0.5D, wither.posY, wither.posZ + 0.5D, 0.0F, 0.0F);
                world.spawnEntityInWorld(deathManager);
            }
        }
    }

    @SubscribeEvent
    public void damageCap(LivingHurtEvent hurtEvent) {
        if (hurtEvent.entity instanceof EntityWither) {
            EntityWither wither = (EntityWither) hurtEvent.entity;

            if (wither.getHealth() <= wither.getMaxHealth() / 2.3F) {
                hurtEvent.ammount *= 0.3f;
            }

            hurtEvent.ammount = Math.min(15, hurtEvent.ammount);
        }


        if (hurtEvent.entity instanceof EntitySkeleton) {
            EntitySkeleton ske = (EntitySkeleton) hurtEvent.entity;
            WitherProps props = WitherProps.get(ske);
            if (props != null) {

                if (props.immuneToExplosions) {
                    if (hurtEvent.source.isExplosion() && hurtEvent.source.isFireDamage()) {
                        hurtEvent.setCanceled(true);
                    }
                }

            }

        }
    }


    @SubscribeEvent
    public void mobTickEvent(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving.worldObj.isRemote) return;

        if (event.entityLiving instanceof EntityWither) {
            EntityWither wither = (EntityWither) event.entityLiving;
            World world = wither.worldObj;

            WitherProps props = WitherProps.get(wither);
            if (props == null) return;

            props.ticks++;


            if (wither.isArmored()) {
                wither.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.1D);
                // 64 blocks instead of 32
                List list = wither.worldObj.getEntitiesWithinAABBExcludingEntity(wither, wither.boundingBox.expand(64,32,64));


                if (props.ticks % Config.healTimer == 0) {
                    wither.heal(Config.healAmm);
                }

                for (int i = 0; i < list.size(); i++) {

                    Entity entity = (Entity) list.get(i);

                    if (entity instanceof EntityLivingBase) {
                        EntityLivingBase base = (EntityLivingBase) entity;
                        if (!(base instanceof IMob)) {
                            base.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 300, 2));
                            base.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 300, 4));
                            base.addPotionEffect(new PotionEffect(Potion.hunger.id, 300, 4));
                        }

                        if (base instanceof EntityPlayer) {

                            EntityPlayer player = (EntityPlayer) base;

                            // new
                            boolean desperateWither = wither.getHealth() <= 45;
                            if (desperateWither) {
                                int timer2 = 45;
                                player.addPotionEffect(new PotionEffect(Potion.confusion.id, 300, 4));

                                if (props.ticks % 170 == 0) {
                                    while (timer2 > 0) {
                                        timer2--;

                                        System.out.println(timer2);

                                        int YP = MathHelper.floor_double(wither.posY);
                                        int i1 = MathHelper.floor_double(wither.posX);
                                        int j1 = MathHelper.floor_double(wither.posZ);
                                        boolean flag = false;

                                        for (int l1 = -1; l1 <= 3; ++l1)
                                        {
                                            for (int i2 = -1; i2 <= 3; ++i2)
                                            {
                                                for (int j = -3; j <= 3; ++j)
                                                {
                                                    int j2 = i1 + l1;
                                                    int k = YP + j;
                                                    int l = j1 + i2;
                                                    Block block = world.getBlock(j2, k, l);

                                                    if (!block.isAir(world, j2, k, l) && block.canEntityDestroy(world, j2, k, l, wither))
                                                    {
                                                        flag = world.func_147480_a(j2, k, l, true) || flag;
                                                    }
                                                }
                                            }
                                        }

                                        if (timer2 <= 3) {
                                            props.forcePinchMode = true;
                                        } else {
                                            props.forcePinchMode = false;
                                        }

                                    }

                                    if (props.ticks % 180 == 0) {
                                        for (int j = 0; j < 4 + world.rand.nextInt(2); j++) {
                                            Evil.spawnSkeketonMinionNearPlayer(new EntitySkeleton(world), world, player, null);
                                        }
                                    }

                                }

                            }

                            // TODO: ADD CHARGE ATTACK! IDK HOW THO


                            if (props.ticks % 180 == 0) {
                                for (int j = 0; j < 4 + world.rand.nextInt(2); j++) {
                                    Evil.spawnSkeketonMinionNearPlayer(new EntitySkeleton(world), world, player, null);
                                }
                            }




                            if (props.ticks % 160 == 0) {
                                for (int j = 0; j < 4; j++) {
                                    ItemStack[] g = player.inventory.armorInventory;


                                    for (int k = 0; k < g.length; k++) {
                                        ItemStack stack = g[k];

                                        if (stack != null && stack.getItem() != null) {
                                            stack.damageItem(player.worldObj.rand.nextInt(4) + 2, player);
                                        }

                                    }


                                }

                                ItemStack heildItem = player.inventory.getCurrentItem();

                                if (heildItem != null && heildItem.getItem() != null) {
                                    if (heildItem.isItemStackDamageable()) {
                                        heildItem.damageItem(player.worldObj.rand.nextInt(16) + 2, player);
                                    }
                                }

                            }


                        }
                    }

                }

                if (wither.getHealth() <= wither.getMaxHealth() / 1.8F) {
                    if (!props.exploded) {
                        // fire
                        world.newExplosion(wither, wither.posX, wither.posY, wither.posZ, 8.0F, true, true);
                        props.exploded = true;
                    }
                }


            }

        }
    }



}
