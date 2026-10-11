package fakeMod;

// Since Animal Plus does not have a Thing to get the INSTANCE, create a fake Mod
import cpw.mods.fml.common.Mod;
@Mod(modid = "AnimalPlusMod", version = "1.0", name = "AnimalPlusMod", acceptedMinecraftVersions = "[1.7.10]")
public class AnimalPlusProxy {

    @Mod.Instance("AnimalPlusMod")
    public static AnimalPlusProxy INSTANCE;


}
