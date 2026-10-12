package com.jerios.evilMinecraftFixes.content;

import com.jerios.evilMinecraftFixes.Evil;
import com.jerios.evilMinecraftFixes.content.entity.nether.AcidicSlime;
import com.jerios.evilMinecraftFixes.content.entity.nether.EntityCryingSoul;
import com.jerios.evilMinecraftFixes.content.entity.nether.CorruptedWolf;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

public class EvilMobs {

    public static void addMobs() {
        registerMob(AcidicSlime.class, "AcidicSlime");
        addSpawn(AcidicSlime.class, 50,1,4, EnumCreatureType.monster, BiomeGenBase.hell);
        registerMob(CorruptedWolf.class, "CorruptedWolf");
         addSpawn(CorruptedWolf.class, 55,3,4, EnumCreatureType.monster, BiomeGenBase.hell);
        registerMob(EntityCryingSoul.class, "CryingSoul");
        addSpawn(CorruptedWolf.class, 60,1,4, EnumCreatureType.monster, BiomeGenBase.hell);
    }

   static int id = 1;

    private static void registerMob(Class<? extends Entity> mob, String name) {
        EntityRegistry.registerModEntity(mob, name, id++, Evil.INSTANCE, 128, 1, true);
        ItemSpawnEgg.addSpawnEgg(id++, mob, name, 453,432);
    }

    private static void addSpawn(Class<? extends EntityLiving> mob, int weight, int min, int max, EnumCreatureType creatureType, BiomeGenBase... base) {
        EntityRegistry.addSpawn(mob, weight, min, max, creatureType, base);
    }

}
