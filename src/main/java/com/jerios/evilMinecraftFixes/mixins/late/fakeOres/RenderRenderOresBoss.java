package com.jerios.evilMinecraftFixes.mixins.late.fakeOres;

import com.jerios.evilMinecraftFixes.evilOres.IGetNewPhase;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import fr.elias.fakeores.client.RenderOresBoss;
import fr.elias.fakeores.common.EntityOresBoss;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RenderOresBoss.class)
public class RenderRenderOresBoss extends RenderLiving {
    public RenderRenderOresBoss(ModelBase p_i1262_1_, float p_i1262_2_) {
        super(p_i1262_1_, p_i1262_2_);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite(remap = false)
    public void renderHealthBar(EntityOresBoss mob, double x, double y, double z, float par8, float par9) {
        BossStatus.setBossStatus(mob, true);
        if (mob.getPhase() == 2) {
            GL11.glColor3f(0.0F, 2.0F, 1.0F);
        }

        if (mob.getPhase() == 3) {
            int p = ((IGetNewPhase)mob).evil$getPhase();
            if (p == 1) {
                GL11.glColor3f(3.0F, 0.0F, 0.0F);
            } else if (p == 2) {
                GL11.glColor3f(4.0F, 0.0F, 0.0F);
            } else if (p == 3) {
                GL11.glColor3f(5.0F, 0.0F, 0.0F);
            } else {
                GL11.glColor3f(2.0F, 1.0F, 2.0F);
            }

        }

        super.doRender(mob, x, y, z, par8, par9);
    }




    @Shadow
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return null;
    }
}
