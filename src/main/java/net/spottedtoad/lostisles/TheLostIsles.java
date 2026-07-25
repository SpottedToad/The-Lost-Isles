package net.spottedtoad.lostisles;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTabs;
import net.spottedtoad.lostisles.block.ModBlocks;
import net.spottedtoad.lostisles.item.ModItems;
import net.spottedtoad.lostisles.tab.ModTabs;
import net.spottedtoad.lostisles.util.ModFlammableBlocks;
import net.spottedtoad.lostisles.util.ModStrippableBlocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.minecraft.world.item.Items.*;
import static net.spottedtoad.lostisles.block.ModBlocks.*;

public class TheLostIsles implements ModInitializer {
	public static final String MOD_ID = "lostisles";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Identifier CONIFER_BOATS_ID = Identifier.fromNamespaceAndPath(MOD_ID, "conifer");

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing The Lost Isles");
		ModTabs.registerModTabs();
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModStrippableBlocks.registerStrippableBlocks();
		ModFlammableBlocks.registerFlammableBlocks();

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(output -> {
			output.insertBefore(PALE_OAK_LOG, CONIFER_LOG, CONIFER_WOOD, STRIPPED_CONIFER_LOG, STRIPPED_CONIFER_WOOD,
					CONIFER_PLANKS, CONIFER_STAIRS, CONIFER_SLAB, CONIFER_FENCE, CONIFER_FENCE_GATE, CONIFER_DOOR,
					CONIFER_TRAPDOOR, CONIFER_PRESSURE_PLATE, CONIFER_BUTTON);
		});
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COLORED_BLOCKS).register(output -> {
		});
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(output -> {
			output.insertBefore(PALE_OAK_LOG, CONIFER_LOG, CONIFER_WOOD);
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
		});
	}
}
