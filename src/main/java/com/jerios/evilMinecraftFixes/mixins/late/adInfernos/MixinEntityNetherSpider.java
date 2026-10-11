package com.jerios.evilMinecraftFixes.mixins.late.adInfernos;

import com.superdextor.dextersnether.entity.monster.EntityNetherSpider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityNetherSpider.class)
public class MixinEntityNetherSpider {


    @Redirect(method = "onSpawnWithEgg", at= @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityList;createEntityByName(Ljava/lang/String;Lnet/minecraft/world/World;)Lnet/minecraft/entity/Entity;"))
    private Entity evil$nameFix(String oclass, World exception) {

        return EntityList.createEntityByName("ThinkBigCore." +oclass, exception);
    }
}
