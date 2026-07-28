package net.spottedtoad.lostisles.world;

import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.spottedtoad.lostisles.TheLostIsles;
import net.spottedtoad.lostisles.block.ModBlocks;

public class ModFeatures {
    public static final TreeConfiguration CONIFER_TREE_CONFIG = new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(ModBlocks.CONIFER_LOG.defaultBlockState()),
            new ConiferTrunkPlacer(9, 5, 6),
            BlockStateProvider.simple(ModBlocks.CONIFER_LEAVES.defaultBlockState()),
            new NullFoliagePlacer(0, 0),
            new TwoLayersFeatureSize(2, 0, 4)
    ).ignoreVines().build();

    public static void register() {}

    public static void registerModFeatures() {
        TheLostIsles.LOGGER.info("Registering Mod Features for The Lost Isles");
    }
}
