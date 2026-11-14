package com.jerios.evilMinecraftFixes.mixins.late.jungles;

import cst7.betterjungles.init.BJBiomes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = BJBiomes.class, remap = false)
public class MixinBJBiome {



    /**
     * @author Jerios
     * @reason Do it our way
     */
    @Overwrite
    public static void Init() {
    }


}
