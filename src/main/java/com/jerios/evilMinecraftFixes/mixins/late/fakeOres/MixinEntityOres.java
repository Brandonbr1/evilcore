package com.jerios.evilMinecraftFixes.mixins.late.fakeOres;

import fr.elias.fakeores.common.EntityOres;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityOres.class)
public class MixinEntityOres extends EntityMob {

    public MixinEntityOres(World p_i1738_1_) {
        super(p_i1738_1_);
    }

    @Inject(method = "<init>(Lnet/minecraft/world/World;)V", at = @At("RETURN"))
    private void readjustExperienceValue(World world, CallbackInfo ci) {
        this.experienceValue = 15 - this.worldObj.difficultySetting.getDifficultyId();
    }

}
