package dev.dassen.desolatedungeons.augment;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public record AugmentExecutionContext(
    ServerPlayerEntity serverPlayer,
    ServerWorld serverWorld,
    boolean canStart
) {
    public static AugmentExecutionContext createDefault(ServerPlayerEntity serverPlayer) {
        return new AugmentExecutionContext(serverPlayer, serverPlayer.getServerWorld(), true);
    }

    public AugmentExecutionContext setCantStart() {
        return new AugmentExecutionContext(serverPlayer, serverWorld, false);
    }
}