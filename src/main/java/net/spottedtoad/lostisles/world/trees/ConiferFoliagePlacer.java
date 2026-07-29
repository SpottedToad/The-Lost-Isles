package net.spottedtoad.lostisles.world.trees;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.spottedtoad.lostisles.world.ModWorldGen;

public class ConiferFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<ConiferFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
            instance -> foliagePlacerParts(instance)
                    .and(IntProviders.validateCodec(0, 24, IntProviders.CODEC)
                            .fieldOf("trunk_height")
                            .forGetter(placer -> placer.trunkHeight))
                    .apply(instance, ConiferFoliagePlacer::new)
    );
    private final IntProvider trunkHeight;
    public ConiferFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider trunkHeight) {
        super(radius, offset);
        this.trunkHeight = trunkHeight;
    }
    @Override
    protected FoliagePlacerType<?> type() {
        return ModWorldGen.CONIFER_FOLIAGE_PLACER;
    }


    @Override
    public int foliageHeight(RandomSource random, int treeHeight, TreeConfiguration config) {
        // Defines the number of layers downwards will be filled with leaves starting at the top log
        return 14;
    }


    @Override
    protected void createFoliage(
    final WorldGenLevel level,
    final FoliagePlacer.FoliageSetter foliageSetter,
    final RandomSource random,
    final TreeConfiguration config,
    final int treeHeight,
    final FoliagePlacer.FoliageAttachment foliageAttachment,
    final int foliageHeight,
    final int leafRadius,
    final int offset
    ) {
        BlockPos topCenter = foliageAttachment.pos();
        for (int layer = 0; layer < 14; layer++) {
            BlockPos layerCenter = foliageAttachment.pos().below(layer);
            switch (layer) {
                case 0 -> { // Layer 1: Tree Top
                    placeLeaf(level, foliageSetter, config, layerCenter.above(), random);}
                case 1 -> { // Layer 2: Thin layer below tree top
                    placeThinCross(level, foliageSetter, config, layerCenter, random);}
                case 2 -> { // Layer 3: Blank Air Gap
                    }
                case 3 -> { // Layer 4: 1-block thin cross layer
                    placeThinCross(level, foliageSetter, config, layerCenter, random);}
                case 4 -> { // Layer 5: Blank Air Gap
                    }
                case 5, 6 -> { // Layers 6 & 7: THIRD BLOCK
                    placeMediumCross(level, foliageSetter, config, layerCenter, random);}
                case 7 -> { // Layer 8: Blank Air Gap
                    }
                case 8, 9, 10 -> { // Layers 9, 10, 11: FOURTH BLOCK
                    placeFullSquare(level, foliageSetter, config, layerCenter, random);}
                case 11 -> { // Layer 12: Blank Air Gap
                    }
                case 12, 13 -> { // Layers 13 & 14: BOTTOM BLOCK
                    placeFullSquare(level, foliageSetter, config, layerCenter, random);
                    }
            }
        }
    }
    // Small "+" around the log
    private void placeThinCross(WorldGenLevel lvl, FoliageSetter setter, TreeConfiguration cfg, BlockPos pos, RandomSource rand) {
        placeLeaf(lvl, setter, cfg, pos.north(), rand);
        placeLeaf(lvl, setter, cfg, pos.east(), rand);
        placeLeaf(lvl, setter, cfg, pos.south(), rand);
        placeLeaf(lvl, setter, cfg, pos.west(), rand);
    }
    // Medium "+" around the log with randomly filled corners
    private void placeMediumCross(WorldGenLevel lvl, FoliageSetter setter, TreeConfiguration cfg, BlockPos pos, RandomSource rand) {
        placeThinCross(lvl, setter, cfg, pos, rand);
        if (rand.nextBoolean()) placeLeaf(lvl, setter, cfg, pos.north().east(), rand);
        if (rand.nextBoolean()) placeLeaf(lvl, setter, cfg, pos.south().east(), rand);
        if (rand.nextBoolean()) placeLeaf(lvl, setter, cfg, pos.south().west(), rand);
        if (rand.nextBoolean()) placeLeaf(lvl, setter, cfg, pos.north().west(), rand);
    }
    // 3x3 block around log
    private void placeFullSquare(WorldGenLevel lvl, FoliageSetter setter, TreeConfiguration cfg, BlockPos pos, RandomSource rand) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                if (dx != 0 || dz != 0) {
                    placeLeaf(lvl, setter, cfg, pos.offset(dx, 0, dz), rand);
                }
            }
        }
    }

    private void placeLeaf(WorldGenLevel lvl, FoliageSetter setter, TreeConfiguration cfg, BlockPos pos, RandomSource rand) {
        setter.set(pos, cfg.foliageProvider.getState(lvl, rand, pos));
    }
    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean largeRoot) {
        return false;
    }
}