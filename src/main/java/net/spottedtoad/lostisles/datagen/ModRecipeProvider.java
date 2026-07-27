package net.spottedtoad.lostisles.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.ItemLike;
import net.spottedtoad.lostisles.block.ModBlocks;
import net.spottedtoad.lostisles.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.spottedtoad.lostisles.block.ModBlocks.*;
import static net.spottedtoad.lostisles.item.ModItems.CONIFER_BOAT;
import static net.spottedtoad.lostisles.item.ModItems.CONIFER_CHEST_BOAT;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {

           woodFromLogs(STRIPPED_CONIFER_WOOD, STRIPPED_CONIFER_LOG);
           woodFromLogs(CONIFER_WOOD, CONIFER_LOG);
            List<ItemLike> CONIFER_LOGS = List.of(ModBlocks.CONIFER_LOG, ModBlocks.STRIPPED_CONIFER_LOG, CONIFER_WOOD, ModBlocks.STRIPPED_CONIFER_WOOD);
                for (ItemLike item : CONIFER_LOGS) {
                    simpleCookingRecipe(getItemName(item), SmeltingRecipe::new, 200, item, Items.CHARCOAL, 0.15f);
                    shapeless(RecipeCategory.BUILDING_BLOCKS, CONIFER_PLANKS, 1)
                            .requires(item).unlockedBy(getHasName(item), has(CONIFER_PLANKS)).group("conifer")
                            .save(output, "conifer_planks_from_" + getItemName(item));
                    }
           stairBuilder(CONIFER_STAIRS, Ingredient.of(ModBlocks.CONIFER_PLANKS))
                    .unlockedBy("has_conifer_planks", this.has(ModBlocks.CONIFER_PLANKS)).save(output);
           slab(RecipeCategory.BUILDING_BLOCKS, CONIFER_SLAB, CONIFER_PLANKS);
           fenceBuilder(CONIFER_FENCE, Ingredient.of(ModBlocks.CONIFER_PLANKS))
                    .unlockedBy("has_conifer_planks", this.has(ModBlocks.CONIFER_PLANKS)).save(output);
           fenceGateBuilder(CONIFER_FENCE_GATE, Ingredient.of(ModBlocks.CONIFER_PLANKS))
                    .unlockedBy("has_conifer_planks", this.has(ModBlocks.CONIFER_PLANKS)).save(output);
           doorBuilder(CONIFER_DOOR, Ingredient.of(ModBlocks.CONIFER_PLANKS))
                    .unlockedBy("has_conifer_planks", this.has(ModBlocks.CONIFER_PLANKS)).save(output);
           trapdoorBuilder(CONIFER_TRAPDOOR, Ingredient.of(ModBlocks.CONIFER_PLANKS))
                    .unlockedBy("has_conifer_planks", this.has(ModBlocks.CONIFER_PLANKS)).save(output);
           pressurePlate(CONIFER_PRESSURE_PLATE, CONIFER_PLANKS);
           buttonBuilder(CONIFER_BUTTON, Ingredient.of(ModBlocks.CONIFER_PLANKS))
                   .unlockedBy("has_conifer_planks", this.has(ModBlocks.CONIFER_PLANKS)).save(output);
           signBuilder(ModItems.CONIFER_SIGN, Ingredient.of(ModBlocks.CONIFER_PLANKS))
                   .unlockedBy("has_conifer_planks", this.has(ModBlocks.CONIFER_PLANKS)).save(output);
           hangingSign(ModItems.CONIFER_HANGING_SIGN, CONIFER_PLANKS);
            shelf(CONIFER_SHELF, STRIPPED_CONIFER_LOG);
            woodenBoat(CONIFER_BOAT, CONIFER_PLANKS);
            chestBoat(CONIFER_CHEST_BOAT, CONIFER_BOAT);
            }
        };
    }

    @Override
    public String getName() {
        return "The Lost Isles Recipes";
    }
}
