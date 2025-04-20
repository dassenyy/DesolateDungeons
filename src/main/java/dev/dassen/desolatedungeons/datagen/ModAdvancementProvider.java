package dev.dassen.desolatedungeons.datagen;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    public ModAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry rootAdvancement = Advancement.Builder.create()
            .display(
                ModItems.ANCIENT_TOTEM,
                Text.translatable("advancements.desolate_dungeons.root.title"),
                Text.translatable("advancements.desolate_dungeons.root.description"),
                Identifier.of("textures/block/sand.png"),
                AdvancementFrame.TASK,
                false,
                false,
                false
            )
            .criterion("root", InventoryChangedCriterion.Conditions.items(Items.CRAFTING_TABLE))
            .build(consumer, DesolateDungeons.MOD_ID + "/root");

        AdvancementEntry sandsweptRuinsAdvancement = Advancement.Builder.create()
            .parent(rootAdvancement)
            .display(
                ModItems.SCARAB_BEETLE,
                Text.translatable("advancements.desolate_dungeons.sandswept_ruins.title"),
                Text.translatable("advancements.desolate_dungeons.sandswept_ruins.description"),
                null,
                AdvancementFrame.TASK,
                true,
                true,
                false
            )
            .criterion(
                "found_sandswept_ruins",
                TickCriterion.Conditions.createLocation(
                    new LocationPredicate.Builder().structure(
                        RegistryEntryList.of(
                            // Fetches the registry wrapper for the structure registry
                            registryLookup.getOrThrow(RegistryKeys.STRUCTURE)
                                // Fetches the registry entry for the specified structure
                                .getOrThrow(RegistryKey.of(RegistryKeys.STRUCTURE, Identifier.of(DesolateDungeons.MOD_ID, "sandswept_ruins")))
                        )
                    )
                )
            )
            .build(consumer, DesolateDungeons.MOD_ID + "/sandswept_ruins");
    }
}
