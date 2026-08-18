package com.jerios.evilMinecraftFixes.mixins.late.fakeOres;

import fr.elias.fakeores.common.EntityBossTeleporter;
import fr.elias.fakeores.common.EntityOresBoss;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(EntityBossTeleporter.class)
public class MixinEntityBossTeleporter {

    @Redirect(method = "onImpact",at= @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;getHealth()F"))
    private float evil$giveRegenInsteadOfInstHeal(EntityLivingBase instance) {
        if (instance instanceof EntityOresBoss) {
            EntityOresBoss oresBoss = (EntityOresBoss) instance;
            Random rand = new Random();
            oresBoss.spawnSomeSbires("all", oresBoss.posX + rand.nextInt(3), oresBoss.posY + 5, oresBoss.posZ + rand.nextInt(3));
        }

        return Float.MAX_VALUE;
    }



}
