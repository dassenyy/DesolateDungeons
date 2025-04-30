package dev.dassen.desolatedungeons.item;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.entity.ModEntities;
import dev.dassen.desolatedungeons.item.custom.AncientTotemItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final Item SCARAB_BEETLE;
    public static final Item ANCIENT_TOTEM;
    public static final Item KHOPESH_HANDLE;
    public static final Item WOODEN_KHOPESH;
    public static final Item STONE_KHOPESH;
    public static final Item IRON_KHOPESH;
    public static final Item GOLDEN_KHOPESH;
    public static final Item DIAMOND_KHOPESH;
    public static final Item NETHERITE_KHOPESH;
    public static final Item SCARAB_BEETLE_SPAWN_EGG;

    static {
        SCARAB_BEETLE = registerItem(
            "scarab_beetle",
            new Item(new Item.Settings().registryKey(itemRegistryKeyOf("scarab_beetle")))
        );
        ANCIENT_TOTEM = registerItem(
            "ancient_totem",
            new AncientTotemItem(new Item.Settings().rarity(Rarity.RARE).maxCount(1).registryKey(itemRegistryKeyOf("ancient_totem")))
        );
        KHOPESH_HANDLE = registerItem(
            "khopesh_handle",
            new Item(new Item.Settings().registryKey(itemRegistryKeyOf("khopesh_handle")))
        );
        WOODEN_KHOPESH = registerItem(
            "wooden_khopesh",
            new SwordItem(ToolMaterial.WOOD, 4, -2.6f, new Item.Settings().registryKey(itemRegistryKeyOf("wooden_khopesh")))
        );
        STONE_KHOPESH = registerItem(
            "stone_khopesh",
            new SwordItem(ToolMaterial.STONE, 5, -2.8f, new Item.Settings().registryKey(itemRegistryKeyOf("stone_khopesh")))
        );
        IRON_KHOPESH = registerItem(
            "iron_khopesh",
            new SwordItem(ToolMaterial.IRON, 4, -2.5f, new Item.Settings().registryKey(itemRegistryKeyOf("iron_khopesh")))
        );
        GOLDEN_KHOPESH = registerItem(
            "golden_khopesh",
            new SwordItem(ToolMaterial.GOLD, 5, -2.7f, new Item.Settings().registryKey(itemRegistryKeyOf("golden_khopesh")))
        );
        DIAMOND_KHOPESH = registerItem(
            "diamond_khopesh",
            new SwordItem(ToolMaterial.DIAMOND, 4, -2.5f, new Item.Settings().registryKey(itemRegistryKeyOf("diamond_khopesh")))
        );
        NETHERITE_KHOPESH = registerItem(
            "netherite_khopesh",
            new SwordItem(ToolMaterial.NETHERITE, 4, -2.5f, new Item.Settings().registryKey(itemRegistryKeyOf("netherite_khopesh")))
        );
        SCARAB_BEETLE_SPAWN_EGG = registerItem(
            "scarab_beetle_spawn_egg",
            new SpawnEggItem(ModEntities.SCARAB_BEETLE, new Item.Settings().registryKey(itemRegistryKeyOf("scarab_beetle_spawn_egg")))
        );
    }

    public static void register() {
        DesolateDungeons.LOGGER.info("Registering Items for " + DesolateDungeons.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.addBefore(Items.TRIDENT, WOODEN_KHOPESH);
            entries.addBefore(Items.TRIDENT, STONE_KHOPESH);
            entries.addBefore(Items.TRIDENT, IRON_KHOPESH);
            entries.addBefore(Items.TRIDENT, GOLDEN_KHOPESH);
            entries.addBefore(Items.TRIDENT, DIAMOND_KHOPESH);
            entries.addBefore(Items.TRIDENT, NETHERITE_KHOPESH);
        });
    }

    private static Item registerItem(String path, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(DesolateDungeons.MOD_ID, path), item);
    }

    private static RegistryKey<Item> itemRegistryKeyOf(String path) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(DesolateDungeons.MOD_ID, path));
    }
}
