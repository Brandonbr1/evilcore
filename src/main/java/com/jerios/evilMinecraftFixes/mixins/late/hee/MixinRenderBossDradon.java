package com.jerios.evilMinecraftFixes.mixins.late.hee;

import chylex.hee.entity.boss.EntityBossDragon;
import chylex.hee.render.entity.RenderBossDragon;
import chylex.hee.sound.EndMusicType;
import chylex.hee.system.util.MathUtil;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RenderBossDragon.class)
public class MixinRenderBossDradon extends RenderLiving {

    public MixinRenderBossDradon(ModelBase p_i1262_1_, float p_i1262_2_) {
        super(p_i1262_1_, p_i1262_2_);
    }

    @Shadow(remap = false) private static final ResourceLocation texCrystalBeam = new ResourceLocation("textures/entity/endercrystal/endercrystal_beam.png");




    /**
     * @author
     * @reason
**/

    @Overwrite(remap = false)
    public void renderDragon(EntityBossDragon dragon, double x, double y, double z, float yaw, float partialTickTime) {
        BossStatus.setBossStatus(dragon, false);

        if (dragon.attacks.getHealthPercentage() < 40) {
            BossStatus.bossName = EnumChatFormatting.LIGHT_PURPLE + "YOUR DOOM!";
        }else if (dragon.isAngry()) {
            BossStatus.bossName = EnumChatFormatting.LIGHT_PURPLE + "Your Doom";
        } else if (dragon.worldObj.difficultySetting.getDifficultyId() < 2) {
            BossStatus.bossName = "FLYING DERPY LIZARD";
        } else {
            BossStatus.bossName = I18n.format(dragon.getCommandSenderName());
        }

      //  BossStatus.bossName = (dragon.isAngry() ? EnumChatFormatting.LIGHT_PURPLE : "") + I18n.format(dragon.getCommandSenderName(), new Object[0]);
        EndMusicType.update(dragon.isAngry() ? EndMusicType.DRAGON_ANGRY : EndMusicType.DRAGON_CALM);
        super.doRender(dragon, x, y, z, yaw, partialTickTime);
        if (dragon.healingEnderCrystal != null) {
            float animRot = (float)dragon.healingEnderCrystal.innerRotation + partialTickTime;
            float yCorrection = MathHelper.sin(animRot * 0.2F) * 0.5F + 0.5F;
            yCorrection = (yCorrection * yCorrection + yCorrection) * 0.2F;
            float diffX = (float)(dragon.healingEnderCrystal.posX - dragon.posX - (dragon.prevPosX - dragon.posX) * (double)(1.0F - partialTickTime));
            float diffY = (float)((double)yCorrection + dragon.healingEnderCrystal.posY - (double)1.0F - dragon.posY - (dragon.prevPosY - dragon.posY) * (double)(1.0F - partialTickTime));
            float diffZ = (float)(dragon.healingEnderCrystal.posZ - dragon.posZ - (dragon.prevPosZ - dragon.posZ) * (double)(1.0F - partialTickTime));
            float distXZ = MathHelper.sqrt_float(diffX * diffX + diffZ * diffZ);
            float distXYZ = MathHelper.sqrt_float(diffX * diffX + diffY * diffY + diffZ * diffZ);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)x, (float)y + 2.0F, (float)z);
            GL11.glRotatef(MathUtil.toDeg((float)(-Math.atan2((double)diffZ, (double)diffX))) - 90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(MathUtil.toDeg((float)(-Math.atan2((double)distXZ, (double)diffY))) - 90.0F, 1.0F, 0.0F, 0.0F);
            Tessellator tessellator = Tessellator.instance;
            RenderHelper.disableStandardItemLighting();
            GL11.glDisable(2884);
            this.bindTexture(texCrystalBeam);
            GL11.glShadeModel(7425);
            float animTime = -((float)dragon.ticksExisted + partialTickTime) * 0.01F;
            float textureV = MathHelper.sqrt_float(diffX * diffX + diffY * diffY + diffZ * diffZ) * 0.03125F - ((float)dragon.ticksExisted + partialTickTime) * 0.01F;
            tessellator.startDrawing(5);
            byte sideAmount = 8;

            for(int i = 0; i <= sideAmount; ++i) {
                float f11 = MathHelper.sin((float)(i % sideAmount) * (float)Math.PI * 2.0F / (float)sideAmount) * 0.75F;
                float f12 = MathHelper.cos((float)(i % sideAmount) * (float)Math.PI * 2.0F / (float)sideAmount) * 0.75F;
                float f13 = (float)(i % sideAmount / sideAmount);
                tessellator.setColorOpaque_I(0);
                tessellator.addVertexWithUV((double)(f11 * 0.2F), (double)(f12 * 0.2F), (double)0.0F, (double)f13, (double)textureV);
                tessellator.setColorOpaque_I(16777215);
                tessellator.addVertexWithUV((double)f11, (double)f12, (double)distXYZ, (double)f13, (double)animTime);
            }

            tessellator.draw();
            GL11.glEnable(2884);
            GL11.glShadeModel(7424);
            RenderHelper.enableStandardItemLighting();
            GL11.glPopMatrix();
        }

    }




    @Shadow
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return null;
    }
}
