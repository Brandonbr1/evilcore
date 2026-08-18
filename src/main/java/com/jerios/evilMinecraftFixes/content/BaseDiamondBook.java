package com.jerios.evilMinecraftFixes.content;

import com.jerios.evilMinecraftFixes.Evil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BaseDiamondBook extends Item {

    public BaseDiamondBook() {

        this.setUnlocalizedName("diamond_basebook");
        setTextureName(Evil.PREFIX2 + "diamond_book_base");
        setCreativeTab(CreativeTabs.tabCombat);

    }
}
