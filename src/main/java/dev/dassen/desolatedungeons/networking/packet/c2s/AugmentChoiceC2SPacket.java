package dev.dassen.desolatedungeons.networking.packet.c2s;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.impl.entity.player.AugmentImpl;
import dev.dassen.desolatedungeons.networking.packet.AugmentChoicePayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class AugmentChoiceC2SPacket {
    public static final Identifier IDENTIFIER = Identifier.of(DesolateDungeons.MOD_ID, "augment_choice");

    public static void receive(AugmentChoicePayload payload, ServerPlayNetworking.Context serverContext) {
        ((AugmentImpl) serverContext.player()).getAugmentInventory().pickAugment(payload.augment());
    }
}
