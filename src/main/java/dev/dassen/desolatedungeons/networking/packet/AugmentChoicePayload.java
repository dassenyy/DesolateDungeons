package dev.dassen.desolatedungeons.networking.packet;

import dev.dassen.desolatedungeons.augment.Augment;
import dev.dassen.desolatedungeons.networking.packet.c2s.AugmentChoiceC2SPacket;
import dev.dassen.desolatedungeons.registry.key.ModRegistryKeys;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.registry.entry.RegistryEntry;

public record AugmentChoicePayload(RegistryEntry<Augment> augment) implements CustomPayload {
    public static final CustomPayload.Id<AugmentChoicePayload> IDENTIFIER = new CustomPayload.Id<>(AugmentChoiceC2SPacket.IDENTIFIER);
    public static final PacketCodec<RegistryByteBuf, AugmentChoicePayload> CODEC = PacketCodec.tuple(
        PacketCodecs.registryEntry(ModRegistryKeys.AUGMENT), AugmentChoicePayload::augment,
        AugmentChoicePayload::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return IDENTIFIER;
    }
}
