package com.jerios.evilMinecraftFixes.mixins.late.hw;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(hostileworlds.entity.monster.ZombieBlockWielder.class)
public class MixinBlockWeilder extends EntityZombie {

    public MixinBlockWeilder(World p_i1745_1_) {
        super(p_i1745_1_);
    }

    /**
     * @author Vortex
     * @reason new hp
     */
    @Overwrite
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)500.0F);
    }
}
