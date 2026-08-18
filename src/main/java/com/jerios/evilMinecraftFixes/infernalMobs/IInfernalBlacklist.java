package com.jerios.evilMinecraftFixes.infernalMobs;

import net.minecraftforge.common.config.Configuration;

public interface IInfernalBlacklist {

     String[] emptyString = new String[] {};
     String[] creeperString = new String[] { "net.minecraft.entity.monster.EntityCreeper" };
     String[] spiderString = new String[] { "net.minecraft.entity.monster.EntitySpider" };

    void load(Configuration configuration);

    public Class<?>[] getBannedClassesToArray();

    void addDefaultBlacklistedMobs(String[] s);

    void addEmptyString();
    void addCreeperString();
    void addSpiderString();
}
