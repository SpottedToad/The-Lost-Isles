package net.spottedtoad.lostisles.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.spottedtoad.lostisles.TheLostIsles;
import org.jspecify.annotations.NullMarked;

import java.util.List;

@NullMarked
public class ModRegistryHelper {
    @SuppressWarnings("UnnecessaryReturnStatement")
    private void TheLostIslesRegistryHelper() {
        return;
    }

    public static <T extends Feature<FC>, FC extends FeatureConfiguration> T register(String name, T feature) {
        return Registry.register(BuiltInRegistries.FEATURE, Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, name), feature);
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> registerable, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        FeatureUtils.register(registerable, key, feature, config);
    }

    public static void register(BootstrapContext<PlacedFeature> registerable, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> feature, PlacementModifier... placementModifiers) {
        register(registerable, key, feature, List.of(placementModifiers));
    }

    public static void register(BootstrapContext<PlacedFeature> registerable, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placementModifiers) {
        PlacementUtils.register(registerable, key,
                registerable.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(feature),
                placementModifiers);
    }

    public static void registerRegistryHelper() {
        TheLostIsles.LOGGER.info("Registering Mod Registry Helper for The Lost Isles");
    }

}
