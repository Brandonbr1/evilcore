package com.jerios.evilMinecraftFixes.mixins.late.fakeOres;

import com.jerios.evilMinecraftFixes.evilOres.mob.EntityNetheriteOre;
import fr.elias.fakeores.common.EntityOresBoss;
import fr.elias.fakeores.common.FakeOres;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(EntityOresBoss.class)
public class MixinOreBoss extends EntityMob {

   @Shadow(remap = false)
   public int phase = 1;

    public MixinOreBoss(World p_i1738_1_) {
        super(p_i1738_1_);
    }

    @Inject(method = "onDeath", at=@At("TAIL"))
    private void evil$injectOnDeath(DamageSource sourceOfDamage, CallbackInfo ci) {
        for (int i = 0; i < 4; i++) {
            EntityOresBoss ore = ((EntityOresBoss)(Object)this);
            ore.spawnSomeSbires("all", ore.posX + ore.worldObj.rand.nextInt(3), ore.posY + 5, ore.posZ + ore.worldObj.rand.nextInt(3));
        }
    }

    @Inject(method = "attackEntity", at=@At("TAIL"))
    private void evil$injectOnAttack(Entity entity, float f, CallbackInfo ci) {
        EntityOresBoss oresBoss = ((EntityOresBoss)(Object)this);
        EntityPlayer globalPlayer = null;
        if (entity instanceof EntityPlayer) {
            globalPlayer = (EntityPlayer) entity;
        }

        if (oresBoss.phase == 2) {
            if (rand.nextInt(240) == 0) {
                EntityNetheriteOre ore = new EntityNetheriteOre(oresBoss.worldObj);
                ore.setPosition(oresBoss.posX + rand.nextInt(5), oresBoss.posY + 3.0D, oresBoss.posZ + rand.nextInt(5));
                oresBoss.worldObj.spawnEntityInWorld(ore);
            }
        }


        if (this.attackTime <= 10 + this.worldObj.difficultySetting.getDifficultyId()) {
            if (oresBoss.getHealth() <= 200) {

                if (globalPlayer != null) {
                    globalPlayer.motionY += 0.6D;
                    globalPlayer.isAirBorne = true;
                }

                if (this.rand.nextInt(30) == 0) {
                    for (int i = 0; i < 4 + this.worldObj.difficultySetting.getDifficultyId(); i++) {
                        this.attackEntityWithRangedAttack((EntityLivingBase)entity, f);
                    }

                }

            }

            if (oresBoss.getHealth() <= 100 ) {

                if (globalPlayer != null) {
                    globalPlayer.motionY += 0.7D;
                    globalPlayer.isAirBorne = true;
                }

                if (this.rand.nextInt(25) == 0) {
                    for (int i = 0; i < 6 + + this.worldObj.difficultySetting.getDifficultyId(); i++) {
                        this.attackEntityWithRangedAttack((EntityLivingBase)entity, f);
                    }
                }
            }
        }

    }

    @Shadow
    public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2) {
    }

    /**
     * @author Jerios
     * @reason Make Anti ores blade useful and needed
     */
    @Overwrite
    public boolean attackEntityFrom(DamageSource damagesource, float f) {
        Entity entity = damagesource.getEntity();
        if (entity instanceof EntityLightningBolt) {
            f = 0.0F;
        }
        EntityOresBoss oresBoss = ((EntityOresBoss)(Object)this);

        if (oresBoss.getHealth() <= 150 && oresBoss.getHealth() >= 100) {
            if (rand.nextInt(16) == 0) {
                oresBoss.spawnSomeSbires("all", oresBoss.posX + rand.nextInt(3), oresBoss.posY + 5, oresBoss.posZ + rand.nextInt(3));
            }
        }

        if (oresBoss.getHealth() <= 100 && oresBoss.getHealth() >= 50) {
            if (rand.nextInt(12) == 0) {
                oresBoss.spawnSomeSbires("all", oresBoss.posX + rand.nextInt(3), oresBoss.posY + 5, oresBoss.posZ + rand.nextInt(3));
            }
        }

        if (oresBoss.getHealth() <= 50) {
            if (rand.nextInt(8) == 0) {
                oresBoss.spawnSomeSbires("all", oresBoss.posX + rand.nextInt(3), oresBoss.posY + 5, oresBoss.posZ + rand.nextInt(3));
            }
        }

        if (entity instanceof EntityPlayer) {
            ItemStack itemstack = ((EntityPlayer)entity).getCurrentEquippedItem();
            if (itemstack != null && itemstack.getItem() == FakeOres.antiOresBlade) {
                switch (phase) {
                    case 1:
                        f *= 2.5f;
                        break;
                    case 2:
                        f *= 4.2f;
                        break;
                    case 3:
                        f *= 2.1f;
                        break;
                }
            } else {
                f *= 0.5f;
            }
        }

        return super.attackEntityFrom(damagesource, f);
    }
}
