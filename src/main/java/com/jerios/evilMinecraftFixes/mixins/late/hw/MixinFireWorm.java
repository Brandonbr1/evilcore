package com.jerios.evilMinecraftFixes.mixins.late.hw;

import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import hostileworlds.entity.monster.EntityWormFire;

@Mixin(EntityWormFire.class)
public class MixinFireWorm extends EntityFlying {

    @Shadow(remap = false)
    public int nodePieces = 7;

    public MixinFireWorm(World p_i1587_1_) {
        super(p_i1587_1_);
    }

    @Inject(method = "<init>", at = @At("CTOR_HEAD"), remap = false)
    private void injectUpdatedNodgePieces(CallbackInfo ci) {
        nodePieces = 15;
       // this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15D * 18D);
      //  this.setHealth((float) (15D * 18D));
    }

    @Unique final double tempMaxHealth2 = 15D * 18D;

    /**
     * @author Vortex
     * @reason new hp
     */
    @Overwrite
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
     //   double tempMaxHealth = 15D * 18D;
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(this.tempMaxHealth2);
    }
}
