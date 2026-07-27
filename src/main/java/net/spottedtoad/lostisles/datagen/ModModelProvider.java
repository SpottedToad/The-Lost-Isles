package net.spottedtoad.lostisles.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.spottedtoad.lostisles.block.ModBlocks;
import net.spottedtoad.lostisles.item.ModItems;

import static net.spottedtoad.lostisles.block.ModBlocks.*;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

        //Adds models for conifer variants
        blockModelGenerators.createAxisAlignedPillarBlock(CONIFER_LOG, TexturedModel.COLUMN);
        blockModelGenerators.createAxisAlignedPillarBlock(STRIPPED_CONIFER_LOG, TexturedModel.COLUMN);
        blockModelGenerators.createAxisAlignedPillarBlock(CONIFER_WOOD, TexturedModel.COLUMN);
        blockModelGenerators.createAxisAlignedPillarBlock(STRIPPED_CONIFER_WOOD, TexturedModel.COLUMN);
        blockModelGenerators.family(CONIFER_PLANKS)
                .stairs(CONIFER_STAIRS)
                .slab(CONIFER_SLAB)
                .fence(CONIFER_FENCE)
                .fenceGate(CONIFER_FENCE_GATE)
                .pressurePlate(CONIFER_PRESSURE_PLATE)
                .button(CONIFER_BUTTON);
        blockModelGenerators.createDoor(CONIFER_DOOR);
        blockModelGenerators.createTrapdoor(CONIFER_TRAPDOOR);
        blockModelGenerators.createHangingSign(CONIFER_PLANKS, CONIFER_HANGING_SIGN, CONIFER_WALL_HANGING_SIGN);
        blockModelGenerators.createShelf(CONIFER_SHELF, CONIFER_PLANKS);
        blockModelGenerators.createPlantWithDefaultItem(CONIFER_SAPLING, POTTED_CONIFER_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        blockModelGenerators.createTintedLeaves(CONIFER_LEAVES, TexturedModel.LEAVES, 0x2ba33a);


        blockModelGenerators.createTrivialCube(TEMPLATE_BLOCK_1);
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
