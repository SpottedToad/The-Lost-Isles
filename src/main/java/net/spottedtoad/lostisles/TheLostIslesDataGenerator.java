package net.spottedtoad.lostisles;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.spottedtoad.lostisles.datagen.*;
import net.spottedtoad.lostisles.init.ModDynamicRegistryProvider;
import net.spottedtoad.lostisles.world.dimension.ModDimensionChunkGenerators;

public class TheLostIslesDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();
		pack.addProvider(ModDynamicRegistryProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModBiomesTagsProvider::new);
		pack.addProvider(ModItemsTagsProvider::new);
		pack.addProvider(ModBlocksTagsProvider::new);
		pack.addProvider(ModBlockLootTableProvider::new);
		pack.addProvider(ModRecipeProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		ModDynamicRegistryProvider.buildRegistry(registryBuilder);
	}
}
