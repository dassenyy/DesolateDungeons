package dev.dassen.desolatedungeons.item;

import dev.dassen.desolatedungeons.registry.key.DesolateWorldKeys;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
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
                serverWorld.playSound(null, serverPlayer.getBlockPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS);

                serverPlayer.teleport(
                    serverWorld.getServer().getWorld(DesolateWorldKeys.DESOLATE_DUNGEON),
                    0d,
                    20d,
                    0d,
                    PositionFlag.combine(PositionFlag.DELTA, PositionFlag.ROT),
                    0f,
                    0f,
                    false
                );
            } else {
                serverWorld.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS);

                serverPlayer.teleportTo(serverPlayer.getRespawnTarget(false, TeleportTarget.NO_OP));
            }
        }

        player.incrementStat(Stats.USED.getOrCreateStat(this));
        return ActionResult.SUCCESS;
    }
}
