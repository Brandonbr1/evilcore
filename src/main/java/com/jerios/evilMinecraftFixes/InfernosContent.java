package com.jerios.evilMinecraftFixes;

import com.jerios.evilMinecraftFixes.content.ContentRegistry;
import com.superdextor.dextersnether.DextersNether;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucketMilk;

public class InfernosContent {

    public static Item goldenMilk;

    public static void addContent() {


        goldenMilk = (new ItemBucketMilk()).setUnlocalizedName("golden_bucket_milk").setTextureName("dextersnether:golden_bucket_milk").setCreativeTab(DextersNether.tabDextersNether);
        ContentRegistry.registerItm(goldenMilk, "Golden Milk Bucket");


    }


}
