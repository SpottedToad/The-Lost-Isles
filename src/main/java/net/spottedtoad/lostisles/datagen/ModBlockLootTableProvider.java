package net.spottedtoad.lostisles.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.spottedtoad.lostisles.block.ModBlocks;
import net.spottedtoad.lostisles.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    public ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        var enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        //Adds conifer variant drops
        dropSelf(ModBlocks.CONIFER_SAPLING);
        dropPottedContents(ModBlocks.POTTED_CONIFER_SAPLING);
        dropSelf(ModBlocks.CONIFER_LOG);
        dropSelf(ModBlocks.CONIFER_WOOD);
        dropSelf(ModBlocks.STRIPPED_CONIFER_LOG);
        dropSelf(ModBlocks.STRIPPED_CONIFER_WOOD);
        dropSelf(ModBlocks.CONIFER_PLANKS);
        dropSelf(ModBlocks.CONIFER_STAIRS);
        dropSelf(ModBlocks.CONIFER_SLAB);
        dropSelf(ModBlocks.CONIFER_FENCE);
        dropSelf(ModBlocks.CONIFER_FENCE_GATE);
        add(ModBlocks.CONIFER_DOOR, createDoorTable(ModBlocks.CONIFER_DOOR));
        dropSelf(ModBlocks.CONIFER_TRAPDOOR);
        dropSelf(ModBlocks.CONIFER_PRESSURE_PLATE);
        dropSelf(ModBlocks.CONIFER_BUTTON);
        dropSelf(ModBlocks.CONIFER_SIGN);
        dropSelf(ModBlocks.CONIFER_WALL_SIGN);
        dropSelf(ModBlocks.CONIFER_HANGING_SIGN);
        dropSelf(ModBlocks.CONIFER_WALL_HANGING_SIGN);
        dropSelf(ModBlocks.CONIFER_SHELF);

        add(ModBlocks.CONIFER_LEAVES, createLeavesDrops(ModBlocks.CONIFER_LEAVES, ModBlocks.CONIFER_SAPLING, 0.05f, 0.0625f, 0.083333336f, 0.1f));

        //Adds template block drops
        add(ModBlocks.TEMPLATE_BLOCK_1, createOreDrop(ModBlocks.TEMPLATE_BLOCK_1, ModItems.TEMPLATE_ITEM_1));
    }

    public LootTable.Builder createMultipleOreDrops(final Block block, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        return this.createSilkTouchDispatchTable(block, this.applyExplosionDecay(
                block, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))));
    }
}
