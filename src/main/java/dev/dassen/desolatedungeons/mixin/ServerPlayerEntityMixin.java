package dev.dassen.desolatedungeons.mixin;

import dev.dassen.desolatedungeons.entity.player.CrossDimensionalPlayerStateManager;
import dev.dassen.desolatedungeons.impl.entity.player.PlayerPersistentDataSaver;
import dev.dassen.desolatedungeons.registry.key.ModWorldKeys;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("unused")
@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {
    @Inject(method = "worldChanged(Lnet/minecraft/server/world/ServerWorld;)V", at = @At("HEAD"))
    public void handleDesolateDungeonDimensionTravel(ServerWorld originWorld, CallbackInfo info) {
        //noinspection DataFlowIssue
        ServerPlayerEntity thisPlayer = ((ServerPlayerEntity) (Object) this);
        PlayerPersistentDataSaver thisPlayerDataSaver = ((PlayerPersistentDataSaver) thisPlayer);

        RegistryKey<World> originWorldKey = originWorld.getRegistryKey();
        RegistryKey<World> currentWorldKey = thisPlayer.getWorld().getRegistryKey();

        if (currentWorldKey == ModWorldKeys.DESOLATE_DUNGEON) {
            CrossDimensionalPlayerStateManager.saveAndClearVanillaData(
                thisPlayer,
                thisPlayerDataSaver.getStashedPlayerData()
            );
        } else if (originWorldKey == ModWorldKeys.DESOLATE_DUNGEON) {
            CrossDimensionalPlayerStateManager.loadVanillaData(
                thisPlayer,
                thisPlayerDataSaver.getStashedPlayerData()
            );
        }
    }
}