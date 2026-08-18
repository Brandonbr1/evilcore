package com.jerios.evilMinecraftFixes.mixins.late;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import thor12022.hardcorewither.items.ItemStarryApple;

@Mixin(ItemStarryApple.class)
public class MixinWitherApple extends ItemFood {
    public MixinWitherApple(int p_i45340_1_, boolean p_i45340_2_) {
        super(p_i45340_1_, p_i45340_2_);
    }

    @Redirect(method = "onFoodEaten", at= @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayer;addPotionEffect(Lnet/minecraft/potion/PotionEffect;)V", ordinal = 1), remap = false)
    private void evil$a(EntityPlayer instance, PotionEffect effect) {
        instance.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 1200, 3));
    }

    @Redirect(method = "onFoodEaten", at= @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayer;addPotionEffect(Lnet/minecraft/potion/PotionEffect;)V", ordinal = 2), remap = false)
    private void evil$b(EntityPlayer instance, PotionEffect effect) {
        instance.addPotionEffect(new PotionEffect(Potion.regeneration.id, 2200, 6));
    }

    @Redirect(method = "onFoodEaten", at= @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayer;addPotionEffect(Lnet/minecraft/potion/PotionEffect;)V", ordinal = 3), remap = false)
    private void evil$c(EntityPlayer instance, PotionEffect effect) {
        instance.addPotionEffect(new PotionEffect(Potion.resistance.id, 1200, 2));
    }

    @Redirect(method = "onFoodEaten", at= @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayer;addPotionEffect(Lnet/minecraft/potion/PotionEffect;)V", ordinal = 4), remap = false)
    private void evil$d(EntityPlayer instance, PotionEffect effect) {
        instance.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 900, 2));
    }

    @Redirect(method = "onFoodEaten", at= @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayer;addPotionEffect(Lnet/minecraft/potion/PotionEffect;)V", ordinal = 5), remap = false)
    private void evil$e(EntityPlayer instance, PotionEffect effect) {
        instance.addPotionEffect(new PotionEffect(Potion.heal.id, 200, 100));
        instance.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 0, 3200));
    }



}
