package com.jerios.evilMinecraftFixes.mixins;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.builders.ITargetMod;
import com.gtnewhorizon.gtnhmixins.builders.TargetModBuilder;

public enum TargetMods implements ITargetMod {

    HWINVASION("HostileWorlds"),
    SPM("SpecialMobs"),
    AD_INF("dextersnether"),
    ORE("fakeores"),
    MP("moveplus"),
    MUTANT("MutantCreatures"),
    HARDCORE_WITHER("hardcorewither"),
    HEE("HardcoreEnderExpansion"),
    INFERNAL_MOBS("InfernalMobs"),
    DI("DamageIndicatorsMod"),
    CQ("chocolateQuest"),
    THIRST("thirstmod"),
    PM("ParticleMan");

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
