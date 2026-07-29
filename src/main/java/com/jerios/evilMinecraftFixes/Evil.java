package com.jerios.evilMinecraftFixes;

import com.jerios.evilMinecraftFixes.athena.AthenaEvents;
import com.jerios.evilMinecraftFixes.athena.EntityWitherDeathManager;
import com.jerios.evilMinecraftFixes.athena.WitherProps;
import com.jerios.evilMinecraftFixes.evilOres.OreBossTickEvent;
import com.jerios.evilMinecraftFixes.evilOres.OresAttackEvent;
import com.jerios.evilMinecraftFixes.evilOres.OresInteg;
import com.jerios.evilMinecraftFixes.hee.EntityCrystalBomb;
import com.jerios.evilMinecraftFixes.infernalMobs.InfernalMobsMakeNeturalMobsAgressiveEvent;
import com.jerios.evilMinecraftFixes.mixins.early.IEntityPigmenAccessor;
import com.jerios.evilMinecraftFixes.zombieAwareness.WorldRefEvent;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.EntityRegistry;
import hostileworlds.entity.monster.ZombieClimber;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jerios.evilMinecraftFixes.mixins.Config;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;


@Mod(modid = Evil.MODID, version = "1.0", name = "evilMinecraftFixes", acceptedMinecraftVersions = "[1.7.10]")
public class Evil {

    public static final String MODID = "evilMinecraftFixes";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @SidedProxy(
        clientSide = "com.jerios.evilMinecraftFixes.ClientProxy",
        serverSide = "com.jerios.evilMinecraftFixes.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance("evilMinecraftFixes")
    public static Evil INSTANCE;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());
        proxy.preInit(event);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new OreBossTickEvent());
        MinecraftForge.EVENT_BUS.register(new WorldRefEvent());
        MinecraftForge.EVENT_BUS.register(new OresAttackEvent());
        MinecraftForge.EVENT_BUS.register(new AthenaEvents());
        MinecraftForge.EVENT_BUS.register(new InfernalMobsMakeNeturalMobsAgressiveEvent());
        WitherProps.register();
        EntityRegistry.registerModEntity(EntityWitherDeathManager.class, "death", 54, INSTANCE, 256, 1, true);
        EntityRegistry.registerModEntity(EntityCrystalBomb.class, "bomb", 808, INSTANCE, 256, 1, true);
    }



    @Mod.EventHandler
    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        if (Config.spawnLadderZombie) {
            BiomeGenBase[] biomes = {BiomeGenBase.desert, BiomeGenBase.taiga, BiomeGenBase.forest, BiomeGenBase.jungle, BiomeGenBase.plains, BiomeGenBase.swampland, BiomeGenBase.mesa, BiomeGenBase.roofedForest};
            EntityRegistry.addSpawn(ZombieClimber.class, 1, 1, 2, EnumCreatureType.monster, biomes);
        }

        if (Loader.isModLoaded("dextersnether")) {
            OresInteg.init(event);
        }
    }

    @SubscribeEvent
    public void noRegeneration(WorldEvent.Load event) {
        if (Config.naturalRegeneration) {
            GameRules rules = event.world.getGameRules();
            if (rules.getGameRuleBooleanValue("naturalRegeneration")) {
                rules.setOrCreateGameRule("naturalRegeneration", "false");
            }
        }
    }

    @Mod.EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }




    public static void spawnSkeketonMinionNearPlayer(EntityLiving spawn, World world, EntityPlayer player, Entity entity) {
      //  EntitySkeleton skeleton = new EntitySkeleton(world);
        EntityLiving skeleton = spawn;
        skeleton.setAlwaysRenderNameTag(true);
        skeleton.setCustomNameTag("WITHER MINIONS");

        skeleton.addPotionEffect(new PotionEffect(Potion.regeneration.id, Integer.MAX_VALUE , 1, false));
        skeleton.addPotionEffect(new PotionEffect(Potion.resistance.id, Integer.MAX_VALUE , 1, false));

        if (skeleton instanceof EntitySkeleton) {
            ((EntitySkeleton)skeleton).setSkeletonType(1);
        }

        if (skeleton instanceof EntityPigZombie) {
            EntityPigZombie pig = (EntityPigZombie) skeleton;
            ((IEntityPigmenAccessor)pig).evil$setAnger(900);
            ((IEntityPigmenAccessor)pig).evil$soundDelay(900);
        }

        skeleton.setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));


        skeleton.setCurrentItemOrArmor(4, new ItemStack(Items.iron_helmet));
        skeleton.setCurrentItemOrArmor(3, new ItemStack(Items.iron_chestplate));
        skeleton.setCurrentItemOrArmor(2, new ItemStack(Items.iron_leggings));
        skeleton.setCurrentItemOrArmor(1, new ItemStack(Items.iron_boots));
        skeleton.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D + world.difficultySetting.getDifficultyId());

        double x = 0;
        double y = 0;
        double z= 0;
        if (entity != null) {
             x = entity.posX;
             y = entity.posY;
             z = entity.posZ;
        }

        if (player != null) {
            x = player.posX;
            y = player.posY;
            z = player.posZ;
        }
        if (y == 0 && x == 0 && z == 0) {
            System.out.println("I was equal to zero... That seems bad");
        }


        skeleton.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
        skeleton.rotationYawHead = skeleton.rotationYaw;
        skeleton.renderYawOffset = skeleton.rotationYaw;
        ///  entityliving.onSpawnWithEgg((IEntityLivingData)null);
        world.spawnEntityInWorld(skeleton);
        skeleton.playLivingSound();

        WitherProps skeprops = WitherProps.get(skeleton);
        if (skeprops != null) {
            skeprops.immuneToExplosions = true;
        }

    }
}
