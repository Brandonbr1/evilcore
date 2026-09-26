package com.jerios.evilMinecraftFixes.mixins.late.animalPlus;

import clickme.animals.AnimalsPlus;
import clickme.animals.EntityManager;
import clickme.animals.entity.ambient.EntityButterfly;
import clickme.animals.entity.ambient.EntityCentipede;
import clickme.animals.entity.ambient.EntityCricket;
import clickme.animals.entity.ambient.EntityMoth;
import clickme.animals.entity.passive.EntityBird;
import clickme.animals.entity.passive.EntityDuck;
import clickme.animals.entity.passive.EntityLizard;
import clickme.animals.entity.passive.EntityMouse;
import clickme.animals.entity.passive.EntityPenguin;
import clickme.animals.entity.passive.EntitySnake;
import clickme.animals.entity.water.EntityAngler;
import clickme.animals.entity.water.EntityFish;
import clickme.animals.entity.water.EntityMantaRay;
import clickme.animals.entity.water.EntityPiranha;
import clickme.animals.entity.water.EntityShark;
import clickme.animals.entity.water.EntityTropiFish;
import clickme.animals.entity.water.EntityWhale;
import com.jerios.evilMinecraftFixes.Evil;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

@Mixin(EntityManager.class)
public class MixinEntityManager {

    /**
     * @author Jerios
     * @reason use Mod Registry
     */
    @Overwrite(remap = false)
    public static void registerEntities() {
        evil$registerMod(EntityCentipede.class, "Centipede",  15708256, 5848090);
        evil$registerMod(EntityCricket.class, "Cricket",  8343842, 2100236);
        evil$registerMod(EntityButterfly.class, "Butterfly",  15493137, 721666);
        evil$registerMod(EntityMoth.class, "Moth",  13614758, 6704950);
        evil$registerMod(EntityFish.class, "Fish",  6928807, 6057867);
        evil$registerMod(EntityTropiFish.class, "TropicalFish",  15887623, 15725300);
        evil$registerMod(EntityAngler.class, "Angler",  5397296, 15392616);
        evil$registerMod(EntityMantaRay.class, "MantaRay",  1052965, 14474460);
        evil$registerMod(EntityWhale.class, "Whale",  12772830, 8497600);
        evil$registerMod(EntitySnake.class, "Snake",  7096116, 14531977);
        evil$registerMod(EntityLizard.class, "Lizard",  13815232, 8219967);
        evil$registerMod(EntityMouse.class, "Mouse",  5986381, 15902877);
        evil$registerMod(EntityBird.class, "Bird",  4934535, 15910160);
        evil$registerMod(EntityDuck.class, "Duck",  4413191, 13155998);
        evil$registerMod(EntityPenguin.class, "Pinguin",  1066089, 13948116);
        evil$registerMod(EntityPiranha.class, "Piranha",  2829109, 14634030);
        evil$registerMod(EntityShark.class, "Shark",  11053224, 7631988);
    }

    @Unique
    private static int evil$modID = 2;

    private static void evil$registerMod(Class<? extends Entity> entityClass, String entityName, int i1, int i2) {
        EntityRegistry.registerModEntity(entityClass, entityName,evil$modID++, Evil.INSTANCE, 128, 1, true);
    }

}
