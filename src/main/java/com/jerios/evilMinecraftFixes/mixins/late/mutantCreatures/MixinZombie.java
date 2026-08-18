package com.jerios.evilMinecraftFixes.mixins.late.mutantCreatures;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thehippomaster.MutantCreatures.Zombie;

@Mixin(Zombie.class)
public class MixinZombie extends EntityZombie {
    public MixinZombie(World p_i1745_1_) {
        super(p_i1745_1_);
    }


    @Inject(method = "onUpdate", at=@At("TAIL"))
    private void evil$buffZombies(CallbackInfo ci) {
        Zombie zombie = ((Zombie)(Object)this);
        zombie.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 9000, 1));
    }

}
