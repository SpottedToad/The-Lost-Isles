package net.spottedtoad.lostisles.world.biome;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import net.minecraft.world.level.biome.Biomes;
import net.spottedtoad.lostisles.TheLostIsles;

public class ModBiomePlacement {
    public static void init() {
        //Replaces 100% of DEEP_LUKEWARM_OCEAN biomes with the DEEP_PRIMORDIAL_OCEAN biome
        BiomePlacement.replaceOverworld(
                Biomes.DEEP_LUKEWARM_OCEAN,
                ModBiomes.DEEP_STORMY_LUKEWARM_OCEAN,
                1.0F
        );
    }

    public static void registerModBiomePlacement() {
        TheLostIsles.LOGGER.info("Registering Mod Biome Placement for The Lost Isles");
        init();
    }
}