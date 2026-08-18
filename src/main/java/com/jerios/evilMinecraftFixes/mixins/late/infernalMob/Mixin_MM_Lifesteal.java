package com.jerios.evilMinecraftFixes.mixins.late.infernalMob;


import atomicstryker.infernalmobs.common.MobModifier;
import atomicstryker.infernalmobs.common.mods.MM_Lifesteal;
import atomicstryker.infernalmobs.common.mods.MM_Sticky;
import com.jerios.evilMinecraftFixes.infernalMobs.IInfernalBlacklist;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(MM_Lifesteal.class)
public class Mixin_MM_Lifesteal extends MobModifier {

    /**
     * @author Jerios
     * @reason Listen to blacklist
     */
    @Overwrite(remap = false)
    public Class<?>[] getBlackListMobClasses() {

        MM_Lifesteal up = ((MM_Lifesteal)(Object)this);
        return ((IInfernalBlacklist)up).getBannedClassesToArray();
    }
}
