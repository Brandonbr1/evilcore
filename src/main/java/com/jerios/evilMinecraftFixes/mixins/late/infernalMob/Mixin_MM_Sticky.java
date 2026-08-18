package com.jerios.evilMinecraftFixes.mixins.late.infernalMob;

import atomicstryker.infernalmobs.common.MobModifier;
import atomicstryker.infernalmobs.common.mods.MM_Cloaking;
import atomicstryker.infernalmobs.common.mods.MM_Sticky;
import com.jerios.evilMinecraftFixes.infernalMobs.IInfernalBlacklist;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(MM_Sticky.class)
public class Mixin_MM_Sticky extends MobModifier {

    /**
     * @author Jerios
     * @reason Listen to blacklist
     */
    @Overwrite(remap = false)
    public Class<?>[] getBlackListMobClasses() {

        MM_Sticky up = ((MM_Sticky)(Object)this);
        return ((IInfernalBlacklist)up).getBannedClassesToArray();
    }
}
