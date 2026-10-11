package com.jerios.evilMinecraftFixes.client.rendering;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderCorruptedWolf extends RenderLiving {

    private static final ResourceLocation TEXTURE = new ResourceLocation("evilmc", "textures/entity/wolf.png");

    public RenderCorruptedWolf() {
        super(new ModelCorruptedWolf(), 1.2f);
    }


    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return TEXTURE;
    }
}
