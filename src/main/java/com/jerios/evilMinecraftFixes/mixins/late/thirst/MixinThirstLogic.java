package com.jerios.evilMinecraftFixes.mixins.late.thirst;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.thetorine.thirstmod.core.network.NetworkHandler;
import com.thetorine.thirstmod.core.network.PacketUpdateClient;
import com.thetorine.thirstmod.core.player.PoisonLogic;
import com.thetorine.thirstmod.core.player.ThirstLogic;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import fr.elias.fakeores.common.EntityOresBoss;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ThirstLogic.class)
public class MixinThirstLogic {

   @Shadow(remap = false)
   public int thirstLevel;
   @Shadow(remap = false)
   public float thirstSaturation;

   @Shadow public PoisonLogic poisonLogic;

   @Shadow
    public EntityPlayer player;

   @Unique
    public int evil$lastthirstLevel;
    @Unique
    public float evil$lastthirstSaturation;

    @Unique public PoisonLogic evil$lastpoisonLogic;


   // improve network load by only sending a packet when one of the data on the server has changed.
    @WrapWithCondition(method = "onTick",at= @At(value = "INVOKE", target = "Lcpw/mods/fml/common/network/simpleimpl/SimpleNetworkWrapper;sendTo(Lcpw/mods/fml/common/network/simpleimpl/IMessage;Lnet/minecraft/entity/player/EntityPlayerMP;)V"), remap = false)
    private boolean evil$booleanCheck(SimpleNetworkWrapper instance, IMessage message, EntityPlayerMP player) {
        if (
            thirstLevel != evil$lastthirstLevel
                || thirstSaturation != evil$lastthirstSaturation
                || poisonLogic.isPlayerPoisoned() != evil$lastpoisonLogic.isPlayerPoisoned()
                || poisonLogic.getPoisonTimeRemaining() != evil$lastpoisonLogic.getPoisonTimeRemaining()
        ) {
            evil$lastthirstLevel = thirstLevel;
            evil$lastthirstSaturation = thirstSaturation;
            evil$lastpoisonLogic = poisonLogic;
            if (evil$lastpoisonLogic != null) {
                evil$lastpoisonLogic.changeValues(poisonLogic.isPlayerPoisoned(), poisonLogic.getPoisonTimeRemaining());
            }
            return true;
        }
        return false;
    }


  /** @Redirect(method = "onTick",at= @At(value = "INVOKE", target = "Lcpw/mods/fml/common/network/simpleimpl/SimpleNetworkWrapper;sendTo(Lcpw/mods/fml/common/network/simpleimpl/IMessage;Lnet/minecraft/entity/player/EntityPlayerMP;)V"), remap = false)
    private void t(SimpleNetworkWrapper instance, IMessage message, EntityPlayerMP player) {
       ThirstLogic thirstLogic = ((ThirstLogic)(Object)this);
       if (
           thirstLevel != evil$lastthirstLevel
           || thirstSaturation != evil$lastthirstSaturation
           || poisonLogic.isPlayerPoisoned() != evil$lastpoisonLogic.isPlayerPoisoned()
           || poisonLogic.getPoisonTimeRemaining() != evil$lastpoisonLogic.getPoisonTimeRemaining()
       )
       {
           evil$lastthirstLevel = thirstLevel;
           evil$lastthirstSaturation = thirstSaturation;
           evil$lastpoisonLogic = poisonLogic;
           if (evil$lastpoisonLogic !=null) {
               evil$lastpoisonLogic.changeValues(poisonLogic.isPlayerPoisoned(), poisonLogic.getPoisonTimeRemaining());
           }

           System.out.println("SEND");
           instance.sendTo(new PacketUpdateClient(thirstLogic), (EntityPlayerMP)this.player);
       }

   }
   **/

    /**
     * @author Jerios
     * @reason Redirect to IEEP

    @Overwrite(remap = false)
    public void readData() {

    }
     */
    /**
     * @author Jerios
     * @reason Redirect to IEEP

    @Overwrite(remap = false)
    public void writeData() {

    }
     */
}
