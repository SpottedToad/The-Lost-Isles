package net.spottedtoad.lostisles.world.biome;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.Criterion;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import com.terraformersmc.biolith.api.biome.sub.RatioTargets;
import net.minecraft.world.level.biome.Biomes;
import net.spottedtoad.lostisles.TheLostIsles;

public class ModBiomePlacement {
    public static void init() {
        //Replaces 100% of DEEP_LUKEWARM_OCEAN biomes with the DEEP_PRIMORDIAL_OCEAN biome
        BiomePlacement.replaceOverworld(
                Biomes.DEEP_LUKEWARM_OCEAN,
                ModBiomes.DEEP_PRIMORDIAL_OCEAN,
                1.0F
        );

        //Fills the area between 0% and 25% from the center of the DEEP_PRIMORDIAL_OCEAN with PRIMORDIAL_ISLAND
        Criterion islandCenter = CriterionBuilder.ratio(RatioTargets.CENTER, 0.0F, 0.25F);
        BiomePlacement.addSubOverworld(
                ModBiomes.DEEP_PRIMORDIAL_OCEAN,
                ModBiomes.PRIMORDIAL_ISLAND,
                islandCenter
        );

        //Fills the area between 25% and 50% from the center of the DEEP_PRIMORDIAL_OCEAN with PRIMORDIAL_OCEAN
        Criterion shallowOceanRing = CriterionBuilder.ratio(RatioTargets.CENTER, 0.25F, 0.50F);
        BiomePlacement.addSubOverworld(
                ModBiomes.DEEP_PRIMORDIAL_OCEAN,
                ModBiomes.PRIMORDIAL_OCEAN,
                shallowOceanRing
        );
    }

    public static void registerModBiomePlacement() {
        TheLostIsles.LOGGER.info("Registering Mod Biome Placement for The Lost Isles");
        init();
    }
}