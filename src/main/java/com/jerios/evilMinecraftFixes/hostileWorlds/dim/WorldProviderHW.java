package com.jerios.evilMinecraftFixes.hostileWorlds.dim;

import com.jerios.evilMinecraftFixes.content.ContentRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderHell;

public class WorldProviderHW extends WorldProvider {

    /**
     * creates a new world chunk manager for WorldProvider
     */
    public void registerWorldChunkManager()
    {
        this.worldChunkMgr = new WorldChunkManagerHW(ContentRegistry.HW, 0.0F);
        this.hasNoSky = true;
        this.dimensionId = -63;
    }


    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float p_76562_1_, float p_76562_2_)
    {
        int i = 10518688;
        float f2 = MathHelper.cos(p_76562_1_ * (float)Math.PI * 2.0F) * 2.0F + 0.5F;

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }

        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        float f3 = (float)(i >> 16 & 255) / 255.0F;
        float f4 = (float)(i >> 8 & 255) / 255.0F;
        float f5 = (float)(i & 255) / 255.0F;
        f3 *= f2 * 0.0F + 0.15F;
        f4 *= f2 * 0.0F + 0.15F;
        f5 *= f2 * 0.0F + 0.15F;
        return Vec3.createVectorHelper((double)f3, (double)f4, (double)f5);
    }

    /**
     * Creates the light to brightness table
     */
    protected void generateLightBrightnessTable()
    {
        float f = 0.1F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    }

    /**
     * Returns a new chunk provider which generates chunks for this world
     */
    public IChunkProvider createChunkGenerator()
    {
        return new ChunkProviderHW(this.worldObj, this.worldObj.getSeed());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isSkyColored()
    {
        return true;
    }


    /**
     * Gets the hard-coded portal location to use when entering this dimension.
     */
    public ChunkCoordinates getEntrancePortalLocation()
    {
        return new ChunkCoordinates(0, 3, 0);
    }

    public int getAverageGroundLevel()
    {
        return 3;
    }


    public boolean isSurfaceWorld()
    {
        return false;
    }

    public boolean canCoordinateBeSpawn(int p_76566_1_, int p_76566_2_)
    {
        return this.worldObj.getTopBlock(p_76566_1_, p_76566_2_).getMaterial().blocksMovement();
    }


    public float calculateCelestialAngle(long p_76563_1_, float p_76563_3_)
    {
        return 0.5F;
    }



    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int p_76568_1_, int p_76568_2_)
    {
        return true;
    }

    /**
     * Returns the dimension's name, e.g. "The End", "Nether", or "Overworld".
     */
    public String getDimensionName()
    {
        return "HW_INVASION_DIM";
    }


}
