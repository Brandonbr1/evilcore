package com.jerios.evilMinecraftFixes.mixins.late.thirst;

import com.thetorine.thirstmod.core.content.ItemInternalDrink;
import com.thetorine.thirstmod.core.player.PlayerContainer;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ItemInternalDrink.class)
public class MixinItemInternalDrink {


    /**
     * @author
     * @reason
     */
    @Overwrite(remap = false)
    public boolean canDrink(EntityPlayer player) {
        return PlayerContainer.getPlayer(player).getStats().thirstLevel < 20;
    }

    
}
