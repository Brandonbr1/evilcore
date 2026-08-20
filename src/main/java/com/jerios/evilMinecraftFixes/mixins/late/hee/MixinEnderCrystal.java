package com.jerios.evilMinecraftFixes.mixins.late.hee;

import chylex.hee.entity.block.EntityBlockEnderCrystal;
import chylex.hee.entity.boss.EntityBossDragon;
import chylex.hee.proxy.ModCommonProxy;
import com.jerios.evilMinecraftFixes.hee.EntityCrystalBomb;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(EntityBlockEnderCrystal.class)
public class MixinEnderCrystal extends EntityEnderCrystal {


    private byte attackTimer = (byte) (ModCommonProxy.opMobs ? 60 : 110);

    public void onUpdate() {
        super.onUpdate();


        if (this.worldObj.difficultySetting.getDifficultyId() > 0 && (this.attackTimer = (byte) (this.attackTimer - 1)) == 0) {
            this.attackTimer = (byte)(80 + 15 * (3 - this.worldObj.difficultySetting.getDifficultyId()));
            for (EntityPlayer o : this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, ((Entity)this).boundingBox.expand(32.0D, 128.0D, 32.0D))) {
                EntityPlayer player = o;
                double dist = Math.sqrt(Math.pow(this.posX - player.posX, 2.0D) + Math.pow(this.posZ - player.posZ, 2.0D));
                if (dist < 4.0D || dist > 32.0D) {
                    continue;
                }
                int iter = 1;
                if (ModCommonProxy.opMobs) {
                    iter = 3;
                }
                List<EntityBossDragon> list = this.worldObj.getEntitiesWithinAABB(EntityBossDragon.class, this.boundingBox.expand(256, 256, 256));

                for (int i = 0; i < list.size(); i++) {
                    EntityBossDragon dragon = list.get(i);

                    if (dragon.isAngry()) {
                        iter += 3;
                    }
                }
                if (!player.worldObj.isRemote) {
                    player.addPotionEffect(new PotionEffect(Potion.wither.id, 120, 1));
                    player.addPotionEffect(new PotionEffect(Potion.blindness.id, 160, 1));
                }

                for (int i = 0; i < iter; i++) {
                    EntityCrystalBomb bomb = new EntityCrystalBomb(this.worldObj, this.posX, this.posY + 2.2D, this.posZ, player);
                    this.worldObj.spawnEntityInWorld(bomb);
                }

            }
        }
    }

    public MixinEnderCrystal(World p_i1698_1_) {
        super(p_i1698_1_);
    }

}
