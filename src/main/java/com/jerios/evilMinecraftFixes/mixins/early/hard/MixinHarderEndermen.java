package com.jerios.evilMinecraftFixes.mixins.early.hard;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**See if the bedrock endermen can be added fairly **/
@Mixin(EntityEnderman.class)
public class MixinHarderEndermen extends EntityMob {

  //  @Shadow private boolean isAggressive;
  //  @Shadow private int stareTimer;

    public MixinHarderEndermen(World p_i1738_1_) {
        super(p_i1738_1_);
    }

   // @Inject(method = "onLivingUpdate", at=@At("TAIL"))
 //   private void evil$inject(CallbackInfo ci) {
       // EntityEnderman ender = ((EntityEnderman) (Object) this);
     //   dismountEntity(ender);
  //  }

  //  @Shadow
  //  public void setScreaming(boolean p_70819_1_)
  //  {
   //     this.dataWatcher.updateObject(18, Byte.valueOf((byte)(p_70819_1_ ? 1 : 0)));
   // }

    /**
     * @author
     * @reason

    @Overwrite
    protected Entity findPlayerToAttack()
    {
        EntityPlayer entityplayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 64.0D);

        if (entityplayer != null)
        {
            if (this.shouldAttackPlayer(entityplayer))
            {
                this.isAggressive = true;

                this.worldObj.playSoundEffect(entityplayer.posX, entityplayer.posY, entityplayer.posZ, "mob.endermen.stare", 1.0F, 1.0F);
       //         this.stareTimer = 0;
                this.setScreaming(true);
                return entityplayer;

            }
         //   else
         //   {
            //    this.stareTimer = 0;
         //   }
        }

        return null;
    }
     */

    /**
     * @author
     * @reason

    @Overwrite
    private boolean shouldAttackPlayer(EntityPlayer p_70821_1_)
    {
        ItemStack itemstack = p_70821_1_.inventory.armorInventory[3];

        if (itemstack != null && itemstack.getItem() == Item.getItemFromBlock(Blocks.pumpkin))
        {
            return false;
        }
        else
        {
            Vec3 vec3 = p_70821_1_.getLook(1.0F).normalize();
            Vec3 vec31 = Vec3.createVectorHelper(this.posX - p_70821_1_.posX, this.boundingBox.minY + (double)(this.height / 2.0F) - (p_70821_1_.posY + (double)p_70821_1_.getEyeHeight()), this.posZ - p_70821_1_.posZ);
            double d0 = vec31.lengthVector();
            vec31 = vec31.normalize();
            double d1 = vec3.dotProduct(vec31);
            return d1 > 1.0D - 0.039D / d0 && p_70821_1_.canEntityBeSeen(this);
        }
    }*/

}
