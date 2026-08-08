package net.spottedtoad.lostisles.world.dimension;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TimelineTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.spottedtoad.lostisles.TheLostIsles;
import net.spottedtoad.lostisles.world.biome.ModBiomes;

import java.util.List;
import java.util.Optional;

public class ModDimensions {
    public static final ResourceKey<LevelStem> PRIMORDIAL_STEM_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, "primordial_echo"));
    public static final ResourceKey<DimensionType> PRIMORDIAL_TYPE_KEY = ResourceKey.create(Registries.DIMENSION_TYPE,
            Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, "primordial_dimension_type"));

    public static void bootstrapType(BootstrapContext<DimensionType> context) {
        var timelines = context.lookup(Registries.TIMELINE);
        IntProvider lightTestProvider = ConstantInt.of(0);

        context.register(PRIMORDIAL_TYPE_KEY, new DimensionType(
                false,
                true,
                false,
                false,
                1.0,
                -64,
                384,
                384,
                BlockTags.INFINIBURN_OVERWORLD,
                1.0F,
                new DimensionType.MonsterSettings(lightTestProvider, 0),
                DimensionType.Skybox.OVERWORLD,
                CardinalLighting.Type.DEFAULT,
                EnvironmentAttributeMap.EMPTY,
                timelines.getOrThrow(TimelineTags.IN_OVERWORLD),
                Optional.empty()
        ));
    }

    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        var biomes = context.lookup(Registries.BIOME);
        var dimensionTypes = context.lookup(Registries.DIMENSION_TYPE);

        MultiNoiseBiomeSource multiNoiseBiomes = MultiNoiseBiomeSource.createFromList(
                new Climate.ParameterList<>(List.of(
                        Pair.of(Climate.parameters(0.2F, 0.0F, -0.8F, 0.25F, 0.0F, 0.0F, 0.0F),
                                biomes.getOrThrow(ModBiomes.DEEP_PRIMORDIAL_OCEAN)),
                        Pair.of(Climate.parameters(0.2F, 0.0F, -0.32F, 0.25F, 0.0F, 0.0F, 0.0F),
                                biomes.getOrThrow(ModBiomes.PRIMORDIAL_OCEAN)), // Fixed typo if needed
                        Pair.of(Climate.parameters(0.2F, 0.0F, -0.05F, 0.25F, 0.0F, 0.0F, 0.0F),
                                biomes.getOrThrow(ModBiomes.PRIMORDIAL_ISLAND))
                ))
        );

        ModDimensionChunkGenerators primordialGenerator = new ModDimensionChunkGenerators(multiNoiseBiomes);
        LevelStem stem = new LevelStem(dimensionTypes.getOrThrow(ModDimensions.PRIMORDIAL_TYPE_KEY), primordialGenerator);
        context.register(PRIMORDIAL_STEM_KEY, stem);
    }

    public static void registerModDimensions() {
        TheLostIsles.LOGGER.info("Registering Mod Dimensions for The Lost Isles");
    }
}