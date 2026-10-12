package com.jerios.evilMinecraftFixes.mixins.late.specialAI;

import com.jerios.evilMinecraftFixes.savedata.EvilData;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import toast.specialAI.ai.AIHandler;

@Mixin(AIHandler.class)
public class MixinAIHandler {

    @Inject(method = "addDigAI",at=@At("HEAD"), cancellable = true, remap = false)
    private static void evil$injectGriefAI(EntityLiving entity, CallbackInfo ci)
    {
        if (!entity.worldObj.isRemote)
        {
            EvilData data = EvilData.get(entity.worldObj);

            if (data.worldTicks > 90000)
            {
                ci.cancel();
            }

        }

    }


    @Inject(method = "addGriefAI",at=@At("HEAD"), cancellable = true, remap = false)
    private static void evil$injectGriefAi(EntityLiving entity, boolean griefing, boolean fiddling, NBTTagCompound tag, CallbackInfo ci) {
        if (!entity.worldObj.isRemote) {
            EvilData data = EvilData.get(entity.worldObj);

            if (data.worldTicks > 90000)
            {
                ci.cancel();
            }

        }

    }

}
