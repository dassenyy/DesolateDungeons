package dev.dassen.desolatedungeons.networking;

import dev.dassen.desolatedungeons.networking.packet.AugmentChoicePayload;
import dev.dassen.desolatedungeons.networking.packet.OfferAugmentsPayload;
import dev.dassen.desolatedungeons.networking.packet.c2s.AugmentChoiceC2SPacket;
import dev.dassen.desolatedungeons.networking.packet.s2c.OfferAugmentsS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class ModPackets {
    public static void registerC2S() {
        PayloadTypeRegistry.playC2S().register(AugmentChoicePayload.IDENTIFIER, AugmentChoicePayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(AugmentChoicePayload.IDENTIFIER, AugmentChoiceC2SPacket::receive);
    }

    public static void registerS2C() {
        PayloadTypeRegistry.playS2C().register(OfferAugmentsPayload.IDENTIFIER, OfferAugmentsPayload.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(OfferAugmentsPayload.IDENTIFIER, OfferAugmentsS2CPacket::receive);
    }
}
