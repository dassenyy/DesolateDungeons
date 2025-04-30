package dev.dassen.desolatedungeons.networking.packet.s2c;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.networking.packet.LevelUpPayload;
import dev.dassen.desolatedungeons.screen.custom.LevelUpScreen;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

public class LevelUpS2CPacket {
    public static final Identifier IDENTIFIER = Identifier.of(DesolateDungeons.MOD_ID, "level_up");

    public static void receive(LevelUpPayload payload, ClientPlayNetworking.Context clientContext) {
        //noinspection resource
        clientContext.client().setScreen(new LevelUpScreen());
    }
}
