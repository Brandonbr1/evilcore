package com.jerios.evilMinecraftFixes.mixins.late.fakeOres;

import com.jerios.evilMinecraftFixes.cfg.Config;
import fr.elias.fakeores.common.BlockFakeOresVanilla;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockFakeOresVanilla.class)
public class MixinBlockFakeOresVanilla extends Block {
    protected MixinBlockFakeOresVanilla(Material materialIn) {
        super(materialIn);
    }

    public void onNeighborBlockChange(World worldIn, int x, int y, int z, Block neighbor) {
        super.onNeighborBlockChange(worldIn, x, y, z, neighbor);
        if (Config.oreAttack) {
            if (neighbor == this) {
                neighbor.onBlockDestroyedByPlayer(worldIn, x, y, z, worldIn.getBlockMetadata(x, y, z));
                worldIn.setBlockToAir(x,y,z);
            }
        }


    }

}
