package net.spottedtoad.lostisles.world;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.spottedtoad.lostisles.block.ModBlocks;

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

        int branchCutoff = treeHeight - 2;
        int topCanopyStart = branchCutoff - 1;
        int baseTrunkHeight = 2 + random.nextInt(3) + random.nextInt(4);


        for (int i = 0; i < treeHeight; i++) {
            placeLog(level, trunkSetter, random, origin.above(i), config);
            if (i >= baseTrunkHeight && (i - baseTrunkHeight) % 3 == 0 && i < (branchCutoff)) {
                //Branch max length and size decrease over time logic
                int maxBranchLength = Math.max(3, treeHeight / 4);
                int currentLength = Math.max(1, maxBranchLength - ((i - 5) / 3));
                int diagonalLength = Math.max(1, currentLength - 1);

                //Straight Branches
                //North Branch
                for (int len = 0; len <= currentLength; len++) {
                    BlockPos branchPosNorth = origin.above(i).north(len);
                    if (len < currentLength) {
                        placeLog(level, trunkSetter, random, branchPosNorth, config, (state) -> state.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z));
                        // "+" shaped leaf pattern around north branch
                        trunkSetter.accept(branchPosNorth.east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        trunkSetter.accept(branchPosNorth.west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        trunkSetter.accept(branchPosNorth.above(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        trunkSetter.accept(branchPosNorth.below(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        // fill in random corners of the "+" shape around north branch
                        if (random.nextBoolean()) trunkSetter.accept(branchPosNorth.above().east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosNorth.above().west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosNorth.below().east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosNorth.below().west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                    } else {
                        BlockState horizontalWood = ModBlocks.CONIFER_WOOD.defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z);
                        // "+" and corner around final block
                        trunkSetter.accept(branchPosNorth, horizontalWood);
                        trunkSetter.accept(branchPosNorth.east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        trunkSetter.accept(branchPosNorth.west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        trunkSetter.accept(branchPosNorth.above(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        trunkSetter.accept(branchPosNorth.below(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        // fill in random corners of the "+" shape around north branch
                        if (random.nextBoolean()) trunkSetter.accept(branchPosNorth.above().east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosNorth.above().west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosNorth.below().east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosNorth.below().west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        // incomplete "+" shape outside of final branch that always has the center filled in
                        trunkSetter.accept(branchPosNorth.north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosNorth.north().east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosNorth.north().west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosNorth.north().above(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosNorth.north().below(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                    }
                }
                //East Branch
                for (int len = 0; len <= currentLength; len++) {
                    BlockPos branchPosEast = origin.above(i).east(len);
                    if (len < currentLength) {
                        placeLog(level, trunkSetter, random, branchPosEast, config, (state) -> state.setValue(RotatedPillarBlock.AXIS, Direction.Axis.X));
                        // "+" shaped leaf pattern around east branch
                        trunkSetter.accept(branchPosEast.north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        trunkSetter.accept(branchPosEast.south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        trunkSetter.accept(branchPosEast.above(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        trunkSetter.accept(branchPosEast.below(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        // fill in random corners of the "+" shape around east branch
                        if (random.nextBoolean()) trunkSetter.accept(branchPosEast.above().north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosEast.above().south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosEast.below().north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosEast.below().south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                    } else {
                        BlockState horizontalWood = ModBlocks.CONIFER_WOOD.defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.X);
                        // "+" and corner around final block
                        trunkSetter.accept(branchPosEast, horizontalWood);
                        trunkSetter.accept(branchPosEast.north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        trunkSetter.accept(branchPosEast.south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        trunkSetter.accept(branchPosEast.above(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        trunkSetter.accept(branchPosEast.below(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        // fill in random corners of the "+" shape around east branch
                        if (random.nextBoolean()) trunkSetter.accept(branchPosEast.above().north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosEast.above().south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosEast.below().north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosEast.below().south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        // incomplete "+" shape outside of final branch that always has the center filled in
                        trunkSetter.accept(branchPosEast.east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosEast.east().north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosEast.east().south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosEast.east().above(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosEast.east().below(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                    }
                }
                //South Branch
                for (int len = 0; len <= currentLength; len++) {
                    BlockPos branchPosSouth = origin.above(i).south(len);
                    if (len < currentLength) {
                        placeLog(level, trunkSetter, random, branchPosSouth, config, (state) -> state.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z));
                        // "+" shaped leaf pattern around east branch
                        trunkSetter.accept(branchPosSouth.east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        trunkSetter.accept(branchPosSouth.west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        trunkSetter.accept(branchPosSouth.above(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        trunkSetter.accept(branchPosSouth.below(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        // fill in random corners of the "+" shape around east branch
                        if (random.nextBoolean()) trunkSetter.accept(branchPosSouth.above().east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosSouth.above().west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosSouth.below().east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosSouth.below().west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                    } else {
                        BlockState horizontalWood = ModBlocks.CONIFER_WOOD.defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z);
                        // "+" and corner around final block
                        trunkSetter.accept(branchPosSouth, horizontalWood);
                        trunkSetter.accept(branchPosSouth.east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        trunkSetter.accept(branchPosSouth.west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        trunkSetter.accept(branchPosSouth.above(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        trunkSetter.accept(branchPosSouth.below(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        // fill in random corners of the "+" shape around east branch
                        if (random.nextBoolean()) trunkSetter.accept(branchPosSouth.above().east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosSouth.above().west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosSouth.below().east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosSouth.below().west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        // incomplete "+" shape outside of final branch that always has the center filled in
                        trunkSetter.accept(branchPosSouth.south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean())
                            trunkSetter.accept(branchPosSouth.south().east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean())
                            trunkSetter.accept(branchPosSouth.south().west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean())
                            trunkSetter.accept(branchPosSouth.south().above(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean())
                            trunkSetter.accept(branchPosSouth.south().below(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                    }
                }
                //West Branch
                for (int len = 0; len <= currentLength; len++) {
                    BlockPos branchPosWest = origin.above(i).west(len);
                    if (len < currentLength) {
                        placeLog(level, trunkSetter, random, branchPosWest, config, (state) -> state.setValue(RotatedPillarBlock.AXIS, Direction.Axis.X));
                        // "+" shaped leaf pattern around east branch
                        trunkSetter.accept(branchPosWest.north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        trunkSetter.accept(branchPosWest.south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        trunkSetter.accept(branchPosWest.above(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        trunkSetter.accept(branchPosWest.below(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        // fill in random corners of the "+" shape around east branch
                        if (random.nextBoolean()) trunkSetter.accept(branchPosWest.above().north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosWest.above().south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosWest.below().north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosWest.below().south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                    } else {
                        BlockState horizontalWood = ModBlocks.CONIFER_WOOD.defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.X);
                        // "+" and corner around final block
                        trunkSetter.accept(branchPosWest, horizontalWood);
                        trunkSetter.accept(branchPosWest.north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        trunkSetter.accept(branchPosWest.south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        trunkSetter.accept(branchPosWest.above(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        trunkSetter.accept(branchPosWest.below(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        // fill in random corners of the "+" shape around east branch
                        if (random.nextBoolean()) trunkSetter.accept(branchPosWest.above().north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosWest.above().south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosWest.below().north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosWest.below().south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        // incomplete "+" shape outside of final branch that always has the center filled in
                        trunkSetter.accept(branchPosWest.west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosWest.west().north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosWest.west().south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosWest.west().above(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        if (random.nextBoolean()) trunkSetter.accept(branchPosWest.west().below(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                    }
                }

                //Diagonal Branches
                if (diagonalLength > 1) {
                    //North East Branch
                    for (int len = 1; len <= diagonalLength; len++) {
                        BlockPos diagPos = origin.above(i).north(len).east(len);
                        trunkSetter.accept(diagPos, ModBlocks.CONIFER_WOOD.defaultBlockState());
                        if (len < diagonalLength) {
                            trunkSetter.accept(diagPos.above(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.below(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            // Incomplete corners around the initial leaves
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.above().north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.above().east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.above().south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.above().west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.below().north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.below().east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.below().south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.below().west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        } else {
                            // Fill in all of the blocks that directly touch the branch
                            trunkSetter.accept(diagPos.above(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.below(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            // Incomplete corners around the initial leaves
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.above().south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.above().west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.below().south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.below().west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        }
                    }
                    //South East Branch
                    for (int len = 1; len <= diagonalLength; len++) {
                        BlockPos diagPos = origin.above(i).south(len).east(len);
                        trunkSetter.accept(diagPos, ModBlocks.CONIFER_WOOD.defaultBlockState());
                        if (len < diagonalLength) {
                            trunkSetter.accept(diagPos.above(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.below(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            // Incomplete corners around the initial leaves
                            trunkSetter.accept(diagPos.above().north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.above().east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.above().south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.above().west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.below().north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.below().east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.below().south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.below().west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        } else {
                            // Fill in all of the blocks that directly touch the branch
                            trunkSetter.accept(diagPos.above(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.below(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            // Incomplete corners around the initial leaves
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.above().north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.above().west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.below().north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.below().west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        }
                    }
                    //South West Branch
                    for (int len = 1; len <= diagonalLength; len++) {
                        BlockPos diagPos = origin.above(i).south(len).west(len);
                        trunkSetter.accept(diagPos, ModBlocks.CONIFER_WOOD.defaultBlockState());
                        if (len < diagonalLength) {
                            trunkSetter.accept(diagPos.above(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.below(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            // Incomplete corners around the initial leaves
                            trunkSetter.accept(diagPos.above().north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.above().east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.above().south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.above().west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.below().north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.below().east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.below().south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.below().west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        } else {
                            // Fill in all of the blocks that directly touch the branch
                            trunkSetter.accept(diagPos.above(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.below(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            // Incomplete corners around the initial leaves
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.above().north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.above().east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.below().north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.below().east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        }
                    }
                    //North West Branch
                    for (int len = 1; len <= diagonalLength; len++) {
                        BlockPos diagPos = origin.above(i).north(len).west(len);
                        trunkSetter.accept(diagPos, ModBlocks.CONIFER_WOOD.defaultBlockState());
                        if (len < diagonalLength) {
                            trunkSetter.accept(diagPos.above(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.below(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            // Incomplete corners around the initial leaves
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.above().north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.above().east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.above().south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.above().west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.below().north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.below().east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.below().south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.below().west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        } else {
                            // Fill in all of the blocks that directly touch the branch
                            trunkSetter.accept(diagPos.above(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            trunkSetter.accept(diagPos.below(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            // Incomplete corners around the initial leaves
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.above().east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.above().south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.below().east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                            if (random.nextBoolean()) trunkSetter.accept(diagPos.below().south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                        }
                    }
                }
            }
        }

        for (int y = topCanopyStart; y < treeHeight; y++) {
            BlockPos spikePos = origin.above(y);
            if (y == treeHeight - 1) {
                trunkSetter.accept(spikePos.above(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                trunkSetter.accept(spikePos.north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                trunkSetter.accept(spikePos.east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                trunkSetter.accept(spikePos.south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                trunkSetter.accept(spikePos.west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
            }
            else {
                trunkSetter.accept(spikePos.north(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                trunkSetter.accept(spikePos.east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                trunkSetter.accept(spikePos.south(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                trunkSetter.accept(spikePos.west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                if (random.nextBoolean()) trunkSetter.accept(spikePos.north().east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                if (random.nextBoolean()) trunkSetter.accept(spikePos.south().east(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                if (random.nextBoolean()) trunkSetter.accept(spikePos.south().west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
                if (random.nextBoolean()) trunkSetter.accept(spikePos.north().west(), ModBlocks.CONIFER_LEAVES.defaultBlockState());
            }
        }

        foliageNodes.add(new FoliagePlacer.FoliageAttachment(origin.above(treeHeight), 0, false));
        return foliageNodes;
        }
}