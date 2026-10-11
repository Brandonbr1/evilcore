package com.jerios.evilMinecraftFixes.client.rendering;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderCryingSoul extends RenderLiving {

    private static final ResourceLocation TEXTURE = new ResourceLocation("evilmc", "textures/entity/crySoul.png");

    public RenderCryingSoul() {
        super(new ModelCryingSoul(), 1.5f);
    }


    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return TEXTURE;
    }
}
