package com.jerios.evilMinecraftFixes.mixins.late;

import atomicstryker.infernalmobs.common.InfernalMobsCore;
import atomicstryker.infernalmobs.common.MobModifier;
import atomicstryker.infernalmobs.common.mods.MM_Gravity;
import com.jerios.evilMinecraftFixes.infernalMobs.IInfernalBlacklist;
import com.jerios.evilMinecraftFixes.infernalMobs.MM_Unyielding;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import fr.elias.fakeores.common.EntityOresBoss;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.config.Configuration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.ArrayList;
import java.util.Iterator;

@Mixin(InfernalMobsCore.class)
public class MixinInfernalMobsCore {

    @Shadow
    public Configuration config;

    // save
    @Redirect(method = "loadMods", at= @At(value = "INVOKE", target = "Lnet/minecraftforge/common/config/Configuration;save()V"), remap = false)
    private void evil$saveIfNeeded(Configuration instance) {
        if (instance.hasChanged()) {
            config.save();
        }
    }


    @Redirect(method = "loadConfig", at= @At(value = "INVOKE", target = "Lnet/minecraftforge/common/config/Configuration;save()V"), remap = false)
    private void evil$saveIfNeeded2(Configuration instance) {
        if (instance.hasChanged()) {
            config.save();
        }
    }
    // save end


    // load start

    @Redirect(method = "loadConfig", at= @At(value = "INVOKE", target = "Lnet/minecraftforge/common/config/Configuration;load()V"), remap = false)
    private void evil$disableLoad(Configuration instance) {

    }

    // load end

    @Redirect(method = "checkEntityClassAllowed", at= @At(value = "INVOKE", target = "Lnet/minecraftforge/common/config/Configuration;save()V"), remap = false)
    private void evil$saveIfNeeded3(Configuration instance) {
        if (instance.hasChanged()) {
            config.save();
        }
    }

    // load start
    @Redirect(method = "checkEntityClassAllowed", at= @At(value = "INVOKE", target = "Lnet/minecraftforge/common/config/Configuration;load()V"), remap = false)
    private void evil$dontloader(Configuration instance) {
    }

    // load end



    @Redirect(method = "checkEntityClassForced", at= @At(value = "INVOKE", target = "Lnet/minecraftforge/common/config/Configuration;save()V"), remap = false)
    private void evil$saveIfNeeded4(Configuration instance) {
        if (instance.hasChanged()) {
            config.save();
        }
    }

    // load start

    @Redirect(method = "checkEntityClassForced", at= @At(value = "INVOKE", target = "Lnet/minecraftforge/common/config/Configuration;load()V"), remap = false)
    private void evil$load(Configuration instance) {
        if (instance.hasChanged()) {
            config.save();
        }
    }


    // load end

    @Redirect(method = "getMobClassMaxHealth", at= @At(value = "INVOKE", target = "Lnet/minecraftforge/common/config/Configuration;save()V"), remap = false)
    private void evil$saveIfNeeded5(Configuration instance) {
        if (instance.hasChanged()) {
            config.save();
        }
    }

    // load start
    @Redirect(method = "getMobClassMaxHealth", at= @At(value = "INVOKE", target = "Lnet/minecraftforge/common/config/Configuration;load()V"), remap = false)
    private void evil$loadNo(Configuration instance) {
    }
    // load end

    @WrapOperation(method = "loadMods", at= @At(value = "INVOKE", target = "Ljava/util/ArrayList;iterator()Ljava/util/Iterator;"), remap = false)
    private Iterator<Class<? extends MobModifier>> i(ArrayList<Class<? extends MobModifier>> instance, Operation<Iterator<Class<? extends MobModifier>>> original) {
            instance.add(MM_Unyielding.class);

        Iterator<Class<? extends MobModifier>> effects = instance.iterator();

        while (effects.hasNext()) {
            Class<? extends MobModifier> effect = effects.next();


            try {
                if (effect.getSimpleName().equals(MM_Gravity.class.getSimpleName())) {
                  //  MobModifier in = effect.getDeclaredConstructor(EntityLivingBase.class).newInstance(new Object[] {new CreeperEn});
                  //  effect.getMethod("load", Configuration.class).invoke(in, config);
                } else {
                    MobModifier in = effect.getDeclaredConstructor(EntityLivingBase.class).newInstance(new Object[] {null});
                    effect.getMethod("load", Configuration.class).invoke(in, config);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return original.call(instance);
    }

}
