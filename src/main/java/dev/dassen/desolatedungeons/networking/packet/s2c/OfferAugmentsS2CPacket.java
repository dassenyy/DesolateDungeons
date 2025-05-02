package dev.dassen.desolatedungeons.networking.packet.s2c;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.augment.Augment;
import dev.dassen.desolatedungeons.networking.packet.OfferAugmentsPayload;
import dev.dassen.desolatedungeons.registry.key.AugmentKeys;
import dev.dassen.desolatedungeons.registry.key.ModDynamicRegistryKeys;
import dev.dassen.desolatedungeons.screen.custom.OfferAugmentsScreen;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class OfferAugmentsS2CPacket {
    public static final Identifier IDENTIFIER = Identifier.of(DesolateDungeons.MOD_ID, "offer_augments");

    public static void receive(OfferAugmentsPayload payload, ClientPlayNetworking.Context clientContext) {
        Augment eP = clientContext.player().getWorld().getRegistryManager().getOrThrow(ModDynamicRegistryKeys.AUGMENT).getOrThrow(AugmentKeys.EMERGENCY_PUFFERFISH).value();
        DesolateDungeons.LOGGER.info(eP.name);

        //noinspection resource
        clientContext.client().setScreen(
            new OfferAugmentsScreen(
                new RegistryEntry[] {
                    payload.firstAugment(),
                    payload.secondAugment(),
                    payload.thirdAugment()
                }
            )
        );
    }
}
