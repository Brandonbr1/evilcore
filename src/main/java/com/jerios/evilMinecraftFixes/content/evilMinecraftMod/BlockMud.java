package com.jerios.evilMinecraftFixes.content.evilMinecraftMod;

import com.jerios.evilMinecraftFixes.content.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockMud extends BlockBase {
    public BlockMud() {
        super(Material.grass, "Mud", "evilmc:mud", 1.0f, CreativeTabs.tabDecorations);
    }
}
