package com.jerios.evilMinecraftFixes.mixins;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.builders.ITargetMod;
import com.gtnewhorizon.gtnhmixins.builders.TargetModBuilder;

public enum TargetMods implements ITargetMod {

    HWINVASION("hostileworlds.HostileWorlds", true),
    SPM("toast.specialMobs._SpecialMobs", true),
    ORE("fr.elias.fakeores.common.EntityCoalOre", true),
    HEE("HardcoreEnderExpansion");

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
