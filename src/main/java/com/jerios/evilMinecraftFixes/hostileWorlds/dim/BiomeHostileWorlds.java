package com.jerios.evilMinecraftFixes.hostileWorlds.dim;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeHostileWorlds extends BiomeGenBase {

    public BiomeHostileWorlds(int p_i1971_1_) {
        super(p_i1971_1_);
        this.setBiomeName("Catacombs");
        this.setDisableRain();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
    }

}
