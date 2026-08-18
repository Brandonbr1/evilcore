package com.jerios.evilMinecraftFixes;

import com.thetorine.thirstmod.core.client.player.ClientStats;
import com.thetorine.thirstmod.core.main.ThirstMod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.FoodStats;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

import static net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType.FOOD;

public class ThirstSatuartionEvent {

    @SubscribeEvent
    public void render(RenderGameOverlayEvent.Text event) {
        renderThirstSat(event.resolution.getScaledWidth(), event.resolution.getScaledHeight());
    }

    public void renderThirstSat(int width, int height)
    {
        int left = width / 2 + 94;
        int top = height - GuiIngameForge.right_height;
        GuiIngameForge.right_height += 10;

        float sat = ClientStats.getInstance().saturation;

        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("" + sat, left, top, 0xFFFFFF);

    }


}
