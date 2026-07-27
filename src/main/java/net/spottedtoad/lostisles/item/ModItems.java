package net.spottedtoad.lostisles.item;

import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.spottedtoad.lostisles.TheLostIsles;
import net.spottedtoad.lostisles.block.ModBlocks;

import java.util.function.Function;

public class ModItems {
    //Adds custom items
    public static final Item TEMPLATE_ITEM_1 = registerItem("template_item_1", Item::new);
    public static final Item TEMPLATE_ITEM_2 = registerItem("template_item_2", Item::new);

    //Adds sign items with WOOD API
    public static final SignItem CONIFER_SIGN = registerSignItem("conifer_sign",
            ModBlocks.CONIFER_SIGN, ModBlocks.CONIFER_WALL_SIGN);
    public static final HangingSignItem CONIFER_HANGING_SIGN = registerHangingSignItem("conifer_hanging_sign",
            ModBlocks.CONIFER_HANGING_SIGN, ModBlocks.CONIFER_WALL_HANGING_SIGN);

    //Adds boats with WOOD API
    public static BoatItem CONIFER_BOAT = registerBoatItem(TheLostIsles.CONIFER_BOATS_ID, false);
    public static BoatItem CONIFER_CHEST_BOAT = registerBoatItem(TheLostIsles.CONIFER_BOATS_ID, true);


    //Item registries
    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, name),
                function.apply((new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, name))))));
    }
    private static BoatItem registerBoatItem(Identifier id, boolean chest) {
        return TerraformBoatItemHelper.registerBoatItem(id, chest);
    }
    private static SignItem registerSignItem(String name, StandingSignBlock sign, WallSignBlock wallSign) {
        Identifier id = Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, name);
        SignItem item = new SignItem(sign, wallSign, new Item.Properties()
                .stacksTo(16)
                .setId(ResourceKey.create(Registries.ITEM, id))
                .useBlockDescriptionPrefix());
        Registry.register(BuiltInRegistries.ITEM, id, item);
        item.registerBlocks(Item.BY_BLOCK, item);
        return item;
    }
    private static HangingSignItem registerHangingSignItem(String name, CeilingHangingSignBlock sign, WallHangingSignBlock wallSign) {
        Identifier id = Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, name);
        HangingSignItem item = new HangingSignItem(sign, wallSign, new Item.Properties()
                .stacksTo(16)
                .setId(ResourceKey.create(Registries.ITEM, id))
                .useBlockDescriptionPrefix());
        Registry.register(BuiltInRegistries.ITEM, id, item);
        item.registerBlocks(Item.BY_BLOCK, item);
        return item;
    }

    //Logger
    public static void registerModItems() {
        TheLostIsles.LOGGER.info("Registering Mod Items for The Lost Isles");
    }
}
