package net.spottedtoad.lostisles.block;

import com.terraformersmc.terraform.sign.api.block.TerraformSignBlockHelper;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.spottedtoad.lostisles.TheLostIsles;

import java.util.function.Function;

public class ModBlocks {

    public static final Block TEMPLATE_BLOCK_1 = registerBlock("template_block_1",
            properties -> new DropExperienceBlock(UniformInt.of(2, 5), properties
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)
            ));


    public static final Block CONIFER_LOG = registerBlock("conifer_log",
            properties -> new RotatedPillarBlock(properties
                    .strength(2f, 3f).sound(SoundType.WOOD)));
    public static final Block STRIPPED_CONIFER_LOG = registerBlock("stripped_conifer_log",
            properties -> new RotatedPillarBlock(properties
                    .strength(2f, 3f).sound(SoundType.WOOD)));
    public static final Block CONIFER_WOOD = registerBlock("conifer_wood",
            properties -> new RotatedPillarBlock(properties
                    .strength(2f, 3f).sound(SoundType.WOOD)));
    public static final Block STRIPPED_CONIFER_WOOD = registerBlock("stripped_conifer_wood",
            properties -> new RotatedPillarBlock(properties
                    .strength(2f, 3f).sound(SoundType.WOOD)));

    public static final Block CONIFER_PLANKS = registerBlock("conifer_planks",
            properties -> new RotatedPillarBlock(properties
                    .strength(2f, 3f).sound(SoundType.WOOD)));
    public static final Block CONIFER_STAIRS = registerBlock("conifer_stairs",
            properties -> new StairBlock(CONIFER_PLANKS.defaultBlockState(), properties
                    .strength(2f, 3f).requiresCorrectToolForDrops().sound(SoundType.WOOD)));
    public static final Block CONIFER_SLAB = registerBlock("conifer_slab",
            properties -> new SlabBlock(properties
                    .strength(2f, 3f).sound(SoundType.WOOD)));

    public static final Block CONIFER_FENCE = registerBlock("conifer_fence",
            properties -> new FenceBlock(properties
                    .strength(2f, 3f).sound(SoundType.WOOD)));
    public static final Block CONIFER_FENCE_GATE = registerBlock("conifer_fence_gate",
            properties -> new FenceGateBlock(WoodType.SPRUCE, properties
                    .strength(2f, 3f).sound(SoundType.WOOD)));

    public static final Block CONIFER_PRESSURE_PLATE = registerBlock("conifer_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.SPRUCE, properties
                    .noCollision().strength(0.5f).sound(SoundType.WOOD)));
    public static final Block CONIFER_BUTTON = registerBlock("conifer_button",
            properties -> new ButtonBlock(BlockSetType.SPRUCE, 30, properties
                    .noCollision().strength(0.5f).sound(SoundType.WOOD)));

    public static final Block CONIFER_DOOR = registerBlock("conifer_door",
            properties -> new DoorBlock(BlockSetType.SPRUCE, properties
                    .noOcclusion().strength(2f, 3f).sound(SoundType.WOOD)));
    public static final Block CONIFER_TRAPDOOR = registerBlock("conifer_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.SPRUCE, properties
                    .noOcclusion().strength(2f, 3f).sound(SoundType.WOOD)));

    public static final Block CONIFER_SHELF = registerBlock("conifer_shelf",
            properties -> new ShelfBlock(properties
                    .strength(2f, 3f).sound(SoundType.WOOD)));

    public static final BlockSetType CONIFER = BlockSetTypeBuilder.copyOf(BlockSetType.SPRUCE)
            .register(Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, "conifer"));
    public static final WoodType CONIFER_TYPE = WoodTypeBuilder.copyOf(WoodType.SPRUCE)
            .register(Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, "conifer"), CONIFER);

    public static final StandingSignBlock CONIFER_SIGN = TerraformSignBlockHelper.registerSignBlock(
            Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, "conifer_sign"),
            properties -> new StandingSignBlock(CONIFER_TYPE, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SIGN));
    public static final WallSignBlock CONIFER_WALL_SIGN = TerraformSignBlockHelper.registerSignBlock(
            Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, "conifer_wall_sign"),
            properties -> new WallSignBlock(CONIFER_TYPE, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SIGN));
    public static final CeilingHangingSignBlock CONIFER_HANGING_SIGN = TerraformSignBlockHelper.registerSignBlock(
            Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, "conifer_hanging_sign"),
            properties -> new CeilingHangingSignBlock(CONIFER_TYPE, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SIGN));
    public static final WallHangingSignBlock CONIFER_WALL_HANGING_SIGN = TerraformSignBlockHelper.registerSignBlock(
            Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, "conifer_wall_hanging_sign"),
            properties -> new WallHangingSignBlock(CONIFER_TYPE, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SIGN));



    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        TheLostIsles.LOGGER.info("Registering Mod Blocks for The Lost Isles");

        BlockEntityType.SHELF.addValidBlock(CONIFER_SHELF);
    }
}
