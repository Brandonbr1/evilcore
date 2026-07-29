package com.jerios.evilMinecraftFixes.mixins.late.hw;

import net.minecraft.block.Block;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.jerios.evilMinecraftFixes.mixins.Config;

import hostileworlds.block.BlockSourceInvasion;

import java.util.Random;

@Mixin(BlockSourceInvasion.class)
public abstract class MixinBlockSourceInvasion extends Block {

    protected MixinBlockSourceInvasion(Material materialIn) {
        super(materialIn);
    }

    @Inject(method = "<init>", at = @At("TAIL"), remap = false)
    private void injectSetHardness(CallbackInfo ci) {
        this.setHardness((float) Config.hardness);
        this.setResistance((float) Config.resistance);
    }

    public Item getItemDropped(int meta, Random random, int fortune)
    {
        return null;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 0;
    }

}
