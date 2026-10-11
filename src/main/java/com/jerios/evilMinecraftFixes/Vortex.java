package com.jerios.evilMinecraftFixes;

import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class Vortex {

    public static void ensureBlockExists(World world, int x, int y, int z) {
        if (world instanceof WorldServer worldServer) {


            ensureBlockExists(worldServer, x, y, z);
        }
    }

    public static void ensureBlockExists(WorldServer world, int x, int y, int z) {
        if (world != null && !world.blockExists(x, y, z)) {
            System.out.println("It did not exist");
            world.getChunkProvider()
                .loadChunk(x >> 4, z >> 4);
        }
    }

    public static void loadChunk(World world, int x, int z) {
        if (world instanceof WorldServer) {
            loadChunk(world, x,z);
        }
        }

    private static void loadChunk(WorldServer world, int x, int z) {
        if (world != null) {
            world.getChunkProvider()
                .loadChunk(x >> 4, z >> 4);
        }
    }


    }


