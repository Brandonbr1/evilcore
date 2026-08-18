package com.jerios.evilMinecraftFixes.packet;

import com.thetorine.thirstmod.core.player.PlayerContainer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketHunger implements IMessage {

    float hunger;
    public PacketHunger() {

    }

    public PacketHunger(float hunger) {
        this.hunger = hunger;
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        hunger = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeFloat(hunger);

    }

    public static class Handler implements IMessageHandler<PacketHunger, IMessage> {

        @Override
        public IMessage onMessage(PacketHunger message, MessageContext ctx) {
            EntityPlayerMP playerEntity = ctx.getServerHandler().playerEntity;
            if (playerEntity != null) {
                playerEntity.addExhaustion(message.hunger);
            }

            return null;
        }
    }


}
