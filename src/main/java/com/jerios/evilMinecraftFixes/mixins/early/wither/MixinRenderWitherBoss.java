package com.jerios.evilMinecraftFixes.mixins.early.wither;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelWither;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderWither;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RenderWither.class)
public class MixinRenderWitherBoss  extends RenderLiving
{
   @Shadow
   private int field_82419_a;

    public MixinRenderWitherBoss(ModelBase p_i1262_1_, float p_i1262_2_) {
        super(p_i1262_1_, p_i1262_2_);
    }

    /**
  * @author
  * @reason
  */
 @Overwrite
    public void doRender(EntityWither p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        BossStatus.setBossStatus(p_76986_1_, true);
        if (p_76986_1_.isArmored()) {
            // black
            BossStatus.bossName = EnumChatFormatting.BLACK + "YOUR DEMISE";
        }
        int i = ((ModelWither)this.mainModel).func_82903_a();

        if (i != this.field_82419_a)
        {
            this.field_82419_a = i;
            this.mainModel = new ModelWither();
        }

        super.doRender((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    @Shadow
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return null;
    }
}
