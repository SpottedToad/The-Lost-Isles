package net.spottedtoad.lostisles.util;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.spottedtoad.lostisles.TheLostIsles;
import net.spottedtoad.lostisles.block.ModBlocks;

public class ModStrippableBlocks {
    public static void registerStrippableBlocks() {
        TheLostIsles.LOGGER.info("Registering Mod Strippable Blocks for The Lost Isles");

        StrippableBlockRegistry.register(ModBlocks.CONIFER_LOG, ModBlocks.STRIPPED_CONIFER_LOG);
        StrippableBlockRegistry.register(ModBlocks.CONIFER_WOOD, ModBlocks.STRIPPED_CONIFER_WOOD);
    }
}
