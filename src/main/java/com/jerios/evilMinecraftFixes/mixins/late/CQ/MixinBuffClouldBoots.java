package com.jerios.evilMinecraftFixes.mixins.late.CQ;

import com.chocolate.chocolateQuest.items.ItemArmorBootsCloud;
import net.minecraft.item.ItemArmor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ItemArmorBootsCloud.class)
public class MixinBuffClouldBoots {

    @ModifyArg(method = "<init>", at= @At(value = "INVOKE", target = "Lcom/chocolate/chocolateQuest/items/ItemArmorBase;<init>(Lnet/minecraft/item/ItemArmor$ArmorMaterial;I)V"))
    private static ItemArmor.ArmorMaterial evil$buff(ItemArmor.ArmorMaterial material) {

        return ItemArmor.ArmorMaterial.IRON;
    }

}
