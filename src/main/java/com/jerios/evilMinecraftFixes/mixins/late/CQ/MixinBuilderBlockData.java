package com.jerios.evilMinecraftFixes.mixins.late.CQ;

import com.chocolate.chocolateQuest.API.BuilderBlockData;
import com.chocolate.chocolateQuest.builder.schematic.Schematic;
import com.jerios.evilMinecraftFixes.Vortex;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BuilderBlockData.class)
public class MixinBuilderBlockData {


    @Inject(method = "placeBlock(Lnet/minecraft/world/World;IIILjava/util/Random;)V", at=@At("HEAD"), remap = false)
    private void evil$loadManually(World world, int x, int y, int z, Random random, CallbackInfo ci) {
        Vortex.ensureBlockExists(world,x,y,z);
    }


}
