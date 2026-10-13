package com.jerios.evilMinecraftFixes.hostileWorlds.dim;

import com.jerios.evilMinecraftFixes.content.ContentRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.WorldServer;

public class HWTeleportHelper {

    public static void travelToDimension(EntityPlayerMP playerMP, int dimensionId)
    {

        playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, dimensionId, new TeleporterHW(playerMP.mcServer.worldServerForDimension(dimensionId)));

        // change this properly?
           // playerMP.lastExperience = -1;
          //  playerMP.lastHealth = -1.0F;
            //playerMP.lastFoodLevel = -1;
        }


    public static void travelToDimension(Entity ent, int dimensionId)
    {
        if (!ent.worldObj.isRemote && !ent.isDead)
        {
            ent.worldObj.theProfiler.startSection("changeDimension");
            MinecraftServer minecraftserver = MinecraftServer.getServer();
            int originalDim = ent.dimension;
            WorldServer worldserver = minecraftserver.worldServerForDimension(originalDim);
            WorldServer worldserver1 = minecraftserver.worldServerForDimension(dimensionId);
            ent.dimension = dimensionId;

            if (originalDim == 1 && dimensionId == 1)
            {
                worldserver1 = minecraftserver.worldServerForDimension(0);
                ent.dimension = 0;
            }

            ent.worldObj.removeEntity(ent);
            ent.isDead = false;
            ent.worldObj.theProfiler.startSection("reposition");
            minecraftserver.getConfigurationManager().transferEntityToWorld(ent, originalDim, worldserver, worldserver1);
            ent.worldObj.theProfiler.endStartSection("reloading");
            Entity entity = EntityList.createEntityByName(EntityList.getEntityString(ent), worldserver1);

            if (entity != null)
            {
                entity.copyDataFrom(ent, true);

                if (originalDim == 1 && dimensionId == 1)
                {
                    ChunkCoordinates chunkcoordinates = worldserver1.getSpawnPoint();
                    chunkcoordinates.posY = ent.worldObj.getTopSolidOrLiquidBlock(chunkcoordinates.posX, chunkcoordinates.posZ);
                    entity.setLocationAndAngles((double)chunkcoordinates.posX, (double)chunkcoordinates.posY, (double)chunkcoordinates.posZ, entity.rotationYaw, entity.rotationPitch);
                }

                worldserver1.spawnEntityInWorld(entity);
            }

            ent.isDead = true;
            ent.worldObj.theProfiler.endSection();
            worldserver.resetUpdateEntityTick();
            worldserver1.resetUpdateEntityTick();
            ent.worldObj.theProfiler.endSection();
        }
    }

}



