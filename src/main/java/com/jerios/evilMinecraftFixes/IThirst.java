package com.jerios.evilMinecraftFixes;

import com.thetorine.thirstmod.core.player.PlayerContainer;
import net.minecraft.entity.Entity;

public interface IThirst {

    String PROP = "Thirst_Mod";
    public static PlayerContainer get(Entity p) {
        return (PlayerContainer) p.getExtendedProperties(PROP);
    }

}
