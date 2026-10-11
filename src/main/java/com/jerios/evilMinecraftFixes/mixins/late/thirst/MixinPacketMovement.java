package com.jerios.evilMinecraftFixes.mixins.late.thirst;

import com.jerios.evilMinecraftFixes.ducks.IPacketThirst;
import com.jerios.evilMinecraftFixes.ducks.IThirstMovement;
import com.thetorine.thirstmod.core.network.PacketMovement;
import com.thetorine.thirstmod.core.player.PlayerContainer;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PacketMovement.class)
public class MixinPacketMovement implements IPacketThirst, IThirstMovement {

  @Shadow(remap = false)
  private int movementSpeed;
  /**
   * @author
   * @reason
   */
  @Overwrite(remap = false)
    public void fromBytes(ByteBuf buf) {
        this.movementSpeed = buf.readInt();
    }

    /**
     * @author
     * @reason
     */
    @Overwrite(remap = false)
    public void toBytes(ByteBuf buf) {
       // buf.writeInt(ClientStats.getInstance().movementSpeed);
        buf.writeInt(movementSpeed);
    }



    @Override
    public void onThirst(EntityPlayerMP playerMP) {
        PlayerContainer handler = PlayerContainer.getPlayer(playerMP);
        if (handler != null) {
            handler.getStats().movementSpeed = this.movementSpeed;
        }

    }

    /**
     * @author Jerios
     * @reason Redirect properly
     */
    @Overwrite(remap = false)
    public void handleServerSide() {
    }

    @Override
    public void setMovementValue(int speed)
    {
        this.movementSpeed = speed;

    }


    /**
    @Redirect(method = "handleServerSide", at= @At(value = "INVOKE", target = "Lcom/thetorine/thirstmod/core/player/PlayerContainer;getPlayer(Ljava/lang/String;)Lcom/thetorine/thirstmod/core/player/PlayerContainer;"), remap = false)
    private PlayerContainer redirec(String username) {


        for (int i = 0; i < MinecraftServer.getServer().getConfigurationManager().playerEntityList.size(); i++) {
            EntityPlayerMP currPlayer = MinecraftServer.getServer().getConfigurationManager().playerEntityList.get(i);

            if (Objects.equals(currPlayer.getCommandSenderName(), username)) {
                System.out.println("The correct thing has been returned");
                PlayerContainer.getPlayer(currPlayer);
            }
        }


        return null;
    }
    **/



}
