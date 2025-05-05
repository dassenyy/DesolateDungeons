package dev.dassen.desolatedungeons.networking.packet;

import dev.dassen.desolatedungeons.augment.Augment;
import dev.dassen.desolatedungeons.networking.packet.s2c.OfferAugmentsS2CPacket;
import dev.dassen.desolatedungeons.registry.key.ModRegistryKeys;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.registry.entry.RegistryEntry;

public record OfferAugmentsPayload(
    int addedLevel,
    int currentLevel,
    RegistryEntry<Augment> firstAugment,
    RegistryEntry<Augment> secondAugment,
    RegistryEntry<Augment> thirdAugment
) implements CustomPayload {
    public static final CustomPayload.Id<OfferAugmentsPayload> IDENTIFIER = new CustomPayload.Id<>(OfferAugmentsS2CPacket.IDENTIFIER);
    public static final PacketCodec<RegistryByteBuf, OfferAugmentsPayload> CODEC = PacketCodec.tuple(
        PacketCodecs.INTEGER, OfferAugmentsPayload::addedLevel,
        PacketCodecs.INTEGER, OfferAugmentsPayload::currentLevel,
        PacketCodecs.registryEntry(ModRegistryKeys.AUGMENT), OfferAugmentsPayload::firstAugment,
        PacketCodecs.registryEntry(ModRegistryKeys.AUGMENT), OfferAugmentsPayload::secondAugment,
        PacketCodecs.registryEntry(ModRegistryKeys.AUGMENT), OfferAugmentsPayload::thirdAugment,
        OfferAugmentsPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return IDENTIFIER;
    }
}
