package com.jerios.evilMinecraftFixes.mixins.late.specialMobs;

import com.jerios.evilMinecraftFixes.ducks.IGetCreeperProps;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import toast.specialMobs.entity.creeper.EntityDrowningCreeper;
import toast.specialMobs.entity.creeper.Entity_SpecialCreeper;

@Mixin(EntityDrowningCreeper.class)
public class MixinEntityDrowningCreeper extends Entity_SpecialCreeper {
    public MixinEntityDrowningCreeper(World world) {
        super(world);
    }



    @WrapWithCondition(method = "explodeByType", at= @At(value = "INVOKE", target = "Ltoast/specialMobs/MobHelper;drowningExplode(Lnet/minecraft/entity/Entity;I)V"), remap = false)
    private boolean doNotExecuteMoreThanOnce(Entity exploder, int z) {
        if (((IGetCreeperProps)exploder).getHasCreeperExploded()){
            return false;
        }

        return true;
    }

}
