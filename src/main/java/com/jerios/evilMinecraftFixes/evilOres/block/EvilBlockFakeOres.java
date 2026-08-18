package com.jerios.evilMinecraftFixes.evilOres.block;

import com.jerios.evilMinecraftFixes.evilOres.OresInteg;
import com.jerios.evilMinecraftFixes.evilOres.mob.EntityGlowstone;
import com.jerios.evilMinecraftFixes.evilOres.mob.EntityGoldNugget;
import com.jerios.evilMinecraftFixes.evilOres.mob.EntityNetheriteOre;
import com.jerios.evilMinecraftFixes.cfg.Config;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.Loader;
import fr.elias.fakeores.common.FakeOres;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import java.util.Random;

public class EvilBlockFakeOres extends Block {

    public EvilBlockFakeOres() {
        super(Material.rock);
        this.setCreativeTab(FakeOres.fakeOresTab);
    }

    public void onBlockDestroyedByPlayer(World par1World, int x, int y, int z, int metaData) {
        if (par1World.difficultySetting.equals(EnumDifficulty.PEACEFUL)) {
            FMLClientHandler.instance().getClient().thePlayer.addChatMessage(new ChatComponentText("Your Minecraft is in peaceful mode. All fake ores are now disabled. Please change your difficulty setting."));
        }

        if (!par1World.isRemote) {
            if (Loader.isModLoaded("dextersnether")) {
                if (this == OresInteg.fakeNetherrite) {
                    EntityNetheriteOre copperEntity = new EntityNetheriteOre(par1World);
                    copperEntity.setLocationAndAngles((double)x + (double)0.5F, (double)y, (double)z + (double)0.5F, 0.0F, 0.0F);
                    par1World.spawnEntityInWorld(copperEntity);
                }

                if (this == OresInteg.fakeNetherGold) {
                    EntityGoldNugget copperEntity = new EntityGoldNugget(par1World);
                    copperEntity.setLocationAndAngles((double)x + (double)0.5F, (double)y, (double)z + (double)0.5F, 0.0F, 0.0F);
                    par1World.spawnEntityInWorld(copperEntity);
                }

            }

            if (this == OresInteg.fakeGlowstone) {
                EntityGlowstone copperEntity = new EntityGlowstone(par1World);
                copperEntity.setLocationAndAngles((double)x + (double)0.5F, (double)y, (double)z + (double)0.5F, 0.0F, 0.0F);
                par1World.spawnEntityInWorld(copperEntity);
            }


            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 5; k++) {

                    }

                }

            }

            super.onBlockDestroyedByPlayer(par1World, x, y, z, metaData);
        }

    }


    @Override
    public void onNeighborBlockChange(World worldIn, int x, int y, int z, Block neighbor) {
        super.onNeighborBlockChange(worldIn, x, y, z, neighbor);
        if (Config.oreAttack) {
            if (neighbor == this) {
                neighbor.onBlockDestroyedByPlayer(worldIn, x, y, z, worldIn.getBlockMetadata(x, y, z));
                worldIn.setBlockToAir(x,y,z);
            }
        }


    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return null;
    }

    public int quantityDropped(Random par1Random) {
        return 0;
    }

    public int idPicked(World par1World, int par2, int par3, int par4) {
        return 0;
    }
}
