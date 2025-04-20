package dev.dassen.desolatedungeons.block;

import dev.dassen.desolatedungeons.DesolateDungeons;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block SANDSTONE_BRICKS;
    public static final Block SANDSTONE_BRICK_STAIRS;
    public static final Block SANDSTONE_BRICK_SLAB;
    public static final Block SANDSTONE_BRICK_WALL;
    public static final Block LATERITE;
    public static final Block LATERITE_BRICKS;
    public static final Block LATERITE_BRICK_STAIRS;
    public static final Block LATERITE_BRICK_SLAB;
    public static final Block LATERITE_BRICK_WALL;
    public static final Block LIMESTONE;
    public static final Block POLISHED_LIMESTONE;
    public static final Block POLISHED_LIMESTONE_STAIRS;
    public static final Block POLISHED_LIMESTONE_SLAB;
    public static final Block POLISHED_LIMESTONE_WALL;
    public static final Block PERIDOTITE;

    static {
        SANDSTONE_BRICKS = registerBlock(
            "sandstone_bricks",
            new Block(
                AbstractBlock.Settings.create()
                    .strength(1.5F, 3.0F)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .mapColor(MapColor.PALE_YELLOW)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .registryKey(blockRegistryKeyOf("sandstone_bricks"))
            )
        );
        SANDSTONE_BRICK_STAIRS = registerBlock(
            "sandstone_brick_stairs",
            new StairsBlock(
                SANDSTONE_BRICKS.getDefaultState(),
                AbstractBlock.Settings.copy(SANDSTONE_BRICKS)
                    .registryKey(blockRegistryKeyOf("sandstone_brick_stairs"))
            )
        );
        SANDSTONE_BRICK_SLAB = registerBlock(
            "sandstone_brick_slab",
            new SlabBlock(
                AbstractBlock.Settings.copy(SANDSTONE_BRICKS)
                    .registryKey(blockRegistryKeyOf("sandstone_brick_slab"))
            )
        );
        SANDSTONE_BRICK_WALL = registerBlock(
            "sandstone_brick_wall",
            new WallBlock(
                AbstractBlock.Settings.copy(SANDSTONE_BRICKS)
                    .registryKey(blockRegistryKeyOf("sandstone_brick_wall"))
            )
        );
        LATERITE = registerBlock(
            "laterite",
            new Block(
                AbstractBlock.Settings.create()
                    .strength(0.6F)
                    .sounds(BlockSoundGroup.GRAVEL)
                    .mapColor(MapColor.TERRACOTTA_ORANGE)
                    .instrument(NoteBlockInstrument.FLUTE)
                    .registryKey(blockRegistryKeyOf("laterite"))
            )
        );
        LATERITE_BRICKS = registerBlock(
            "laterite_bricks",
            new Block(
                AbstractBlock.Settings.create()
                    .strength(1.5F, 3.0F)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .mapColor(MapColor.TERRACOTTA_RED)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .registryKey(blockRegistryKeyOf("laterite_bricks"))
            )
        );
        LATERITE_BRICK_STAIRS = registerBlock(
            "laterite_brick_stairs",
            new StairsBlock(
                LATERITE_BRICKS.getDefaultState(),
                AbstractBlock.Settings.copy(LATERITE_BRICKS)
                    .registryKey(blockRegistryKeyOf("laterite_brick_stairs"))
            )
        );
        LATERITE_BRICK_SLAB = registerBlock(
            "laterite_brick_slab",
            new SlabBlock(
                AbstractBlock.Settings.copy(LATERITE_BRICKS)
                    .registryKey(blockRegistryKeyOf("laterite_brick_slab"))
            )
        );
        LATERITE_BRICK_WALL = registerBlock(
            "laterite_brick_wall",
            new WallBlock(
                AbstractBlock.Settings.copy(LATERITE_BRICKS)
                    .solid()
                    .registryKey(blockRegistryKeyOf("laterite_brick_wall"))
            )
        );
        LIMESTONE = registerBlock(
            "limestone",
            new Block(
                AbstractBlock.Settings.create()
                    .strength(1.5F, 3.0F)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .mapColor(MapColor.TERRACOTTA_WHITE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .registryKey(blockRegistryKeyOf("limestone"))
            )
        );
        POLISHED_LIMESTONE = registerBlock(
            "polished_limestone",
            new Block(
                AbstractBlock.Settings.create()
                    .strength(1.5F, 3.0F)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .mapColor(MapColor.TERRACOTTA_WHITE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .registryKey(blockRegistryKeyOf("polished_limestone"))
            )
        );
        POLISHED_LIMESTONE_STAIRS = registerBlock(
            "polished_limestone_stairs",
            new StairsBlock(
                POLISHED_LIMESTONE.getDefaultState(),
                AbstractBlock.Settings.copy(POLISHED_LIMESTONE)
                    .registryKey(blockRegistryKeyOf("polished_limestone_stairs"))
            )
        );
        POLISHED_LIMESTONE_SLAB = registerBlock(
            "polished_limestone_slab",
            new SlabBlock(
                AbstractBlock.Settings.copy(POLISHED_LIMESTONE)
                    .registryKey(blockRegistryKeyOf("polished_limestone_slab"))
            )
        );
        POLISHED_LIMESTONE_WALL = registerBlock(
            "polished_limestone_wall",
            new WallBlock(
                AbstractBlock.Settings.copy(POLISHED_LIMESTONE)
                    .registryKey(blockRegistryKeyOf("polished_limestone_wall"))
            )
        );
        PERIDOTITE = registerBlock(
            "peridotite",
            new Block(
                AbstractBlock.Settings.create()
                    .strength(1.5F, 6.0F)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .mapColor(MapColor.DARK_GREEN)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .registryKey(blockRegistryKeyOf("peridotite"))
            )
        );
    }

    public static void register() {
        DesolateDungeons.LOGGER.info("Registering Blocks for " + DesolateDungeons.MOD_ID);
    }

    private static Block registerBlock(String pathName, Block block) {
        registerBlockItem(pathName, block);
        return Registry.register(Registries.BLOCK, Identifier.of(DesolateDungeons.MOD_ID, pathName), block);
    }

    private static void registerBlockItem(String path, Block block) {
        Registry.register(
            Registries.ITEM,
            Identifier.of(DesolateDungeons.MOD_ID, path),
            new BlockItem(block, new Item.Settings().registryKey(itemRegistryKeyOf(path)))
        );
    }

    private static RegistryKey<Block> blockRegistryKeyOf(String path) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(DesolateDungeons.MOD_ID, path));
    }

    private static RegistryKey<Item> itemRegistryKeyOf(String path) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(DesolateDungeons.MOD_ID, path));
    }
}
