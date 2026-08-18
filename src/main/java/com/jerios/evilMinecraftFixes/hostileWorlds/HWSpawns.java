package com.jerios.evilMinecraftFixes.hostileWorlds;

import com.jerios.evilMinecraftFixes.cfg.Config;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.EntityRegistry;
import hostileworlds.config.ModConfigFields;
import hostileworlds.entity.monster.ZombieClawer;
import hostileworlds.entity.monster.ZombieClimber;
import hostileworlds.entity.monster.ZombieHungry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

public class HWSpawns {


    public static void spawnHook() {
        if (Loader.isModLoaded("HostileWorlds")) {
            BiomeGenBase[] newBiomes = {BiomeGenBase.icePlains, BiomeGenBase.iceMountains, BiomeGenBase.river, BiomeGenBase.extremeHills, BiomeGenBase.savanna};
            if (ModConfigFields.spawnHungryZombie) {
                EntityRegistry.addSpawn(ZombieHungry.class, 5, 1, 1, EnumCreatureType.monster, newBiomes);
            }

            if (ModConfigFields.spawnClawerZombie) {
                EntityRegistry.addSpawn(ZombieClawer.class, 1, 1, 1, EnumCreatureType.monster, newBiomes);
            }
            if (Config.spawnLadderZombie) {
                BiomeGenBase[] biomes = {BiomeGenBase.desert, BiomeGenBase.taiga, BiomeGenBase.forest, BiomeGenBase.jungle, BiomeGenBase.plains, BiomeGenBase.swampland, BiomeGenBase.mesa, BiomeGenBase.roofedForest , /** NEW BIOMES SPAWNED**/ BiomeGenBase.icePlains, BiomeGenBase.iceMountains, BiomeGenBase.river, BiomeGenBase.extremeHills, BiomeGenBase.savanna};
                EntityRegistry.addSpawn(ZombieClimber.class, 1, 1, 2, EnumCreatureType.monster, biomes);

            }
        }
    }


}
