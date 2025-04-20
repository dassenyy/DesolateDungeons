package dev.dassen.desolatedungeons.datagen;

import dev.dassen.desolatedungeons.block.ModBlocks;
import dev.dassen.desolatedungeons.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(registryLookup, recipeExporter) {
            @Override
            public void generate() {
                // Khopesh Group
                // Khopesh Handle
                createShaped(RecipeCategory.COMBAT, ModItems.KHOPESH_HANDLE)
                    .pattern(" g")
                    .pattern("g ")
                    .input('g', Items.GOLD_INGOT)
                    .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                    .offerTo(recipeExporter);

                // Wooden Khopesh
                createShaped(RecipeCategory.COMBAT, ModItems.WOODEN_KHOPESH)
                    .pattern("  m")
                    .pattern(" mm")
                    .pattern("h  ")
                    .input('h', Items.STICK)
                    .input('m', ItemTags.PLANKS)
                    .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                    .offerTo(recipeExporter);

                // Stone Khopesh
                createShaped(RecipeCategory.COMBAT, ModItems.STONE_KHOPESH)
                    .pattern("  m")
                    .pattern(" mm")
                    .pattern("h  ")
                    .input('h', Items.STICK)
                    .input('m', ItemTags.STONE_CRAFTING_MATERIALS)
                    .criterion(hasItem(Items.COBBLESTONE), conditionsFromTag(ItemTags.STONE_CRAFTING_MATERIALS))
                    .offerTo(recipeExporter);

                // Iron Khopesh
                createShaped(RecipeCategory.COMBAT, ModItems.IRON_KHOPESH)
                    .pattern("  m")
                    .pattern(" mm")
                    .pattern("h  ")
                    .input('h', ModItems.KHOPESH_HANDLE)
                    .input('m', Items.IRON_INGOT)
                    .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                    .offerTo(recipeExporter);

                // Golden Khopesh
                createShaped(RecipeCategory.COMBAT, ModItems.GOLDEN_KHOPESH)
                    .pattern("  m")
                    .pattern(" mm")
                    .pattern("h  ")
                    .input('h', ModItems.KHOPESH_HANDLE)
                    .input('m', Items.GOLD_INGOT)
                    .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                    .offerTo(recipeExporter);

                // Diamond Khopesh
                createShaped(RecipeCategory.COMBAT, ModItems.DIAMOND_KHOPESH)
                    .pattern("  m")
                    .pattern(" mm")
                    .pattern("h  ")
                    .input('h', ModItems.KHOPESH_HANDLE)
                    .input('m', Items.DIAMOND)
                    .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                    .offerTo(recipeExporter);

                // Netherite Khopesh
                offerNetheriteUpgradeRecipe(ModItems.DIAMOND_KHOPESH, RecipeCategory.COMBAT, ModItems.NETHERITE_KHOPESH);

                // Sandstone Bricks Group
                // Sandstone Bricks
                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SANDSTONE_BRICKS, 4)
                    .pattern("xx")
                    .pattern("xx")
                    .input('x', Blocks.SANDSTONE)
                    .criterion(hasItem(Blocks.SANDSTONE), conditionsFromItem(Blocks.SANDSTONE))
                    .offerTo(recipeExporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SANDSTONE_BRICKS, Blocks.SANDSTONE);

                // Sandstone Brick Stairs
                createStairsRecipe(ModBlocks.SANDSTONE_BRICK_STAIRS, Ingredient.ofItems(ModBlocks.SANDSTONE_BRICKS))
                    .criterion(hasItem(ModBlocks.SANDSTONE_BRICKS), conditionsFromItem(ModBlocks.SANDSTONE_BRICKS))
                    .offerTo(recipeExporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SANDSTONE_BRICK_STAIRS, ModBlocks.SANDSTONE_BRICKS);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SANDSTONE_BRICK_STAIRS, Blocks.SANDSTONE);

                // Sandstone Brick Slab
                createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SANDSTONE_BRICK_SLAB, Ingredient.ofItems(ModBlocks.SANDSTONE_BRICKS))
                    .criterion(hasItem(ModBlocks.SANDSTONE_BRICKS), conditionsFromItem(ModBlocks.SANDSTONE_BRICKS))
                    .offerTo(recipeExporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SANDSTONE_BRICK_SLAB, ModBlocks.SANDSTONE_BRICKS, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SANDSTONE_BRICK_SLAB, Blocks.SANDSTONE, 2);

                // Sandstone Brick Wall
                getWallRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SANDSTONE_BRICK_WALL, Ingredient.ofItems(ModBlocks.SANDSTONE_BRICKS))
                    .criterion(hasItem(ModBlocks.SANDSTONE_BRICKS), conditionsFromItem(ModBlocks.SANDSTONE_BRICKS))
                    .offerTo(recipeExporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SANDSTONE_BRICK_WALL, ModBlocks.SANDSTONE_BRICKS);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SANDSTONE_BRICK_WALL, Blocks.SANDSTONE);

                // Laterite Bricks Group
                // Laterite Bricks
                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LATERITE_BRICKS, 4)
                    .pattern("xx")
                    .pattern("xx")
                    .input('x', ModBlocks.LATERITE)
                    .criterion(hasItem(ModBlocks.LATERITE), conditionsFromItem(ModBlocks.LATERITE))
                    .offerTo(recipeExporter);

                // Laterite Brick Stairs
                createStairsRecipe(ModBlocks.LATERITE_BRICK_STAIRS, Ingredient.ofItems(ModBlocks.LATERITE_BRICKS))
                    .criterion(hasItem(ModBlocks.LATERITE_BRICKS), conditionsFromItem(ModBlocks.LATERITE_BRICKS))
                    .offerTo(recipeExporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LATERITE_BRICK_STAIRS, ModBlocks.LATERITE_BRICKS);

                // Laterite Brick Slab
                createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LATERITE_BRICK_SLAB, Ingredient.ofItems(ModBlocks.LATERITE_BRICKS))
                    .criterion(hasItem(ModBlocks.LATERITE_BRICKS), conditionsFromItem(ModBlocks.LATERITE_BRICKS))
                    .offerTo(recipeExporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LATERITE_BRICK_SLAB, ModBlocks.LATERITE_BRICKS, 2);

                // Laterite Brick Wall
                getWallRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LATERITE_BRICK_WALL, Ingredient.ofItems(ModBlocks.LATERITE_BRICKS))
                    .criterion(hasItem(ModBlocks.LATERITE_BRICKS), conditionsFromItem(ModBlocks.LATERITE_BRICKS))
                    .offerTo(recipeExporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LATERITE_BRICK_WALL, ModBlocks.LATERITE_BRICKS);

                // Polished Limestone Group
                // Polished Limestone
                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE, 4)
                    .pattern("xx")
                    .pattern("xx")
                    .input('x', ModBlocks.LIMESTONE)
                    .criterion(hasItem(ModBlocks.LIMESTONE), conditionsFromItem(ModBlocks.LIMESTONE))
                    .offerTo(recipeExporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE, ModBlocks.LIMESTONE);

                // Polished Limestone Stairs
                createStairsRecipe(ModBlocks.POLISHED_LIMESTONE_STAIRS, Ingredient.ofItems(ModBlocks.POLISHED_LIMESTONE))
                    .criterion(hasItem(ModBlocks.POLISHED_LIMESTONE), conditionsFromItem(ModBlocks.POLISHED_LIMESTONE))
                    .offerTo(recipeExporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE_STAIRS, ModBlocks.POLISHED_LIMESTONE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE_STAIRS, ModBlocks.LIMESTONE);

                // Polished Limestone Slab
                createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE_SLAB, Ingredient.ofItems(ModBlocks.POLISHED_LIMESTONE))
                    .criterion(hasItem(ModBlocks.POLISHED_LIMESTONE), conditionsFromItem(ModBlocks.POLISHED_LIMESTONE))
                    .offerTo(recipeExporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE_SLAB, ModBlocks.POLISHED_LIMESTONE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE_SLAB, ModBlocks.LIMESTONE, 2);

                // Polished Limestone Wall
                getWallRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE_WALL, Ingredient.ofItems(ModBlocks.POLISHED_LIMESTONE))
                    .criterion(hasItem(ModBlocks.POLISHED_LIMESTONE), conditionsFromItem(ModBlocks.POLISHED_LIMESTONE))
                    .offerTo(recipeExporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE_WALL, ModBlocks.POLISHED_LIMESTONE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LIMESTONE_WALL, ModBlocks.LIMESTONE);
            }
        };
    }

    @Override
    public String getName() {
        return "ModRecipeProvider";
    }
}
