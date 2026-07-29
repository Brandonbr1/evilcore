package com.jerios.evilMinecraftFixes.evilOres.mob;

import fr.elias.fakeores.common.EntityOres;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityGlowstone extends EntityOres {
    public EntityGlowstone(World world) {
        super(world);
        this.type = 801;
        this.setSize(1,1);

    }

    @Override
    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(Item.getItemFromBlock(Blocks.glowstone), 1);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(2.0D);
    }
}
