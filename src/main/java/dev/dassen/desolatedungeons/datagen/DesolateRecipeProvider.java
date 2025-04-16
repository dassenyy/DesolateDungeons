package dev.dassen.desolatedungeons.datagen;

import dev.dassen.desolatedungeons.block.DesolateBlocks;
import dev.dassen.desolatedungeons.item.DesolateItems;
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

public class DesolateRecipeProvider extends FabricRecipeProvider {
    public DesolateRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(registryLookup, recipeExporter) {
            @Override
            public void generate() {
                // Khopesh Group
                // Khopesh Handle
                createShaped(RecipeCategory.COMBAT, DesolateItems.KHOPESH_HANDLE)
                    .pattern(" g")
                    .pattern("g ")
                    .input('g', Items.GOLD_INGOT)
                    .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                    .offerTo(recipeExporter);

                // Wooden Khopesh
                createShaped(RecipeCategory.COMBAT, DesolateItems.WOODEN_KHOPESH)
                    .pattern("  m")
                    .pattern(" mm")
                    .pattern("h  ")
                    .input('h', Items.STICK)
                    .input('m', ItemTags.PLANKS)
                    .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                    .offerTo(recipeExporter);

                // Stone Khopesh
                createShaped(RecipeCategory.COMBAT, DesolateItems.STONE_KHOPESH)
                    .pattern("  m")
                    .pattern(" mm")
                    .pattern("h  ")
                    .input('h', Items.STICK)
                    .input('m', ItemTags.STONE_CRAFTING_MATERIALS)
                    .criterion(hasItem(Items.COBBLESTONE), conditionsFromTag(ItemTags.STONE_CRAFTING_MATERIALS))
                    .offerTo(recipeExporter);

                // Iron Khopesh
                createShaped(RecipeCategory.COMBAT, DesolateItems.IRON_KHOPESH)
                    .pattern("  m")
                    .pattern(" mm")
                    .pattern("h  ")
                    .input('h', DesolateItems.KHOPESH_HANDLE)
                    .input('m', Items.IRON_INGOT)
                    .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                    .offerTo(recipeExporter);

                // Golden Khopesh
                createShaped(RecipeCategory.COMBAT, DesolateItems.GOLDEN_KHOPESH)
                    .pattern("  m")
                    .pattern(" mm")
                    .pattern("h  ")
                    .input('h', DesolateItems.KHOPESH_HANDLE)
                    .input('m', Items.GOLD_INGOT)
                    .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                    .offerTo(recipeExporter);

                // Diamond Khopesh
                createShaped(RecipeCategory.COMBAT, DesolateItems.DIAMOND_KHOPESH)
                    .pattern("  m")
                    .pattern(" mm")
                    .pattern("h  ")
                    .input('h', DesolateItems.KHOPESH_HANDLE)
                    .input('m', Items.DIAMOND)
                    .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                    .offerTo(recipeExporter);

                // Netherite Khopesh
                offerNetheriteUpgradeRecipe(DesolateItems.DIAMOND_KHOPESH, RecipeCategory.COMBAT, DesolateItems.NETHERITE_KHOPESH);

                // Sandstone Bricks Group
                // Sandstone Bricks
                createShaped(RecipeCategory.BUILDING_BLOCKS, DesolateBlocks.SANDSTONE_BRICKS, 4)
                    .pattern("xx")
                    .pattern("xx")
                    .input('x', Blocks.SANDSTONE)
                    .criterion(hasItem(Blocks.SANDSTONE), conditionsFromItem(Blocks.SANDSTONE))
                    .offerTo(recipeExporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICKS, Blocks.SANDSTONE);

                // Sandstone Brick Stairs
                createStairsRecipe(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_STAIRS, Ingredient.ofItems(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICKS))
                    .criterion(hasItem(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICKS), conditionsFromItem(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICKS))
                    .offerTo(recipeExporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_STAIRS, dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICKS);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_STAIRS, Blocks.SANDSTONE);

                // Sandstone Brick Slab
                createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_SLAB, Ingredient.ofItems(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICKS))
                    .criterion(hasItem(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICKS), conditionsFromItem(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICKS))
                    .offerTo(recipeExporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_SLAB, dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICKS, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_SLAB, Blocks.SANDSTONE, 2);

                // Sandstone Brick Wall
                getWallRecipe(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_WALL, Ingredient.ofItems(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICKS))
                    .criterion(hasItem(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICKS), conditionsFromItem(dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICKS))
                    .offerTo(recipeExporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_WALL, dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICKS);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.SANDSTONE_BRICK_WALL, Blocks.SANDSTONE);

                // Laterite Bricks Group
                // Laterite Bricks
                createShaped(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICKS, 4)
                    .pattern("xx")
                    .pattern("xx")
                    .input('x', dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE)
                    .criterion(hasItem(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE), conditionsFromItem(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE))
                    .offerTo(recipeExporter);

                // Laterite Brick Stairs
                createStairsRecipe(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_STAIRS, Ingredient.ofItems(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICKS))
                    .criterion(hasItem(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICKS), conditionsFromItem(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICKS))
                    .offerTo(recipeExporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_STAIRS, dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICKS);

                // Laterite Brick Slab
                createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_SLAB, Ingredient.ofItems(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICKS))
                    .criterion(hasItem(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICKS), conditionsFromItem(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICKS))
                    .offerTo(recipeExporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_SLAB, dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICKS, 2);

                // Laterite Brick Wall
                getWallRecipe(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_WALL, Ingredient.ofItems(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICKS))
                    .criterion(hasItem(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICKS), conditionsFromItem(dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICKS))
                    .offerTo(recipeExporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICK_WALL, dev.dassen.desolatedungeons.block.DesolateBlocks.LATERITE_BRICKS);

                // Polished Limestone Group
                // Polished Limestone
                createShaped(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE, 4)
                    .pattern("xx")
                    .pattern("xx")
                    .input('x', dev.dassen.desolatedungeons.block.DesolateBlocks.LIMESTONE)
                    .criterion(hasItem(dev.dassen.desolatedungeons.block.DesolateBlocks.LIMESTONE), conditionsFromItem(dev.dassen.desolatedungeons.block.DesolateBlocks.LIMESTONE))
                    .offerTo(recipeExporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE, dev.dassen.desolatedungeons.block.DesolateBlocks.LIMESTONE);

                // Polished Limestone Stairs
                createStairsRecipe(dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_STAIRS, Ingredient.ofItems(dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE))
                    .criterion(hasItem(dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE), conditionsFromItem(dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE))
                    .offerTo(recipeExporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_STAIRS, dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_STAIRS, dev.dassen.desolatedungeons.block.DesolateBlocks.LIMESTONE);

                // Polished Limestone Slab
                createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_SLAB, Ingredient.ofItems(dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE))
                    .criterion(hasItem(dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE), conditionsFromItem(dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE))
                    .offerTo(recipeExporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_SLAB, dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE, 2);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_SLAB, dev.dassen.desolatedungeons.block.DesolateBlocks.LIMESTONE, 2);

                // Polished Limestone Wall
                getWallRecipe(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_WALL, Ingredient.ofItems(dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE))
                    .criterion(hasItem(dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE), conditionsFromItem(dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE))
                    .offerTo(recipeExporter);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_WALL, dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE);
                offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, dev.dassen.desolatedungeons.block.DesolateBlocks.POLISHED_LIMESTONE_WALL, dev.dassen.desolatedungeons.block.DesolateBlocks.LIMESTONE);
            }
        };
    }

    @Override
    public String getName() {
        return "DesolateRecipeProvider";
    }
}
