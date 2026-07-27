package net.spottedtoad.lostisles.world;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.spottedtoad.lostisles.TheLostIsles;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final TreeGrower CONIFER = new TreeGrower(
            "lostisles_conifer",
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.CONIFER_TREE),
            Optional.empty()
    );

    public static void registerModSaplingGenerators() {
        TheLostIsles.LOGGER.info("Registering Mod Sapling Generators for The Lost Isles");
    }
}
