package net.spottedtoad.lostisles.util;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.spottedtoad.lostisles.TheLostIsles;
import net.spottedtoad.lostisles.block.ModBlocks;

public class ModFlammableBlocks {
    public static void registerFlammableBlocks() {
        TheLostIsles.LOGGER.info("Registering Mod Flammable Blocks for The Lost Isles");
        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();

        registry.add(ModBlocks.CONIFER_LOG, 5, 5);
        registry.add(ModBlocks.CONIFER_WOOD, 5, 5);
        registry.add(ModBlocks.STRIPPED_CONIFER_LOG, 5, 5);
        registry.add(ModBlocks.STRIPPED_CONIFER_WOOD, 5, 5);
        registry.add(ModBlocks.CONIFER_PLANKS, 5, 20);
        registry.add(ModBlocks.CONIFER_STAIRS, 5, 20);
        registry.add(ModBlocks.CONIFER_SLAB, 5, 20);
        registry.add(ModBlocks.CONIFER_FENCE, 5, 20);
        registry.add(ModBlocks.CONIFER_FENCE_GATE, 5, 20);
        registry.add(ModBlocks.CONIFER_LEAVES, 30, 60);
    }
}

