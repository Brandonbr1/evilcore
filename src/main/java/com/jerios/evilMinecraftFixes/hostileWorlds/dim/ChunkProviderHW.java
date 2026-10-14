package com.jerios.evilMinecraftFixes.hostileWorlds.dim;

import hostileworlds.HostileWorlds;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.gen.feature.WorldGenLakes;

import java.util.List;
import java.util.Random;

public class ChunkProviderHW implements IChunkProvider {

    private World worldObj;
    private Random random;
    private final Block[] cachedBlockIDs = new Block[256];
    private final byte[] cachedBlockMetadata = new byte[256];

    private WorldGenLakes airWorldCorruption;

    public ChunkProviderHW(World p_i2004_1_, long p_i2004_2_)
    {
        this.worldObj = p_i2004_1_;
        this.random = new Random(p_i2004_2_ >> System.currentTimeMillis());

        airWorldCorruption = new WorldGenLakes(Blocks.air);
            for (int j = 0; j < 8; ++j)
            {

                if (j <= 3)  {
                    this.cachedBlockIDs[j] = HostileWorlds.blockBloodyCobblestone;
                    this.cachedBlockMetadata[j] = (byte) 0;
                } else {
                    this.cachedBlockIDs[j] = Blocks.mossy_cobblestone;
                    this.cachedBlockMetadata[j] = (byte) 0;
                }

         /**       if (j < 6 && j > 3)
                {
                    if (random.nextInt(4) > 2)
                    {
                        this.cachedBlockIDs[j] = Blocks.air;
                        this.cachedBlockMetadata[j] = (byte) 0;
                    }
                }
                **/
            }

    }

    /**
     * loads or generates the chunk at the chunk location specified
     */
    public Chunk loadChunk(int p_73158_1_, int p_73158_2_)
    {
        return this.provideChunk(p_73158_1_, p_73158_2_);
    }

    /**
     * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
     * specified chunk from the map seed and chunk seed
     */
    public Chunk provideChunk(int p_73154_1_, int p_73154_2_)
    {
        Chunk chunk = new Chunk(this.worldObj, p_73154_1_, p_73154_2_);
        int l;

        for (int k = 0; k < this.cachedBlockIDs.length; ++k)
        {
            Block block = this.cachedBlockIDs[k];

            if (block != null)
            {
                l = k >> 4;
                ExtendedBlockStorage extendedblockstorage = chunk.getBlockStorageArray()[l];

                if (extendedblockstorage == null)
                {
                    extendedblockstorage = new ExtendedBlockStorage(k, !this.worldObj.provider.hasNoSky);
                    chunk.getBlockStorageArray()[l] = extendedblockstorage;
                }

                for (int i1 = 0; i1 < 16; ++i1)
                {
                    for (int j1 = 0; j1 < 16; ++j1)
                    {

                        if (random.nextInt(25) == 0)
                        {
                            int a = k & 15;
                            if (a < 5 && a > 2)
                            {
                                block = Blocks.air;
                            }

                            if (random.nextInt(8) == 0)
                            {
                                block = Blocks.lava;
                            }

                        }

                            extendedblockstorage.func_150818_a(i1, k & 15, j1, block);
                            extendedblockstorage.setExtBlockMetadata(i1, k & 15, j1, this.cachedBlockMetadata[k]);

                    }
                }
            }
        }

        chunk.generateSkylightMap();
        BiomeGenBase[] abiomegenbase = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[])null, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
        byte[] abyte = chunk.getBiomeArray();

        for (l = 0; l < abyte.length; ++l)
        {
            abyte[l] = (byte)abiomegenbase[l].biomeID;
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    /**
     * Checks to see if a chunk exists at x, y
     */
    public boolean chunkExists(int p_73149_1_, int p_73149_2_)
    {
        return true;
    }

    /**
     * Populates chunk with ores etc etc
     */
    public void populate(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_)
    {
        int k = p_73153_2_ * 16;
        int l = p_73153_3_ * 16;
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(k + 16, l + 16);
        this.random.setSeed(this.worldObj.getSeed());
        long i1 = this.random.nextLong() / 2L * 2L + 1L;
        long j1 = this.random.nextLong() / 2L * 2L + 1L;
        this.random.setSeed((long)p_73153_2_ * i1 + (long)p_73153_3_ * j1 ^ this.worldObj.getSeed());

        biomegenbase.decorate(this.worldObj, this.random, k, l);

        int l1;
        int i2;
        int j2;

        if (this.random.nextInt(8) == 0)
        {
            l1 = k + this.random.nextInt(16) + 8;
            i2 = 4;
            j2 = l + this.random.nextInt(16) + 8;

          //  if (this.random.nextInt(4) == 0)
          //  {
                this.airWorldCorruption.generate(this.worldObj, this.random, l1, i2, j2);
          //  }
        }

    }

    /**
     * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
     * Return true if all chunks have been saved.
     */
    public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_)
    {
        return true;
    }

    /**
     * Save extra data not associated with any Chunk.  Not saved during autosave, only during world unload.  Currently
     * unimplemented.
     */
    public void saveExtraData() {}

    /**
     * Unloads chunks that are marked to be unloaded. This is not guaranteed to unload every such chunk.
     */
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    /**
     * Returns if the IChunkProvider supports saving.
     */
    public boolean canSave()
    {
        return true;
    }

    /**
     * Converts the instance data to a readable string.
     */
    public String makeString()
    {
        return "FlatLevelSource";
    }

    /**
     * Returns a list of creatures of the specified type that can spawn at the given location.
     */
    public List<net.minecraft.world.biome.BiomeGenBase.SpawnListEntry> getPossibleCreatures(EnumCreatureType p_73155_1_, int p_73155_2_, int p_73155_3_, int p_73155_4_)
    {
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(p_73155_2_, p_73155_4_);
        return biomegenbase.getSpawnableList(p_73155_1_);
    }

    public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
    {
        return null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }

    public void recreateStructures(int p_82695_1_, int p_82695_2_)
    {
    }
}
