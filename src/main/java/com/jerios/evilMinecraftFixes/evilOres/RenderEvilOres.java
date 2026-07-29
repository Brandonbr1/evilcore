package com.jerios.evilMinecraftFixes.evilOres;

import fr.elias.fakeores.client.RenderOre;
import fr.elias.fakeores.common.EntityOres;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;

public class RenderEvilOres extends RenderOre {
    public RenderEvilOres(ModelBase par1ModelBase) {
        super(par1ModelBase);
    }

    public static final ResourceLocation netherTexture = new ResourceLocation("evilmc:textures/entity/nether_ore.png");
    public static final ResourceLocation gsTexture = new ResourceLocation("evilmc:textures/entity/glowstone.png");
    @Override
    public ResourceLocation bindTexture(EntityOres ore) {
       if (ore.getOreType() == 800) {
           return netherTexture;
       } else if (ore.getOreType() == 801) {
            return gsTexture;
       }
       return noTexture;
    }



}
