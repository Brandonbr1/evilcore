package com.jerios.evilMinecraftFixes.biomeReplace;

import com.jerios.evilMinecraftFixes.biomeReplace.trees.WorldGenMegaJungleMah;
import com.jerios.evilMinecraftFixes.biomeReplace.trees.WorldGenShrubMah;
import com.jerios.evilMinecraftFixes.biomeReplace.trees.WorldGenTreesMah;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;

import java.util.Random;

public class JBetterJungle extends BiomeGenJungle {

    private boolean main;

    // /tp Developer 30 65 1431
    public JBetterJungle(int id, boolean mainBiome)
    {
        super(id, mainBiome);
        this.main  = mainBiome;
        this.setColor(6546587);
        this.func_76733_a(5470985);
        this.setTemperatureRainfall(0.95F, 0.9F);

    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random p_150567_1_)
    {
        return p_150567_1_.nextInt(10) == 0 ? this.worldGeneratorBigTree : (p_150567_1_.nextInt(2) == 0 ? new WorldGenShrubMah(0, 0) : (!this.main && p_150567_1_.nextInt(3) == 0 ? new WorldGenMegaJungleMah(false, 10, 20, 0, 0) : new WorldGenTreesMah(false, 4 + p_150567_1_.nextInt(7), 0, 0, true)));
    }


}
