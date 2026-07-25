package net.spottedtoad.lostisles.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.spottedtoad.lostisles.block.ModBlocks;
import net.spottedtoad.lostisles.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModItemsTagsProvider extends FabricTagsProvider.ItemTagsProvider {


    public ModItemsTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        valueLookupBuilder(ItemTags.SIGNS).add(ModItems.CONIFER_SIGN);
        valueLookupBuilder(ItemTags.HANGING_SIGNS).add(ModItems.CONIFER_HANGING_SIGN);

        valueLookupBuilder(ItemTags.BOATS).add(ModItems.CONIFER_BOAT);
        valueLookupBuilder(ItemTags.CHEST_BOATS).add(ModItems.CONIFER_CHEST_BOAT);
    }
}
