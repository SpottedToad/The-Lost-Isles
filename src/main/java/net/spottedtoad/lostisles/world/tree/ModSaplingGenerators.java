package net.spottedtoad.lostisles.world.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.spottedtoad.lostisles.TheLostIsles;
import net.spottedtoad.lostisles.world.ModConfiguredFeatures;

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
