package com.jerios.evilMinecraftFixes.mixins.late.hee;


import chylex.hee.entity.boss.dragon.attacks.special.DragonAttackBloodlust;
import chylex.hee.entity.boss.dragon.attacks.special.event.DamageTakenEvent;
import com.jerios.evilMinecraftFixes.cfg.ConfigASM;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(DragonAttackBloodlust.class)
public class MixinDragonAttackBloodlust {

    /**
     * @author Jerios
     * @reason Hardcap it to 3hp
     */
    @Overwrite(remap = false)
    public void onDamageTakenEvent(DamageTakenEvent event) {
        event.damage = ConfigASM.maxDragonDamage;
    }


}
