package dev.dassen.desolatedungeons.augment.context;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public record AugmentExecutionContext(
    ServerPlayerEntity serverPlayer,
    ServerWorld serverWorld,
    long time
) {
    public static AugmentExecutionContext createDefault(ServerPlayerEntity serverPlayer) {
        return new AugmentExecutionContext(serverPlayer, serverPlayer.getServerWorld(), serverPlayer.getServerWorld().getTime());
    }
}