package com.jerios.evilMinecraftFixes.mixins.late.adInfernos;

import com.jerios.evilMinecraftFixes.content.ContentRegistry;
import com.superdextor.dextersnether.init.NetherBlocks;
import com.superdextor.dextersnether.init.NetherItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(com.superdextor.dextersnether.world.NetherHooks.class)
public class MixinNetherHooks {

   @Mutable
   @Shadow(remap = false) public static @Final WeightedRandomChestContent[] NETHERCHESTItems;
   @Mutable
   @Shadow(remap = false) public static @Final WeightedRandomChestContent[] PIGGYCHESTItems;

   @Inject(method = "Init", at=@At("HEAD"), remap = false)
    private static void eevil$a(CallbackInfo ci) {
       NETHERCHESTItems = new WeightedRandomChestContent[]{new WeightedRandomChestContent(NetherItems.netherite_ingot, 0, 1, 1, 10), new WeightedRandomChestContent(NetherItems.quartz_ingot, 0, 1, 4, 10), new WeightedRandomChestContent(Items.nether_wart, 0, 1, 1, 10), new WeightedRandomChestContent(Items.rotten_flesh, 0, 1, 4, 10), new WeightedRandomChestContent(NetherItems.wither_dust, 0, 1, 4, 10), new WeightedRandomChestContent(NetherItems.obsidian_chunk, 0, 1, 4, 10), new WeightedRandomChestContent(NetherItems.golden_bucket_empty, 0, 1, 1, 10), new WeightedRandomChestContent(Items.golden_apple, 0, 1, 1, 1), new WeightedRandomChestContent(NetherItems.flameball, 0, 1, 4, 10), new WeightedRandomChestContent(Items.record_11, 0, 1, 1, 10), new WeightedRandomChestContent(NetherItems.nether_disc, 0, 1, 1, 10), new WeightedRandomChestContent(Items.name_tag, 0, 1, 1, 10), new WeightedRandomChestContent(NetherItems.wither_gem, 0, 1, 1, 5), new WeightedRandomChestContent(ContentRegistry.starFragment, 0, 1, 1, 1)};
       PIGGYCHESTItems = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.golden_sword, 0, 1, 1, 6), new WeightedRandomChestContent(Items.gold_nugget, 0, 5, 23, 8), new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5), new WeightedRandomChestContent(Items.carrot, 0, 5, 7, 10), new WeightedRandomChestContent(new ItemStack(NetherBlocks.gold_ore_nether), 3, 7, 6), new WeightedRandomChestContent(Items.flint_and_steel, 0, 1, 1, 4), new WeightedRandomChestContent(new ItemStack(NetherBlocks.nether_log), 4, 13, 9), new WeightedRandomChestContent(new ItemStack(NetherBlocks.nether_planks), 7, 34, 8), new WeightedRandomChestContent(Items.glowstone_dust, 0, 3, 7, 4), new WeightedRandomChestContent(Items.quartz, 0, 3, 7, 4), new WeightedRandomChestContent(Items.golden_pickaxe, 0, 1, 1, 6)};
   }


}
