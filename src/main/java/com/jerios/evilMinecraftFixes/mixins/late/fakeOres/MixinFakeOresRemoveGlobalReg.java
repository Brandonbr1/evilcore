package com.jerios.evilMinecraftFixes.mixins.late.fakeOres;

import fr.elias.fakeores.common.FakeOres;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = FakeOres.class, remap = false)
public class MixinFakeOresRemoveGlobalReg {

    @Redirect(method = "addEntity", at = @At(value = "INVOKE", target = "Lcpw/mods/fml/common/registry/EntityRegistry;registerGlobalEntityID(Ljava/lang/Class;Ljava/lang/String;III)V"))
    private void evil$nope(Class<? extends Entity> activeModContainer, String modId, int entityClass, int entityName, int id) {
    }

    @Redirect(method = "addEntity", at= @At(value = "INVOKE", target = "Lcpw/mods/fml/common/registry/EntityRegistry;findGlobalUniqueEntityId()I"))
    private int evil$doNotReserveID() {
        return Integer.MAX_VALUE;
    }


}
