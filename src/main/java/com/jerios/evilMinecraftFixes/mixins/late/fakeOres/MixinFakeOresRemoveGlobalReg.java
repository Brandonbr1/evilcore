package com.jerios.evilMinecraftFixes.mixins.late.fakeOres;

import com.jerios.evilMinecraftFixes.content.ItemSpawnEgg;
import fr.elias.fakeores.common.FakeOres;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = FakeOres.class, remap = false)
public class MixinFakeOresRemoveGlobalReg {

    @Unique
    private static int evil$modID = 90100;

    @Redirect(method = "addEntity", at = @At(value = "INVOKE", target = "Lcpw/mods/fml/common/registry/EntityRegistry;registerGlobalEntityID(Ljava/lang/Class;Ljava/lang/String;III)V"))
    private void evil$nope(Class<? extends Entity> activeModContainer, String modId, int entityClass, int entityName, int id) {
        ItemSpawnEgg.addSpawnEgg(evil$modID++, activeModContainer, modId, 0,0);
    }

    @Redirect(method = "addEntity", at= @At(value = "INVOKE", target = "Lcpw/mods/fml/common/registry/EntityRegistry;findGlobalUniqueEntityId()I"))
    private int evil$doNotReserveID() {
        return Integer.MAX_VALUE;
    }


}
