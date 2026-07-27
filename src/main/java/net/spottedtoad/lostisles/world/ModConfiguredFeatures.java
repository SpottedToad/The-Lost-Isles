package net.spottedtoad.lostisles.world;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.spottedtoad.lostisles.TheLostIsles;
import net.spottedtoad.lostisles.init.ModRegistryHelper;

public class ModConfiguredFeatures {
    //Trees
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONIFER_TREE = resourceKey("conifer_tree");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        ModRegistryHelper.register(context, CONIFER_TREE, Feature.TREE, ModFeatures.CONIFER_TREE_CONFIG);
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> resourceKey(String path) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID,path));
    }

    public static void registerModConfiguredFeatures() {
        TheLostIsles.LOGGER.info("Registering Mod Configured Features for The Lost Isles");
    }
}
