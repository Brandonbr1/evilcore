package com.jerios.evilMinecraftFixes.mixins.late.thirst;

import com.jerios.evilMinecraftFixes.IThirst;
import com.thetorine.thirstmod.core.player.PlayerContainer;
import com.thetorine.thirstmod.core.player.ThirstLogic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerContainer.class)
public class MixinPlayerContainer implements IExtendedEntityProperties, IThirst {


   @Shadow
   private ThirstLogic stats;

    @Override
    public void saveNBTData(NBTTagCompound compound) {
        NBTTagCompound propertyData = new NBTTagCompound();

        if (stats != null) {
            propertyData.setInteger("level", stats.thirstLevel);
            propertyData.setFloat("exhaustion", stats.thirstExhaustion);
            propertyData.setFloat("saturation", stats.thirstSaturation);
            propertyData.setInteger("timer", stats.timer);
            propertyData.setBoolean("poisoned", stats.poisonLogic.isPlayerPoisoned());
            propertyData.setInteger("poisonTime", stats.poisonLogic.getPoisonTimeRemaining());

        }

        compound.setTag(IThirst.PROP, propertyData);

    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        if(compound.hasKey(IThirst.PROP, Constants.NBT.TAG_COMPOUND)) {
            NBTTagCompound propertyData = compound.getCompoundTag(IThirst.PROP);
            if (stats != null) {
                if (propertyData.hasKey("level")) {
                    stats.thirstLevel = propertyData.getInteger("level");
                    stats.thirstExhaustion = propertyData.getFloat("exhaustion");
                    stats.thirstSaturation = propertyData.getFloat("saturation");
                    stats.timer = propertyData.getInteger("timer");
                    stats.poisonLogic.changeValues(propertyData.getBoolean("poisoned"), propertyData.getInteger("poisonTime"));
                }
            }
        }

    }

    @Override
    public void init(Entity entity, World world) {

    }

    /**
     * @author Jerios
     * @reason Redirect to IEEP
     */
    @Overwrite(remap = false)
    public static PlayerContainer getPlayer(EntityPlayer player) {
        return IThirst.get(player);
    }

    /**
     * @author Jerios
     * @reason Redirected to IEEP
     */
    @Overwrite(remap = false)
    public static void removePlayer(EntityPlayer player) {
    }

    /**
     * @author Jerios
     * @reason Redirected to IEEP
     */
    @Overwrite(remap = false)
    public static void addPlayer(EntityPlayer player) {
    }

    @Shadow
    public ThirstLogic getStats() {
        return this.stats;
    }

}
