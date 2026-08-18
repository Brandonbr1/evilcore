package com.jerios.evilMinecraftFixes.mixins.early.hard;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockMobSpawner.class)
public class MixinTileEntityMobSpawner extends BlockContainer {


    protected MixinTileEntityMobSpawner(Material p_i45386_1_) {
        super(p_i45386_1_);
    }

    @Override
    public void breakBlock(World worldIn, int x, int y, int z, Block blockBroken, int meta) {

        TileEntityMobSpawner thisTile = (TileEntityMobSpawner) worldIn.getTileEntity(x,y,z);

        if (thisTile != null) {

                for (int i = 0; i < 4; i++) {
                        String mobToSpawn = thisTile.func_145881_a().getEntityNameToSpawn();
                        Entity entity = EntityList.createEntityByName(mobToSpawn, worldIn);

                        if (entity instanceof EntityLiving) {

                            for (int j = 0; j < ((EntityLiving) entity).getTotalArmorValue() + 1; j++) {

                                EntityLiving living = (EntityLiving) entity;

                                living.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 5000, 0, false));
                                living.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 5000, 0, false));
                                living.addPotionEffect(new PotionEffect(Potion.resistance.id, 5000, 0, false));
                                entity.setLocationAndAngles(x, y + 1, z, worldIn.rand.nextFloat() * 360.0F, 0.0F);
                                worldIn.spawnEntityInWorld(living);

                            }



                        }



                }


        }


        super.breakBlock(worldIn, x, y, z, blockBroken, meta);
    }

    @Shadow
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }
}
