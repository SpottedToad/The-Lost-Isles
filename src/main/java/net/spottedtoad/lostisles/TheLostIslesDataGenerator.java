package net.spottedtoad.lostisles;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.spottedtoad.lostisles.datagen.*;
import net.spottedtoad.lostisles.init.ModRegistryHelper;
import net.spottedtoad.lostisles.world.ModConfiguredFeatures;

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
