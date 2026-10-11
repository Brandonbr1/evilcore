package com.jerios.evilMinecraftFixes.mixins.late.CQ;

import com.chocolate.chocolateQuest.API.BuilderBase;
import com.chocolate.chocolateQuest.builder.BuilderHelper;
import com.chocolate.chocolateQuest.builder.BuilderTemplateSurface;
import com.chocolate.chocolateQuest.builder.schematic.Schematic;
import com.jerios.evilMinecraftFixes.Vortex;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(BuilderTemplateSurface.class)
public class MixinBuilderTemplateSurface extends BuilderBase {

   @Shadow
   String folderName;

    public void generate(Random random, World world, int i, int k, int mob) {
        Schematic schematic = BuilderHelper.getRandomNBTMap(this.folderName, random, this.getDungeonName());
        int maxX = schematic.width;
        int maxY = schematic.length;
        i -= maxX / 2;
        k -= maxY / 2;
        int cont = 0;
        int cant = 0;
        int media = 0;

        for(int x = 0; x < maxX; ++x) {
            for(int y = 0; y < maxY; ++y) {
                Vortex.loadChunk(world, i + x, y + k);
                int h = world.getTopSolidOrLiquidBlock(i + x, y + k);
                media += h;
                ++cant;
            }
        }

        int height = media / cant;
        this.generate(random, world, i, height, k, mob);
    }

    @Shadow(remap = false)
    public void generate(Random random, World world, int i, int i1, int i2, int i3) {

    }

    @Shadow(remap = false)
    public String getName() {
        return "";
    }
}
