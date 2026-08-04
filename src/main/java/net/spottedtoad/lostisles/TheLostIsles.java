package net.spottedtoad.lostisles;

import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import com.terraformersmc.biolith.impl.Biolith;
import com.terraformersmc.biolith.impl.BiolithInit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTabs;
import net.spottedtoad.lostisles.block.ModBlocks;
import net.spottedtoad.lostisles.entity.custom.CeratosaurusEntity;
import net.spottedtoad.lostisles.entity.custom.ModEntityTypes;
import net.spottedtoad.lostisles.init.ModRegistryHelper;
import net.spottedtoad.lostisles.util.ModCompostables;
import net.spottedtoad.lostisles.block.ModWoodTypes;
import net.spottedtoad.lostisles.item.ModItems;
import net.spottedtoad.lostisles.tab.ModTabs;
import net.spottedtoad.lostisles.util.ModFlammableBlocks;
import net.spottedtoad.lostisles.util.ModStrippableBlocks;
import net.spottedtoad.lostisles.world.ModBiomePlacement;
import net.spottedtoad.lostisles.world.ModConfiguredFeatures;
import net.spottedtoad.lostisles.world.ModFeatures;
import net.spottedtoad.lostisles.world.biomes.ModBiomes;
import net.spottedtoad.lostisles.world.biomes.ModSurfaceRules;
import net.spottedtoad.lostisles.world.trees.ModSaplingGenerators;
import net.spottedtoad.lostisles.world.ModWorldGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.minecraft.world.item.Items.*;
import static net.spottedtoad.lostisles.block.ModBlocks.*;
import static net.spottedtoad.lostisles.entity.custom.ModEntityTypes.CERATOSAURUS;
import static net.spottedtoad.lostisles.item.ModItems.CERATOSAURUS_SPAWN_EGG;

public class TheLostIsles implements ModInitializer {
	public static final String MOD_ID = "lostisles";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Identifier CONIFER_BOATS_ID = Identifier.fromNamespaceAndPath(MOD_ID, "conifer");

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing The Lost Isles");
		ModTabs.registerModTabs();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEntityTypes.registerModEntityTypes();
		ModWoodTypes.registerModWoodTypes();
		ModCompostables.registerCompostableBlocks();
		ModStrippableBlocks.registerStrippableBlocks();
		ModFlammableBlocks.registerFlammableBlocks();
		ModRegistryHelper.registerRegistryHelper();
		ModWorldGen.registerModWorldGen();
		ModFeatures.registerModFeatures();
		ModConfiguredFeatures.registerModConfiguredFeatures();
		ModSaplingGenerators.registerModSaplingGenerators();

		FabricDefaultAttributeRegistry.register(CERATOSAURUS, CeratosaurusEntity.createCeratosaurusAttributes());

		SurfaceGeneration.addOverworldSurfaceRules(
				Identifier.fromNamespaceAndPath("lostisles", "rules/overworld"),
				ModSurfaceRules.overworld());
		ModBiomes.registerModBiomes();
		ModBiomePlacement.registerModBiomePlacement();

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(output -> {
			output.insertBefore(PALE_OAK_LOG, CONIFER_LOG, CONIFER_WOOD, STRIPPED_CONIFER_LOG, STRIPPED_CONIFER_WOOD,
					CONIFER_PLANKS, CONIFER_STAIRS, CONIFER_SLAB, CONIFER_FENCE, CONIFER_FENCE_GATE, CONIFER_DOOR,
					CONIFER_TRAPDOOR, CONIFER_PRESSURE_PLATE, CONIFER_BUTTON);
		});
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COLORED_BLOCKS).register(output -> {
		});
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(output -> {
			output.insertBefore(PALE_OAK_LOG, CONIFER_LOG, CONIFER_WOOD);
			output.insertBefore(PALE_OAK_LEAVES, CONIFER_LEAVES);
			output.insertBefore(PALE_OAK_SAPLING, CONIFER_SAPLING);
		});
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(output -> {
			output.insertBefore(PALE_OAK_SHELF, CONIFER_SHELF);
			output.insertBefore(PALE_OAK_SIGN, ModItems.CONIFER_SIGN, ModItems.CONIFER_HANGING_SIGN);
		});
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.REDSTONE_BLOCKS).register(output -> {
		});
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(output -> {
			output.insertBefore(PALE_OAK_BOAT, ModItems.CONIFER_BOAT, ModItems.CONIFER_CHEST_BOAT);
		});
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(output -> {
		});
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(output -> {
		});
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
		});
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.SPAWN_EGGS).register(output -> {
			output.insertAfter(SHULKER_SPAWN_EGG, CERATOSAURUS_SPAWN_EGG);
		});
	}
}
