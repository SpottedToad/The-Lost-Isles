package net.spottedtoad.lostisles.util;

import net.fabricmc.fabric.api.registry.CompostableRegistry;
import net.spottedtoad.lostisles.TheLostIsles;
import net.spottedtoad.lostisles.block.ModBlocks;

public class ModCompostables {
    public static void registerCompostableBlocks() {
        TheLostIsles.LOGGER.info("Registering Mod Compostable Blocks for The Lost Isles");
        CompostableRegistry registry = CompostableRegistry.INSTANCE;

        registry.add(ModBlocks.CONIFER_SAPLING.asItem(), 0.3F);
        registry.add(ModBlocks.CONIFER_LEAVES.asItem(), 0.3F);
    }
}

