package net.spottedtoad.lostisles.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.spottedtoad.lostisles.TheLostIsles;
import net.spottedtoad.lostisles.world.ModConfiguredFeatures;
import net.spottedtoad.lostisles.world.biome.ModBiomes;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModDynamicRegistryProvider extends FabricDynamicRegistryProvider {
    public ModDynamicRegistryProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
        registryBuilder.add(Registries.BIOME, ModBiomes::bootstrap);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        addAll(entries, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE), TheLostIsles.MOD_ID);
        addAll(entries, registries.lookupOrThrow(Registries.BIOME), TheLostIsles.MOD_ID);
    }

    @SuppressWarnings("UnusedReturnValue")
    public <T> List<Holder<T>> addAll(Entries entries, HolderLookup.RegistryLookup<T> registry, String modId) {
        return registry.listElementIds()
                .filter(registryKey -> registryKey.identifier().getNamespace().equals(modId))
                .map(key -> entries.add(registry, key))
                .toList();
    }

    @Override
    public String getName() {
        return "The Lost Isles Dynamic Registries";
    }
}
