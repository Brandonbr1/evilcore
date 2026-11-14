package com.jerios.evilMinecraftFixes.biomeReplace;

import net.minecraft.world.biome.BiomeGenBase;

public class JungleReplacer {

    protected static final BiomeGenBase.Height jungleLow = new BiomeGenBase.Height(-0.1F, 0.3F);
    protected static final BiomeGenBase.Height jungleHigh = new BiomeGenBase.Height(0.41F, 1.5F);
    public static void replaceBiome()
    {
        int jungle = BiomeGenBase.jungle.biomeID;
        int jungleEdge = BiomeGenBase.jungleEdge.biomeID;
        int jungleHills = BiomeGenBase.jungleHills.biomeID;
        BiomeGenBase.jungle = new JBetterJungle(jungle, true).setBiomeName("Jungle").setHeight(new BiomeGenBase.Height(1.2F, 0.1F));
        BiomeGenBase.jungleEdge = new JBetterJungle(jungleEdge, false).setBiomeName("Jungle Edge").setHeight(jungleLow);
        BiomeGenBase.jungleHills = new JBetterJungle(jungleHills, true).setBiomeName("Jungle Hills").setHeight(jungleHigh);
    }





}
