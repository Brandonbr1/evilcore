package com.jerios.evilMinecraftFixes.mixins.late.mp;

import com.jerios.evilMinecraftFixes.packet.NetworkHandler;
import com.jerios.evilMinecraftFixes.packet.PacketHunger;
import com.thetorine.thirstmod.core.player.PlayerContainer;
import moveplus.config.MovePlusCfg;
import moveplus.forge.ClientTicker;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.PrintStream;

@Mixin(ClientTicker.class)
public class MixinClientTicker {

    @Shadow(remap = false) public static int exaustCounter;


    /**
     * @author Jerios
     * @reason disable NBT saving, not needed
     */
    @Overwrite(remap = false)
    public static void writeGameNBT() {
    }

    /**
     * @author Jerios
     * @reason disable NBT saving, not needed
     */
    @Overwrite(remap = false)
    public static void readGameNBT() {

    }

    /**
     * @author Jerios
     * @reason Exhausation actually works
     */
    @Overwrite(remap = false)
    public static void tryExaust(int inc) {
        if (MovePlusCfg.useStamina) {
            inc = (int) (inc * 0.5);
            exaustCounter += inc;

           if (exaustCounter >= 5) {
               exaustCounter = 0;
             // PlayerContainer.getPlayer(theplayer).addExhaustion(0.1f);
               NetworkHandler.wrapper.sendToServer(new PacketHunger(1f));
	    	}

        }

    }

    @Redirect(method = "checkKey", at= @At(value = "INVOKE", target = "Ljava/io/PrintStream;println(Ljava/lang/String;)V"), remap = false)
    private static void evil$disableLogSpam(PrintStream instance, String x) {

    }


}
