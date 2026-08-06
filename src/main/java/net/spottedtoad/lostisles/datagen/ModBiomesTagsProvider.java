package net.spottedtoad.lostisles.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.spottedtoad.lostisles.world.biome.ModBiomes;

import java.util.concurrent.CompletableFuture;

public class ModBiomesTagsProvider extends FabricTagsProvider<Biome> {
    public ModBiomesTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.BIOME, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        builder(BiomeTags.IS_OCEAN)
                .add(ModBiomes.PRIMORDIAL_OCEAN)
                .add(ModBiomes.DEEP_PRIMORDIAL_OCEAN);

        builder(BiomeTags.IS_DEEP_OCEAN)
                .add(ModBiomes.DEEP_PRIMORDIAL_OCEAN);
    }
}