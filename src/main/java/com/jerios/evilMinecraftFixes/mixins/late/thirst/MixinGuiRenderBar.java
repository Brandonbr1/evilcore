package com.jerios.evilMinecraftFixes.mixins.late.thirst;

import com.thetorine.thirstmod.core.client.gui.GuiRenderBar;
import com.thetorine.thirstmod.core.main.ThirstMod;
import com.thetorine.thirstmod.core.player.PlayerContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(GuiRenderBar.class)
public class MixinGuiRenderBar {

   @Shadow(remap = false)
   public static Minecraft mc = Minecraft.getMinecraft();
    @Shadow(remap = false)  public static Random rand = new Random();
    @Shadow(remap = false)  public static int updateCounter;

    /**
     * @author
     * @reason
     */
    @Overwrite(remap = false)
    public static void renderThirst(int rwidth, int rheight) {
        bind(new ResourceLocation("thirstmod:textures/gui/thirstBar-new.png"));
     //   ClientStats stats = ClientStats.getInstance();
        PlayerContainer stats = PlayerContainer.getPlayer(mc.thePlayer);
        GuiIngame ingameGUI = mc.ingameGUI;
        updateCounter = ingameGUI.getUpdateCounter();

        for(int i = 0; i < 10; ++i) {
            int width = ThirstMod.config.METER_ON_LEFT ? rwidth / 2 - 91 + i * 8 : rwidth / 2 + 91 - i * 8 - 9;
            int height = rheight - 49;
            int xStart = 1;
            int yStart = 1;
            int yEnd = 9;
            int xEnd = 7;
            if (stats.getStats().thirstSaturation <= 0.0F && updateCounter % ((stats.getStats().thirstLevel + 1) * 3 + 1) == 0) {
                height += rand.nextInt(3) - 1;
            }

            ingameGUI.drawTexturedModalRect(width, height, xStart, yStart, xEnd, yEnd);
            if (!stats.getStats().poisonLogic.isPlayerPoisoned()) {
                if (i * 2 + 1 < stats.getStats().thirstLevel) {
                    ingameGUI.drawTexturedModalRect(width, height, xStart + 8, yStart, xEnd, yEnd);
                } else if (i * 2 + 1 == stats.getStats().thirstLevel) {
                    ingameGUI.drawTexturedModalRect(width, height, xStart + 16, yStart, xEnd, yEnd);
                }
            } else if (i * 2 + 1 < stats.getStats().thirstLevel) {
                ingameGUI.drawTexturedModalRect(width, height, xStart + 24, yStart, xEnd, yEnd);
            } else if (i * 2 + 1 == stats.getStats().thirstLevel) {
                ingameGUI.drawTexturedModalRect(width, height, xStart + 32, yStart, xEnd, yEnd);
            }
        }

        bind(Gui.icons);
    }

    private static void bind(ResourceLocation res) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(res);
    }

}
