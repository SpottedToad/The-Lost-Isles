package net.spottedtoad.lostisles.world.trees;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.spottedtoad.lostisles.world.ModWorldGen;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class ConiferTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<ConiferTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            trunkPlacerParts(instance).apply(instance, ConiferTrunkPlacer::new));

    public ConiferTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModWorldGen.CONIFER_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(WorldGenLevel level, BiConsumer<BlockPos, BlockState> trunkSetter, RandomSource random, int treeHeight, BlockPos origin, TreeConfiguration config) {
        placeBelowTrunkBlock(level, trunkSetter, random, origin.below(), config);
        List<FoliagePlacer.FoliageAttachment> foliageNodes = new ArrayList<>();

        for (int i = 0; i < treeHeight; i++) {
            placeLog(level, trunkSetter, random, origin.above(i), config);
        }

        foliageNodes.add(new FoliagePlacer.FoliageAttachment(origin.above(treeHeight), 0, false));
        return foliageNodes;
        }
}