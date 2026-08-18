package com.jerios.evilMinecraftFixes;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import thehippomaster.MutantCreatures.CreeperMinion;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReplaceMobFilter {

  /**  public static void replaceMobFilterWithInstanceThatDoesNotAttackBomby() {

        String[] filterNames = new String[]{
            "mobSelector", "field_82192_a"
        };

        for (int i = 0; i < filterNames.length; i++) {
            try {
                Field mob = IMob.class.getDeclaredField(filterNames[i]);
                mob.setAccessible(true);

                if (Modifier.isFinal(mob.getModifiers())) {
                    Field modfield = Field.class.getDeclaredField("modifiers");
                    modfield.setAccessible(true);
                    modfield.setInt(mob, mob.getModifiers() & ~Modifier.FINAL);
                }

                IEntitySelector mobSelector = new IEntitySelector()
                {
                    public boolean isEntityApplicable(Entity p_82704_1_)
                    {
                        if (p_82704_1_ instanceof CreeperMinion) {
                            CreeperMinion m = (CreeperMinion) p_82704_1_;
                            if (m.getTamed()) {
                                return false;
                            }
                            return true;
                        }
                        return p_82704_1_ instanceof EntityMob;
                    }
                };

                mob.set(null, mobSelector);


            } catch (Exception ignored) {

            }

        }


    }
   **/
}
