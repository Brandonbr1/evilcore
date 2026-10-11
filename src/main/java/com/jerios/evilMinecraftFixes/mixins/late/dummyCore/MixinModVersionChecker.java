package com.jerios.evilMinecraftFixes.mixins.late.dummyCore;

import DummyCore.Core.DCMod;
import DummyCore.Utils.ModVersionChecker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ModVersionChecker.class)
public class MixinModVersionChecker {

    /**
     * @author Jerios
     * @reason Disable Update checker
     */
    public static void addRequest(Class<?> mod, String uri) {


    }
    /**
     * @author Jerios
     * @reason Disable Update checker
     */
    public static void dispatchModChecks() {


    }
    /**
     * @author Jerios
     * @reason Disable Update checker
     */
    public static void requestModVCheck(DCMod mod) {

    }

    /**
     * @author Jerios
     * @reason Disable Update checker
     */
    public static void respondToURICheckSuccess(DCMod mod) {
    }

    /**
     * @author Jerios
     * @reason Disable Update checker
     */
    public static void tryNotifyDMOutdatedMod(DCMod mod, String latest, int step) {

    }


    /**
     * @author Jerios
     * @reason Disable Update checker
     */
    public static void tryNotifySimpleOutdatedMod(DCMod mod, String latest) {

    }

    /**
     * @author Jerios
     * @reason Disable Update checker
     */
    @Overwrite(remap = false)
    public static boolean isVersionDMValid(String version) {
        return true;
    }

    /**
     * @author Jerios
     * @reason Disable Update checker
     */
    @Overwrite(remap = false)
    public static String removeButNumbers(String s) {
        String result = "";

        return result;
    }
}
