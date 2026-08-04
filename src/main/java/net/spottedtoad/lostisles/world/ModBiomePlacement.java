package net.spottedtoad.lostisles.world;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.Criterion;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import com.terraformersmc.biolith.api.biome.sub.RatioTargets;
import net.minecraft.world.level.biome.Climate;
import net.spottedtoad.lostisles.TheLostIsles;
import net.spottedtoad.lostisles.world.biomes.ModBiomes;

public class ModBiomePlacement {
    public static void init() {
        Climate.ParameterPoint islandBuilder = Climate.parameters(
                Climate.Parameter.span(0.4F, 1.0F), // Temperature
                Climate.Parameter.span(-1.0F, 1.0F), // Humidity
                Climate.Parameter.span(-1.25F, -0.455F), // Continentalness
                Climate.Parameter.span(-1.0F, 1.0F), // Erosion
                Climate.Parameter.span(0.0F, 0.0F),  // Depth
                Climate.Parameter.span(-0.1F, 0.2F), // Weirdness
                0L // Offset (Artificially increase rarity)
        );
        //Creates PRIMORDIAL_ISLAND
        BiomePlacement.addOverworld(ModBiomes.PRIMORDIAL_ISLAND, islandBuilder);
        //Fills 70% of the outer area of the PRIMORDIAL_ISLAND with PRIMORDIAL_OCEAN
        Criterion shallowOceanRing = CriterionBuilder.ratioMax(RatioTargets.EDGE, 0.70F);
        BiomePlacement.addSubOverworld(
                ModBiomes.PRIMORDIAL_ISLAND,
                ModBiomes.PRIMORDIAL_OCEAN,
                shallowOceanRing
        );
        //Fills 80% of the outer area of the PRIMORDIAL_OCEAN with DEEP_PRIMORDIAL_OCEAN
        //Which is roughly 56% of the outer area of the PRIMORDIAL_ISLAND
        Criterion deepOceanRing = CriterionBuilder.ratioMax(RatioTargets.EDGE, 0.80F);
        BiomePlacement.addSubOverworld(
                ModBiomes.PRIMORDIAL_OCEAN,
                ModBiomes.DEEP_PRIMORDIAL_OCEAN,
                deepOceanRing
        );
    }

    public static void registerModBiomePlacement() {
        TheLostIsles.LOGGER.info("Registering Mod Biome Placement for The Lost Isles");
        init();
    }
}