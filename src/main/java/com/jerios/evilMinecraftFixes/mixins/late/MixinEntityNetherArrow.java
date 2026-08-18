package com.jerios.evilMinecraftFixes.mixins.late;

import com.superdextor.dextersnether.entity.projectile.EntityNetherArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityNetherArrow.class)
public class MixinEntityNetherArrow extends EntityArrow {


    public MixinEntityNetherArrow(World p_i1753_1_) {
        super(p_i1753_1_);
    }

    @Redirect(method = "<init>(Lnet/minecraft/world/World;Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/entity/EntityLivingBase;FF)V", at= @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;getBoundingBox()Lnet/minecraft/util/AxisAlignedBB;"))
    private AxisAlignedBB evil$actualBB(EntityLivingBase instance) {
        return instance.boundingBox;
    }
}
