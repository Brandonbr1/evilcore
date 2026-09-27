package com.jerios.evilMinecraftFixes;

import com.thetorine.thirstmod.core.player.PlayerContainer;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public interface IThirst {

    String PROP = "Thirst_Mod";
    public static PlayerContainer get(Entity p) {
        return (PlayerContainer) p.getExtendedProperties(PROP);
    }

    public void saveNBTData2(NBTTagCompound compound);

    public void loadNBTData2(NBTTagCompound compound);

}
