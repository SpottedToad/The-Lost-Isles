package net.spottedtoad.lostisles.world.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.spottedtoad.lostisles.TheLostIsles;

public class ModBiomes {
    private static void register(BootstrapContext<Biome> context, ResourceKey<Biome> key, Biome biome) {
        context.register(key, biome);}
    private static ResourceKey<Biome> registerBiomeKey(String name) {
        return ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, name));}

    public static final ResourceKey<Biome> PRIMORDIAL_ISLAND = registerBiomeKey("primordial_island");
    public static final ResourceKey<Biome> PRIMORDIAL_OCEAN = registerBiomeKey("primordial_ocean");
    public static final ResourceKey<Biome> DEEP_PRIMORDIAL_OCEAN = registerBiomeKey("deep_primordial_ocean");
    public static final ResourceKey<Biome> NULL_BIOME = registerBiomeKey("null");

    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(PRIMORDIAL_ISLAND, createPrimordialIslandBiome(context));
        context.register(PRIMORDIAL_OCEAN, createPrimordialOceanBiome(context, false));
        context.register(DEEP_PRIMORDIAL_OCEAN, createDeepPrimordialOceanBiome(context, true));
        context.register(NULL_BIOME, createNullBiome(context));
    }

    private static Biome createNullBiome(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder geo = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE),
                context.lookup(Registries.CONFIGURED_CARVER));
        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(0.5F)
                .downfall(0.0F)
                .mobSpawnSettings(spawns.build())
                .generationSettings(geo.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x000000)
                        .grassColorOverride(0x000000)
                        .foliageColorOverride(0x000000)
                        .grassColorModifier(BiomeSpecialEffects.GrassColorModifier.NONE)
                        .build())
                .build();
    }

    private static Biome createPrimordialIslandBiome(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
            BiomeDefaultFeatures.farmAnimals(spawns);
            BiomeDefaultFeatures.monsters(spawns, 0, 0, 0, 0, false);
        BiomeGenerationSettings.Builder geo = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE),
                context.lookup(Registries.CONFIGURED_CARVER));
        BiomeDefaultFeatures.addDefaultCarversAndLakes(geo);
        BiomeDefaultFeatures.addDefaultOres(geo);
        BiomeDefaultFeatures.addDefaultSoftDisks(geo);
        BiomeDefaultFeatures.addFerns(geo);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.7F)
                .downfall(0.5F)
                .mobSpawnSettings(spawns.build())
                .generationSettings(geo.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x29B6F6)
                        .grassColorOverride(0x79C05A)
                        .foliageColorOverride(0x59AE30)
                        .grassColorModifier(BiomeSpecialEffects.GrassColorModifier.DARK_FOREST)
                        .build())
                .build();
    }

    private static Biome createPrimordialOceanBiome(BootstrapContext<Biome> context, boolean isDeep) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder geo = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE),
                context.lookup(Registries.CONFIGURED_CARVER));
        BiomeDefaultFeatures.addDefaultCarversAndLakes(geo);
        BiomeDefaultFeatures.addDefaultOres(geo);
        BiomeDefaultFeatures.addDefaultSoftDisks(geo);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.5F)
                .downfall(0.5F)
                .mobSpawnSettings(spawns.build())
                .generationSettings(geo.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(isDeep ? 0x1A237E : 0x19B6F6)
                        .grassColorModifier(BiomeSpecialEffects.GrassColorModifier.DARK_FOREST)
                        .build())
                .build();
    }

    private static Biome createDeepPrimordialOceanBiome(BootstrapContext<Biome> context, boolean isDeep) {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder geo = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE),
                context.lookup(Registries.CONFIGURED_CARVER));
        BiomeDefaultFeatures.addDefaultCarversAndLakes(geo);
        BiomeDefaultFeatures.addDefaultOres(geo);
        BiomeDefaultFeatures.addDefaultSoftDisks(geo);
        BiomeDefaultFeatures.addColdOceanExtraVegetation(geo);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.5F)
                .downfall(0.5F)
                .mobSpawnSettings(spawns.build())
                .generationSettings(geo.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x0116F6)
                        .grassColorModifier(BiomeSpecialEffects.GrassColorModifier.DARK_FOREST)
                        .build())
                .build();
    }

    public static void registerModBiomes() {
        TheLostIsles.LOGGER.info("Registering Mod Biomes for The Lost Isles");
    }
}
