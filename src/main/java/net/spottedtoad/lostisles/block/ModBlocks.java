package net.spottedtoad.lostisles.block;

import com.terraformersmc.terraform.sign.api.block.TerraformSignBlockHelper;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
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
import net.spottedtoad.lostisles.world.trees.ModSaplingGenerators;

import java.util.function.Function;
import java.util.function.Supplier;

import static net.minecraft.world.level.block.Blocks.*;

public class ModBlocks {

    //Adds custom blocks that do not copy properties from vanilla blocks
    public static final Block TEMPLATE_BLOCK_1 = registerCustomBlock("template_block_1",
            properties -> new DropExperienceBlock(UniformInt.of(2, 5), properties
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)
            ));

    //Add saplings, potted saplings, and leaves
    public static final Block CONIFER_SAPLING = registerBlock("conifer_sapling",
            properties -> new SaplingBlock(ModSaplingGenerators.CONIFER, properties), () -> BlockBehaviour.Properties.ofFullCopy(SPRUCE_SAPLING));
    public static final Block POTTED_CONIFER_SAPLING = registerBlockWithoutItem("potted_conifer_sapling",
            properties -> new FlowerPotBlock(CONIFER_SAPLING, properties.noOcclusion().instabreak()));
    public static final Block CONIFER_LEAVES = registerBlock("conifer_leaves",
            properties -> new UntintedParticleLeavesBlock(
            0.01f, ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, 0x176C37), properties),
            () -> BlockBehaviour.Properties.ofFullCopy(SPRUCE_LEAVES));

    //Add logs, stripped logs, wood, stripped wood, and planks
    public static final Block CONIFER_LOG = registerBlock("conifer_log", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(SPRUCE_LOG));
    public static final Block STRIPPED_CONIFER_LOG = registerBlock("stripped_conifer_log", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(STRIPPED_SPRUCE_LOG));
    public static final Block CONIFER_WOOD = registerBlock("conifer_wood", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(SPRUCE_WOOD));
    public static final Block STRIPPED_CONIFER_WOOD = registerBlock("stripped_conifer_wood", RotatedPillarBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(STRIPPED_SPRUCE_WOOD));
    public static final Block CONIFER_PLANKS = registerBlock("conifer_planks", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(SPRUCE_PLANKS));

    //Adds stairs, slabs, fences, fence gates, pressure plates, buttons, doors, trapdoors, and shelves using custom registries
    public static final Block CONIFER_STAIRS = registerStairs("conifer_stairs", CONIFER_PLANKS);
    public static final Block CONIFER_SLAB = registerSlab("conifer_slab");
    public static final Block CONIFER_FENCE = registerFence("conifer_fence");
    public static final Block CONIFER_FENCE_GATE = registerFenceGate("conifer_fence_gate", ModWoodTypes.CONIFER);
    public static final Block CONIFER_PRESSURE_PLATE = registerPressurePlate("conifer_pressure_plate");
    public static final Block CONIFER_BUTTON = registerButton("conifer_button");
    public static final Block CONIFER_DOOR = registerDoor("conifer_door");
    public static final Block CONIFER_TRAPDOOR = registerTrapdoor("conifer_trapdoor");
    public static final Block CONIFER_SHELF = registerShelf("conifer_shelf");

    //Adds sign blocks using Wood API
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


    //Block registries
    private static Block registerSlab(String name) {return registerBlock(name, SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(SPRUCE_SLAB));}
    private static Block registerStairs(String name, Block baseBlock) {return registerBlock(name, properties -> new StairBlock(baseBlock.defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(SPRUCE_STAIRS));}
    private static Block registerFence(String name) {return registerBlock(name, FenceBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(SPRUCE_FENCE));}
    private static Block registerFenceGate(String name, WoodType woodType) {return registerBlock(name, properties -> new FenceGateBlock(woodType, properties), () -> BlockBehaviour.Properties.ofFullCopy(SPRUCE_FENCE_GATE));}
    private static Block registerPressurePlate(String name) {return registerBlock(name, properties -> new PressurePlateBlock(BlockSetType.SPRUCE, properties), () -> BlockBehaviour.Properties.ofFullCopy(SPRUCE_PRESSURE_PLATE));}
    private static Block registerButton(String name) {return registerBlock(name, properties -> new ButtonBlock(BlockSetType.SPRUCE, 30, properties), () -> BlockBehaviour.Properties.ofFullCopy(SPRUCE_BUTTON));}
    private static Block registerDoor(String name) {return registerBlock(name, properties -> new DoorBlock(BlockSetType.SPRUCE, properties), () -> BlockBehaviour.Properties.ofFullCopy(SPRUCE_DOOR));}
    private static Block registerTrapdoor(String name) {return registerBlock(name, properties -> new TrapDoorBlock(BlockSetType.SPRUCE, properties), () -> BlockBehaviour.Properties.ofFullCopy(SPRUCE_TRAPDOOR));}
    private static Block registerShelf(String name) {return registerBlock(name, ShelfBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(SPRUCE_SHELF));}

    private static Block registerBlock(String name,
        Function<BlockBehaviour.Properties, Block> factory,
        Supplier<BlockBehaviour.Properties> propertiesSupplier) {
        Identifier id = Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, name);
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, id);
        Block block = factory.apply(propertiesSupplier.get().setId(key));
        Block registeredBlock = Registry.register(BuiltInRegistries.BLOCK, id, block);
        registerBlockItem(name, registeredBlock);
        return registeredBlock;
    }
    private static Block registerCustomBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, name), toRegister);
    }
    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, name)))));
    }

    private static Block registerBlockWithoutItem(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, name))));
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(TheLostIsles.MOD_ID, name), toRegister);
    }

    //Logger and block entity validation
    public static void registerModBlocks() {
        TheLostIsles.LOGGER.info("Registering Mod Blocks for The Lost Isles");

        BlockEntityType.SHELF.addValidBlock(CONIFER_SHELF);
    }
}
