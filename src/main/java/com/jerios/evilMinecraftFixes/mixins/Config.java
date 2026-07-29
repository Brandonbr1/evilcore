package com.jerios.evilMinecraftFixes.mixins;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static float hardness;
    public static float resistance;
    public static boolean spawnLadderZombie;
    public static float healAmm;
    public static int healTimer;
    public static boolean naturalRegeneration;

    public static boolean pigmenAgressiveIfModifier;
    public static boolean spiderAgressiveIfModifier;
    public static boolean endermenAgressiveIfModifier;


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

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }

}
