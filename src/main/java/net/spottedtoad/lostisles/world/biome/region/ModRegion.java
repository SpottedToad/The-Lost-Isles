package net.spottedtoad.lostisles.world.biome.region;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.spottedtoad.lostisles.world.biome.ModBiomes;
import terrablender.api.Region;
import terrablender.api.RegionType;
import java.util.function.Consumer;

public class ModRegion extends Region {
    public ModRegion(Identifier name, RegionType type, int weight) {
        super(name, type, weight);
        }

        @Override
        public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        Climate.ParameterPoint PrimordialOceanPlacement = new Climate.ParameterPoint(
            Climate.Parameter.span(-0.15F, 0.55F), // Temperature level 3-4
            Climate.Parameter.span(-1.0F, 1.0F), // Humidity level 0-4
            Climate.Parameter.span(-1.2F, -0.455F), // Continentalness level deep ocean
            Climate.Parameter.span(0.05F, 0.45F), // Erosion level 4
            Climate.Parameter.span(0.0F, 0.0F), // Depth level surface biome
            Climate.Parameter.span(-0.2F, 0.2F), // Weirdness level mid
            0L); //Offset

            mapper.accept(Pair.of(PrimordialOceanPlacement, ModBiomes.DEEP_PRIMORDIAL_OCEAN));
    }
}