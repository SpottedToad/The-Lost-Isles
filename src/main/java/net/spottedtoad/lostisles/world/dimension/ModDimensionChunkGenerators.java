package net.spottedtoad.lostisles.world.dimension;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
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

    public ModDimensionChunkGenerators(BiomeSource biomeSource) {super(biomeSource);}
    public BiomeSource getBiomeSource() {return this.biomeSource;}
    public static final MapCodec<ModDimensionChunkGenerators> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(BiomeSource.CODEC.fieldOf("biome_source")
                    .forGetter(ModDimensionChunkGenerators::getBiomeSource))
                    .apply(instance, ModDimensionChunkGenerators::new));

    public int getGenDepth() {
        return 384;
    }

    public int getMinY() {
        return -64;
    }

    public int getSeaLevel() {
        return 63;
    }

    public CompletableFuture<ChunkAccess> fillFromNoise(Blender blender, RandomState randomState, StructureManager structureManager, ChunkAccess centerChunk) {
        ChunkPos chunkPos = centerChunk.getPos();
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        BlockState stoneMaterial = Blocks.STONE.defaultBlockState();
        BlockState waterMaterial = Blocks.WATER.defaultBlockState();
        // Provides perlin map for random terrain generation
        NormalNoise islandNoiseSampler = randomState.getOrCreateNoise(Noises.CALCITE);

        for (int x = 0; x < 16; x++) {
            int worldX = chunkPos.getMinBlockX() + x;
            for (int z = 0; z < 16; z++) {
                int worldZ = chunkPos.getMinBlockZ() + z;
                // Calculates distance from origin (0, 0)
                double distanceFromCenter = Math.sqrt((double) worldX * worldX + (double) worldZ * worldZ);

                // Stored distance values
                int maxArchipelagoBoundary = 500;
                int shallowOceanRadius = 350;

                // Stored height values
                int maxTerrainHeight = 96;
                int deepOceanFloorHeight = 30;
                int shallowOceanFloorHeight = 54;
                int currentFloorHeight = deepOceanFloorHeight;

                // Horizontal amplitude modifiers
                double macroHorizontalNoiseIntensity = 0.012;
                double microHorizontalNoiseIntensity = 0.06;

                // Elevation modifiers
                double islandHeightNoiseIntensity = 1.5;
                double archipelagoDepthModifier = -0.15;

                // Noise multipliers
                double macroNoiseMultiplier = 2.0;
                double microNoiseMultiplier = 0.5;

                // Generation Logic
                if (distanceFromCenter < maxArchipelagoBoundary) {
                    // Creates a parabolic curve based on distance from the origin
                    double normalizedDist = distanceFromCenter / (double) maxArchipelagoBoundary;
                    double beveledFlare = 1.0 - (normalizedDist * normalizedDist);
                    // Applies perlin noise to warp the beveled flare into a more dynamic shape
                    double noiseYAxisMix = ((double) worldX + (double) worldZ) * 0.01;
                    double macroNoise = islandNoiseSampler.getValue(worldX * macroHorizontalNoiseIntensity, noiseYAxisMix, worldZ * macroHorizontalNoiseIntensity) * macroNoiseMultiplier;
                    double microNoise = islandNoiseSampler.getValue(worldX * microHorizontalNoiseIntensity, noiseYAxisMix, worldZ * microHorizontalNoiseIntensity) * microNoiseMultiplier;
                    // Sinks archipelago deeper into the water
                    double terrainWeight = beveledFlare + macroNoise + microNoise + archipelagoDepthModifier;
                    if (terrainWeight > 0.0) {
                        // Apply height modification to terrain
                        double normalizedNoise = macroNoise / macroNoiseMultiplier;
                        double gradientEnvelope = (0.3 + normalizedNoise) * beveledFlare;
                        gradientEnvelope = Math.max(0.0, Math.min(1.0, gradientEnvelope));
                        double scaledWeight = gradientEnvelope * islandHeightNoiseIntensity;
                        // Scale elevation change more dramatically when generated terrain is above water
                        int peakHeight = shallowOceanFloorHeight + (int) ((maxTerrainHeight - shallowOceanFloorHeight) * scaledWeight);
                        currentFloorHeight = Math.min(maxTerrainHeight, peakHeight);
                    } else {
                        if (distanceFromCenter < shallowOceanRadius) {
                            // Create a shallow ocean within a specified radius
                            currentFloorHeight = shallowOceanFloorHeight + (int) (microNoise / 2);
                        } else {
                            // Creates a steep slope towards deep ocean
                            double slopeRange = (double) (maxArchipelagoBoundary - shallowOceanRadius);
                            double slopeProgress = (distanceFromCenter - shallowOceanRadius) / slopeRange;
                            // Creates a deep ocean that stretches outwards
                            currentFloorHeight = shallowOceanFloorHeight - (int) ((shallowOceanFloorHeight - deepOceanFloorHeight) * slopeProgress);
                        }
                    }
                }

            // Empty Ocean surrounding the Primordial Ocean Basin
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