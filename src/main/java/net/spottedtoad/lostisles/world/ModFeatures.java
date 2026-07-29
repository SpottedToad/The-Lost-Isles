package net.spottedtoad.lostisles.world;

import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.spottedtoad.lostisles.TheLostIsles;
import net.spottedtoad.lostisles.block.ModBlocks;
import net.spottedtoad.lostisles.world.trees.ConiferFoliagePlacer;
import net.spottedtoad.lostisles.world.trees.ConiferTrunkPlacer;

public class ModFeatures {
    public static final TreeConfiguration CONIFER_TREE_CONFIG = new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(ModBlocks.CONIFER_LOG.defaultBlockState()),
            new ConiferTrunkPlacer(16, 2, 4),
            BlockStateProvider.simple(ModBlocks.CONIFER_LEAVES.defaultBlockState()),
            new ConiferFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), ConstantInt.of(2)),
            new TwoLayersFeatureSize(2, 0, 4)
    ).ignoreVines().build();

    public static void register() {}

    public static void registerModFeatures() {
        TheLostIsles.LOGGER.info("Registering Mod Features for The Lost Isles");
    }
}
