package com.jerios.evilMinecraftFixes.mixins.late.thirst;

import com.jerios.evilMinecraftFixes.ducks.IPacketThirst;
import com.thetorine.thirstmod.core.network.PacketMovement;
import com.thetorine.thirstmod.core.player.PlayerContainer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PacketMovement.Handler.class)
public class MixinT {


    @Inject(method = "onMessage(Lcom/thetorine/thirstmod/core/network/PacketMovement;Lcpw/mods/fml/common/network/simpleimpl/MessageContext;)Lcpw/mods/fml/common/network/simpleimpl/IMessage;", at=@At("HEAD"), remap = false)
    private void evil$onMoessage(PacketMovement message, MessageContext ctx, CallbackInfoReturnable<IMessage> cir) {
        ((IPacketThirst)message).onThirst(ctx.getServerHandler().playerEntity);
    }
}
