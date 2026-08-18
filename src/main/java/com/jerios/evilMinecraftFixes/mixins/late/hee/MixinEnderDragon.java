package com.jerios.evilMinecraftFixes.mixins.late.hee;

import chylex.hee.entity.boss.EntityBossDragon;
import chylex.hee.entity.boss.dragon.attacks.passive.DragonAttackBite;
import chylex.hee.entity.boss.dragon.attacks.special.DragonAttackBloodlust;
import chylex.hee.entity.boss.dragon.managers.DragonAttackManager;
import com.jerios.evilMinecraftFixes.cfg.Config;
import com.jerios.evilMinecraftFixes.hee.DragonByteMadness;
import fr.elias.fakeores.common.EntityOresBoss;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityBossDragon.class)
public class MixinEnderDragon extends EntityLiving {

   @Shadow(remap = false)
   public @Final DragonAttackManager attacks;
    public MixinEnderDragon(World p_i1595_1_) {
        super(p_i1595_1_);
    }

    @Inject(method = "<init>", at=@At("TAIL"))
    private void evil$in(World world, CallbackInfo ci) {
        EntityBossDragon dragon = ((EntityBossDragon)(Object)this);
        this.attacks.registerSpecial((new DragonByteMadness(dragon, 6, 5)).setDisabledPassiveAttacks(new byte[]{0, 1}));
    }


    public void knockBack(Entity p_70653_1_, float p_70653_2_, double p_70653_3_, double p_70653_5_) {
        if (this.getHealth() >= Config.enderDragonNoKnockbackHp) {
            super.knockBack(p_70653_1_, p_70653_2_, p_70653_3_, p_70653_5_);
        }
    }
}
