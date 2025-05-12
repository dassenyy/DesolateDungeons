package dev.dassen.desolatedungeons.augment;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionContext;
import dev.dassen.desolatedungeons.networking.packet.OfferAugmentsPayload;
import dev.dassen.desolatedungeons.registry.key.ModRegistryKeys;
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
import net.minecraft.util.collection.DefaultedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AugmentInventory {
    // Both inventory and offered need to be reworked to contain some sort of "AugmentStack" instead, similar to ItemStack
    private final List<Augment> inventory = new ArrayList<>();
    private final DefaultedList<Augment> offered = DefaultedList.ofSize(3, Augments.DAMAGE);
    public final PlayerEntity player;

    public AugmentInventory(PlayerEntity player) {
        this.player = player;
    }

    private void add(Augment augment) {
        inventory.add(augment);
    }

    public void offerAugments(int addedLevel, int currentLevel) {
        if (player instanceof ServerPlayerEntity serverPlayer) {
            Registry<Augment> augmentRegistry = serverPlayer.getWorld().getRegistryManager().getOrThrow(ModRegistryKeys.AUGMENT);

            List<RegistryKey<Augment>> augmentKeys = new ArrayList<>(augmentRegistry.getKeys());
            Collections.shuffle(augmentKeys);

            for (int i = 0; i < 3; i++) {
                this.offered.set(i, augmentRegistry.getOrThrow(augmentKeys.get(i)).value());
            }

            ServerPlayNetworking.send(
                serverPlayer,
                new OfferAugmentsPayload(
                    addedLevel,
                    currentLevel,
                    RegistryEntry.of(offered.get(0)),
                    RegistryEntry.of(offered.get(1)),
                    RegistryEntry.of(offered.get(2))
                )
            );
        }
    }

    public void pickAugment(Augment augment) {
        if (offered.stream().noneMatch(offeredAugment -> offeredAugment == augment)) {
            return;
        }

        add(augment);
    }

    public void tickAugments() {
        for (Augment augment : inventory) {
            augment.augmentFunction.run(new AugmentFunctionContext(augment, player, player.getWorld()));
        }
    }

    public void readNbt(NbtList nbtList) {
        Registry<Augment> augmentRegistry = player.getWorld().getRegistryManager().getOrThrow(ModRegistryKeys.AUGMENT);

        for (NbtElement nbtElement : nbtList) {
            Optional<Augment> optionalAugment = augmentRegistry.getOptionalValue(Identifier.of(nbtElement.asString()));
            DesolateDungeons.LOGGER.info(optionalAugment.toString());
            optionalAugment.ifPresent(inventory::add);
        }
    }

    public NbtList writeNbt(NbtList nbtList) {
        Registry<Augment> augmentRegistry = player.getWorld().getRegistryManager().getOrThrow(ModRegistryKeys.AUGMENT);

        for (Augment augment : inventory) {
            nbtList.add(NbtString.of(augmentRegistry.getEntry(augment).getIdAsString()));
        }

        return nbtList;
    }
}
