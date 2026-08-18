package com.jerios.evilMinecraftFixes.cfg;

import com.gtnewhorizon.gtnhlib.config.Config;
import com.gtnewhorizon.gtnhlib.config.ConfigurationManager;

@Config(modid = "evilerMinecraftASM", category = "EvilerMinecraftFixes")
@Config.RequiresMcRestart
public class ConfigASM {

    private ConfigASM() {

    }

    @Config.Comment("Use Faster Math?")
    @Config.DefaultBoolean(true)
    public static boolean math;

    @Config.Comment("Athena?")
    @Config.DefaultBoolean(true)
    public static boolean athenaWither ;

    @Config.Comment("Harder Creeper?")
    @Config.DefaultBoolean(true)
    public static boolean hardCreeper ;

    @Config.Comment("Harder Ghasts?")
    @Config.DefaultBoolean(true)
    public static boolean hardGhast ;

    @Config.Comment("Harder Skeletons?")
    @Config.DefaultBoolean(true)
    public static boolean hardSkele ;

    @Config.Comment("Fix Broken Ore Gen")
    @Config.DefaultBoolean(true)
    public static boolean fixOreGen ;

    @Config.Comment("Longer Sleep?")
    @Config.DefaultBoolean(true)
    public static boolean longerSleep ;

    @Config.Comment("Nerf XP Levels of ORES?")
    @Config.DefaultBoolean(true)
    public static boolean nerfXPLevelOres ;

    @Config.Comment("Disable HW log spam")
    @Config.DefaultBoolean(true)
    public static boolean hwSpam ;

    @Config.Comment("Particle Recipe Change?")
    @Config.DefaultBoolean(true)
    public static boolean particleRecipeChange ;

    @Config.Comment("Enable Infernal Mobs Fixes?")
    @Config.DefaultBoolean(true)
    public static boolean IFFixes ;

    @Config.Comment("Chemical X works on Special Mobs?")
    @Config.DefaultBoolean(true)
    public static boolean chemXSpecialMobsWorks ;

    @Config.Comment("Adds QOL improvements to the thrist, and fixes some bugs")
    @Config.DefaultBoolean(true)
    public static boolean thirstChanges ;

    @Config.Comment("Fix crash with herobrine")
    @Config.DefaultBoolean(true)
    public static boolean arrowFixes ;

    @Config.Comment("Make cloud boots as good as iron")
    @Config.DefaultBoolean(true)
    public static boolean buffClouldBoots ;

    @Config.Comment("Disable the Ore boss gaining full HP when low HP")
    @Config.DefaultBoolean(true)
    public static boolean disableOreBossInstaHp ;

    @Config.Comment("Add New Ore Boss phase")
    @Config.DefaultBoolean(true)
    public static boolean newOreBossPhase ;

    @Config.Comment("Remove Global Entities from Fake Ores")
    @Config.DefaultBoolean(true)
    public static boolean removeGlobalEntityRegOres ;

    @Config.Comment("Ore Attack!")
    @Config.DefaultBoolean(true)
    public static boolean oreAttack ;

    @Config.Comment("Fix MovePlus log spam, and enable hunger deprecation on moves")
    @Config.DefaultBoolean(true)
    public static boolean movePlusFix ;

    @Config.Comment("Fix broke HEE music")
    @Config.DefaultBoolean(true)
    public static boolean hEEMusicFix ;

    @Config.Comment("Increase Block Wielder HP")
    @Config.DefaultBoolean(true)
    public static boolean blockWilderBuff ;

    @Config.Comment("Increase Fire Worm HP")
    @Config.DefaultBoolean(true)
    public static boolean fireWormHPIncrease ;

    @Config.Comment("Source Invasion Block harder to break, and is impossible to drop")
    @Config.DefaultBoolean(true)
    public static boolean invasionBlock ;

    @Config.Comment("Buff Mutants")
    @Config.DefaultBoolean(true)
    public static boolean buffMutants ;

    @Config.Comment("Increased Spawning")
    @Config.DefaultBoolean(true)
    public static boolean increasedSpawning ;

    @Config.Comment("{test} make ender deamon structure thing try and spawn")
    @Config.DefaultBoolean(true)
    public static boolean decoratorEnchancedIsland ;

    @Config.Comment("Bomby Fixes")
    @Config.DefaultBoolean(true)
    public static boolean bomby ;


    @Config.Comment("Harder Endermen")
    @Config.DefaultBoolean(true)
    public static boolean harderEndermen ;

    @Config.Comment("Rare Spawners")
    @Config.DefaultBoolean(true)
    public static boolean rareSpawners ;

    @Config.Comment("HEE Tweaks")
    @Config.DefaultBoolean(true)
    public static boolean HeeTweaks ;

    @Config.Comment("Particle Glove consumes Thirst")
    @Config.DefaultBoolean(true)
    public static boolean PGGloveThirst ;

    @Config.Comment("DI UPDATE CHECKER OFF")
    @Config.DefaultBoolean(true)
    public static boolean DIUpCheck ;

    @Config.Comment("Hardcore apple buff")
    @Config.DefaultBoolean(true)
    public static boolean hardcoreApple ;

    @Config.Comment("Turtle Armour now get damaged when revive effect is used")
    @Config.DefaultBoolean(true)
    public static boolean nerfCQArmor;


    public static void init() {

    }



 static {
     ConfigurationManager.registerConfig(ConfigASM.class);
 }



}
