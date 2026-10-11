package com.jerios.evilMinecraftFixes.mixins.late.thirst;

import com.thetorine.thirstmod.core.client.player.ClientStats;
import com.thetorine.thirstmod.core.network.PacketUpdateClient;
import com.thetorine.thirstmod.core.player.PlayerContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PacketUpdateClient.class)
public class MixinPacketUpdateClient {


   @Shadow(remap = false)
   private int level;
    @Shadow(remap = false)  private float saturation;
    @Shadow(remap = false)  private boolean poisoned;


    /**
     * @author
     * @reason
     */
    @Overwrite(remap = false)
    public void handleClientSide() {
        EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
        PlayerContainer.getPlayer(player).getStats().thirstLevel = level;
      //  ClientStats.getInstance().level = this.level;
        PlayerContainer.getPlayer(player).getStats().thirstSaturation = saturation;
      //  ClientStats.getInstance().saturation = this.saturation;
      //  PlayerContainer.getPlayer(player).getStats().poisonLogic.isPlayerPoisoned()
       // PlayerContainer.getPlayer(player).getStats().poisonLogic.changeValues(poisoned, PlayerContainer.getPlayer(player).getStats().poisonLogic.getPoisonTimeRemaining());
      // ClientStats.getInstance().isPoisoned = this.poisoned;
        ((PoisionLogicAccessor)PlayerContainer.getPlayer(player).getStats().poisonLogic).evil$setPlayerPosioned(poisoned);
    }


}
