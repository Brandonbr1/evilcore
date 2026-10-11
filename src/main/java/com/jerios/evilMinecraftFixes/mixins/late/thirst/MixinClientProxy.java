package com.jerios.evilMinecraftFixes.mixins.late.thirst;

import com.jerios.evilMinecraftFixes.ducks.IThirstMovement;
import com.thetorine.thirstmod.core.client.player.ClientStats;
import com.thetorine.thirstmod.core.network.NetworkHandler;
import com.thetorine.thirstmod.core.network.PacketMovement;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(com.thetorine.thirstmod.core.client.ClientProxy.class)
public class MixinClientProxy {


    /**
     * @author
     * @reason
     */
    @Overwrite(remap = false)
    public void clientTick(EntityPlayer player) {
        //ClientStats.getInstance().movementSpeed = getMovementStat(player);
        IMessage fs = new PacketMovement();
        ((IThirstMovement)fs).setMovementValue(getMovementStat(player));
        NetworkHandler.networkWrapper.sendToServer(fs);
    }


    @Shadow(remap = false)
    public static int getMovementStat(EntityPlayer player) {
        double d = player.posX - player.prevPosX;
        double d1 = player.posY - player.prevPosY;
        double d2 = player.posZ - player.prevPosZ;
        return Math.round(MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2) * 100.0F);
    }
}
