package net.spottedtoad.lostisles.world.dimension;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.ChunkGeneratorStructureState;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.synth.ImprovedNoise;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.spottedtoad.lostisles.TheLostIsles;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModDimensionChunkGenerators extends ChunkGenerator {
    protected MapCodec<? extends ChunkGenerator> codec() {return CODEC;}
    public static void bootstrap(BootstrapContext<MapCodec<? extends ChunkGenerator>> context) {
        MapCodec<? extends ChunkGenerator> genericCodec = CODEC;
        context.register(
                ResourceKey.create(Registries.CHUNK_GENERATOR,
                Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, "primordial_generator")),
                genericCodec
        );
    }
    public BiomeSource getBiomeSource() {return this.biomeSource;}
    public static final MapCodec<ModDimensionChunkGenerators> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(BiomeSource.CODEC.fieldOf("biome_source")
                            .forGetter(ModDimensionChunkGenerators::getBiomeSource))
                    .apply(instance, ModDimensionChunkGenerators::new));
    public ModDimensionChunkGenerators(BiomeSource biomeSource) {
        super(biomeSource);
    }

    public int getGenDepth() {
        return 384;
    }

    public int getMinY() {
        return -64;
    }

    public int getSeaLevel() {
        return 63;
    }

    private ImprovedNoise verticalNoiseSampler;
    public ChunkGeneratorStructureState createState(
        HolderLookup<StructureSet> structureSets, RandomState randomState, long legacyLevelSeed) {
            if (this.verticalNoiseSampler == null) {
                RandomSource randomSource = RandomSource.create(legacyLevelSeed);
                this.verticalNoiseSampler = new ImprovedNoise(randomSource);
            }
        return super.createState(structureSets, randomState, legacyLevelSeed);
    }

    public CompletableFuture<ChunkAccess> fillFromNoise(Blender blender, RandomState randomState, StructureManager structureManager, ChunkAccess centerChunk) {
        if (this.verticalNoiseSampler == null) {this.verticalNoiseSampler = new ImprovedNoise(RandomSource.create(42L));}
        Climate.Sampler climateSampler = randomState.sampler();
        ChunkPos chunkPos = centerChunk.getPos();
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        BlockState stoneMaterial = Blocks.STONE.defaultBlockState();
        BlockState waterMaterial = Blocks.WATER.defaultBlockState();

        for (int x = 0; x < 16; x++) {
            int worldX = chunkPos.getMinBlockX() + x;
            for (int z = 0; z < 16; z++) {
                int worldZ = chunkPos.getMinBlockZ() + z;

                // Stored radius values
                int maxArchipelagoBoundary = 532;
                double shallowOceanBoundaryRadius = 50; //(modify to accept block length)
                double dropOffSlopeRate = 2.0;
                double maxBoundaryRadius = 100; // Blocks

                // Stored height values
                int beachBaseHeight = 5;
                int shallowOceanHeightMax = 55;
                int shallowOceanHeightMin = 41;
                int deepOceanFloorHeight = 33;
                int maxFlareHeight = 200;
                int currentFloorHeight = deepOceanFloorHeight;

                // Height noise values
                double flareHeightMultiplier = 0.55;
                double erosionIntensity = 1.12;

                // Horizontal noise values
                double horizontalWarpIntensity = 11.0;
                double horizontalNoiseFrequency = 0.039;

                // Slope rate values
                double flareExponent = 0.85;
                double maxNoiseFrequency = 0.017;
                double minNoiseFrequency = 0.0015;
                double frequencyChangeRate = 2.12;
                double shallowOceanMaxSink = 0.40;
                double dropOffSinkWorkspace = 0.60;

                // Distance from center logic
                double warpX = this.verticalNoiseSampler.noise((double) worldX * horizontalNoiseFrequency, 10.0, (double) worldZ * horizontalNoiseFrequency) * horizontalWarpIntensity;
                double warpZ = this.verticalNoiseSampler.noise((double) worldX * horizontalNoiseFrequency, 20.0, (double) worldZ * horizontalNoiseFrequency) * horizontalWarpIntensity;
                double warpedX = (double) worldX + warpX;
                double warpedZ = (double) worldZ + warpZ;
                double distanceFromCenter = Math.sqrt(warpedX * warpedX + warpedZ * warpedZ);
                double normalizedDist = distanceFromCenter / (double) maxArchipelagoBoundary;

                double extendedOceanBoundary = (double) maxArchipelagoBoundary + shallowOceanBoundaryRadius;
                double dropOffEndBoundary = extendedOceanBoundary + maxBoundaryRadius;

                // Terrain shape logic
                if (distanceFromCenter < dropOffEndBoundary) {
                    // Creates a smooth curving flare centered at (0, 0)
                    double rawFlare = (Math.cos(Math.PI * Math.min(1.0, normalizedDist)) + 1.0) / 2.0;
                    double beveledFlare = Math.pow(rawFlare, flareExponent);
                    // Create noise frequencies to generate dynamic terrain
                    double mountainNoise = this.verticalNoiseSampler.noise(warpedX * maxNoiseFrequency, 0.0, warpedZ * maxNoiseFrequency);
                    double plainsNoise = this.verticalNoiseSampler.noise(warpedX * minNoiseFrequency, 0.0, warpedZ * minNoiseFrequency);
                    double rawMountainMap = (mountainNoise + 1.0) / 2.0;
                    double rawPlainsMap = (plainsNoise + 1.0) / 2.0;
                    // Exponentially transition between the noise frequencies
                    double blendProgress = Math.pow(beveledFlare, frequencyChangeRate);
                    double combinedHeightmap = (rawPlainsMap * (1.0 - blendProgress)) + (rawMountainMap * blendProgress);
                    // Combine terrain parameters into cohesive whole
                    double erodedNoise = Math.pow(combinedHeightmap, erosionIntensity);
                    double polishedWeight = flareHeightMultiplier * (erodedNoise * beveledFlare);
                    // Below water terrain logic
                    int calculatedHeight = deepOceanFloorHeight + (int) ((maxFlareHeight - deepOceanFloorHeight) * polishedWeight);
                    int seaLevel = this.getSeaLevel();
                    if (calculatedHeight < seaLevel || distanceFromCenter > (double) maxArchipelagoBoundary) {
                        double oceanSinkFactor = 0.0;
                        if (distanceFromCenter > (double) maxArchipelagoBoundary) {
                            double oceanProgress = (distanceFromCenter - (double) maxArchipelagoBoundary) / (dropOffEndBoundary - (double) maxArchipelagoBoundary);
                            oceanSinkFactor = Math.pow(Math.min(1.0, oceanProgress), dropOffSlopeRate);
                        }
                        calculatedHeight = (int) (calculatedHeight * (1.0 - oceanSinkFactor) + (double) deepOceanFloorHeight * oceanSinkFactor);
                    }
                    currentFloorHeight = Math.max(deepOceanFloorHeight, calculatedHeight);
                }

            // Terrain and water placer
                for (int y = centerChunk.getMinY(); y < centerChunk.getMaxY(); y++) {
                    mutablePos.set(x, y, z);
                    // Fills the area below "currentFloorHeight" with stone
                    if (y <= currentFloorHeight) {
                        centerChunk.setBlockState(mutablePos, stoneMaterial, Block.UPDATE_NONE);
                    // Fills the area below sea level with water
                    } else if (y <= this.getSeaLevel()) {
                        centerChunk.setBlockState(mutablePos, waterMaterial, Block.UPDATE_NONE);
                    }
                }
            }
        }
        return CompletableFuture.completedFuture(centerChunk);
    }

    public void applyCarvers(WorldGenRegion region, long seed, RandomState randomState, BiomeManager biomeManager, StructureManager structureManager, ChunkAccess chunk) {
    }

    public void buildSurface(WorldGenRegion level, StructureManager structureManager, RandomState randomState, ChunkAccess protoChunk) {
    }

    public void spawnOriginalMobs(WorldGenRegion worldGenRegion) {
    }

    public int getBaseHeight(int x, int z, Heightmap.Types type, LevelHeightAccessor heightAccessor, RandomState randomState) {
        return 0;
    }

    public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor heightAccessor, RandomState randomState) {
        return new NoiseColumn(heightAccessor.getMinY(), new BlockState[0]);
    }

    public void addDebugScreenInfo(List<String> result, RandomState randomState, BlockPos feetPos) {
    }

    public static void registerModDimensionChunkGenerators() {
        TheLostIsles.LOGGER.info("Registering Mod Dimension Chunk Generators for The Lost Isles");
        Registry.register(
                BuiltInRegistries.CHUNK_GENERATOR,
                Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, "primordial_generator"),
                CODEC
        );
    }
}