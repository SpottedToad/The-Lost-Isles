package net.spottedtoad.lostisles.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.spottedtoad.lostisles.block.ModBlocks;
import net.spottedtoad.lostisles.item.ModItems;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModItemsTagsProvider extends FabricTagsProvider.ItemTagsProvider {


    public ModItemsTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        Map<TagKey<Item>, ItemLike> blockToItemMaps = Map.ofEntries(
        Map.entry(ItemTags.PLANKS, ModBlocks.CONIFER_PLANKS),
        Map.entry(ItemTags.STAIRS, ModBlocks.CONIFER_STAIRS),
        Map.entry(ItemTags.WOODEN_STAIRS, ModBlocks.CONIFER_STAIRS),
        Map.entry(ItemTags.SLABS, ModBlocks.CONIFER_SLAB),
        Map.entry(ItemTags.WOODEN_SLABS, ModBlocks.CONIFER_SLAB),
        Map.entry(ItemTags.WOODEN_FENCES, ModBlocks.CONIFER_FENCE),
        Map.entry(ItemTags.FENCE_GATES, ModBlocks.CONIFER_FENCE_GATE),
        Map.entry(ItemTags.BUTTONS, ModBlocks.CONIFER_BUTTON),
        Map.entry(ItemTags.WOODEN_BUTTONS, ModBlocks.CONIFER_BUTTON),
        Map.entry(ItemTags.WOODEN_PRESSURE_PLATES, ModBlocks.CONIFER_PRESSURE_PLATE),
        Map.entry(ItemTags.DOORS, ModBlocks.CONIFER_DOOR),
        Map.entry(ItemTags.WOODEN_DOORS, ModBlocks.CONIFER_DOOR),
        Map.entry(ItemTags.TRAPDOORS, ModBlocks.CONIFER_TRAPDOOR),
        Map.entry(ItemTags.WOODEN_TRAPDOORS, ModBlocks.CONIFER_TRAPDOOR),
        Map.entry(ItemTags.SAPLINGS, ModBlocks.CONIFER_SAPLING),
        Map.entry(ItemTags.LEAVES, ModBlocks.CONIFER_LEAVES)
        );
        blockToItemMaps.forEach((tag, block) -> {
            var key = BuiltInRegistries.ITEM.getKey(block.asItem());
            this.getOrCreateRawBuilder(tag).add(TagEntry.element(key));
        });

        var logsTagBuilder = this.getOrCreateRawBuilder(ItemTags.LOGS);
        var logsThatBurnTagBuilder = this.getOrCreateRawBuilder(ItemTags.LOGS_THAT_BURN);
        List<ItemLike> coniferLogs = List.of(
                ModBlocks.CONIFER_LOG,
                ModBlocks.STRIPPED_CONIFER_LOG,
                ModBlocks.CONIFER_WOOD,
                ModBlocks.STRIPPED_CONIFER_WOOD
        );
        for (ItemLike logBlock : coniferLogs) {
            var logKey = BuiltInRegistries.ITEM.getKey(logBlock.asItem());
            TagEntry entry = TagEntry.element(logKey);
            logsTagBuilder.add(entry);
            logsThatBurnTagBuilder.add(entry);
        }

        valueLookupBuilder(ItemTags.SIGNS).add(ModItems.CONIFER_SIGN);
        valueLookupBuilder(ItemTags.HANGING_SIGNS).add(ModItems.CONIFER_HANGING_SIGN);

        valueLookupBuilder(ItemTags.BOATS).add(ModItems.CONIFER_BOAT);
        valueLookupBuilder(ItemTags.CHEST_BOATS).add(ModItems.CONIFER_CHEST_BOAT);
    }
}
