package com.jerios.evilMinecraftFixes.mixins.late.thirst;

import com.thetorine.thirstmod.core.player.ThirstLogic;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ThirstLogic.class)
public class MixinThirstLogic {

    /**
     * @author Jerios
     * @reason Redirect to IEEP
     */
    @Overwrite(remap = false)
    public void readData() {

    }

    /**
     * @author Jerios
     * @reason Redirect to IEEP
     */
    @Overwrite(remap = false)
    public void writeData() {

    }
}
