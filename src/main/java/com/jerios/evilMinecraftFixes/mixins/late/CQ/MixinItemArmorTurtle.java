package com.jerios.evilMinecraftFixes.mixins.late.CQ;

import com.chocolate.chocolateQuest.items.ItemArmorBase;
import com.chocolate.chocolateQuest.items.ItemArmorTurtle;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemArmorTurtle.class)
public class MixinItemArmorTurtle extends ItemArmorBase {

    @Unique
    public int evil$difficulty = 1;

    public MixinItemArmorTurtle(ArmorMaterial material, int renderIndex) {
        super(material, renderIndex);
    }

    @Inject(method = "onHit", at= @At(value = "INVOKE", target = "Lcom/chocolate/chocolateQuest/items/ItemArmorTurtle;isFullSet(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;)Z",shift = At.Shift.AFTER), remap = false)
    private void evil$moreDamageIfCooldown(LivingHurtEvent event, ItemStack is, EntityLivingBase entity, CallbackInfo ci) {
   /**     if (getCoolDown(is) > 1) {
            is.damageItem((int) (event.ammount + 1 + entity.worldObj.difficultySetting.getDifficultyId()), entity);
        }
    **/
        evil$difficulty = entity.worldObj.difficultySetting.getDifficultyId();
    }

 /**   @Redirect(method = "onHit", at= @At(value = "INVOKE", target = "Lcom/chocolate/chocolateQuest/items/ItemArmorTurtle;setCooldown(Lnet/minecraft/item/ItemStack;I)V"), remap = false)
    private void evil$cool(ItemArmorTurtle instance, ItemStack is, int cooldown) {
        setCooldown(is, cooldown * (evil$difficulty + 1));
    }
  **/



    @Inject(method = "onHit", at= @At(value = "INVOKE", target = "Lcom/chocolate/chocolateQuest/items/ItemArmorTurtle;setCooldown(Lnet/minecraft/item/ItemStack;I)V", shift = At.Shift.AFTER) ,remap = false)
    private void evil$cooldown(LivingHurtEvent event, ItemStack is, EntityLivingBase entity, CallbackInfo ci) {
        is.damageItem(130 + evil$difficulty, entity);
    }

    @Shadow(remap = false)
    public int getCoolDown(ItemStack is) {
        return is.stackTagCompound == null ? 0 : is.stackTagCompound.getInteger("CD");
    }

    @Shadow(remap = false)
    public void setCooldown(ItemStack is, int cooldown) {
    }



}
