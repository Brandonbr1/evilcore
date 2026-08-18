package com.jerios.evilMinecraftFixes.mixins;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.builders.ITargetMod;
import com.gtnewhorizon.gtnhmixins.builders.TargetModBuilder;

public enum TargetMods implements ITargetMod {

    HWINVASION("hostileworlds.HostileWorlds", true),
    SPM("SpecialMobs"),
    AD_INF("dextersnether"),
    ORE("fr.elias.fakeores.common.EntityCoalOre", true),
    MP("moveplus.forge.ClientTicker", true),
    MUTANT("thehippomaster.MutantCreatures.MutantCreatures", true),
    HARDCORE_WITHER("thor12022.hardcorewither.HardcoreWither", true),
    HEE("HardcoreEnderExpansion"),
    INFERNAL_MOBS("atomicstryker.infernalmobs.common.InfernalMobsCore", true),
    DI("DamageIndicatorsMod.client.DIClientProxy", true),
    CQ("com.chocolate.chocolateQuest.ChocolateQuest", true),
    THIRST("com.thetorine.thirstmod.core.main.ThirstMod", true),
    PM("particleman.forge.ParticleMan", true);

    private final TargetModBuilder builder;

    TargetMods(String clazz, boolean b) {
        this.builder = new TargetModBuilder().setTargetClass(clazz);
    }

    TargetMods(String mod) {
        this.builder = new TargetModBuilder().setModId(mod);
    }

    // TargetMods(String clazz, String modId) {
    // this.builder = new TargetModBuilder().setTargetClass(clazz).setModId(modId);
    // }

    @Nonnull
    @Override
    public TargetModBuilder getBuilder() {
        return builder;
    }
}
