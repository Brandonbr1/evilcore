package com.jerios.evilMinecraftFixes.cfg;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static float hardness;
    public static float resistance;
    public static boolean spawnLadderZombie;
    public static float healAmm;
    public static int healTimer;
    public static boolean naturalRegeneration;

    public static boolean oreAttack;

    public static boolean pigmenAgressiveIfModifier;
    public static boolean spiderAgressiveIfModifier;
    public static boolean endermenAgressiveIfModifier;

    public static int initalSpawnDelay = 3;
    public static int maxMobs = 12;
    public static int playerActivationDistance = 64;
    public static int minTimer = 200;
    public static int maxTimer = 800;

    public static float enderDragonNoKnockbackHp = 80;

    public static int maxBP;
    public static boolean dangerousBombs;

    public static boolean athena;

    public static boolean bufffNetherMobs;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);
        hardness = configuration.getFloat("hardness", "HW", 50f, 0f, 1000f, "HW source invasion hardness(break)");
        resistance = configuration
            .getFloat("resistance", "HW", 1000f, 0f, 1000f, "HW source invasion block resistance(explosion)");

        healAmm = configuration
            .getFloat("Heal AMM", "Wither", 4f, 0f, 1000f, "");


        naturalRegeneration = configuration.getBoolean("Natural regeneration", "Player", false, "Should natural Regeneration be forcefully turned off?");

        healTimer = configuration
            .getInt("Heal Timer", "Wither", 120, 0, 100000, "");

        spawnLadderZombie = configuration.getBoolean("Spawn Ladder Zombie", "HW", true, "Should the Ladder Zombie Spawn in?");

        pigmenAgressiveIfModifier = configuration.getBoolean("Pigmen Aggressive if modifier", "Infernal Mobs", true, "If a pigmen is with a modifier, should it be aggressive?");

        spiderAgressiveIfModifier = configuration.getBoolean("Spider Aggressive if modifier", "Infernal Mobs", true, "If a spider is with a modifier, should it be aggressive?");

        endermenAgressiveIfModifier = configuration.getBoolean("Endermen Aggressive if modifier", "Infernal Mobs", false, "If a endermen is with a modifier, should it be aggressive?");

        oreAttack = configuration.getBoolean("All ores in vein attack when mined", "Fake Ores", false, "If a fake ore is mined, should all come after you?");

        initalSpawnDelay = configuration.getInt("Spawn Delay", "Monster Spawners", 3, 0, 9000, "The amount of time in ticks when mobs should spawn when the spawner is 1st placed.");

        maxMobs = configuration.getInt("Max Mobs", "Monster Spawners", 12, 0, 9000, "How many mobs before the spawners don't spawn anymore.");

        playerActivationDistance = configuration.getInt("Monster spawner range from player", "Monster Spawners", 64, 16, 256, "The distance in which the monster spawner activates(does not dictate spawn radius)");

        minTimer = configuration.getInt("Min timer", "Monster Spawners", 200, 0, 9000, "After the 1st entity has spawned, what is the minimum time between next spawn?");

        maxTimer = configuration.getInt("Max timer", "Monster Spawners", 800, 0, 9000, "After the 1st entity has spawned, what is the max time between next spawn?");

        enderDragonNoKnockbackHp = configuration.getFloat("Ender Dragon No Knock Hp", "HEE", 80, 0 ,9000, "When the ender dragon is below this HP point, it will take no knockback.");

        maxBP = configuration.getInt("Max Backpack", "Backpacks", 2, 0, 99, "Max ammount of backpacks allowed before you can't hold anymore");

        dangerousBombs = configuration.getBoolean("Dangerous Bombs", "HEE", false, "Make the bombs destroy blocks");

        bufffNetherMobs = configuration.getBoolean("Buff Nether Mobs", "Nether", true, "Give Nether Mobs an increased HP");

        athena = configuration.getBoolean("Athena", "Wither", true, "Enable Athena?");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }

}
