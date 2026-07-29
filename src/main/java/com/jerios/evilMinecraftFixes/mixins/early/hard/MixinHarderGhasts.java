package com.jerios.evilMinecraftFixes.mixins.early.hard;

import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityGhast.class)
public class MixinHarderGhasts extends EntityFlying implements IMob {
    public MixinHarderGhasts(World p_i1587_1_) {
        super(p_i1587_1_);
    }


    @Inject(method = "attackEntityFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityFlying;attackEntityFrom(Lnet/minecraft/util/DamageSource;F)Z", ordinal = 0), cancellable = true)
    private void evil$newdamagecalc(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        super.attackEntityFrom(source, amount - this.worldObj.difficultySetting.getDifficultyId());
        ((EntityPlayer)source.getEntity()).triggerAchievement(AchievementList.ghast);
        cir.setReturnValue(true);
        cir.cancel();
    }

    /**
     * @author Jerios
     * @reason Ghasts no longer insta die
     */
  /**  @Overwrite
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else if ("fireball".equals(source.getDamageType()) && source.getEntity() instanceof EntityPlayer)
        {
            super.attackEntityFrom(source, amount - this.worldObj.difficultySetting.getDifficultyId());
            if (this.getHealth() <= 0) {
                ((EntityPlayer) source.getEntity()).triggerAchievement(AchievementList.ghast);
            }
            return true;
        }
        else
        {
            return super.attackEntityFrom(source, amount);
        }
    }
  **/

}
