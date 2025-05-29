package dev.dassen.desolatedungeons.augment;

import dev.dassen.desolatedungeons.DesolateDungeons;
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
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class AugmentInventory {
    // Both inventory and offered need to be reworked to contain some sort of "AugmentStack" instead, similar to ItemStack
    private final HashMap<RegistryKey<Augment>, RegistryEntry<Augment>> inventory = new HashMap<>();
    private final ArrayList<@Nullable RegistryEntry<Augment>> offered = new ArrayList<>();
    public final PlayerEntity player;

    public AugmentInventory(PlayerEntity player) {
        this.player = player;
    }

    private void add(RegistryEntry<Augment> augment) {
        if (augment instanceof RegistryEntry.Reference<Augment> augmentReference) {
            inventory.put(augmentReference.registryKey(), augment);
        } else {
            // Direct registry entry never has a key
            DesolateDungeons.LOGGER.warn("Could not add augment {} to augment inventory as it does not have a registry key", augment.value().name);
        }
    }

    public void offerAugments(int addedLevel, int currentLevel) {
        if (player instanceof ServerPlayerEntity serverPlayer) {
            Registry<Augment> augmentRegistry = serverPlayer.getWorld().getRegistryManager().getOrThrow(ModRegistryKeys.AUGMENT);

            List<RegistryEntry<Augment>> augments = new ArrayList<>();
            augmentRegistry.iterateEntries(AugmentTags.DESOLATE_DUNGEONS_SET).forEach(augments::add);
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

    public void pickAugment(RegistryEntry<Augment> augment) {
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
            augment.value().augmentFunction.run(AugmentExecutionContext.createDefault((ServerPlayerEntity) player));
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
        for (RegistryEntry<Augment> augment : inventory.values()) {
            nbtList.add(NbtString.of(augment.getIdAsString()));
        }

        return nbtList;
    }
}
