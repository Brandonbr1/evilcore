package com.jerios.evilMinecraftFixes.mixins.late.fakeOres;

import com.jerios.evilMinecraftFixes.evilOres.mob.EntityNetheriteOre;
import com.jerios.evilMinecraftFixes.evilOres.IGetNewPhase;
import fr.elias.fakeores.common.EntityOresBoss;
import fr.elias.fakeores.common.FakeOres;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(EntityOresBoss.class)
public class MixinOreBoss extends EntityMob implements IGetNewPhase {

   @Shadow(remap = false)
   public int phase = 1;
   @Unique
   public int evil$newPhase = 0;
    @Unique
   public int evil$spawnAmm;

    public MixinOreBoss(World p_i1738_1_) {
        super(p_i1738_1_);
    }

    @Inject(method = "onDeath", at=@At("TAIL"))
    private void evil$injectOnDeath(DamageSource sourceOfDamage, CallbackInfo ci) {
        for (int i = 0; i < 4; i++) {
            EntityOresBoss ore = ((EntityOresBoss)(Object)this);
            if (!ore.worldObj.isRemote) {
                ore.spawnSomeSbires("all", ore.posX + ore.worldObj.rand.nextInt(3), ore.posY + 5, ore.posZ + ore.worldObj.rand.nextInt(3));
            }
        }
    }

    @Inject(method = "onLivingUpdate", at=@At("HEAD"))
    private void evil$injectUpdate(CallbackInfo ci) {
        EntityOresBoss ore = ((EntityOresBoss)(Object)this);

        boolean canThrowUp = ore.getHealth() <= 200;

        if(evil$newPhase == 0 && canThrowUp) {
            evil$newPhase = 1;
        } else if (evil$newPhase == 1 && ore.getHealth() <= 100) {
            evil$newPhase = 2;
        } else if (evil$newPhase == 2 && ore.getHealth() <= 50) {
            evil$newPhase = 3;
        }

        if (canThrowUp) {
            evil$attackTimer++;

            if (evil$attackTimer % 20 == 0) {
                evil$attackTimer += ore.worldObj.difficultySetting.getDifficultyId();
                evil$attackTimer += evil$newPhase;
            }
        }


    }

    @Inject(method = "spawnSomeSbires", at=@At("HEAD"), remap = false, cancellable = true)
    private void evil$injectAuthoritySafety(String entityToSpawn, double x, double y, double z, CallbackInfo ci) {
        EntityOresBoss ore = ((EntityOresBoss)(Object)this);
        if (ore.worldObj.isRemote) {
            ci.cancel();
        }

    }

    @Inject(method = "spawnSomeSbires",at= @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntityInWorld(Lnet/minecraft/entity/Entity;)Z", ordinal = 12))
    private void evil$injectNetheriteOnSpawnList(String entityToSpawn, double x, double y, double z, CallbackInfo ci) {
        EntityOresBoss ore = ((EntityOresBoss)(Object)this);
        EntityNetheriteOre netheriteOre = new EntityNetheriteOre(ore.worldObj);
        netheriteOre.setPosition(x, y, z);
        ore.worldObj.spawnEntityInWorld(netheriteOre);
    }

    @Unique
    int evil$attackTimer = 0;


    @Inject(method = "attackEntity", at=@At("TAIL"))
    private void evil$injectOnAttack(Entity entity, float f, CallbackInfo ci) {
        EntityOresBoss oresBoss = ((EntityOresBoss)(Object)this);
        EntityPlayer globalPlayer = null;
        if (entity instanceof EntityPlayer) {
            globalPlayer = (EntityPlayer) entity;
        }

        if (this.getHealth() <= 200) {
            double d0 = entity.posX - this.posX;
            double d1 = entity.boundingBox.minY + (double)(entity.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
            double d2 = entity.posZ - this.posZ;
            if (this.rand.nextInt(12) == 0) {
                float f1 = MathHelper.sqrt_float(f) * 0.5F;
                this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1009, (int)this.posX, (int)this.posY, (int)this.posZ, 0);

                for(int i = 0; i < 2 + this.worldObj.difficultySetting.getDifficultyId(); ++i) {
                    EntityLargeFireball entitysmallfireball = new EntityLargeFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
                    entitysmallfireball.posY = this.posY + (double)(this.height / 2.0F) + (double)0.5F;
                    this.worldObj.spawnEntityInWorld(entitysmallfireball);
                }
            }

            if (this.rand.nextInt(30) == 0) {
                this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, this.rand.nextInt(2) == 0 ? this.posX : entity.posX, this.rand.nextInt(2) == 0 ? this.posY : entity.posY, this.rand.nextInt(2) == 0 ? this.posZ : entity.posZ));
            }
        }

