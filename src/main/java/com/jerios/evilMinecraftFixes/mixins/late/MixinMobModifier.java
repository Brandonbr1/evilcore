package com.jerios.evilMinecraftFixes.mixins.late;

import atomicstryker.infernalmobs.common.MobModifier;
import com.jerios.evilMinecraftFixes.infernalMobs.IInfernalBlacklist;
import net.minecraftforge.common.config.Configuration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayList;
import java.util.List;

@Mixin(MobModifier.class)
public abstract class MixinMobModifier implements IInfernalBlacklist {

    @Shadow(remap = false) protected String modName;

    @Unique
    public final List<Class<?>> bannedClassesList = new ArrayList<>();

   @Unique
   String[] blacklistedMobs = emptyString;


   /**
    * @author Jerios
    * @reason Blacklist certain Mobs
    */
   @Overwrite(remap = false)
    public Class<?>[] getBlackListMobClasses() {
        return getBannedClassesToArray();
    }


    @Override
    public void load(Configuration configuration) {
        String[] bannedClassString = configuration.getStringList(
            "Disallowed Mob Classes",
            modName,
            blacklistedMobs,
            "Fully Qualified Mob classes which can not have this effect.");
        try {
            for (int i = 0; i < bannedClassString.length; i++) {
                if (!bannedClassString[i].isEmpty()) {
                    Class<?> clazz = Class.forName(bannedClassString[i]);
                    bannedClassesList.add(clazz);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Class<?>[] getBannedClassesToArray() {
        return bannedClassesList.toArray(new Class<?>[0]);
    }

    @Override
    public void addDefaultBlacklistedMobs(String[] s) {
        blacklistedMobs = s;
    }

    @Override
    public void addEmptyString() {
        addDefaultBlacklistedMobs(emptyString);
    }

    @Override
    public void addCreeperString() {
        addDefaultBlacklistedMobs(creeperString);
    }

    @Override
    public void addSpiderString() {
    addDefaultBlacklistedMobs(spiderString);
    }
}
