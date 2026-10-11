package com.jerios.evilMinecraftFixes.client.rendering;

import com.jerios.evilMinecraftFixes.content.entity.nether.AcidicSlime;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderAcidicSlime extends RenderSlime {

    private static final ResourceLocation slimeTextures = new ResourceLocation("evilmc", "textures/entity/acid_slime.png");

    public RenderAcidicSlime() {
        super(new ModelSlime(16), new ModelSlime(0), 0.25F);
    }

    protected ResourceLocation getEntityTexture(AcidicSlime p_110775_1_)
    {
        return slimeTextures;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((AcidicSlime)p_110775_1_);
    }




}
