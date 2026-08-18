package com.jerios.evilMinecraftFixes.mixins;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.gtnewhorizon.gtnhmixins.builders.MixinBuilder;
import com.jerios.evilMinecraftFixes.cfg.ConfigASM;

public enum Mixins implements IMixins {

    LOOKHELPER(new MixinBuilder().setPhase(Phase.EARLY)
        .setApplyIf(() -> ConfigASM.math)
        .addCommonMixins("math.MixinEntityLookHelper")),

    BEDROCK_ENDERMEN(new MixinBuilder().setPhase(Phase.EARLY)
        .setApplyIf(() -> ConfigASM.harderEndermen)
        .addCommonMixins("hard.MixinHarderEndermen")),

    /**  HARDER_SPAWNERS(new MixinBuilder().setPhase(Phase.EARLY)
        .setApplyIf(() -> ConfigASM.rareSpawners)
        .addCommonMixins("hard.MixinEvilMobSpawnerBaseLogic")),
     **/

  /**  RARE_SPAWNERS(new MixinBuilder().setPhase(Phase.EARLY)
        .setApplyIf(() -> ConfigASM.rareSpawners)
        .addCommonMixins("hard.MixinTileEntityMobSpawner")),
   **/


       MOVEHELPER(new MixinBuilder().setPhase(Phase.EARLY)
           .setApplyIf(() -> ConfigASM.math)
           .addCommonMixins("math.MixinMoveHelper")),

       LIVINGBASE(new MixinBuilder().setPhase(Phase.EARLY)
           .setApplyIf(() -> ConfigASM.math)
           .addCommonMixins("math.MixinEntityLivingBase")),

      LIVING(new MixinBuilder().setPhase(Phase.EARLY)
          .setApplyIf(() -> ConfigASM.math)
          .addCommonMixins("math.MixinEntityLiving")),


           CREATURE(new MixinBuilder().setPhase(Phase.EARLY)
               .setApplyIf(() -> ConfigASM.math)
        .addCommonMixins("math.MixinEntityCreature")),


    BAT(new MixinBuilder().setPhase(Phase.EARLY)
        .setApplyIf(() -> ConfigASM.math)
        .addCommonMixins("math.MixinEntityBat")),


    SQUID(new MixinBuilder().setPhase(Phase.EARLY)
        .setApplyIf(() -> ConfigASM.math)
        .addCommonMixins("math.MixinEntitySquid")),


    GHAST(new MixinBuilder().setPhase(Phase.EARLY)
        .setApplyIf(() -> ConfigASM.math)
        .addCommonMixins("math.MixinEntityGhast")),


    BLAZE(new MixinBuilder().setPhase(Phase.EARLY)
        .setApplyIf(() -> ConfigASM.math)
        .addCommonMixins("math.MixinEntityBlaze")),


    BLOCKLIQUID(new MixinBuilder().setPhase(Phase.EARLY)
        .setApplyIf(() -> ConfigASM.math)
        .addCommonMixins("math.MixinBlockLiquid")),





   BEDROCKWITHER(new MixinBuilder().setPhase(Phase.EARLY)
       .setApplyIf(() -> ConfigASM.athenaWither)
        .addCommonMixins("wither.MixinBedrockWither")),

    HARDER_CREEPERS(new MixinBuilder().setPhase(Phase.EARLY)
        .setApplyIf(() -> ConfigASM.hardCreeper)
       .addCommonMixins("hard.MixinCreeperEntity")),

    DEMISE(new MixinBuilder().setPhase(Phase.EARLY)
        .setApplyIf(() -> ConfigASM.athenaWither)
        .addClientMixins("wither.MixinRenderWitherBoss")),

    HARDER_WITHER_SKULLS(new MixinBuilder().setPhase(Phase.EARLY)
        .setApplyIf(() -> ConfigASM.athenaWither)
        .addCommonMixins("wither.MixinEntityWitherSkull")),

