package com.jerios.evilMinecraftFixes.mixins.late.pg;

import com.thetorine.thirstmod.core.player.PlayerContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import particleman.items.ItemParticleGlove;

@Mixin(ItemParticleGlove.class)
public class MixinItemParticleGlove extends Item {

    @Inject(method = "<init>", at=@At("TAIL"), remap = false)
    private void evil$limitParticleGloveAmmount(CallbackInfo ci) {
        this.maxStackSize = 1;
    }

    @Inject(method = "onLeftClickEntity",at= @At(value = "INVOKE", target = "Lnet/minecraft/util/FoodStats;addExhaustion(F)V"))
    private void evil$exhaustThirst(ItemStack stack, EntityPlayer player, Entity entity, CallbackInfoReturnable<Boolean> cir) {
        PlayerContainer.getPlayer(player.getCommandSenderName()).addExhaustion(1.0f);
    }

    @Inject(method = "onItemUse",at= @At(value = "INVOKE", target = "Lnet/minecraft/util/FoodStats;addExhaustion(F)V"))
    private void evil$exhaustThirstUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10, CallbackInfoReturnable<Boolean> cir) {
        PlayerContainer.getPlayer(par2EntityPlayer.getCommandSenderName()).addExhaustion(1.0f);
    }

}
