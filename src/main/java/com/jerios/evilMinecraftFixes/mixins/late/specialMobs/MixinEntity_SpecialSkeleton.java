package com.jerios.evilMinecraftFixes.mixins.late.specialMobs;

import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import toast.specialMobs.entity.skeleton.Entity_SpecialSkeleton;

@Mixin(Entity_SpecialSkeleton.class)
public class MixinEntity_SpecialSkeleton extends EntitySkeleton {


    public MixinEntity_SpecialSkeleton(World p_i1741_1_) {
        super(p_i1741_1_);
    }

    /**
     * @author _
     * @reason -
     */
    @Overwrite(remap = false)
    protected void initTypeAI() {
        this.setRangedAI((double)1.0F, 30, 31, 15.0F);
        this.setMeleeAI(1.2);
    }

    @Shadow(remap = false)
    protected void setRangedAI(double moveSpeed, int minDelay, int maxDelay, float range) {
    }

    @Shadow(remap = false)
    protected void setMeleeAI(double moveSpeed) {
    }

    @Shadow(remap = false)
    public void setCombatTask() {

    }
}
