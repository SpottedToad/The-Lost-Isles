package net.spottedtoad.lostisles.tab;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.spottedtoad.lostisles.TheLostIsles;
import net.spottedtoad.lostisles.block.ModBlocks;
import net.spottedtoad.lostisles.item.ModItems;

public class ModTabs {

    public static final CreativeModeTab THE_LOST_ISLES_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, "the_lost_isles_tab"), FabricCreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.TEMPLATE_ITEM_1))
                    .title(Component.translatable("tab.lostisles.the_lost_isles_tab"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.CONIFER_LOG);
                        output.accept(ModBlocks.CONIFER_WOOD);
                        output.accept(ModBlocks.STRIPPED_CONIFER_LOG);
                        output.accept(ModBlocks.STRIPPED_CONIFER_WOOD);
                        output.accept(ModBlocks.CONIFER_PLANKS);
                        output.accept(ModBlocks.CONIFER_STAIRS);
                        output.accept(ModBlocks.CONIFER_SLAB);
                        output.accept(ModBlocks.CONIFER_FENCE);
                        output.accept(ModBlocks.CONIFER_FENCE_GATE);
                        output.accept(ModBlocks.CONIFER_SHELF);
                        output.accept(ModItems.CONIFER_SIGN);
                        output.accept(ModItems.CONIFER_HANGING_SIGN);
                        output.accept(ModBlocks.CONIFER_DOOR);
                        output.accept(ModBlocks.CONIFER_TRAPDOOR);
                        output.accept(ModBlocks.CONIFER_PRESSURE_PLATE);
                        output.accept(ModBlocks.CONIFER_BUTTON);
                        output.accept(ModItems.CONIFER_BOAT);
                        output.accept(ModItems.CONIFER_CHEST_BOAT);
                        output.accept(ModBlocks.CONIFER_LEAVES);
                        output.accept(ModBlocks.CONIFER_SAPLING);

                        output.accept(ModBlocks.TEMPLATE_BLOCK_1);
                        output.accept(ModItems.TEMPLATE_ITEM_1);
                        output.accept(ModItems.TEMPLATE_ITEM_2);

                    }).build());


    public static void registerModTabs() {
        TheLostIsles.LOGGER.info("Registering Mod Tabs for The Lost Isles");
    }
}
