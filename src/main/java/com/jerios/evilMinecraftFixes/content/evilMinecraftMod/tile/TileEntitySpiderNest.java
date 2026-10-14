package com.jerios.evilMinecraftFixes.content.evilMinecraftMod.tile;

import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.tileentity.TileEntity;
import toast.specialMobs.entity.spider.EntityBabySpider;

import java.util.Random;

public class TileEntitySpiderNest extends TileEntity {
    Random random = new Random(5000 << 12 >> 4);

    int spawnTimer;
    int requiredSpawnTimer = random.nextInt(80) + 240;

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!worldObj.isRemote){
            if (spawnTimer++ > requiredSpawnTimer) {
                spawnTimer = 0;
                requiredSpawnTimer = random.nextInt(80) + 240;
                EntityBabySpider spider = new EntityBabySpider(worldObj);
                spider.setLocationAndAngles(this.xCoord,this.yCoord,this.zCoord,5,1);
                worldObj.spawnEntityInWorld(spider);
            }
    }

    }
}
