package com.jerios.evilMinecraftFixes.CQAdditions;

import com.jerios.evilMinecraftFixes.Evil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemGoldenFeathers extends Item {

    public ItemGoldenFeathers() {
        this.setUnlocalizedName("golden_feathers");
        this.setTextureName("evilmc:" + "golden_feathers");
        this.setCreativeTab(CreativeTabs.tabFood);
    }

}
