package com.jerios.evilMinecraftFixes.mixins.late.thirst;

import com.thetorine.thirstmod.core.client.player.ClientStats;
import com.thetorine.thirstmod.core.content.ItemDrink;
import com.thetorine.thirstmod.core.player.PlayerContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ItemDrink.class)
public class MixinItemDrink {

    @Shadow(remap = false) public float saturationHeal;

    @Inject(method = "addInformation", at=@At("TAIL"))
    private void evil$injectInfo(ItemStack stack, EntityPlayer player, List list, boolean advancedItemTooltip, CallbackInfo ci) {
        float f = saturationHeal;
        String s2 = Float.toString(f);
        list.add("Heals " + (s2.endsWith(".0") ? s2.replace(".0", "") : s2) + " Saturation");
    }

}
