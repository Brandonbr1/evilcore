package com.jerios.evilMinecraftFixes.ducks;

import net.minecraft.entity.player.EntityPlayerMP;

public interface IPacketThirst {
    void onThirst(EntityPlayerMP playerMP);
}
