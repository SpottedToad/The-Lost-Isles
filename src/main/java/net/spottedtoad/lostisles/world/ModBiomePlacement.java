package net.spottedtoad.lostisles.world;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.Criterion;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import com.terraformersmc.biolith.api.biome.sub.RatioTargets;
import net.minecraft.world.level.biome.Biomes;
import net.spottedtoad.lostisles.TheLostIsles;
import net.spottedtoad.lostisles.world.biomes.ModBiomes;

import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.ratio;

public class ModBiomePlacement {
    public static void init() {
        //Replace 100% of DEEP_OCEAN with DEEP_PRIMORDIAL_OCEAN
        BiomePlacement.replaceOverworld(
                Biomes.DEEP_OCEAN,
                ModBiomes.DEEP_PRIMORDIAL_OCEAN,
                1.0D
        );
        //Fills the area between 25% and 80% from the edge of the DEEP_PRIMORDIAL_OCEAN with PRIMORDIAL_OCEAN
        Criterion shallowOceanRing = CriterionBuilder.ratio(RatioTargets.EDGE, 0.25F, 0.80F);
        BiomePlacement.addSubOverworld(
                ModBiomes.DEEP_PRIMORDIAL_OCEAN,
                ModBiomes.PRIMORDIAL_OCEAN,
                shallowOceanRing
        );
        //Fills the area between 80% and 100% from the edge of the PRIMORDIAL_OCEAN with PRIMORDIAL_ISLAND
        Criterion deepOceanRing = CriterionBuilder.ratio(RatioTargets.EDGE, 0.80F, 1F);
        BiomePlacement.addSubOverworld(
                ModBiomes.PRIMORDIAL_OCEAN,
                ModBiomes.PRIMORDIAL_ISLAND,
                deepOceanRing
        );
    }

    public static void registerModBiomePlacement() {
        TheLostIsles.LOGGER.info("Registering Mod Biome Placement for The Lost Isles");
        init();
    }
}