package com.jerios.evilMinecraftFixes.mixins.late.infernalMob;

import atomicstryker.infernalmobs.common.MobModifier;
import atomicstryker.infernalmobs.common.mods.MM_Berserk;
import com.jerios.evilMinecraftFixes.infernalMobs.IInfernalBlacklist;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MM_Berserk.class)
public class Mixin_MM_Beserk extends MobModifier {

    @Inject(method = "<init>(Lnet/minecraft/entity/EntityLivingBase;)V", at=@At("TAIL"))
    private void evil$init(EntityLivingBase mob, CallbackInfo ci) {
        MM_Berserk ore = ((MM_Berserk)(Object)this);
        ((IInfernalBlacklist)ore).addCreeperString();
    }

    /**
     * @author Jerios
     * @reason Listen to blacklist
     */
    @Overwrite(remap = false)
    public Class<?>[] getBlackListMobClasses() {
        MM_Berserk ore = ((MM_Berserk)(Object)this);
        return ((IInfernalBlacklist)ore).getBannedClassesToArray();
    }
}
