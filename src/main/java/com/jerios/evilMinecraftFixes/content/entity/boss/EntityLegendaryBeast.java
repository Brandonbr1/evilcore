package com.jerios.evilMinecraftFixes.content.entity.boss;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

import java.util.List;

public class EntityLegendaryBeast extends EntityMob {
    public EntityLegendaryBeast(World p_i1738_1_) {
        super(p_i1738_1_);
    }

    int lightingTimer = 0;
    int mobBuffTimer = 0;
    int mobSpawnTimer = 0;
    int lightingSphereTimer = 0;
    int lightingAgentTimer = 0;
    int lightingAgentCounter = 0;
    int abilityOffCooldown = 0;
    int useAllAbilitiesAtOnceTimer =0;
    int healHimselfAbility = 0;

    int waitTimer = 0;

    // fire element timer
    int fireballTime = 0;
    int spawnMobsFromDimTimer = 0;

    int jumpTimer = 0;

    // Elf Hunter
    int arrowRainTimer = 0;

    // Snow Beast
    int destroyBlocksTimer = 0;

    // if the player to too high up
    int highTimer = 0;
    int lowTimer = 0;

    // damage
    int snowballHit = 0;
    int snowballHitTimer = 0;

    int arrowHit = 0;
    int arrowHitTimer = 0;

    int fireHit = 0;
    int fireHitTimer = 0;

    int removeAllPotionEffectsTimer = 0;

