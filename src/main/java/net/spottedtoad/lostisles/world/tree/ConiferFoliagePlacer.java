package net.spottedtoad.lostisles.world.tree;

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
import net.spottedtoad.lostisles.world.gen.ModWorldGen;

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
        BlockPos topCenter = foliageAttachment.pos().below();
        for (int layer = 0; layer <= 14; layer++) {
            BlockPos layerCenter = topCenter.below(layer);
            switch (layer) {
                // TOP BLOCK
                case 0 -> { // Layer 0: Tree top
                    placeFoliageTop(level, foliageSetter, config, layerCenter, random);
                    }
                case 1 -> { // Layer 1: Very thin layer
                    placeThinB(level, foliageSetter, config, layerCenter, random);
                    }
                // FIRST BLOCK
                case 2 -> { // Layer 2: Wide layer
                    placeWideA(level, foliageSetter, config, layerCenter, random);
                    }
                case 3 -> { // Layer 3: Very thin layer
                    placeThinB(level, foliageSetter, config, layerCenter, random);
                    }
                // SECOND BLOCK
                case 4 -> { // Layer 4: Wide layer
                    placeWideA(level, foliageSetter, config, layerCenter, random);
                    }
                case 5 -> { // Layer 5: Very wide layer
                    placeWideB(level, foliageSetter, config, layerCenter, random);
                    }
                case 6 -> { // Layer 6: Wide layer
                    placeWideA(level, foliageSetter, config, layerCenter, random);
                    }
                case 7 -> { // Layer 7: Thin layer
                    placeThinA(level, foliageSetter, config, layerCenter, random);
                    }
                // THIRD BLOCK
                case 8, 9 -> { // Layers 8 & 9: Very, very wide layers
                    placeWideC(level, foliageSetter, config, layerCenter, random);
                }
                case 10 -> { // Layer 10: Wide layer
                    placeWideA(level, foliageSetter, config, layerCenter, random);
                }
                //FOURTH BLOCK
                case 11 -> { // Layer 11: Very wide layer
                    placeWideB(level, foliageSetter, config, layerCenter, random);
                    }
                case 12 -> { // Layers 12: Very thin layer
                    placeThinB(level, foliageSetter, config, layerCenter, random);
                    }
                // BOTTOM BLOCK
                case 13 -> { // Wide layer
                    placeWideA(level, foliageSetter, config, layerCenter, random);
                }
                case 14 -> { // Very, Very thin layer
                    placeFoliageBottom(level, foliageSetter, config, layerCenter, random);
                }
            }
        }
    }


    //Tree bottom
    private void placeFoliageBottom(WorldGenLevel lvl, FoliageSetter setter, TreeConfiguration cfg, BlockPos pos, RandomSource rand) {
        // Inner "+"  at 25% chance per block
        if (rand.nextFloat() < 0.25f) placeLeaf(lvl, setter, cfg, pos.north(), rand);
        if (rand.nextFloat() < 0.25f) placeLeaf(lvl, setter, cfg, pos.east(), rand);
        if (rand.nextFloat() < 0.25f) placeLeaf(lvl, setter, cfg, pos.south(), rand);
        if (rand.nextFloat() < 0.25f) placeLeaf(lvl, setter, cfg, pos.west(), rand);
    }
    // Very Thin Layer
    private void placeThinB(WorldGenLevel lvl, FoliageSetter setter, TreeConfiguration cfg, BlockPos pos, RandomSource rand) {
        // Inner "+"
        placeLeaf(lvl, setter, cfg, pos.north(), rand);
        placeLeaf(lvl, setter, cfg, pos.east(), rand);
        placeLeaf(lvl, setter, cfg, pos.south(), rand);
        placeLeaf(lvl, setter, cfg, pos.west(), rand);
        // Inner "+" corners at 25% chance per block
        if (rand.nextFloat() < 0.25f) placeLeaf(lvl, setter, cfg, pos.north().east(), rand);
        if (rand.nextFloat() < 0.25f) placeLeaf(lvl, setter, cfg, pos.south().east(), rand);
        if (rand.nextFloat() < 0.25f) placeLeaf(lvl, setter, cfg, pos.south().west(), rand);
        if (rand.nextFloat() < 0.25f) placeLeaf(lvl, setter, cfg, pos.north().west(), rand);
    }
    // Thin Layer
    private void placeThinA(WorldGenLevel lvl, FoliageSetter setter, TreeConfiguration cfg, BlockPos pos, RandomSource rand) {
        // Inner "+"
        placeLeaf(lvl, setter, cfg, pos.north(), rand);
        placeLeaf(lvl, setter, cfg, pos.east(), rand);
        placeLeaf(lvl, setter, cfg, pos.south(), rand);
        placeLeaf(lvl, setter, cfg, pos.west(), rand);
        // Filled "+" corners at 90% chance per block
        if (rand.nextFloat() < 0.90f) placeLeaf(lvl, setter, cfg, pos.north().east(), rand);
        if (rand.nextFloat() < 0.90f) placeLeaf(lvl, setter, cfg, pos.south().east(), rand);
        if (rand.nextFloat() < 0.90f) placeLeaf(lvl, setter, cfg, pos.south().west(), rand);
        if (rand.nextFloat() < 0.90f) placeLeaf(lvl, setter, cfg, pos.north().west(), rand);
        // Outer "+" at 75% chance per block
        if (rand.nextFloat() < 0.75f) placeLeaf(lvl, setter, cfg, pos.north(2), rand);
        if (rand.nextFloat() < 0.75f) placeLeaf(lvl, setter, cfg, pos.east(2), rand);
        if (rand.nextFloat() < 0.75f) placeLeaf(lvl, setter, cfg, pos.south(2), rand);
        if (rand.nextFloat() < 0.75f) placeLeaf(lvl, setter, cfg, pos.west(2), rand);
    }
    //Tree top
    private void placeFoliageTop(WorldGenLevel lvl, FoliageSetter setter, TreeConfiguration cfg, BlockPos pos, RandomSource rand) {
        // Lower "+" and two blocks above
        placeLeaf(lvl, setter, cfg, pos.north(), rand);
        placeLeaf(lvl, setter, cfg, pos.east(), rand);
        placeLeaf(lvl, setter, cfg, pos.south(), rand);
        placeLeaf(lvl, setter, cfg, pos.west(), rand);
        placeLeaf(lvl, setter, cfg, pos.above(), rand);
        placeLeaf(lvl, setter, cfg, pos.above(2), rand);
        // Upper "+" sides at 25% chance per block
        if (rand.nextFloat() < 0.25f) placeLeaf(lvl, setter, cfg, pos.above().north(), rand);
        if (rand.nextFloat() < 0.25f) placeLeaf(lvl, setter, cfg, pos.above().east(), rand);
        if (rand.nextFloat() < 0.25f) placeLeaf(lvl, setter, cfg, pos.above().south(), rand);
        if (rand.nextFloat() < 0.25f) placeLeaf(lvl, setter, cfg, pos.above().west(), rand);
    }
    // Wide Layer
    private void placeWideA(WorldGenLevel lvl, FoliageSetter setter, TreeConfiguration cfg, BlockPos pos, RandomSource rand) {
        // Inner "+"
        placeLeaf(lvl, setter, cfg, pos.north(), rand);
        placeLeaf(lvl, setter, cfg, pos.east(), rand);
        placeLeaf(lvl, setter, cfg, pos.south(), rand);
        placeLeaf(lvl, setter, cfg, pos.west(), rand);
        // Filled "+" corners at 75% chance per block
        if (rand.nextFloat() < 0.75f) placeLeaf(lvl, setter, cfg, pos.north().east(), rand);
        if (rand.nextFloat() < 0.75f) placeLeaf(lvl, setter, cfg, pos.south().east(), rand);
        if (rand.nextFloat() < 0.75f) placeLeaf(lvl, setter, cfg, pos.south().west(), rand);
        if (rand.nextFloat() < 0.75f) placeLeaf(lvl, setter, cfg, pos.north().west(), rand);
        // Outer "+" at 90% chance per block
        if (rand.nextFloat() < 0.90f) placeLeaf(lvl, setter, cfg, pos.north(2), rand);
        if (rand.nextFloat() < 0.90f) placeLeaf(lvl, setter, cfg, pos.east(2), rand);
        if (rand.nextFloat() < 0.90f) placeLeaf(lvl, setter, cfg, pos.south(2), rand);
        if (rand.nextFloat() < 0.90f) placeLeaf(lvl, setter, cfg, pos.west(2), rand);
        // Filled outer "+" inner corners at 12% chance per block
        if (rand.nextFloat() < 0.12f) placeLeaf(lvl, setter, cfg, pos.north(2).east(), rand);
        if (rand.nextFloat() < 0.12f) placeLeaf(lvl, setter, cfg, pos.north(2).west(), rand);
        if (rand.nextFloat() < 0.12f) placeLeaf(lvl, setter, cfg, pos.east(2).north(), rand);
        if (rand.nextFloat() < 0.12f) placeLeaf(lvl, setter, cfg, pos.east(2).south(), rand);
        if (rand.nextFloat() < 0.12f) placeLeaf(lvl, setter, cfg, pos.south(2).east(), rand);
        if (rand.nextFloat() < 0.12f) placeLeaf(lvl, setter, cfg, pos.south(2).west(), rand);
        if (rand.nextFloat() < 0.12f) placeLeaf(lvl, setter, cfg, pos.west(2).south(), rand);
        if (rand.nextFloat() < 0.12f) placeLeaf(lvl, setter, cfg, pos.west(2).north(), rand);
    }
    // Very Wide Layer
    private void placeWideB(WorldGenLevel lvl, FoliageSetter setter, TreeConfiguration cfg, BlockPos pos, RandomSource rand) {
        // Inner "+"
        placeLeaf(lvl, setter, cfg, pos.north(), rand);
        placeLeaf(lvl, setter, cfg, pos.east(), rand);
        placeLeaf(lvl, setter, cfg, pos.south(), rand);
        placeLeaf(lvl, setter, cfg, pos.west(), rand);
        // Filled inner "+" corners
        placeLeaf(lvl, setter, cfg, pos.north().east(), rand);
        placeLeaf(lvl, setter, cfg, pos.south().east(), rand);
        placeLeaf(lvl, setter, cfg, pos.south().west(), rand);
        placeLeaf(lvl, setter, cfg, pos.north().west(), rand);
        // Outer "+"
        placeLeaf(lvl, setter, cfg, pos.north(2), rand);
        placeLeaf(lvl, setter, cfg, pos.east(2), rand);
        placeLeaf(lvl, setter, cfg, pos.south(2), rand);
        placeLeaf(lvl, setter, cfg, pos.west(2), rand);
        // Filled outer "+" inner corners at 80% chance per block
        if (rand.nextFloat() < 0.80f) placeLeaf(lvl, setter, cfg, pos.north(2).east(), rand);
        if (rand.nextFloat() < 0.80f) placeLeaf(lvl, setter, cfg, pos.north(2).west(), rand);
        if (rand.nextFloat() < 0.80f) placeLeaf(lvl, setter, cfg, pos.east(2).north(), rand);
        if (rand.nextFloat() < 0.80f) placeLeaf(lvl, setter, cfg, pos.east(2).south(), rand);
        if (rand.nextFloat() < 0.80f) placeLeaf(lvl, setter, cfg, pos.south(2).east(), rand);
        if (rand.nextFloat() < 0.80f) placeLeaf(lvl, setter, cfg, pos.south(2).west(), rand);
        if (rand.nextFloat() < 0.80f) placeLeaf(lvl, setter, cfg, pos.west(2).south(), rand);
        if (rand.nextFloat() < 0.80f) placeLeaf(lvl, setter, cfg, pos.west(2).north(), rand);
    }
    // Very, Very Wide Layer
    private void placeWideC(WorldGenLevel lvl, FoliageSetter setter, TreeConfiguration cfg, BlockPos pos, RandomSource rand) {
        // Inner "+"
        placeLeaf(lvl, setter, cfg, pos.north(), rand);
        placeLeaf(lvl, setter, cfg, pos.east(), rand);
        placeLeaf(lvl, setter, cfg, pos.south(), rand);
        placeLeaf(lvl, setter, cfg, pos.west(), rand);
        // Filled inner "+" corners
        placeLeaf(lvl, setter, cfg, pos.north().east(), rand);
        placeLeaf(lvl, setter, cfg, pos.south().east(), rand);
        placeLeaf(lvl, setter, cfg, pos.south().west(), rand);
        placeLeaf(lvl, setter, cfg, pos.north().west(), rand);
        // Outer "+"
        placeLeaf(lvl, setter, cfg, pos.north(2), rand);
        placeLeaf(lvl, setter, cfg, pos.east(2), rand);
        placeLeaf(lvl, setter, cfg, pos.south(2), rand);
        placeLeaf(lvl, setter, cfg, pos.west(2), rand);
        // Filled outer "+" inner corners
        placeLeaf(lvl, setter, cfg, pos.north(2).east(), rand);
        placeLeaf(lvl, setter, cfg, pos.north(2).west(), rand);
        placeLeaf(lvl, setter, cfg, pos.east(2).north(), rand);
        placeLeaf(lvl, setter, cfg, pos.east(2).south(), rand);
        placeLeaf(lvl, setter, cfg, pos.south(2).east(), rand);
        placeLeaf(lvl, setter, cfg, pos.south(2).west(), rand);
        placeLeaf(lvl, setter, cfg, pos.west(2).south(), rand);
        placeLeaf(lvl, setter, cfg, pos.west(2).north(), rand);
        // Outermost "+" at 50% chance per block
        if (rand.nextFloat() < 0.50f) placeLeaf(lvl, setter, cfg, pos.north(3), rand);
        if (rand.nextFloat() < 0.50f) placeLeaf(lvl, setter, cfg, pos.east(3), rand);
        if (rand.nextFloat() < 0.50f) placeLeaf(lvl, setter, cfg, pos.south(3), rand);
        if (rand.nextFloat() < 0.50f) placeLeaf(lvl, setter, cfg, pos.west(3), rand);
        // Outermost "+" inner corners at 50% chance per block
        if (rand.nextFloat() < 0.50f) placeLeaf(lvl, setter, cfg, pos.north(3).east(), rand);
        if (rand.nextFloat() < 0.50f) placeLeaf(lvl, setter, cfg, pos.south(3).east(), rand);
        if (rand.nextFloat() < 0.50f) placeLeaf(lvl, setter, cfg, pos.south(3).west(), rand);
        if (rand.nextFloat() < 0.50f) placeLeaf(lvl, setter, cfg, pos.north(3).west(), rand);
        // Outermost "+" outer corners at 50% chance per block
        if (rand.nextFloat() < 0.50f) placeLeaf(lvl, setter, cfg, pos.north(2).east(2), rand);
        if (rand.nextFloat() < 0.50f) placeLeaf(lvl, setter, cfg, pos.south(2).east(2), rand);
        if (rand.nextFloat() < 0.50f) placeLeaf(lvl, setter, cfg, pos.south(2).west(2), rand);
        if (rand.nextFloat() < 0.50f) placeLeaf(lvl, setter, cfg, pos.north(2).west(2), rand);
    }
    private void placeLeaf(WorldGenLevel lvl, FoliageSetter setter, TreeConfiguration cfg, BlockPos pos, RandomSource rand) {
        setter.set(pos, cfg.foliageProvider.getState(lvl, rand, pos));
    }
    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean largeRoot) {
        return false;
    }
}