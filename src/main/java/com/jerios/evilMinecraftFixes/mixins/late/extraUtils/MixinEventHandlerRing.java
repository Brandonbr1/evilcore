package com.jerios.evilMinecraftFixes.mixins.late.extraUtils;

import com.rwtema.extrautils.item.ItemAngelRing;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemAngelRing.EventHandlerRing.class)
public class MixinEventHandlerRing {

}
