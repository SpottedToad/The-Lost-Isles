package net.spottedtoad.lostisles.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.spottedtoad.lostisles.TheLostIsles;

import java.util.function.Function;

import static net.minecraft.world.item.Items.COAL;

public class ModItems {
    public static final Item TEMPLATE_ITEM_1 = registerItem("template_item_1", Item::new);
    public static final Item TEMPLATE_ITEM_2 = registerItem("template_item_2", Item::new);



    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, name),
                function.apply((new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, name))))));
    }

    public static void registerModItems() {
        TheLostIsles.LOGGER.info("Registering Mod Items for The Lost Isles");

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.insertBefore(COAL, TEMPLATE_ITEM_1);
            output.insertAfter(TEMPLATE_ITEM_1, TEMPLATE_ITEM_2);
        });

    }
}
