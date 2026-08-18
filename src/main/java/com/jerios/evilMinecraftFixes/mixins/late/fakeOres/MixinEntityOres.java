package com.jerios.evilMinecraftFixes.mixins.late.fakeOres;

import fr.elias.fakeores.common.EntityOres;
import fr.elias.fakeores.common.EntityOresBoss;
import fr.elias.fakeores.common.FakeOres;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityOres.class)
public class MixinEntityOres extends EntityMob {

    public MixinEntityOres(World p_i1738_1_) {
        super(p_i1738_1_);
    }

    @Inject(method = "<init>(Lnet/minecraft/world/World;)V", at = @At("RETURN"))
    private void readjustExperienceValue(World world, CallbackInfo ci) {
        this.experienceValue = 15 - this.worldObj.difficultySetting.getDifficultyId();
    }

    @Inject(method = "dropFewItems",at = @At("HEAD"))
    private void evil$inject(boolean par1, int par2, CallbackInfo ci) {
        if (this.rand.nextInt(28) == 0) {
            this.dropItem(FakeOres.fragment_part4, 1);

        }
    }

    @Override
    public void onUpdate() {
        if (this.entityToAttack instanceof EntityOresBoss) {
            this.entityToAttack = null;
        }
        super.onUpdate();
    }
}
