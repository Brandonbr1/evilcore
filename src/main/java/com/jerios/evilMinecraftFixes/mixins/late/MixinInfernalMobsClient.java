package com.jerios.evilMinecraftFixes.mixins.late;

import atomicstryker.infernalmobs.client.InfernalMobsClient;
import atomicstryker.infernalmobs.common.InfernalMobsCore;
import atomicstryker.infernalmobs.common.MobModifier;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.world.WorldEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(InfernalMobsClient.class)
public class MixinInfernalMobsClient {

    @Shadow(remap = false) private EntityLivingBase retainedTarget;
    @Shadow(remap = false) private long healthBarRetainTime;
    @Shadow(remap = false)  private Minecraft mc;

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {
        if (event.world.isRemote) retainedTarget = null;
    }

    @SubscribeEvent
    public void onRenderGameInfo(RenderGameOverlayEvent.Text event) {

        Entity ent = getEntityCrosshairOver(event.partialTicks, mc);
        boolean retained = false;

        boolean renderBossBar = System.currentTimeMillis() < healthBarRetainTime;
        if (ent == null && renderBossBar) {
            ent = retainedTarget;
            retained = true;
        } else if (ent == null) {
            retainedTarget = null;
        }

        if (ent instanceof EntityLivingBase) {
            MobModifier mod = InfernalMobsCore.getMobModifiers((EntityLivingBase) ent);
            if (mod != null) {
                EntityLivingBase target = (EntityLivingBase) ent;

                String buffer = mod.getEntityDisplayName(target);
                int screenwidth = event.resolution.getScaledWidth();
                FontRenderer fontR = mc.fontRenderer;

                int yCoord = 12;
                fontR
                    .drawStringWithShadow(buffer, screenwidth / 2 - fontR.getStringWidth(buffer) / 2, yCoord, 0x2F96EB);

                // spacing for healthbar
                yCoord += 8;

                String[] display = mod.getDisplayNames();
                int i = 0;
                while (i < display.length && display[i] != null) {
                    yCoord += 10;
                    fontR.drawStringWithShadow(
                        display[i],
                        screenwidth / 2 - fontR.getStringWidth(display[i]) / 2,
                        yCoord,
                        0xffffff);
                    i++;
                }

                if (!retained) {
                    retainedTarget = target;
                    healthBarRetainTime = System.currentTimeMillis() + 3000L;
                }

            }

        }

    }

    /**
     * @author Jerios, Atomic
     * @reason Disable Health Bar, we have damage indicators for this reason.
     */
    @Overwrite(remap = false)
    @SubscribeEvent
    public void onPreRenderGameOverlay(RenderGameOverlayEvent.Pre event) {
    }

    /**
     * @author Jerios
     * @reason Diable this horrible system...
     */
    @Overwrite(remap = false)
    @SubscribeEvent
    public void onTick(TickEvent.RenderTickEvent tick) {
    }

    @Shadow(remap = false)
    private Entity getEntityCrosshairOver(float renderTick, Minecraft mc) {
        return null;
    }


}
