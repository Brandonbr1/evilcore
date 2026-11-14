package com.jerios.evilMinecraftFixes.client.charger;

import chylex.hee.init.ItemList;
import com.jerios.evilMinecraftFixes.CQAdditions.CQInteg;
import com.rwtema.extrautils.ExtraUtils;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

public class ChargerItems {

    public static final Map<Item, Integer> ITEM_FUEL_VALUE = new HashMap<>();

    static {
        ITEM_FUEL_VALUE.put(Items.feather, 500);
        ITEM_FUEL_VALUE.put(CQInteg.goldFeather, 50000);
        ITEM_FUEL_VALUE.put(ItemList.essence, 10000);
        ITEM_FUEL_VALUE.put(Items.fireworks, 1000);
        ITEM_FUEL_VALUE.put(ExtraUtils.divisionSigil, 600000);
    }

    public static int getFuelValueForItem(Item item) {
        return ITEM_FUEL_VALUE.get(item);
    }

}
