package dev.dassen.desolatedungeons.networking;

import dev.dassen.desolatedungeons.networking.packet.LevelUpPayload;
import dev.dassen.desolatedungeons.networking.packet.s2c.LevelUpS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class ModPackets {
    public static void registerC2S() {
    }

    public static void registerS2C() {
        PayloadTypeRegistry.playS2C().register(LevelUpPayload.IDENTIFIER, LevelUpPayload.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(LevelUpPayload.IDENTIFIER, LevelUpS2CPacket::receive);
    }
}
