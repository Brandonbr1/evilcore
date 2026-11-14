package com.jerios.evilMinecraftFixes.mixins.late.jungles;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cst7.betterjungles.BetterJungles;
import cst7.betterjungles.init.BJConfigs;
import net.minecraftforge.common.config.Configuration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BetterJungles.class)
public class MixinBetterJungles {

    /**
     * @author Jerios
     * @reason Fix Broken Config
     */
    @Overwrite(remap = false)
    public void initConfiguration(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        BJConfigs.betterjungleID = config.get("Biome", "Better Jungles", 120).getInt();

        if (config.hasChanged()) {
            config.save();
        }

    }
}
