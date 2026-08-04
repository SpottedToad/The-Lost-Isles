package net.spottedtoad.lostisles.world;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import net.minecraft.world.level.biome.Climate;
import net.spottedtoad.lostisles.TheLostIsles;
import net.spottedtoad.lostisles.world.biomes.ModBiomes;

public class ModBiomePlacement {
    public static void init() {
        Climate.ParameterPoint oceanPoints = Climate.parameters(
                Climate.Parameter.span(-0.15F, 0.5F), // Temperature
                Climate.Parameter.span(-1.0F, 1.0F), // Humidity
                Climate.Parameter.span(-2F, -0.5F), // Continentalness
                Climate.Parameter.span(-1.0F, 1.0F), // Erosion
                Climate.Parameter.span(0.0F, 1.0F),  // Depth
                Climate.Parameter.span(-2.0F, 1.0F), // Weirdness
                0L // Offset
        );

        BiomePlacement.addOverworld(ModBiomes.PRIMORDIAL_OCEAN, oceanPoints);

        BiomePlacement.addSubOverworld(
                ModBiomes.PRIMORDIAL_OCEAN,
                ModBiomes.DEEP_PRIMORDIAL_OCEAN,
                CriterionBuilder.NEAR_BORDER
        );

        BiomePlacement.addSubOverworld(
                ModBiomes.PRIMORDIAL_OCEAN,
                ModBiomes.PRIMORDIAL_ISLAND,
                CriterionBuilder.NEAR_INTERIOR
        );
    }

    public static void registerModBiomePlacement() {
        TheLostIsles.LOGGER.info("Registering Mod Biome Placement for The Lost Isles");
        init();
    }
}