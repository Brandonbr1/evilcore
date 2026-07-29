package com.jerios.evilMinecraftFixes.evilOres.mob;

import com.superdextor.dextersnether.init.NetherBlocks;
import fr.elias.fakeores.common.EntityOres;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityNetheriteOre extends EntityOres {
    public EntityNetheriteOre(World world, int oreType) {
        super(world, oreType);
    }

    public EntityNetheriteOre(World world) {
        super(world);

        type = 800;
        setSize(1,1);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.addPotionEffect(new PotionEffect(Potion.resistance.id, 900, 3));
    }

    @Override
    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(Item.getItemFromBlock(NetherBlocks.netherite_ore), 1);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(55D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(40D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(22.0D);
    }
}
