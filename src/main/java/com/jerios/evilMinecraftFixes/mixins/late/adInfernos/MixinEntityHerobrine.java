package com.jerios.evilMinecraftFixes.mixins.late.adInfernos;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.superdextor.dextersnether.entity.monster.EntityHerobrine;
import com.superdextor.dextersnether.init.NetherItems;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityHerobrine.class)
public class MixinEntityHerobrine extends EntityMob {

   @Shadow(remap = false)
   private int cooldown = 0;
    @Shadow(remap = false)   private int attackUpdateTimer = 0;
    @Shadow(remap = false)  private int SpecialCooldown = 0;

    public MixinEntityHerobrine(World p_i1738_1_) {
        super(p_i1738_1_);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_) {
        this.setCurrentItemOrArmor(1, new ItemStack(NetherItems.wither_helmet));
        this.setCurrentItemOrArmor(2, new ItemStack(NetherItems.wither_chestplate));
        this.setCurrentItemOrArmor(3, new ItemStack(NetherItems.wither_leggings));
        this.setCurrentItemOrArmor(4, new ItemStack(NetherItems.wither_boots));
        return super.onSpawnWithEgg(p_110161_1_);
    }

    /**
     * @author Jerios
     * @reason Cap it so it is not unkillable
     */
    @Overwrite
    public int getTotalArmorValue() {
        return Math.max(6, Math.min(21, super.getTotalArmorValue()));
    }


    @Inject(method = "onLivingUpdate", at=@At("HEAD"))
   private void evil$pinchMode(CallbackInfo ci) {
       if (!this.worldObj.isRemote) {


           if (this.getHealth() <= 160) {
               if (this.getEquipmentInSlot(1) != null) {
                   this.renderBrokenItemStack(this.getEquipmentInSlot(1));
                   this.setCurrentItemOrArmor(1, null);
               }
           }

           if (this.getHealth() <= 120) {
               if (this.getEquipmentInSlot(2) != null) {
                   this.renderBrokenItemStack(this.getEquipmentInSlot(2));
                   this.setCurrentItemOrArmor(2, null);

               }
           }
           if (this.getHealth() <= 80) {
               if (this.getEquipmentInSlot(3) != null) {
                   if (this.getEquipmentInSlot(3) != null) {
                       this.renderBrokenItemStack(this.getEquipmentInSlot(3));
                       this.setCurrentItemOrArmor(3, null);

                   }

               }
           }

           if (this.getHealth() <= 40) {
           if (this.getEquipmentInSlot(4) != null) {
               if (this.getEquipmentInSlot(4) != null) {
                   this.renderBrokenItemStack(this.getEquipmentInSlot(4));
                   this.setCurrentItemOrArmor(4, null);

               }
           }

           }


           if (this.getHealth() <= 120) {
               cooldown--;
               attackUpdateTimer--;
               SpecialCooldown--;
           }

           if (this.getHealth() <= 80) {
               cooldown--;
               attackUpdateTimer--;
               SpecialCooldown-=2;
           }

           if (this.getHealth() <= 40) {
               cooldown--;
               attackUpdateTimer--;
               SpecialCooldown-=3;
           }
       }
   }

    @Override
    protected void onDeathUpdate() {
        if (this.deathTime > 15)
        {
            SpecialAttack(0);
            SpecialAttack(2);
        }
        super.onDeathUpdate();
    }

    @Shadow(remap = false)
    protected void SpecialAttack(int attackID) { }

    @WrapOperation(method = "attackEntityWithRangedAttack", at= @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
    private Item evil$hero(ItemStack instance, Operation<Item> original) {
        Item item = instance.getItem();

        if (item == NetherItems.netherite_bow) {
            cooldown = 8;
        }

        return original.call(instance);
    }

}
