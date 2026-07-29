package com.jerios.evilMinecraftFixes.mixins;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.gtnewhorizon.gtnhmixins.builders.MixinBuilder;

public enum Mixins implements IMixins {

    LOOKHELPER(new MixinBuilder().setPhase(Phase.EARLY)
        .addCommonMixins("math.MixinEntityLookHelper")),
    MOVEHELPER(new MixinBuilder().setPhase(Phase.EARLY)
        .addCommonMixins("math.MixinMoveHelper")),
    LIVINGBASE(new MixinBuilder().setPhase(Phase.EARLY)
        .addServerMixins("math.MixinEntityLivingBase")),
    LIVING(new MixinBuilder().setPhase(Phase.EARLY)
        .addCommonMixins("math.MixinEntityLiving")),
    CREATURE(new MixinBuilder().setPhase(Phase.EARLY)
        .addCommonMixins("math.MixinEntityCreature")),
    BAT(new MixinBuilder().setPhase(Phase.EARLY)
        .addCommonMixins("math.MixinEntityBat")),
    SQUID(new MixinBuilder().setPhase(Phase.EARLY)
        .addCommonMixins("math.MixinEntitySquid")),
    GHAST(new MixinBuilder().setPhase(Phase.EARLY)
        .addCommonMixins("math.MixinEntityGhast")),
    BLAZE(new MixinBuilder().setPhase(Phase.EARLY)
        .addCommonMixins("math.MixinEntityBlaze")),
    BLOCKLIQUID(new MixinBuilder().setPhase(Phase.EARLY)
        .addCommonMixins("math.MixinBlockLiquid")),
    BEDROCKWITHER(new MixinBuilder().setPhase(Phase.EARLY)
        .addCommonMixins("wither.MixinBedrockWither")),
    HARDER_CREEPERS(new MixinBuilder().setPhase(Phase.EARLY)
        .addCommonMixins("hard.MixinCreeperEntity")),
    DEMISE(new MixinBuilder().setPhase(Phase.EARLY)
        .addClientMixins("wither.MixinRenderWitherBoss")),
    HARDER_WITHER_SKULLS(new MixinBuilder().setPhase(Phase.EARLY)
        .addCommonMixins("wither.MixinEntityWitherSkull")),
    GHASTS(new MixinBuilder().setPhase(Phase.EARLY)
        .addCommonMixins("hard.MixinHarderGhasts")),

    ACESSOR1(new MixinBuilder().setPhase(Phase.EARLY)
        .addCommonMixins("IEntityPigmenAccessor")),

    HARDERSKEKE(new MixinBuilder().setPhase(Phase.EARLY)
        .addClientMixins("hard.MixinHarderSkeletons")),

    SRCINVASION(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.HWINVASION)
        .addCommonMixins("hw.MixinBlockSourceInvasion")),
    FIREWORM(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.HWINVASION)
        .addCommonMixins("hw.MixinFireWorm")),

    HEE_RENDER(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.HEE)
        .addClientMixins("hee.MixinRenderBossDradon")),

    ENDER_DEAMON(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.HEE)
        .addCommonMixins("hee.MixinEnderDeamon")),
    CRYSTALS(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.HEE)
        .addCommonMixins("hee.MixinEnderCrystal")),

    REDUCEXP(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.ORE)
        .addCommonMixins("fakeOres.MixinEntityOres")),

    BW(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.HWINVASION)
        .addCommonMixins("hw.MixinBlockWeilder")),

    OREBOSS(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.ORE)
        .addCommonMixins("fakeOres.MixinOreBoss")),

    SPM_CREEPER(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.SPM)
        .addCommonMixins("specialMobs.MixinEntity_SpecialCreeper")),

    SHUTUP_HW_SPAM(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.HWINVASION)
        .addCommonMixins("hw.MixinAreaScanner")),

    SPM_SKELE(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.SPM)
        .addCommonMixins("specialMobs.MixinEntity_SpecialSkeleton"));

    ;

    private final MixinBuilder builder;

    Mixins(MixinBuilder builder) {
        this.builder = builder;
    }

    @Nonnull
    @Override
    public MixinBuilder getBuilder() {
        return builder;
    }
}
