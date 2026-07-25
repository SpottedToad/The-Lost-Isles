package net.spottedtoad.lostisles;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.spottedtoad.lostisles.datagen.ModBlockLootTableProvider;
import net.spottedtoad.lostisles.datagen.ModBlocksTagsProvider;
import net.spottedtoad.lostisles.datagen.ModModelProvider;

public class TheLostIslesDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModBlocksTagsProvider::new);
		pack.addProvider(ModBlockLootTableProvider::new);
	}

	public static void registerDataGenerator() {
		TheLostIsles.LOGGER.info("Initializing Data Generation for The Lost Isles");
	}
}
