package com.jerios.evilMinecraftFixes.mixins.late.mutantCreatures;

import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thehippomaster.MutantCreatures.MutantCreeper;
import thehippomaster.MutantCreatures.MutantEnderman;
import thehippomaster.MutantCreatures.MutantSkeleton;
import thehippomaster.MutantCreatures.MutantZombie;
import thehippomaster.MutantCreatures.item.ChemicalX;
import toast.specialMobs.entity.creeper.Entity_SpecialCreeper;
import toast.specialMobs.entity.enderman.Entity_SpecialEnderman;
import toast.specialMobs.entity.skeleton.Entity_SpecialSkeleton;
import toast.specialMobs.entity.zombie.Entity_SpecialZombie;

import java.util.HashMap;

@Mixin(ChemicalX.class)
public class MixinChemicalX {

    @Shadow(remap = false) private static HashMap<Class<? extends EntityLivingBase>, Class<? extends EntityLivingBase>> mcMap = new HashMap();

    @Inject(method = "<init>",at=@At("TAIL")  , remap = false)
    private void evil$inject(CallbackInfo ci) {

        mcMap.put(Entity_SpecialZombie.class, MutantZombie.class);
        mcMap.put(Entity_SpecialSkeleton.class, MutantSkeleton.class);
        mcMap.put(Entity_SpecialCreeper.class, MutantCreeper.class);
        mcMap.put(Entity_SpecialEnderman.class, MutantEnderman.class);
    }


}
