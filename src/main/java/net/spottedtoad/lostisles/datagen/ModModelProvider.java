package net.spottedtoad.lostisles.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.spottedtoad.lostisles.block.ModBlocks;
import net.spottedtoad.lostisles.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(ModBlocks.TEMPLATE_BLOCK_1);

        blockModelGenerators.createAxisAlignedPillarBlock(ModBlocks.CONIFER_LOG, TexturedModel.COLUMN);
        blockModelGenerators.createAxisAlignedPillarBlock(ModBlocks.STRIPPED_CONIFER_LOG, TexturedModel.COLUMN);
        blockModelGenerators.createAxisAlignedPillarBlock(ModBlocks.CONIFER_WOOD, TexturedModel.COLUMN);
        blockModelGenerators.createAxisAlignedPillarBlock(ModBlocks.STRIPPED_CONIFER_WOOD, TexturedModel.COLUMN);

        blockModelGenerators.family(ModBlocks.CONIFER_PLANKS)
                .stairs(ModBlocks.CONIFER_STAIRS)
                .slab(ModBlocks.CONIFER_SLAB)
                .fence(ModBlocks.CONIFER_FENCE)
                .fenceGate(ModBlocks.CONIFER_FENCE_GATE)
                .pressurePlate(ModBlocks.CONIFER_PRESSURE_PLATE)
                .button(ModBlocks.CONIFER_BUTTON);

        blockModelGenerators.createDoor(ModBlocks.CONIFER_DOOR);
        blockModelGenerators.createTrapdoor(ModBlocks.CONIFER_TRAPDOOR);
        blockModelGenerators.createShelf(ModBlocks.CONIFER_SHELF, ModBlocks.STRIPPED_CONIFER_LOG);

        blockModelGenerators.createHangingSign(
                ModBlocks.CONIFER_PLANKS,
                ModBlocks.CONIFER_HANGING_SIGN,
                ModBlocks.CONIFER_WALL_HANGING_SIGN
        );
    }


    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.TEMPLATE_ITEM_1, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.TEMPLATE_ITEM_2, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.CONIFER_SIGN, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.CONIFER_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.CONIFER_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
    }
}
