package net.spottedtoad.lostisles.block;

import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.spottedtoad.lostisles.TheLostIsles;

public class ModWoodTypes {
    public static final WoodType CONIFER = register("conifer");

    private static WoodType register(String name) {
        return WoodTypeBuilder.copyOf(WoodType.SPRUCE)
                .register(Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, name), BlockSetType.OAK);
    }

    public static void registerModWoodTypes() {
        TheLostIsles.LOGGER.info("Registering Mod Wood Types for The Lost Isles");
    }
}
