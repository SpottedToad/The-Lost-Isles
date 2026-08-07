package net.spottedtoad.lostisles.world.biome;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.Criterion;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import com.terraformersmc.biolith.api.biome.sub.RatioTargets;
import net.minecraft.world.level.biome.Biomes;
import net.spottedtoad.lostisles.TheLostIsles;

public class ModBiomePlacement {
    public static void init() {
        // Replace 100% (temporary testing value) of DEEP_LUKEWARM_OCEAN biomes with DEEP_STORMY_LUKEWARM_OCEAN biome
        BiomePlacement.replaceOverworld(
                Biomes.DEEP_LUKEWARM_OCEAN,
                ModBiomes.DEEP_STORMY_LUKEWARM_OCEAN,
                1.0D
        );

        // Fills the area between 0% and 20% from the edge of the targeted biome
        Criterion centerStorm = CriterionBuilder.ratio(RatioTargets.EDGE, 0.0F, 0.20F);
        // Add DEEP_LUKEWARM_OCEAN as a sub biome of DEEP_STORMY_LUKEWARM_OCEAN
        BiomePlacement.addSubOverworld(
                ModBiomes.DEEP_STORMY_LUKEWARM_OCEAN,
                Biomes.DEEP_LUKEWARM_OCEAN,
                centerStorm
        );
    }

    public static void registerModBiomePlacement() {
        TheLostIsles.LOGGER.info("Registering Mod Biome Placement for The Lost Isles");
        init();
    }
}