package com.jerios.evilMinecraftFixes.mixins.early;

import com.jerios.evilMinecraftFixes.ThirstSatuartionEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thehippomaster.MutantCreatures.CreeperMinion;

@Mixin(targets = "net/minecraft/entity/monster/IMob$1")
public class MixinNotAttacked {


    /**
     * @author Jerios
     * @reason Make Bomby Not get attacked!
     */
    @Overwrite
    public boolean isEntityApplicable(Entity p_82704_1_) {
        if (p_82704_1_ instanceof CreeperMinion) {
            CreeperMinion m = (CreeperMinion) p_82704_1_;
            if (m.getTamed()) {
                return false;
            }
            return true;
        }
        return !(p_82704_1_ instanceof CreeperMinion) && p_82704_1_ instanceof EntityMob;
    }

}
