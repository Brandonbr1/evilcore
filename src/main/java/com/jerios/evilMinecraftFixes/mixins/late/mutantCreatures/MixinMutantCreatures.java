package com.jerios.evilMinecraftFixes.mixins.late.mutantCreatures;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import thehippomaster.MutantCreatures.MutantCreatures;

@Mixin(MutantCreatures.class)
public class MixinMutantCreatures {

    /**
     * @author Jerios
     * @reason Force spawnrate to not be so damm low
     */
    @Overwrite(remap = false)
    public static boolean getRandomSpawnChance() {
        return true;
    }
}
