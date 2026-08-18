package com.jerios.evilMinecraftFixes.mixins.late.infernalMob;

import atomicstryker.infernalmobs.common.MobModifier;
import atomicstryker.infernalmobs.common.mods.MM_1UP;
import atomicstryker.infernalmobs.common.mods.MM_Cloaking;
import com.jerios.evilMinecraftFixes.infernalMobs.IInfernalBlacklist;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(MM_Cloaking.class)
public class Mixin_MM_Cloaking extends MobModifier {
    /**
     * @author Jerios
     * @reason Listen to blacklist
     */
    @Overwrite(remap = false)
    public Class<?>[] getBlackListMobClasses() {

        MM_Cloaking up = ((MM_Cloaking)(Object)this);
        return ((IInfernalBlacklist)up).getBannedClassesToArray();
    }

}
