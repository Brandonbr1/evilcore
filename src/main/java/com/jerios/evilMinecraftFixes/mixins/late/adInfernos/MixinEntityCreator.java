package com.jerios.evilMinecraftFixes.mixins.late.adInfernos;

import com.superdextor.dextersnether.entity.monster.EntityHerobrine;
import com.superdextor.dextersnether.entity.monster.EntityHerobrineClone;
import com.superdextor.thinkbigcore.ThinkBigCore;
import com.superdextor.thinkbigcore.entity.EntityCreator;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.HashMap;
import java.util.Map;

@Mixin(value = EntityCreator.class, remap = false)
public class MixinEntityCreator {

    @Shadow public static Map classToIDMapping = new HashMap();

   @Unique
   private static int evil$modCounter = 1;

    /**
     * @author Jerios
     * @reason No global IDS PLEASE
     */
    @Overwrite
    public static final void createEntity(Class entityClass, String entityName, EnumCreatureType type, int probability, int minSpawn, int maxSpawn, BiomeGenBase[] biome, int solidColor, int spotColor, boolean hasSpawnEgg) {

        if (entityClass == EntityHerobrine.class || entityClass == EntityHerobrineClone.class) {
            int randomId = EntityRegistry.findGlobalUniqueEntityId();
            EntityRegistry.registerGlobalEntityID(entityClass, entityName, randomId, solidColor, spotColor);
        }
        int count = evil$modCounter++;

        EntityRegistry.registerModEntity(entityClass, entityName, count, ThinkBigCore.modInstance, 60, 1, true);
        EntityRegistry.addSpawn(entityName, probability, minSpawn, maxSpawn, type, biome);
        classToIDMapping.put(entityClass, count);



    }


}
