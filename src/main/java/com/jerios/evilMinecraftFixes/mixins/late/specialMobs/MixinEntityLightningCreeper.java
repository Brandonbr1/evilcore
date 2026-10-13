package com.jerios.evilMinecraftFixes.mixins.late.specialMobs;

import com.jerios.evilMinecraftFixes.ducks.IGetCreeperProps;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import toast.specialMobs.entity.creeper.EntityLightningCreeper;
import toast.specialMobs.entity.creeper.Entity_SpecialCreeper;

@Mixin(EntityLightningCreeper.class)
public class MixinEntityLightningCreeper extends Entity_SpecialCreeper {
    public MixinEntityLightningCreeper(World world) {
        super(world);
    }

    @WrapWithCondition(method = "explodeByType", at= @At(value = "INVOKE", target = "Ltoast/specialMobs/MobHelper;lightningExplode(Lnet/minecraft/entity/Entity;I)V"), remap = false)
    private boolean doNotExecuteMoreThanOnce(Entity exploder, int radius) {
        if (((IGetCreeperProps)exploder).getHasCreeperExploded()){
            return false;
        }

        return true;
    }
}
