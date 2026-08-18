package com.jerios.evilMinecraftFixes.mixins.late.thirst;

import com.thetorine.thirstmod.core.main.EventSystem;
import com.thetorine.thirstmod.core.player.PlayerContainer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EventSystem.class)
public class MixinEventSystem {

    /**
     * @author Jerios
     * @reason Replaced with IEEP
     */
    @Overwrite(remap = false)
    @SubscribeEvent
    public void clientTick(TickEvent.ClientTickEvent event) {
    }

    /**
     * @author Jerios
     * @reason Replaced with IEEP
     */
    @Overwrite(remap = false)
    @SubscribeEvent
    public void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
    }

    /**
     * @author Jerios
     * @reason Replaced with IEEP
     */
    @Overwrite(remap = false)
    @SubscribeEvent
    public void onLogout(PlayerEvent.PlayerLoggedOutEvent event) {
    }
}
