package com.jerios.evilMinecraftFixes.mixins.late.fakeOres;

import fr.elias.fakeores.common.EntityDangerousPlant;
import fr.elias.fakeores.common.FakeOres;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityDangerousPlant.class)
public class MixinDangerousPlant extends EntityCreature {


    public MixinDangerousPlant(World p_i1602_1_) {
        super(p_i1602_1_);
    }

    int say = 0;

    /**
     * @author Jerios
     * @reason Make it Killable with hoe
     */
    @Overwrite(remap = false)
    public boolean attackEntityFrom(DamageSource damagesource, float damageValue) {
        if (damagesource.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) damagesource.getEntity();

            if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemHoe)
            {
                Item item = player.getCurrentEquippedItem().getItem();

                if (item == Items.diamond_hoe) {
                    damageValue *= 7;
                } else if (item == Items.iron_hoe) {
                    damageValue *= 1.7;
                }
                return super.attackEntityFrom(damagesource, damageValue * 50);
            }
            return super.attackEntityFrom(damagesource, damageValue);

        }


        return super.attackEntityFrom(damagesource, damageValue);
    }

    /**
     * @author Jerios
     * @reason Make it Killable with hoe
     */
    public boolean canBeCollidedWith() {
        return !this.isDead;
    }

    /**
     * @author Jerios
     * @reason Make it spawn in jungles
     */
    @Overwrite
    public boolean getCanSpawnHere() {
        return super.getCanSpawnHere();
    }

}
