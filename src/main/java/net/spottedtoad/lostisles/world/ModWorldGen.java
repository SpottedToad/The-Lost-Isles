package net.spottedtoad.lostisles.world;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.spottedtoad.lostisles.TheLostIsles;

public class ModWorldGen {
    public static final TrunkPlacerType<ConiferTrunkPlacer> CONIFER_TRUNK_PLACER = registerTrunkPlacer("conifer_trunk_placer", ConiferTrunkPlacer.CODEC);
    public static final FoliagePlacerType<NullFoliagePlacer> NULL_FOLIAGE_PLACER = registerFoliagePlacer("null_foliage_placer", NullFoliagePlacer.CODEC);



    private static <P extends FoliagePlacer> FoliagePlacerType<P> registerFoliagePlacer(String id, MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, id), new FoliagePlacerType(codec));
    }
    private static <P extends TrunkPlacer> TrunkPlacerType<P> registerTrunkPlacer(String id, MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE, Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, id), new TrunkPlacerType<>(codec));
    }
    private static <P extends TreeDecorator> TreeDecoratorType<P> registerTreeDecorator(String id, MapCodec<P> codec) {
        return Registry.register(BuiltInRegistries.TREE_DECORATOR_TYPE, Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, id), new TreeDecoratorType(codec));
    }


    public static void registerModWorldGen() {
        TheLostIsles.LOGGER.info("Registering Mod World Gen for The Lost Isles");
    }
}
