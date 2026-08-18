package com.jerios.evilMinecraftFixes;

import com.jerios.evilMinecraftFixes.CQAdditions.CQInteg;
import com.jerios.evilMinecraftFixes.athena.AthenaEvents;
import com.jerios.evilMinecraftFixes.athena.EntityWitherDeathManager;
import com.jerios.evilMinecraftFixes.athena.WitherProps;
import com.jerios.evilMinecraftFixes.content.ContentRegistry;
import com.jerios.evilMinecraftFixes.evilOres.OreBossTickEvent;
import com.jerios.evilMinecraftFixes.evilOres.OresAttackEvent;
import com.jerios.evilMinecraftFixes.evilOres.OresInteg;
import com.jerios.evilMinecraftFixes.evilOres.world.EvilGenFakeOres;
import com.jerios.evilMinecraftFixes.hee.EntityCrystalBomb;
import com.jerios.evilMinecraftFixes.hostileWorlds.HWSpawns;
import com.jerios.evilMinecraftFixes.infernalMobs.InfernalMobsMakeNeturalMobsAgressiveEvent;
import com.jerios.evilMinecraftFixes.infernalMobs.InfernalMobsSaveHandler;
import com.jerios.evilMinecraftFixes.ironBackPacks.IronBackPacksLimitAmmountEvent;
import com.jerios.evilMinecraftFixes.mixins.early.IEntityPigmenAccessor;
import com.jerios.evilMinecraftFixes.packet.NetworkHandler;
import com.jerios.evilMinecraftFixes.pg.PGI;
import com.jerios.evilMinecraftFixes.zombieAwareness.WorldRefEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jerios.evilMinecraftFixes.cfg.Config;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import java.io.File;


@Mod(modid = Evil.MODID, version = "1.0", name = "evilMinecraftFixes", acceptedMinecraftVersions = "[1.7.10]")
public class Evil {

    public static final String PREFIX2 = "evilmc:";
    public static final String MODID = "evilMinecraftFixes";
    public static final String PREFIX = "evilMinecraftFixes:";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @SidedProxy(
        clientSide = "com.jerios.evilMinecraftFixes.ClientProxy",
        serverSide = "com.jerios.evilMinecraftFixes.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance("evilMinecraftFixes")
    public static Evil INSTANCE;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
       // ReplaceMobFilter.replaceMobFilterWithInstanceThatDoesNotAttackBomby();

        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());
        proxy.preInit(event);
        ContentRegistry.register();
        NetworkHandler.init();


        if (Config.bufffNetherMobs) {
            MinecraftForge.EVENT_BUS.register(new BuffMobs());
        }

        MinecraftForge.EVENT_BUS.register(this);

        if (Loader.isModLoaded("ZAMod")) {
            MinecraftForge.EVENT_BUS.register(new WorldRefEvent());
        }

        if (Loader.isModLoaded("fakeores")) {
            MinecraftForge.EVENT_BUS.register(new OreBossTickEvent());
            MinecraftForge.EVENT_BUS.register(new OresAttackEvent());
        }

        if (Config.athena) {
            MinecraftForge.EVENT_BUS.register(new AthenaEvents());
        }

        if (Loader.isModLoaded("InfernalMobs")) {
            FMLCommonHandler.instance().bus().register(new InfernalMobsSaveHandler());
            MinecraftForge.EVENT_BUS.register(new InfernalMobsSaveHandler());
            MinecraftForge.EVENT_BUS.register(new InfernalMobsMakeNeturalMobsAgressiveEvent());

        }



        if (Loader.isModLoaded("thirstmod")) {
            MinecraftForge.EVENT_BUS.register(new ThirstEvents());
            MinecraftForge.EVENT_BUS.register(new ThirstSatuartionEvent());
        }

        if (Loader.isModLoaded("ironbackpacks")) {
            FMLCommonHandler.instance().bus().register(new IronBackPacksLimitAmmountEvent());

            MinecraftForge.EVENT_BUS.register(new IronBackPacksLimitAmmountEvent());
        }


        WitherProps.register();

        EntityRegistry.registerModEntity(EntityWitherDeathManager.class, "death", 54, INSTANCE, 256, 1, true);

        EntityRegistry.registerModEntity(EntityCrystalBomb.class, "bomb", 808, INSTANCE, 256, 1, true);

        if (Loader.isModLoaded("fakeores")) {
            GameRegistry.registerWorldGenerator(new EvilGenFakeOres(), 1);
        }
    }



    @Mod.EventHandler
    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        proxy.init(event);

        HWSpawns.spawnHook();


        if (Loader.isModLoaded("fakeores")) {
            OresInteg.init(event);
        }


        if (Loader.isModLoaded("chocolateQuest")) {
            CQInteg.register();
        }

        if (Loader.isModLoaded("ParticleMan")) {
            PGI.re();
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
