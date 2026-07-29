package com.jerios.evilMinecraftFixes.evilOres.block;

import com.jerios.evilMinecraftFixes.evilOres.OresInteg;
import com.jerios.evilMinecraftFixes.evilOres.mob.EntityGlowstone;
import com.jerios.evilMinecraftFixes.evilOres.mob.EntityNetheriteOre;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import fr.elias.fakeores.common.BlockFakeOres;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EvilBlockFakeOres extends BlockFakeOres {

    public void onBlockDestroyedByPlayer(World par1World, int x, int y, int z, int metaData) {
        if (FMLCommonHandler.instance().getEffectiveSide().isClient() && par1World.difficultySetting == EnumDifficulty.PEACEFUL) {
            FMLClientHandler.instance().getClient().thePlayer.addChatMessage(new ChatComponentText("Your Minecraft is in peaceful mode. All fake ores are now disabled. Please change your difficulty setting."));
        }

        if (!par1World.isRemote) {
            if (Loader.isModLoaded("dextersnether")) {
                if (this == OresInteg.fakeNetherrite) {
                    EntityNetheriteOre copperEntity = new EntityNetheriteOre(par1World);
                    copperEntity.setLocationAndAngles((double)x + (double)0.5F, (double)y, (double)z + (double)0.5F, 0.0F, 0.0F);
                    par1World.spawnEntityInWorld(copperEntity);
                }

                if (this == OresInteg.fakeGlowstone) {
                    EntityGlowstone copperEntity = new EntityGlowstone(par1World);
                    copperEntity.setLocationAndAngles((double)x + (double)0.5F, (double)y, (double)z + (double)0.5F, 0.0F, 0.0F);
                    par1World.spawnEntityInWorld(copperEntity);
                }
            }

            super.onBlockDestroyedByPlayer(par1World, x, y, z, metaData);
        }

    }
}
