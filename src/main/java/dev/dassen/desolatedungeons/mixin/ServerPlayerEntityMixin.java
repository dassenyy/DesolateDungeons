package dev.dassen.desolatedungeons.mixin;

import dev.dassen.desolatedungeons.entity.player.CrossDimensionalPlayerStateManager;
import dev.dassen.desolatedungeons.impl.entity.PersistentDataSaver;
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
        PersistentDataSaver thisPlayerDataSaver = ((PersistentDataSaver) thisPlayer);

        RegistryKey<World> originWorldKey = originWorld.getRegistryKey();
        RegistryKey<World> currentWorldKey = thisPlayer.getWorld().getRegistryKey();

        if (currentWorldKey == ModWorldKeys.DESOLATE_DUNGEON) {
            CrossDimensionalPlayerStateManager.saveAndClearVanillaData(
                thisPlayer,
                thisPlayerDataSaver.getOrCreateNbtCompound(thisPlayerDataSaver.getPersistentData(), "StashedPlayerData")
            );
        } else if (originWorldKey == ModWorldKeys.DESOLATE_DUNGEON) {
            CrossDimensionalPlayerStateManager.loadVanillaData(
                thisPlayer,
                thisPlayerDataSaver.getOrCreateNbtCompound(thisPlayerDataSaver.getPersistentData(), "StashedPlayerData")
            );
        }
    }
}