        if (this.getHealth() <= 100) {
            double d0 = entity.posX - this.posX;
            double d1 = entity.boundingBox.minY + (double)(entity.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
            double d2 = entity.posZ - this.posZ;
            if (this.rand.nextInt(6) == 0) {
                float f1 = MathHelper.sqrt_float(f) * 0.5F;
                this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1009, (int)this.posX, (int)this.posY, (int)this.posZ, 0);

                for(int i = 0; i < 2 + this.worldObj.difficultySetting.getDifficultyId(); ++i) {
                    EntityLargeFireball entitysmallfireball = new EntityLargeFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
                    entitysmallfireball.posY = this.posY + (double)(this.height / 2.0F) + (double)0.5F;
                    this.worldObj.spawnEntityInWorld(entitysmallfireball);
                }
            }

            if (this.rand.nextInt(25) == 0) {
                this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, this.rand.nextInt(2) == 0 ? this.posX : entity.posX, this.rand.nextInt(2) == 0 ? this.posY : entity.posY, this.rand.nextInt(2) == 0 ? this.posZ : entity.posZ));
            }
        }




        if (evil$attackTimer > 200) {
            if (oresBoss.getHealth() <= 200) {

                if (globalPlayer != null) {
                    globalPlayer.motionY += 0.4D;
                    globalPlayer.isAirBorne = true;
                }

                if (this.rand.nextInt(30) == 0) {
                    for (int i = 0; i < 4 + this.worldObj.difficultySetting.getDifficultyId(); i++) {
                        this.attackEntityWithRangedAttack((EntityLivingBase)entity, f);
                    }

                }

            }

            if (oresBoss.getHealth() <= 100) {

                if (globalPlayer != null) {
                    globalPlayer.motionY += 0.3D;
                    globalPlayer.isAirBorne = true;
                }

                if (this.rand.nextInt(25) == 0) {
                    for (int i = 0; i < 6 + + this.worldObj.difficultySetting.getDifficultyId(); i++) {
                        this.attackEntityWithRangedAttack((EntityLivingBase)entity, f);
                    }
                }
            }
            evil$attackTimer = 0;
        }

    }

    @Shadow(remap = false)
    public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2) {
    }

    /**
     * @author Jerios
     * @reason Make Anti ores blade useful and needed
     */
    @Overwrite
    public boolean attackEntityFrom(DamageSource damagesource, float f) {
        Random rand = new Random();
        Entity entity = damagesource.getEntity();
        if (entity instanceof EntityLightningBolt) {
            f = 0.0F;
        }
        EntityOresBoss oresBoss = ((EntityOresBoss)(Object)this);

        if (!oresBoss.worldObj.isRemote) {
            if (oresBoss.getHealth() <= 150) {
                if (evil$spawnAmm >= 1) {
                    evil$spawnAmm++;
                    oresBoss.spawnSomeSbires("all", oresBoss.posX + rand.nextInt(3), oresBoss.posY + 5, oresBoss.posZ + rand.nextInt(3));
                }
            }

            if (oresBoss.getHealth() <= 100) {
                if (evil$spawnAmm >= 2) {
                    evil$spawnAmm++;
                    oresBoss.spawnSomeSbires("all", oresBoss.posX + rand.nextInt(3), oresBoss.posY + 5, oresBoss.posZ + rand.nextInt(3));
                }
            }

            if (oresBoss.getHealth() <= 50) {
                if (evil$spawnAmm >= 2) {
                    evil$spawnAmm++;
                    oresBoss.spawnSomeSbires("all", oresBoss.posX + rand.nextInt(3), oresBoss.posY + 5, oresBoss.posZ + rand.nextInt(3));
                }
            }

        }


        if (entity instanceof EntityPlayer) {
            ItemStack itemstack = ((EntityPlayer)entity).getCurrentEquippedItem();
            if (itemstack != null && itemstack.getItem() == FakeOres.antiOresBlade) {
                switch (phase) {
                    case 1:
                        f *= 1.5f;
                        break;
                    case 2:
                        f *= 1.8f;
                        break;
                    case 3:
                        f *= 1.3f;
                        break;
                }
            } else {
                if (evil$newPhase == 1) {
                    f = 0;
                } else {
                    f *= 0.3f;
                }

            }
        }

        return super.attackEntityFrom(damagesource, f);
    }

    @Override
    public int evil$getPhase() {
        return evil$newPhase;
    }
}
