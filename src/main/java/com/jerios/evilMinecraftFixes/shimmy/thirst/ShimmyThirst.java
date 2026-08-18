package com.jerios.evilMinecraftFixes.shimmy.thirst;

import com.jerios.evilMinecraftFixes.IThirst;
import com.thetorine.thirstmod.core.player.PlayerContainer;
import com.thetorine.thirstmod.core.player.ThirstLogic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.util.Constants;
import org.spongepowered.asm.mixin.Shadow;

public class ShimmyThirst implements IExtendedEntityProperties {

    private ThirstLogic stats;
    private EntityPlayer player;

    static String PROP = "Thirst_Mod";

    public static ShimmyThirst get(Entity p) {
        return (ShimmyThirst) p.getExtendedProperties(PROP);
    }

    public static ShimmyThirst getPlayer(EntityPlayer player) {
        return get(player);
    }

    public ShimmyThirst(EntityPlayer player, ThirstLogic stats) {
        this.player = player;
        this.stats = stats;
    }

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
        if(compound.hasKey(PROP, Constants.NBT.TAG_COMPOUND)) {
            NBTTagCompound propertyData = compound.getCompoundTag(PROP);
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
}
