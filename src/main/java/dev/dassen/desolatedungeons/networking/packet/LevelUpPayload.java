package dev.dassen.desolatedungeons.networking.packet;

import dev.dassen.desolatedungeons.networking.packet.s2c.LevelUpS2CPacket;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record LevelUpPayload(int addedLevel, int currentLevel) implements CustomPayload {
    public static final CustomPayload.Id<LevelUpPayload> IDENTIFIER = new CustomPayload.Id<>(LevelUpS2CPacket.IDENTIFIER);
    public static final PacketCodec<RegistryByteBuf, LevelUpPayload> CODEC = PacketCodec.tuple(
        PacketCodecs.INTEGER, LevelUpPayload::addedLevel,
        PacketCodecs.INTEGER, LevelUpPayload::currentLevel,
        LevelUpPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return IDENTIFIER;
    }
}