    int phase = 0;

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(220.0D);
    }

    private boolean canSnowTimerDecr(){
        return  snowballHit > 120;
    }

    private boolean canArrowTimerDecr(){
        return  arrowHitTimer > 120;
    }

    private boolean canFireTimerDecr(){
        return  fireHitTimer > 120;
    }

    @Override
    public int getTotalArmorValue() {
        return Math.min(20, lightingAgentCounter + 4);
    }

    private boolean canHealCooldown() {
        return healHimselfAbility > 780;
    }

    private boolean areAllAbilitiesOnCooldown(){
        return !lightingOffCoooldown() && !lightingAgentOnCooldown() && !lightingSpearOffCooldown()
            && !mobBuffoffCooldown() && !mobSpawnTimerOffCoolown() && !canHealCooldown();
    }

    private boolean canUseAllAbilities(){
        return useAllAbilitiesAtOnceTimer > 1200;
    }

    private boolean lightingAgentOnCooldown() {
        return lightingAgentTimer > 240;
    }

    private boolean lightingSpearOffCooldown() {
        return lightingSphereTimer > 450;
    }

    private boolean mobBuffoffCooldown() {
        return mobBuffTimer > 345;
    }

    private boolean mobSpawnTimerOffCoolown(){
        return mobSpawnTimer > 320;
    }

    private boolean lightingOffCoooldown(){
       return lightingTimer > 145;
    }

    private boolean lightingAbilityFinish(){
        return lightingTimer > 150;
    }

    private boolean canForcefullyUseAbilities() {
        return abilityOffCooldown > 220;
    }

    private boolean canUseJumpAbility() {
       return jumpTimer > 643;
    }

    private boolean canUseArrowRain() {
        return arrowRainTimer > 764;
    }

    private boolean hasWaitedEnough() {
        return waitTimer > 80;
    }

    private boolean canRemoveAllPlayerPotionEffects() {
        return removeAllPotionEffectsTimer > 1596;
    }

    private boolean activatePinchMode() {
        return getHealth() <= this.getMaxHealth() / 2.5f;
    }
    private boolean canDestroyBlocksTimer() {
        return destroyBlocksTimer > 1000;
    }

    private void increaseTimerIfSpecial(){
        lightingTimer+=2;
        mobBuffTimer+=2;
        mobSpawnTimer+=2;
        lightingSphereTimer+=2;
        lightingAgentTimer+=2;
        lightingAgentCounter+=2;
        abilityOffCooldown+=2;
        useAllAbilitiesAtOnceTimer+=2;
        spawnMobsFromDimTimer+=2;
        jumpTimer+=2;
        arrowRainTimer+=2;
    }

    private void ensureAllAgressiveMobsAttackPlayer(EntityMob entity, EntityPlayer playerToAttack)
    {
        entity.setTarget(playerToAttack);
        entity.setRevengeTarget(playerToAttack);
        entity.setAttackTarget(playerToAttack);
        entity.setLastAttacker(playerToAttack);

    }


    private void phase1(EntityPlayer entity) {
        entity.hurtTime--;
      //  executeUseAllAbilities();

    }

    private void phase2(EntityPlayer entity) {
        entity.hurtTime--;
    }


    private void phase3(EntityPlayer entity) {
        entity.hurtTime--;
    }


    private void phase4(EntityPlayer entity) {
        entity.hurtTime--;
        entity.setSprinting(false);
    }

    private void executeMobBuff(List<Entity> mobsInChunk) {

        for (int i = 0; i < mobsInChunk.size(); i++) {
            Entity entity = mobsInChunk.get(i);

            if (entity instanceof EntityMob) {
                EntityMob mob = (EntityMob) entity;
                mob.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 900, 2));
            }

            if (entity instanceof EntityPlayer) {
                EntityPlayer mob = (EntityPlayer) entity;
                mob.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 900, 2));
            }
        }
        mobBuffTimer = 0;
    }

    private void executeSetToFire() {
        int x = MathHelper.floor_double(this.posY);
        int y = MathHelper.floor_double(this.posX);
        int z = MathHelper.floor_double(this.posZ);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (worldObj.getBlock(x + i, y, z+j) == Blocks.air) {
                    worldObj.setBlock(x + i, y, z+j, Blocks.fire);
                }

            }

        }

    }

    private void executeLightingSpearAttack(int off) {
        for (int i = 0; i < 16 + off; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 16 + off; k++) {
                    EntityLightningBolt lightningBolt = new EntityLightningBolt(worldObj, posX + i + rand.nextDouble(), posY + 1, posZ + k + rand.nextDouble());
                    worldObj.addWeatherEffect(lightningBolt);
                }

            }

        }

        lightingSphereTimer = 0;
    }

    int stuckTimer = 0;
    private boolean isTooStill() {
        int x = MathHelper.floor_double(this.posY);
        int y = MathHelper.floor_double(this.posX);
        int z = MathHelper.floor_double(this.posZ);

        if (MathHelper.floor_double(posX) == x && MathHelper.floor_double(posZ) == z ) {
            stuckTimer++;

            if (stuckTimer > 240) {

              worldObj.newExplosion(this, posX, posY,posZ, 2.4f, true , true);
                stuckTimer = 0;
                return true;
            }

        }
        return false;
    }

    private void setThunderingRainNight(boolean set) {
        worldObj.getWorldInfo().setThundering(true);
        worldObj.getWorldInfo().setRaining(true);
        worldObj.getWorldInfo().setRainTime(1);
        if (set) {
            worldObj.getWorldInfo().setThunderTime(20);
        } else {
            if (worldObj.getWorldInfo().getThunderTime() <= 0) {
                worldObj.getWorldInfo().setThunderTime(20);
            }
        }

    }

    private void destroyBlocksInRadius() {
        if (destroyBlocksTimer > 100)
        {
            int i;
            int i1;

            if (this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"))
            {
                i = MathHelper.floor_double(this.posY);
                i1 = MathHelper.floor_double(this.posX);
                int j1 = MathHelper.floor_double(this.posZ);
                boolean flag = false;

                for (int l1 = -1; l1 <= 1; ++l1)
                {
                    for (int i2 = -1; i2 <= 1; ++i2)
                    {
                        for (int j = 0; j <= 3; ++j)
                        {
                            int j2 = i1 + l1;
                            int k = i + j;
                            int l = j1 + i2;
                            Block block = this.worldObj.getBlock(j2, k, l);

                            if (!block.isAir(worldObj, j2, k, l) && block.canEntityDestroy(worldObj, j2, k, l, this))
                            {
                                flag = this.worldObj.func_147480_a(j2, k, l, true) || flag;
                            }
                        }
                    }
                }

                if (flag)
                {
                    this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1012, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
                }
            }
            destroyBlocksTimer = 0;
        }
    }

    private void isHigherCheese(Entity attacker) {
        if (this.posY > attacker.posY && isTooStill()) {
            highTimer++;
            teleportToEntity(attacker);
            worldObj.newExplosion(this, posX, posY,posZ, 2.4f, true , true);
        } else {
            highTimer--;
        }

    }

    private void isLowerCheese(Entity attacker) {
        if (attacker.posY < posY && isTooStill()) {
            lowTimer++;
            teleportToEntity(attacker);
            worldObj.newExplosion(this, posX, posY,posZ, 2.4f, true , true);
        } else {
            lowTimer--;
        }
    }

    private void teleportIfTooFarAway(Entity attacker) {
        float distance = getDistanceToEntity(attacker);
        if (distance > 128) {
            teleportToEntity(attacker);
        }
    }

    private void burnEntitiesNear(List<Entity> mobs) {

        for (int i = 0; i < mobs.size(); i++) {
            Entity entity = mobs.get(i);
            setOnFireFromLava(entity);

        }

    }

    protected void setOnFireFromLava(Entity entity)
    {
        if (!this.isImmuneToFire)
        {
            entity.attackEntityFrom(DamageSource.lava, 4.0F);
            entity.setFire(15);
        }
    }

    private void buffIfOnFire() {
        if (isBurning()) {
            addPotionEffect(new PotionEffect(Potion.regeneration.id, 80, 1));
        }
    }

    private void boostSpeedInWaterOrLava() {
        if (handleWaterMovement() || handleLavaMovement()) {
            addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 80, 3));
        }
    }


    @Override
    public void addPotionEffect(PotionEffect effect) {
        Potion potion = Potion.potionTypes[effect.getPotionID()];

        if (!potion.isBadEffect()) {
            super.addPotionEffect(effect);
        }
    }

    private void executeHealAbility(List<Entity> list) {
      heal(10);
      addPotionEffect(new PotionEffect(Potion.regeneration.id, 320, 1));


        for (int i = 0; i < list.size(); i++) {
            Entity e = list.get(i);

            if (e instanceof EntityLiving && e instanceof IMob) {
                EntityLiving living = (EntityLiving) e;
                living.heal(10);
                living.addPotionEffect(new PotionEffect(Potion.regeneration.id, 320, 1));


            } else if (e instanceof EntityMob) {
                EntityMob mob = (EntityMob) e;
                mob.heal(10);
                mob.addPotionEffect(new PotionEffect(Potion.regeneration.id, 320, 1));

            }

        }
        healHimselfAbility = 0;
    }



    private void executeMobSpawner(){
        for (int i = 0; i < 4 + rand.nextInt(worldObj.difficultySetting.getDifficultyId()); i++) {
            EntityCreeper creeper = new EntityCreeper(worldObj);
            creeper.setLocationAndAngles(posX, posY, posZ, 0, 0);
            creeper.getDataWatcher().updateObject(17, Byte.valueOf((byte)1));
        }

        mobSpawnTimer = 0;
    }

    private void executeJitterPlayerScreen(List<EntityPlayer> playersIn2Chunks) {
        for (int i = 0; i < playersIn2Chunks.size(); i++) {
            EntityPlayer player = playersIn2Chunks.get(i);

            player.cameraYaw = (float) (player.cameraYaw + rand.nextGaussian() + rand.nextFloat());
            player.cameraPitch = (float) (player.cameraPitch + rand.nextGaussian() + rand.nextFloat());
        }
    }

    private void executeLightingRainAttack(Entity attacker) {
        if (lightingOffCoooldown()) {
            EntityLightningBolt lightningBolt = new EntityLightningBolt(worldObj, attacker.posX + rand.nextDouble(), attacker.posY, attacker.posZ + rand.nextDouble());
            worldObj.addWeatherEffect(lightningBolt);

            if (lightingAbilityFinish()) {
                lightingTimer = 0;
            }
        }
    }

    private void executeArrowRain(EntityLivingBase attackTarg) {
        for (int i = 0; i < 16; i++) {
            EntityPotionArrow entityarrow = new EntityPotionArrow(this.worldObj, this, attackTarg, 1.0F, 0);
            entityarrow.canBePickedUp = 0;
            this.worldObj.spawnEntityInWorld(entityarrow);
        }


    }

    private void executeForcefullyUseOneAbilities(List<Entity> entitiesInThisChunk, List<Entity> mobs2Chunk) {
        switch (rand.nextInt(9)) {
            case 1:
                executeLightingAgentSpawn();
                break;
            case 2:
                executeLightingSpearAttack(0);
                break;
            case 3:
                executeMobBuff(entitiesInThisChunk);
                break;
            case 4:
                executeMobSpawner();
                break;
            case 5:
                if (super.findPlayerToAttack() != null) {
                    Entity attacker = super.findPlayerToAttack();
                    executeLightingRainAttack(attacker);
                }
                break;
            case 6:
                executeHealAbility(mobs2Chunk);
                break;
            case 7:
                spawnRandomMobFromHell(false, posX, posY, posZ);
                spawnRandomMobFromHell(true, posX, posY, posZ);
                break;
            case 8:
                executeJumpAbility();
                break;
        }

        abilityOffCooldown = 0;
    }

    private void executeUseAllAbilities(List<Entity> entitiesInThisChunk, List<Entity> mobs2Chunk) {
        if (waitTimer < 1) {
            if (super.findPlayerToAttack() != null ) {
                Entity attacker = super.findPlayerToAttack();
                executeLightingRainAttack(attacker);

                if (attacker instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) attacker;
                    executeArrowRain(player);
                }

            }
            executeJumpAbility();

        }
        waitTimer++;

        if (hasWaitedEnough()) {
            executeLightingAgentSpawn();
            executeLightingSpearAttack(0);
            executeMobBuff(entitiesInThisChunk);
            executeMobSpawner();
            executeHealAbility(mobs2Chunk);
            spawnRandomMobFromHell(false, posX, posY, posZ);
            spawnRandomMobFromHell(true, posX, posY, posZ);

            waitTimer = 0;
        }
    }

    @Override
    protected void jump()
    {
        this.motionY = 3.85D;

        if (this.isSprinting())
        {
            float f = this.rotationYaw * 0.017453292F;
            this.motionX -= (double)(MathHelper.sin(f) * 0.2F);
            this.motionZ += (double)(MathHelper.cos(f) * 0.2F);
        }

        this.isAirBorne = true;
        ForgeHooks.onLivingJump(this);
    }

    private void executeJumpAbility() {
        jump();
        jump();
        jump();
        if (this.onGround) {
            executeLightingSpearAttack(6);
            executeLightingSpearAttack(2);
            executeLightingSpearAttack(7);
            executeLightingSpearAttack(4);

            jumpTimer = 0;

        }
    }

    private void executeWaterShock(List<Entity> mobsInWater) {
        for (int i = 0; i < mobsInWater.size(); i++) {
            Entity mob = mobsInWater.get(i);

            if (mob instanceof EntityLiving && !(mob instanceof IMob))
            {
                EntityLiving living = (EntityLiving) mob;
                living.attackEntityFrom(DamageSource.causeMobDamage(this), 4);
            }

        }
    }

    public boolean willEntitySuffocateIfSpawned(Entity entity, double spawnX, double spawnY, double spawnZ)
    {
        for (int i = 0; i < 8; ++i)
        {
            float f = ((float)((i >> 0) % 2) - 0.5F) * entity.width * 0.8F;
            float f1 = ((float)((i >> 1) % 2) - 0.5F) * 0.1F;
            float f2 = ((float)((i >> 2) % 2) - 0.5F) * entity.width * 0.8F;
            int j = MathHelper.floor_double(spawnX + (double)f);
            int k = MathHelper.floor_double(spawnY + (double)entity.getEyeHeight() + (double)f1);
            int l = MathHelper.floor_double(spawnZ + (double)f2);

            if (this.worldObj.getBlock(j, k, l).isNormalCube())
            {
                return true;
            }
        }

        return false;
    }

    //   boolean stop=false;


    private void executeRemoveAllPlayerPotionEffects(List<EntityPlayer> playersIn2Chunks) {
        for (int i = 0; i < playersIn2Chunks.size(); i++) {
            EntityPlayer player = playersIn2Chunks.get(i);

            for (int j = 0; j < Potion.potionTypes.length; j++) {
                Potion potion = Potion.potionTypes[i];

                player.removePotionEffect(potion.id);

            }

        }
        removeAllPotionEffectsTimer = 0;

    }

    private void disableFlight(List<EntityPlayer> playersIn8Chunks) {
        for (int i = 0; i < playersIn8Chunks.size(); i++) {
            EntityPlayer player = playersIn8Chunks.get(i);

            if (!player.capabilities.isCreativeMode) {
                player.capabilities.isFlying = false;
                player.capabilities.allowFlying = false;
            }

        }
    }

    private void executeLightingAgentSpawn() {

        double lpx = posX + rand.nextInt(64);
        double lpz = posZ + rand.nextInt(64);
        double lpy = posY + rand.nextInt(64);

        LightingAgent agent = new LightingAgent(worldObj);

        if (!willEntitySuffocateIfSpawned(agent, lpx, lpy, lpy)) {
            agent.setLocationAndAngles(lpy, lpy, lpz, 0, 0);
            worldObj.spawnEntityInWorld(agent);
        } else {
            for (int i = 0; i < 16; i++) {
                if (!willEntitySuffocateIfSpawned(agent, lpx, lpy, lpz)) {
                    agent.setLocationAndAngles(lpx, lpy, lpz, 0, 0);
                    worldObj.spawnEntityInWorld(agent);
                }

            }

        }

        lightingAgentTimer = 0;

    }

    private void executeLightingAgentCounter(List<LightingAgent> agents) {
        lightingAgentCounter = agents.size();
    }



    private void spawnRandomMobFromHell(boolean end, double x, double y, double z) {
        List<BiomeGenBase.SpawnListEntry> mobsterSpawnList;

        if (end) {
            mobsterSpawnList = BiomeGenBase.sky.getSpawnableList(EnumCreatureType.monster);

        } else {
            mobsterSpawnList = BiomeGenBase.hell.getSpawnableList(EnumCreatureType.monster);
        }

        for (int i = 0; i < mobsterSpawnList.size(); i++) {
            BiomeGenBase.SpawnListEntry entry = mobsterSpawnList.get(rand.nextInt(mobsterSpawnList.size()));

            Class<? extends EntityLiving> monsterClazz = entry.entityClass;

            try {
                EntityLiving entity = monsterClazz.getConstructor(new Class[]{World.class}).newInstance(new Object[]{worldObj});
                entity.setLocationAndAngles(x,y,z,MathHelper.wrapAngleTo180_float(rand.nextFloat() * 360.0F), 0.0F);
            } catch (Exception e) {

            }
        }
    }


    private void addTimers() {
        lightingTimer = lightingTimer+ 1 + rand.nextInt(2);
        mobBuffTimer= mobBuffTimer+ 1 + rand.nextInt(4);
        mobSpawnTimer=mobSpawnTimer+ 1+ rand.nextInt(3);
        lightingSphereTimer=lightingSphereTimer+ 1+ rand.nextInt(2);
        lightingAgentTimer=lightingAgentTimer+ 1+ rand.nextInt(2);
        useAllAbilitiesAtOnceTimer = useAllAbilitiesAtOnceTimer + rand.nextInt(3);
        spawnMobsFromDimTimer= spawnMobsFromDimTimer + 2 + rand.nextInt(2);
        jumpTimer++;
        arrowRainTimer = arrowRainTimer + 1 + rand.nextInt(3);
        removeAllPotionEffectsTimer = 1 + rand.nextInt(2);
        snowballHitTimer++;
        fireHitTimer++;
        arrowHitTimer++;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!worldObj.isRemote) {
            executeSetToFire();
        }

    }

    @Override
    protected void updateEntityActionState() {
        super.updateEntityActionState();

        if (!worldObj.isRemote) {
            addTimers();

            if (super.findPlayerToAttack() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) super.findPlayerToAttack();
                teleportIfTooFarAway(player);
                isHigherCheese(player);
                isLowerCheese(player);


                if (phase == 0 && this.getHealth() <= 160) {
                    phase = 1;
                    phase1(player);
                }

                if (phase == 1 && this.getHealth() <= 120) {
                    phase = 2;
                    phase2(player);
                }

                if (phase == 2 && this.getHealth() <= 80) {
                    phase = 3;
                    phase3(player);
                }

                if (phase == 3 && this.getHealth() <= 40) {
                    phase = 4;
                    phase4(player);
                }

                if (phase == 4 && this.getHealth() <= 20) {
                    phase = 5;
                }



            }

            if (canUseJumpAbility()) {
                executeJumpAbility();
            }

            if (canDestroyBlocksTimer()) {
                destroyBlocksInRadius();
            }

            if (canArrowTimerDecr()) {
                arrowHit--;
            }

            if (canSnowTimerDecr()) {
                snowballHit--;
            }

            if (canFireTimerDecr()) {
                fireHit--;
            }

            isTooStill();

            AxisAlignedBB sixteenBB = this.boundingBox.expand(16,16,16);

            List<Entity> entsClose = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(4, 4, 4));

            List<LightingAgent> agent = worldObj.getEntitiesWithinAABB(LightingAgent.class, boundingBox.expand(64, 64, 64));
           List<Entity> entitiesInThisChunk = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(16, 16, 16));
           List<Entity> mobs2Chunk = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(32,32,32));
            List<EntityPlayer> playersIn2Chunks = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(32,32,32));
            List<EntityPlayer> playersIn8Chunks = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(128,128,128));

            if (mobSpawnTimerOffCoolown()) {
                spawnRandomMobFromHell(rand.nextBoolean(), posX + rand.nextDouble(), posY, posZ + rand.nextDouble());
                spawnMobsFromDimTimer = 0;
            }

            if (canRemoveAllPlayerPotionEffects()) {
                executeRemoveAllPlayerPotionEffects(playersIn2Chunks);
            }

            disableFlight(playersIn8Chunks);

            buffIfOnFire();
            boostSpeedInWaterOrLava();
            burnEntitiesNear(entsClose);


            executeLightingAgentCounter(agent);

            if (areAllAbilitiesOnCooldown()) {
                abilityOffCooldown = lightingTimer+ 1 + rand.nextInt(7);
                if (canForcefullyUseAbilities()) {
                    executeForcefullyUseOneAbilities(entitiesInThisChunk, mobs2Chunk);

                }

            }

            if (canUseAllAbilities()) {
                executeUseAllAbilities(entitiesInThisChunk, mobs2Chunk);
            }

            if (lightingAgentOnCooldown()) {
                executeLightingAgentSpawn();
            }

            if (lightingSpearOffCooldown()) {
                executeLightingSpearAttack(0);
            }

            if (mobBuffoffCooldown()) {
                executeMobBuff(entitiesInThisChunk);
            }

            if (mobSpawnTimerOffCoolown()) {
                executeMobSpawner();
            }

            Entity attacker = super.findPlayerToAttack();
            if (attacker != null) {

                executeJitterPlayerScreen(playersIn2Chunks);
                executeLightingRainAttack(attacker);
               ;
                for (int i = 0; i < entitiesInThisChunk.size(); i++) {
                  Entity entity =  entitiesInThisChunk.get(i);

                  if (entity instanceof EntityMob)  {
                      EntityMob mob = (EntityMob) entity;
                      if (attacker instanceof EntityPlayer) {
                          EntityPlayer player = (EntityPlayer) attacker;
                          ensureAllAgressiveMobsAttackPlayer(mob,player);
                          if  (canUseArrowRain()) {
                              executeArrowRain(player);
                          }
                      }

                  }

                }
            }

            if (canHealCooldown()) {
                executeHealAbility(mobs2Chunk);
            }


            if (activatePinchMode()) {
                setThunderingRainNight(false);
                increaseTimerIfSpecial();
            }

            if ((this.ticksExisted & 120) == 0) {
                heal(3);
                heal(lightingAgentCounter);
            }

            if (this.handleWaterMovement()) {
               List<Entity> mobsInWater = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(8,8,8));
                executeWaterShock(mobsInWater);
            }

            if (this.handleWaterMovement()) {
                increaseTimerIfSpecial();
            }
        }

    }

    @Override
    protected void attackEntity(Entity p_70785_1_, float p_70785_2_)
    {
        if (activatePinchMode() && rand.nextInt(4) == 0) {
            destroyBlocksInRadius();
        }
        if (this.attackTime <= 0 && p_70785_2_ < 2.0F && p_70785_1_.boundingBox.maxY > this.boundingBox.minY && p_70785_1_.boundingBox.minY < this.boundingBox.maxY)
        {
            this.attackTime = 20;
            this.attackEntityAsMob(p_70785_1_);
        }
        else if (p_70785_2_ < 30.0F)
        {
            double d0 = p_70785_1_.posX - this.posX;
            double d1 = p_70785_1_.boundingBox.minY + (double)(p_70785_1_.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
            double d2 = p_70785_1_.posZ - this.posZ;

            if (this.attackTime == 0)
            {
                ++this.fireballTime;

                if (this.fireballTime == 1)
                {
                    this.attackTime = 60;
                }
                else if (this.fireballTime <= 4)
                {
                    this.attackTime = 6;
                }
                else
                {
                    this.attackTime = 100;
                    this.fireballTime = 0;
                }

                if (this.fireballTime > 1)
                {
                    float f1 = MathHelper.sqrt_float(p_70785_2_) * 0.5F;
                    this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1009, (int)this.posX, (int)this.posY, (int)this.posZ, 0);

                    for (int i = 0; i < 6; ++i)
                    {
                        EntityLargeFireball fireball = new EntityLargeFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
                        fireball.posY = this.posY + (double)(this.height / 2.0F) + 0.5D;
                        this.worldObj.spawnEntityInWorld(fireball);
                    }
                }
            }

            this.rotationYaw = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
            this.hasAttacked = true;
        }
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_) {
        if (!worldObj.isRemote) {
            setThunderingRainNight(true);
        }
        return super.onSpawnWithEgg(p_110161_1_);
    }



    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn) {
        entityIn.onStruckByLightning(null);
        super.onCollideWithPlayer(entityIn);
    }

    @Override
    public void knockBack(Entity p_70653_1_, float p_70653_2_, double p_70653_3_, double p_70653_5_) {
        if (p_70653_1_ instanceof EntityLiving) {
            EntityLiving entityLiving = (EntityLiving) p_70653_1_;
            double d1 = entityLiving.posX - this.posX;
            double d0;

            for (d0 = entityLiving.posZ - this.posZ; d1 * d1 + d0 * d0 < 1.0E-4D; d0 = (Math.random() - Math.random()) * 0.01D)
            {
                d1 = (Math.random() - Math.random()) * 0.01D;
            }

            this.attackedAtYaw = (float)(Math.atan2(d0, d1) * 180.0D / Math.PI) - this.rotationYaw;
            entityLiving.knockBack(entityLiving, 1.2f, d1 * 1.2, d0 * 1.2);
        }
        super.knockBack(p_70653_1_, p_70653_2_, p_70653_3_, p_70653_5_);
    }




    @Override
    protected void onDeathUpdate()
    {
        ++this.deathTime;

        if (this.deathTime == 1200)
        {
            int i;

            if (!this.worldObj.isRemote && (this.recentlyHit > 0 || this.isPlayer()) && this.func_146066_aG() && this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot"))
            {
                i = this.getExperiencePoints(this.attackingPlayer);

                while (i > 0)
                {
                    int j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
                }
            }

            this.setDead();

            for (i = 0; i < 20; ++i)
            {
                double d2 = this.rand.nextGaussian() * 0.02D;
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                this.worldObj.spawnParticle("explode", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d2, d0, d1);

            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
       Entity e = source.getEntity();

      Entity src = source.getSourceOfDamage();

      if (phase == 5) {
          heal((float) (amount * 0.5));
      }

       if (e instanceof EntityPlayer) {
           if (src instanceof EntitySnowball)
           {
               snowballHit++;
               snowballHitTimer = 0;
           }

           if (src instanceof EntityArrow)
           {
                arrowHit++;
                arrowHitTimer = 0;
           }

           EntityPlayer player = (EntityPlayer) e;
          ItemStack is = player.inventory.getCurrentItem();
          if (is != null) {
              Item item = is.getItem();

              if (item != null) {

                  if (item == Items.lava_bucket) {
                      fireHit++;

                      fireHitTimer = 0;
                  }


                  if (item.isDamageable()) {
                      is.damageItem((int) amount, player);
                  }
              }
          }

       }

        if (source.isExplosion() || source.isFireDamage()) {
            return false;
        }

        if (!(e instanceof EntityPlayer)) {
            return false;
        }

        if (!source.isProjectile()) {
            source.getEntity().attackEntityFrom(DamageSource.causeMobDamage(this), amount);
        }

        if (amount > 10) amount = 10;

        amount+=fireHit;
        amount+=snowballHit;
        amount+=arrowHit;


        return super.attackEntityFrom(source, amount);
    }


    /**
     * Teleport the enderman to another entity
     */
    protected boolean teleportToEntity(Entity p_70816_1_)
    {
        Vec3 vec3 = Vec3.createVectorHelper(this.posX - p_70816_1_.posX, this.boundingBox.minY + (double)(this.height / 2.0F) - p_70816_1_.posY + (double)p_70816_1_.getEyeHeight(), this.posZ - p_70816_1_.posZ);
        vec3 = vec3.normalize();
        double d0 = 16.0D;
        double d1 = this.posX + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.xCoord * d0;
        double d2 = this.posY + (double)(this.rand.nextInt(16) - 8) - vec3.yCoord * d0;
        double d3 = this.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.zCoord * d0;
        return this.teleportTo(d1, d2, d3);
    }

    /**
     * Teleport the enderman
     */
    protected boolean teleportTo(double p_70825_1_, double p_70825_3_, double p_70825_5_)
    {
        EnderTeleportEvent event = new EnderTeleportEvent(this, p_70825_1_, p_70825_3_, p_70825_5_, 0);
        if (MinecraftForge.EVENT_BUS.post(event)){
            return false;
        }
        double d3 = this.posX;
        double d4 = this.posY;
        double d5 = this.posZ;
        this.posX = event.targetX;
        this.posY = event.targetY;
        this.posZ = event.targetZ;
        boolean flag = false;
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.posY);
        int k = MathHelper.floor_double(this.posZ);

        if (this.worldObj.blockExists(i, j, k))
        {
            boolean flag1 = false;

            while (!flag1 && j > 0)
            {
                Block block = this.worldObj.getBlock(i, j - 1, k);

                if (block.getMaterial().blocksMovement())
                {
                    flag1 = true;
                }
                else
                {
                    --this.posY;
                    --j;
                }
            }

            if (flag1)
            {
                this.setPosition(this.posX, this.posY, this.posZ);

                if (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox))
                {
                    flag = true;
                }
            }
        }

        if (!flag)
        {
            this.setPosition(d3, d4, d5);
            return false;
        }
        else
        {
            short short1 = 128;

            for (int l = 0; l < short1; ++l)
            {
                double d6 = (double)l / ((double)short1 - 1.0D);
                float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
                float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
                double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * (double)this.height;
                double d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
                this.worldObj.spawnParticle("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
            }

            this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
            this.playSound("mob.endermen.portal", 1.0F, 1.0F);
            return true;
        }
    }


    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompound) {
        super.readEntityFromNBT(tagCompound);
        lightingTimer=  tagCompound.getInteger("LightningTimer");
        mobBuffTimer= tagCompound.getInteger("MobBuffTimer");
        mobSpawnTimer= tagCompound.getInteger("MobSpawnTimer");
        lightingSphereTimer= tagCompound.getInteger("LightingSphereTimer");
        lightingAgentTimer=  tagCompound.getInteger("LightingAgentTimer");
        lightingAgentCounter =  tagCompound.getInteger("LightingAgentCounter");
        abilityOffCooldown= tagCompound.getInteger("AbilityOffCooldown");
        useAllAbilitiesAtOnceTimer=tagCompound.getInteger("UseAllAbilitiesAtOnceTimer");
        healHimselfAbility=  tagCompound.getInteger("HealHimselfAbility");
        waitTimer=tagCompound.getInteger("WaitTimer");
        fireballTime= tagCompound.getInteger("FireballTime");
        spawnMobsFromDimTimer=  tagCompound.getInteger("SpawnMobsFromDimTimer");
        jumpTimer= tagCompound.getInteger("JumpTimer");
        arrowRainTimer= tagCompound.getInteger("ArrowRainTimer");
        destroyBlocksTimer = tagCompound.getInteger("DestroyBlocksTimer");
        highTimer = tagCompound.getInteger("HighTimer");
        lowTimer = tagCompound.getInteger("LowTimer");

        fireHit = tagCompound.getInteger("FireHit");
        fireHitTimer = tagCompound.getInteger("FireHitTimer");

        snowballHit = tagCompound.getInteger("SnowballHit");
        snowballHitTimer = tagCompound.getInteger("SnowballHitTimer");

        arrowHit = tagCompound.getInteger("ArrowHit");
        arrowHitTimer = tagCompound.getInteger("ArrowHitTimer");

        removeAllPotionEffectsTimer = tagCompound.getInteger("removeAllPotionEffectsTimer");

        phase = tagCompound.getInteger("Phase");
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);

        tagCompound.setInteger("LightningTimer", lightingTimer);
        tagCompound.setInteger("MobBuffTimer", mobBuffTimer);
        tagCompound.setInteger("MobSpawnTimer", mobSpawnTimer);
        tagCompound.setInteger("LightingSphereTimer", lightingSphereTimer);
        tagCompound.setInteger("LightingAgentTimer", lightingAgentTimer);
        tagCompound.setInteger("LightingAgentCounter", lightingAgentCounter);
        tagCompound.setInteger("AbilityOffCooldown", abilityOffCooldown);
        tagCompound.setInteger("UseAllAbilitiesAtOnceTimer", useAllAbilitiesAtOnceTimer);
        tagCompound.setInteger("HealHimselfAbility", healHimselfAbility);
        tagCompound.setInteger("WaitTimer", waitTimer);
        tagCompound.setInteger("FireballTime", fireballTime);
        tagCompound.setInteger("SpawnMobsFromDimTimer", spawnMobsFromDimTimer);
        tagCompound.setInteger("JumpTimer", jumpTimer);
        tagCompound.setInteger("ArrowRainTimer", arrowRainTimer);
        tagCompound.setInteger("DestroyBlocksTimer", destroyBlocksTimer);
        tagCompound.setInteger("HighTimer", highTimer);
        tagCompound.setInteger("LowTimer", lowTimer);

        tagCompound.setInteger("FireHit", fireHit);
        tagCompound.setInteger("FireHitTimer", fireHitTimer);

        tagCompound.setInteger("SnowballHit", snowballHit);
        tagCompound.setInteger("SnowballHitTimer", snowballHitTimer);

        tagCompound.setInteger("ArrowHit", arrowHit);
        tagCompound.setInteger("ArrowHitTimer", arrowHitTimer);

        tagCompound.setInteger("removeAllPotionEffectsTimer", removeAllPotionEffectsTimer);


        tagCompound.setInteger("Phase", phase);
    }
}
