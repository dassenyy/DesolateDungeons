package dev.dassen.desolatedungeons.datagen;

import dev.dassen.desolatedungeons.item.DesolateItems;
import dev.dassen.desolatedungeons.registry.key.DesolateLootTableKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantWithLevelsLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.function.SetDamageLootFunction;
import net.minecraft.loot.function.SetPotionLootFunction;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class DesolateChestLootTableProvider extends SimpleFabricLootTableProvider {
    private final CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture;

    public DesolateChestLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
        super(output, registryLookupFuture, LootContextTypes.CHEST);
        this.registryLookupFuture = registryLookupFuture;
    }

    // https://misode.github.io/loot-table/
    // https://minecraft.wiki/w/Item_modifier#Function_types
    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> lootTableBiConsumer) {
        // The registry lookup is needed so registry entries can be fetched.
        // These are required by various loot function builders, like the SetPotionLootFunction for example.
        registryLookupFuture.thenAccept(registryLookup -> {
            lootTableBiConsumer.accept(
                DesolateLootTableKeys.SANDSWEPT_RUINS_COMMON,
                buildSandsweptRuinsCommon(registryLookup)
            );

            lootTableBiConsumer.accept(
                DesolateLootTableKeys.SANDSWEPT_RUINS_RARE,
                buildSandsweptRuinsRare(registryLookup)
            );
        }).join();
    }

    private LootTable.Builder buildSandsweptRuinsCommon(RegistryWrapper.WrapperLookup registryLookup) {
        return LootTable.builder()
            .pool(
                LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(4.0f, 6.0f))
                    .with(
                        ItemEntry.builder(Items.GOLD_NUGGET)
                            .weight(3)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(16, 0.75f)))
                    )
                    .with(
                        ItemEntry.builder(Items.GOLD_INGOT)
                            .weight(3)
                    )
                    .with(
                        ItemEntry.builder(Items.ROTTEN_FLESH)
                            .weight(2)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(7, 0.75f)))
                    )
                    .with(
                        ItemEntry.builder(Items.BONE)
                            .weight(2)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(7, 0.75f)))
                    )
                    .with(
                        ItemEntry.builder(Items.BONE_MEAL)
                            .weight(2)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(7, 0.75f)))
                    )
                    .with(
                        ItemEntry.builder(Items.STRING)
                            .weight(2)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(12, 0.75f)))
                    )
                    .with(
                        ItemEntry.builder(Items.STICK)
                            .weight(2)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(12, 0.75f)))
                    )
                    .with(
                        ItemEntry.builder(Items.ARROW)
                            .weight(2)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(12, 0.75f)))
                    )
                    .with(
                        ItemEntry.builder(Items.POTION)
                            .weight(1)
                            .apply(SetPotionLootFunction.builder(
                                registryLookup
                                    .getOrThrow(RegistryKeys.POTION)
                                    .getOrThrow(RegistryKey.of(RegistryKeys.POTION, Identifier.ofVanilla("swiftness")))
                            ))
                    )
            );
    }

    private LootTable.Builder buildSandsweptRuinsRare(RegistryWrapper.WrapperLookup registryLookup) {
        return LootTable.builder()
            .pool(
                LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(6.0f, 8.0f))
                    .with(
                        ItemEntry.builder(Items.LAPIS_LAZULI)
                            .weight(12)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(16, 0.75f)))
                    )
                    .with(
                        ItemEntry.builder(Items.GOLD_INGOT)
                            .weight(12)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(12, 0.75f)))
                    )
                    .with(
                        ItemEntry.builder(Items.TIPPED_ARROW)
                            .weight(12)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(20, 0.75f)))
                            .apply(SetPotionLootFunction.builder(
                                registryLookup
                                    .getOrThrow(RegistryKeys.POTION)
                                    .getOrThrow(RegistryKey.of(RegistryKeys.POTION, Identifier.ofVanilla("poison")))
                            ))
                    )
                    .with(
                        ItemEntry.builder(Items.LEATHER_HELMET)
                            .weight(8)
                            .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.5f, 0.8f)))
                            .apply(EnchantWithLevelsLootFunction.builder(registryLookup, UniformLootNumberProvider.create(10.0f, 20.0f)))
                    )
                    .with(
                        ItemEntry.builder(Items.LEATHER_CHESTPLATE)
                            .weight(8)
                            .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.5f, 0.8f)))
                            .apply(EnchantWithLevelsLootFunction.builder(registryLookup, UniformLootNumberProvider.create(10.0f, 20.0f)))
                    )
                    .with(
                        ItemEntry.builder(Items.LEATHER_LEGGINGS)
                            .weight(8)
                            .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.5f, 0.8f)))
                            .apply(EnchantWithLevelsLootFunction.builder(registryLookup, UniformLootNumberProvider.create(10.0f, 20.0f)))
                    )
                    .with(
                        ItemEntry.builder(Items.LEATHER_BOOTS)
                            .weight(8)
                            .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.5f, 0.8f)))
                            .apply(EnchantWithLevelsLootFunction.builder(registryLookup, UniformLootNumberProvider.create(10.0f, 20.0f)))
                    )
                    .with(
                        ItemEntry.builder(DesolateItems.IRON_KHOPESH)
                            .weight(8)
                            .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.5f, 0.8f)))
                            .apply(EnchantWithLevelsLootFunction.builder(registryLookup, UniformLootNumberProvider.create(10.0f, 20.0f)))
                    )
                    .with(
                        ItemEntry.builder(Items.IRON_SHOVEL)
                            .weight(8)
                            .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.7f, 0.9f)))
                            .apply(EnchantWithLevelsLootFunction.builder(registryLookup, UniformLootNumberProvider.create(10.0f, 20.0f)))
                    )
                    .with(
                        ItemEntry.builder(Items.BOW)
                            .weight(8)
                            .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.7f, 0.9f)))
                            .apply(EnchantWithLevelsLootFunction.builder(registryLookup, UniformLootNumberProvider.create(10.0f, 20.0f)))
                    )
                    .with(
                        ItemEntry.builder(Items.BREAD)
                            .weight(6)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(8, 0.75f)))
                    )
                    .with(
                        ItemEntry.builder(Items.COOKIE)
                            .weight(6)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(8, 0.75f)))
                    )
                    .with(
                        ItemEntry.builder(Items.RABBIT_STEW)
                            .weight(4)
                    )
                    .with(
                        ItemEntry.builder(Items.RABBIT_HIDE)
                            .weight(4)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(5, 0.75f)))
                    )
                    .with(
                        ItemEntry.builder(Items.SPLASH_POTION)
                            .weight(2)
                            .apply(SetPotionLootFunction.builder(
                                registryLookup
                                    .getOrThrow(RegistryKeys.POTION)
                                    .getOrThrow(RegistryKey.of(RegistryKeys.POTION, Identifier.ofVanilla("slowness")))
                            ))
                    )
                    .with(
                        ItemEntry.builder(Items.SPLASH_POTION)
                            .weight(2)
                            .apply(SetPotionLootFunction.builder(
                                registryLookup
                                    .getOrThrow(RegistryKeys.POTION)
                                    .getOrThrow(RegistryKey.of(RegistryKeys.POTION, Identifier.ofVanilla("fire_resistance")))
                            ))
                    )
                    .with(
                        ItemEntry.builder(Items.SPLASH_POTION)
                            .weight(2)
                            .apply(SetPotionLootFunction.builder(
                                registryLookup
                                    .getOrThrow(RegistryKeys.POTION)
                                    .getOrThrow(RegistryKey.of(RegistryKeys.POTION, Identifier.ofVanilla("harming")))
                            ))
                    )
                    .with(
                        ItemEntry.builder(Items.SPLASH_POTION)
                            .weight(2)
                            .apply(SetPotionLootFunction.builder(
                                registryLookup
                                    .getOrThrow(RegistryKeys.POTION)
                                    .getOrThrow(RegistryKey.of(RegistryKeys.POTION, Identifier.ofVanilla("poison")))
                            ))
                    )
                    .with(
                        ItemEntry.builder(Items.RABBIT_FOOT)
                            .weight(1)
                    )
                    .with(
                        ItemEntry.builder(Items.SPYGLASS)
                            .weight(1)
                    )
            );
    }
}
