package dev.dassen.desolatedungeons.item.custom;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.registry.key.ModWorldKeys;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.Heightmap;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

public class AncientTotemItem extends Item{
    public AncientTotemItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        if (world instanceof ServerWorld serverWorld && player instanceof ServerPlayerEntity serverPlayer) {
            if (serverWorld.getRegistryKey() == World.OVERWORLD) {
                ServerWorld desolateDungeonWorld = serverWorld.getServer().getWorld(ModWorldKeys.DESOLATE_DUNGEON);

                if (desolateDungeonWorld == null) {
                    DesolateDungeons.LOGGER.error("Did not find Desolate Dungeon world, but it should exist!");
                    return ActionResult.FAIL;
                }

                desolateDungeonWorld.setChunkForced(0, 0, true);

                serverPlayer.teleport(
                    desolateDungeonWorld,
                    0d,
                    desolateDungeonWorld.getTopY(Heightmap.Type.WORLD_SURFACE, 0, 0),
                    0d,
                    PositionFlag.combine(PositionFlag.DELTA, PositionFlag.ROT),
                    0f,
                    0f,
                    false
                );

                player.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT);
            } else {
                serverPlayer.teleportTo(serverPlayer.getRespawnTarget(false, TeleportTarget.NO_OP));

                player.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT);
            }
        }

        player.incrementStat(Stats.USED.getOrCreateStat(this));
        return ActionResult.SUCCESS;
    }
}
