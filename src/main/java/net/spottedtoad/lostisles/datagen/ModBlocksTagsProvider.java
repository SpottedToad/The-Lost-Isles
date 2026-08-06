package net.spottedtoad.lostisles.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.spottedtoad.lostisles.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlocksTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlocksTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        valueLookupBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.TEMPLATE_BLOCK_1);

        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.TEMPLATE_BLOCK_1);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.CONIFER_LOG)
                .add(ModBlocks.CONIFER_WOOD)
                .add(ModBlocks.STRIPPED_CONIFER_LOG)
                .add(ModBlocks.STRIPPED_CONIFER_WOOD)
                .add(ModBlocks.CONIFER_PLANKS)
                .add(ModBlocks.CONIFER_STAIRS)
                .add(ModBlocks.CONIFER_SLAB)
                .add(ModBlocks.CONIFER_FENCE)
                .add(ModBlocks.CONIFER_FENCE_GATE)
                .add(ModBlocks.CONIFER_DOOR)
                .add(ModBlocks.CONIFER_TRAPDOOR)
                .add(ModBlocks.CONIFER_SHELF)
                .add(ModBlocks.CONIFER_SIGN)
                .add(ModBlocks.CONIFER_WALL_SIGN)
                .add(ModBlocks.CONIFER_HANGING_SIGN)
                .add(ModBlocks.CONIFER_WALL_HANGING_SIGN);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.CONIFER_LEAVES);

        valueLookupBuilder(BlockTags.LOGS)
                .add(ModBlocks.CONIFER_LOG)
                .add(ModBlocks.STRIPPED_CONIFER_LOG)
                .add(ModBlocks.CONIFER_WOOD)
                .add(ModBlocks.STRIPPED_CONIFER_WOOD);

        valueLookupBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.CONIFER_LOG)
                .add(ModBlocks.CONIFER_WOOD)
                .add(ModBlocks.STRIPPED_CONIFER_LOG)
                .add(ModBlocks.STRIPPED_CONIFER_WOOD);

        valueLookupBuilder(BlockTags.COMPLETES_FIND_TREE_TUTORIAL)
                .add(ModBlocks.CONIFER_LOG)
                .add(ModBlocks.CONIFER_WOOD)
                .add(ModBlocks.STRIPPED_CONIFER_LOG)
                .add(ModBlocks.STRIPPED_CONIFER_WOOD);

        valueLookupBuilder(BlockTags.PLANKS).add(ModBlocks.CONIFER_PLANKS);
        valueLookupBuilder(BlockTags.WOODEN_STAIRS).add(ModBlocks.CONIFER_STAIRS);
        valueLookupBuilder(BlockTags.WOODEN_SLABS).add(ModBlocks.CONIFER_SLAB);
        valueLookupBuilder(BlockTags.WOODEN_FENCES).add(ModBlocks.CONIFER_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.CONIFER_FENCE_GATE);
        valueLookupBuilder(BlockTags.WOODEN_DOORS).add(ModBlocks.CONIFER_DOOR);
        valueLookupBuilder(BlockTags.WOODEN_TRAPDOORS).add(ModBlocks.CONIFER_TRAPDOOR);
        valueLookupBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(ModBlocks.CONIFER_PRESSURE_PLATE);
        valueLookupBuilder(BlockTags.WOODEN_BUTTONS).add(ModBlocks.CONIFER_BUTTON);
        valueLookupBuilder(BlockTags.WOODEN_SHELVES).add(ModBlocks.CONIFER_SHELF);

        valueLookupBuilder(BlockTags.STANDING_SIGNS).add(ModBlocks.CONIFER_SIGN);
        valueLookupBuilder(BlockTags.WALL_SIGNS).add(ModBlocks.CONIFER_WALL_SIGN);
        valueLookupBuilder(BlockTags.CEILING_HANGING_SIGNS).add(ModBlocks.CONIFER_HANGING_SIGN);
        valueLookupBuilder(BlockTags.WALL_HANGING_SIGNS).add(ModBlocks.CONIFER_WALL_HANGING_SIGN);

        valueLookupBuilder(BlockTags.SAPLINGS).add(ModBlocks.CONIFER_SAPLING);
        valueLookupBuilder(BlockTags.FLOWER_POTS).add(ModBlocks.POTTED_CONIFER_SAPLING);
        valueLookupBuilder(BlockTags.LEAVES).add(ModBlocks.CONIFER_LEAVES);
    }
}
