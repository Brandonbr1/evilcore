package com.jerios.evilMinecraftFixes.evilOres.mob;

import com.superdextor.dextersnether.init.NetherBlocks;
import fr.elias.fakeores.common.EntityOres;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityGoldNugget extends EntityOres {

    public EntityGoldNugget(World world) {
        super(world);
        type = 802;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.ticksExisted % 40 == 0) {
            this.heal(1);
        }
        this.addPotionEffect(new PotionEffect(Potion.resistance.id, 6000, 0));
        this.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 6000, 0));
    }

    @Override
    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(Item.getItemFromBlock(NetherBlocks.gold_ore_nether), 1);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0F);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(40.0F);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(4.9);
    }
}
