package dev.dassen.desolatedungeons.augment;

import dev.dassen.desolatedungeons.augment.context.AugmentExecutionContext;
import dev.dassen.desolatedungeons.networking.packet.OfferAugmentsPayload;
import dev.dassen.desolatedungeons.registry.key.ModRegistryKeys;
import dev.dassen.desolatedungeons.registry.tag.AugmentTags;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class AugmentInventory {
    // Both inventory and offered need to be reworked to contain some sort of "AugmentStack" instead, similar to ItemStack
    private final HashMap<RegistryKey<Augment>, RegistryEntry.Reference<Augment>> inventory = new HashMap<>();
    private final ArrayList<RegistryEntry.@Nullable Reference<Augment>> offered = new ArrayList<>();
    public final PlayerEntity player;

    public AugmentInventory(PlayerEntity player) {
        this.player = player;
    }

    public void add(RegistryEntry.Reference<Augment> augment) {
        inventory.put(augment.registryKey(), augment);
    }

    public void remove(RegistryKey<Augment> augmentRegistryKey) {
        inventory.remove(augmentRegistryKey);
    }

    public int getSize() {
        return inventory.size();
    }

    public void offerAugments(int addedLevel, int currentLevel) {
        if (player instanceof ServerPlayerEntity serverPlayer) {
            Registry<Augment> augmentRegistry = serverPlayer.getWorld().getRegistryManager().getOrThrow(ModRegistryKeys.AUGMENT);

            List<RegistryEntry.Reference<Augment>> augments = new ArrayList<>();
            augmentRegistry
                .iterateEntries(AugmentTags.DESOLATE_DUNGEONS_SET)
                .forEach(augment -> augments.add((RegistryEntry.Reference<Augment>) augment));
            Collections.shuffle(augments);

            for (int i = 0; i < 3; i++) {
                this.offered.add(augments.get(i));
            }

            ServerPlayNetworking.send(
                serverPlayer,
                new OfferAugmentsPayload(
                    addedLevel,
                    currentLevel,
                    offered.get(0),
                    offered.get(1),
                    offered.get(2)
                )
            );
        }
    }

    private void clearOfferedAugments() {
        offered.clear();
    }

    public void pickAugment(RegistryEntry.Reference<Augment> augment) {
        if (offered.stream().anyMatch(offeredAugment ->
            offeredAugment == augment
            && augment instanceof RegistryEntry.Reference<Augment> augmentReference
            && !inventory.containsKey(augmentReference.registryKey())
        )) {
            add(augment);
            clearOfferedAugments();
        }
    }

    public void tickAugments() {
        for (RegistryEntry<Augment> augment : inventory.values()) {
            augment.value().augmentFunction.tryStartingOrKeepRunning(AugmentExecutionContext.createDefault((ServerPlayerEntity) player));
        }
    }

    public void readNbt(NbtList nbtList) {
        Registry<Augment> augmentRegistry = player.getWorld().getRegistryManager().getOrThrow(ModRegistryKeys.AUGMENT);

        for (NbtElement nbtElement : nbtList) {
            Optional<RegistryEntry.Reference<Augment>> optionalAugmentReference = augmentRegistry.getEntry(Identifier.of(nbtElement.asString()));
            optionalAugmentReference.ifPresent(augment -> inventory.put(augment.registryKey(), augment));
        }
    }

    public NbtList writeNbt(NbtList nbtList) {
        for (RegistryEntry.Reference<Augment> augment : inventory.values()) {
            nbtList.add(NbtString.of(augment.getIdAsString()));
        }

        return nbtList;
    }

    public Text toHoverableText() {
        MutableText hoveredText = Text.empty();
        inventory.values().forEach(augment -> hoveredText.append(Augment.toHoverableText(augment)).append(", "));
        if (hoveredText.getSiblings().size() > 1) { hoveredText.getSiblings().removeLast(); }
        return hoveredText;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        inventory.keySet().forEach(augmentKey -> builder.append(augmentKey.getValue()).append(", "));
        if (builder.length() > 1) { builder.setLength(builder.length() - 2); }
        builder.append("}");
        return builder.toString();
    }
}
