package dev.dassen.desolatedungeons.mixin;

import dev.dassen.desolatedungeons.entity.player.CrossDimensionalPlayerStateManager;
import dev.dassen.desolatedungeons.impl.entity.player.PlayerPersistentDataSaver;
import dev.dassen.desolatedungeons.registry.key.ModWorldKeys;
import net.minecraft.entity.Entity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("unused")
@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "remove(Lnet/minecraft/entity/Entity$RemovalReason;)V", at = @At("TAIL"))
    public void injectRemove(Entity.RemovalReason reason, CallbackInfo ci) {
        //noinspection DataFlowIssue
        if ((Entity) (Object) this instanceof ServerPlayerEntity serverPlayer && reason == Entity.RemovalReason.KILLED) {
            PlayerPersistentDataSaver playerDataSaver = ((PlayerPersistentDataSaver) serverPlayer);

            RegistryKey<World> originWorldKey = serverPlayer.getWorld().getRegistryKey();

            if (originWorldKey == ModWorldKeys.DESOLATE_DUNGEON && serverPlayer.getSpawnPointDimension() != ModWorldKeys.DESOLATE_DUNGEON) {
                CrossDimensionalPlayerStateManager.loadVanillaData(
                    serverPlayer,
                    playerDataSaver.getStashedPlayerData()
                );
            }
        }
    }
}
