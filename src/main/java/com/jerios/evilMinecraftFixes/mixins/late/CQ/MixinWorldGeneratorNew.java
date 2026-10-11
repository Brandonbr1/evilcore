package com.jerios.evilMinecraftFixes.mixins.late.CQ;

import com.chocolate.chocolateQuest.WorldGeneratorNew;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WorldGeneratorNew.class)
public class MixinWorldGeneratorNew {


    @Redirect(method = "generateBigDungeon", at= @At(value = "INVOKE", target = "Lcom/chocolate/chocolateQuest/utils/BDHelper;println(Ljava/lang/String;)V"), remap = false)
    private void evil$disableLogSpam(String msg) {

    }
}
