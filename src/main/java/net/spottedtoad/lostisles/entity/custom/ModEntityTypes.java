package net.spottedtoad.lostisles.entity.custom;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.spottedtoad.lostisles.TheLostIsles;

public class ModEntityTypes {
    public static final EntityType<CeratosaurusEntity> CERATOSAURUS = register(
            "ceratosaurus",
            EntityType.Builder.<CeratosaurusEntity>of(CeratosaurusEntity::new, MobCategory.MISC)
                    .sized(1f, 2f)
    );

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    public static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(CERATOSAURUS, CeratosaurusEntity.createCeratosaurusAttributes());
    }

    public static void registerModEntityTypes() {
        TheLostIsles.LOGGER.info("Registering Mod Entity Types for The Lost Isles");
    }
}