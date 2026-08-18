package com.jerios.evilMinecraftFixes.mixins.late.infernalMob;

import atomicstryker.infernalmobs.common.MobModifier;
import atomicstryker.infernalmobs.common.mods.MM_1UP;
import com.jerios.evilMinecraftFixes.infernalMobs.IInfernalBlacklist;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(MM_1UP.class)
public class Mixin_MM1UP extends MobModifier {

    /**
     * @author Jerios
     * @reason Listen to blacklist
     */
    @Overwrite(remap = false)
    public Class<?>[] getBlackListMobClasses() {

        MM_1UP up = ((MM_1UP)(Object)this);
        return ((IInfernalBlacklist)up).getBannedClassesToArray();
    }
}