    GHASTS(new MixinBuilder().setPhase(Phase.EARLY)
        .setApplyIf(() -> ConfigASM.hardGhast)
        .addCommonMixins("hard.MixinHarderGhasts")),

    LONGER_SLEEP(new MixinBuilder().setPhase(Phase.EARLY)
        .setApplyIf(() -> ConfigASM.longerSleep)
        .addCommonMixins("hard.MixinLongerSleepTime")),

    CREEPER_MINION(new MixinBuilder().setPhase(Phase.EARLY)
        .setApplyIf(() -> ConfigASM.bomby)
        .addCommonMixins("MixinNotAttacked")),


    ACESSOR1(new MixinBuilder().setPhase(Phase.EARLY)
        .addCommonMixins("IEntityPigmenAccessor")),

    HARDERSKEKE(new MixinBuilder().setPhase(Phase.EARLY)
        .setApplyIf(() -> ConfigASM.hardSkele)
        .addClientMixins("hard.MixinHarderSkeletons")),



    SRCINVASION(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.HWINVASION)
        .setApplyIf(() -> ConfigASM.invasionBlock)
        .addCommonMixins("hw.MixinBlockSourceInvasion")),

    FIREWORM(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.HWINVASION)
        .setApplyIf(() -> ConfigASM.fireWormHPIncrease)
        .addCommonMixins("hw.MixinFireWorm")),

    PM_THIRST_DEPLETION(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.PM)
        .addRequiredMod(TargetMods.THIRST)
        .setApplyIf(() -> ConfigASM.PGGloveThirst)
        .addCommonMixins("pg.MixinItemParticleGlove")),



    HEE_RENDER(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.HEE)
        .setApplyIf(() -> ConfigASM.HeeTweaks)
        .addClientMixins("hee.MixinRenderBossDradon")),

    ENDER_DEAMON(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.HEE)
        .setApplyIf(() -> ConfigASM.HeeTweaks)
        .addCommonMixins("hee.MixinEnderDeamon")),

    CRYSTALS(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.HEE)
        .setApplyIf(() -> ConfigASM.HeeTweaks)
        .addCommonMixins("hee.MixinEnderCrystal")),

    REDUCEXP(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.ORE)
        .setApplyIf(() -> ConfigASM.nerfXPLevelOres)
        .addCommonMixins("fakeOres.MixinEntityOres")),

    BW(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.HWINVASION)
        .setApplyIf(() -> ConfigASM.blockWilderBuff)
        .addCommonMixins("hw.MixinBlockWeilder")),


    OREBOSS(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.ORE)
        .setApplyIf(() -> ConfigASM.newOreBossPhase)
        .addCommonMixins("fakeOres.MixinOreBoss")),

    REMOVE_GLOBAL_REGISTRY_FAKE_ORES(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.ORE)
        .setApplyIf(() -> ConfigASM.removeGlobalEntityRegOres)
        .addCommonMixins("fakeOres.MixinFakeOresRemoveGlobalReg")),

    ALL_FAKE_ORES_ATTACK(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.ORE)
        .setApplyIf(() -> ConfigASM.oreAttack)
        .addCommonMixins("fakeOres.MixinBlockFakeOresVanilla")),

    MOVE_PLUS_FIXES(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.MP)
        .setApplyIf(() -> ConfigASM.movePlusFix)
        .addClientMixins("mp.MixinClientTicker")),

    CQ_TURTLE_ARMOUR_NERF(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.CQ)
        .setApplyIf(() -> ConfigASM.nerfCQArmor)
        .addCommonMixins("CQ.MixinItemArmorTurtle")),

    ENDER_DRAGON_NO_KNOCKBACK(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.HEE)
        .setApplyIf(() -> ConfigASM.HeeTweaks)
        .addCommonMixins("hee.MixinEnderDragon")),

    DISABLE_UPDATE_CHECKER_2(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.DI)
        .setApplyIf(() -> ConfigASM.DIUpCheck)
        .addClientMixins("damageInd.MixinDIProxy")),

    DISABLE_UPDATE_CHECKER(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.DI)
        .setApplyIf(() -> ConfigASM.DIUpCheck)
        .addClientMixins("damageInd.MixinG")),

    FIX_ORE_GEN(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.ORE)
        .setApplyIf(() -> ConfigASM.fixOreGen)
        .addCommonMixins("fakeOres.MixinWorldGen")),

   SPM_CREEPER(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.SPM)
        .setApplyIf(() -> ConfigASM.hardCreeper)
        .addCommonMixins("specialMobs.MixinEntity_SpecialCreeper")),

    CLOUD_BOOTS(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.CQ)
        .setApplyIf(() -> ConfigASM.buffClouldBoots)
        .addCommonMixins("CQ.MixinBuffClouldBoots")),

    SHUTUP_HW_SPAM(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.HWINVASION)
        .setApplyIf(() -> ConfigASM.hwSpam)
        .addCommonMixins("hw.MixinAreaScanner")),

    THIRST_MOD_SPAM(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.THIRST)
        .setApplyIf(() -> ConfigASM.thirstChanges)
        .addCommonMixins("thirst.MixinThirstMod")),

    THIRST_LOADER(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.THIRST)
        .setApplyIf(() -> ConfigASM.thirstChanges)
        .addCommonMixins("thirst.MixinItemLoader")),

    THIRST_SHOW_SAT(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.THIRST)
        .setApplyIf(() -> ConfigASM.thirstChanges)
        .addCommonMixins("thirst.MixinItemDrink")),

    ORE_BOSS_RENDER(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.ORE)
        .setApplyIf(() -> ConfigASM.newOreBossPhase)
        .addClientMixins("fakeOres.RenderRenderOresBoss")),

    ORE_BOSS_DISABLE_FULL_HP_REGEN(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.ORE)
        .setApplyIf(() -> ConfigASM.disableOreBossInstaHp)
        .addCommonMixins("fakeOres.MixinEntityBossTeleporter")),

    CHEM_X_SPECIAL_MOBS_WORKS(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.MUTANT)
        .setApplyIf(() -> ConfigASM.chemXSpecialMobsWorks)
        .addCommonMixins("mutantCreatures.MixinChemicalX")),

    ZOMBIES_STRENGTH(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.MUTANT)
        .setApplyIf(() -> ConfigASM.buffMutants)
        .addCommonMixins("mutantCreatures.MixinZombie")),

    HIGHER_SPAWN_RATE(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.MUTANT)
        .setApplyIf(() -> ConfigASM.increasedSpawning)
        .addCommonMixins("mutantCreatures.MixinMutantCreatures")),

    ENCHANCED_ISLAND_DECORATOR(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.HEE)
        .setApplyIf(() -> ConfigASM.decoratorEnchancedIsland)
        .addCommonMixins("hee.MixinBiomeDecoratorEnchantedIsland")),

    CREEPER_MUTANT_HIGHERCHANCE_CHARGED(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.MUTANT)
        .setApplyIf(() -> ConfigASM.buffMutants)
        .addCommonMixins("mutantCreatures.MixinMutantCreeper")),

    EXPLODE_ON_HIT(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.MUTANT)
        .setApplyIf(() -> ConfigASM.buffMutants)
        .addCommonMixins("mutantCreatures.MixinMCAIAttackOnCollide")),

    HARDER_ENDERMEN_SCREAM(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.MUTANT)
        .setApplyIf(() -> ConfigASM.buffMutants)
        .addCommonMixins("mutantCreatures.MixinMCAIEnderScream")),

    ENDERMEN_CLONE(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.MUTANT)
        .setApplyIf(() -> ConfigASM.buffMutants)
        .addCommonMixins("mutantCreatures.MixinMCAIEnderClone")),


    BOMBY_DO_NOT_DESPAWN_ON_PEACEFUL(new MixinBuilder()
        .setPhase(Phase.EARLY)
        .setApplyIf(() -> ConfigASM.bomby)
        .addCommonMixins("bomby.MixinEntityMob")),

    MUTANT_CRAZIER_THROW(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.MUTANT)
        .setApplyIf(() -> ConfigASM.buffMutants)
        .addCommonMixins("mutantCreatures.MixinMCAIZombieThrow")),

    MUTANT_ZOMBIE_HARDER(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.MUTANT)
        .setApplyIf(() -> ConfigASM.buffMutants)
        .addCommonMixins("mutantCreatures.MixinMutantZombie")),

    WITHER_APPLE(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.HARDCORE_WITHER)
        .setApplyIf(() -> ConfigASM.hardcoreApple)
        .addCommonMixins("MixinWitherApple")),

    MUSIC_FIXES(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.HEE)
        .setApplyIf(() -> ConfigASM.hEEMusicFix)
        .addClientMixins("hee.MixinCustomMusicTicker")),

    INFERNAL_MOBS(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.INFERNAL_MOBS)
        .setApplyIf(() -> ConfigASM.IFFixes)
        .addClientMixins("MixinInfernalMobsClient")),

    IF_CLIENT(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.INFERNAL_MOBS)
        .setApplyIf(() -> ConfigASM.IFFixes)
        .addClientMixins("MixinRendererBossGlow")),

    MM_M(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.INFERNAL_MOBS)
        .setApplyIf(() -> ConfigASM.IFFixes)
        .addCommonMixins("MixinMobModifier")),

    IF_CORE(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.INFERNAL_MOBS)
        .setApplyIf(() -> ConfigASM.IFFixes)
        .addCommonMixins("MixinInfernalMobsCore")),

    ONEUP(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.INFERNAL_MOBS)
        .setApplyIf(() -> ConfigASM.IFFixes)
        .addCommonMixins("infernalMob.Mixin_MM1UP")),

    BESERK(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.INFERNAL_MOBS)
        .setApplyIf(() -> ConfigASM.IFFixes)
        .addCommonMixins("infernalMob.Mixin_MM_Beserk")),

    CLOAKING(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.INFERNAL_MOBS)
        .setApplyIf(() -> ConfigASM.IFFixes)
        .addCommonMixins("infernalMob.Mixin_MM_Cloaking")),

    LSTEAL(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.INFERNAL_MOBS)
        .setApplyIf(() -> ConfigASM.IFFixes)
        .addCommonMixins("infernalMob.Mixin_MM_Lifesteal")),

    STICKY(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.INFERNAL_MOBS)
        .setApplyIf(() -> ConfigASM.IFFixes)
        .addCommonMixins("infernalMob.Mixin_MM_Sticky")),

    PARTICLE_GLOVE_RECIPE(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.PM)
        .setApplyIf(() -> ConfigASM.particleRecipeChange)
        .addCommonMixins("pg.MixinPGCommon")),

    THIRST_0(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.THIRST)
        .setApplyIf(() -> ConfigASM.thirstChanges)
        .addCommonMixins("thirst.MixinEventSystem")),

    THIRST_1(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.THIRST)
        .setApplyIf(() -> ConfigASM.thirstChanges)
        .addCommonMixins("thirst.MixinPlayerContainer")),

    THIRST_2(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.THIRST)
        .setApplyIf(() -> ConfigASM.thirstChanges)
        .addCommonMixins("thirst.MixinThirstLogic")),

    HEROBRINE_FIX_CRASH(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.AD_INF)
        .setApplyIf(() -> ConfigASM.arrowFixes)
        .addCommonMixins("MixinEntityNetherArrow")),

    HEROBRINE_FIX2(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.AD_INF)
        .setApplyIf(() -> ConfigASM.arrowFixes)
        .addCommonMixins("adInfernos.MixinEntityHerobrine")),

    CHEST_LOOT_NERF_IF(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.AD_INF)
        .setApplyIf(() -> ConfigASM.arrowFixes)
        .addCommonMixins("adInfernos.MixinNetherHooks")),

    SPM_SKELE(new MixinBuilder().setPhase(Phase.LATE)
        .addRequiredMod(TargetMods.SPM)
        .setApplyIf(() -> ConfigASM.hardSkele)
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
