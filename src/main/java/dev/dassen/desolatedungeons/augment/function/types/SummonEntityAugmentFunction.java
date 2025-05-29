package dev.dassen.desolatedungeons.augment.function.types;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.augment.AugmentState;
import dev.dassen.desolatedungeons.augment.function.AugmentFunction;
import dev.dassen.desolatedungeons.augment.AugmentExecutionContext;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionType;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class SummonEntityAugmentFunction implements AugmentFunction {
    public static final MapCodec<SummonEntityAugmentFunction> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
            Identifier.CODEC.fieldOf("entity_type_identifier").forGetter(augmentFunction -> augmentFunction.entityTypeIdentifier),
            NbtCompound.CODEC.optionalFieldOf("entity_nbt", new NbtCompound()).forGetter(augmentFunction -> augmentFunction.entityNbt)
        ).apply(instance, SummonEntityAugmentFunction::new)
    );

    private final Identifier entityTypeIdentifier;
    private final NbtCompound entityNbt;

    public SummonEntityAugmentFunction(Identifier entityTypeIdentifier, NbtCompound entityNbt) {
        this.entityTypeIdentifier = entityTypeIdentifier;
        this.entityNbt = entityNbt;
    }

    @Override
    public @NotNull AugmentFunctionType<?> getType() {
        return AugmentFunctionTypes.SUMMON_ENTITY;
    }

    @Override
    public AugmentState run(AugmentExecutionContext context) {
        EntityType<?> entityType = context.serverWorld().getRegistryManager()
            .getOrThrow(RegistryKeys.ENTITY_TYPE)
            .getOrThrow(RegistryKey.of(RegistryKeys.ENTITY_TYPE, entityTypeIdentifier))
            .value();
        if (!entityType.isSummonable()) {
            DesolateDungeons.LOGGER.warn("EntityType with Identifier {} is not summonable", entityTypeIdentifier);
        }

        boolean initialize = entityNbt.isEmpty();
        entityNbt.putString("id", entityTypeIdentifier.toString());
        Entity entity = EntityType.loadEntityWithPassengers(entityNbt, context.serverWorld(), SpawnReason.TRIGGERED, processorEntity -> {
            processorEntity.refreshPositionAndAngles(
                context.serverPlayer().getBlockX(),
                context.serverPlayer().getBlockY(),
                context.serverPlayer().getBlockZ(),
                processorEntity.getYaw(),
                processorEntity.getPitch()
            );
            return processorEntity;
        });

        if (entity == null) {
            DesolateDungeons.LOGGER.warn("SummonEntityAugmentFunction failed to load the entity {}", entityTypeIdentifier);
        } else {
            if (entity instanceof MobEntity mobEntity && initialize) {
                mobEntity.initialize(context.serverWorld(), context.serverWorld().getLocalDifficulty(entity.getBlockPos()), SpawnReason.TRIGGERED, null);
            }

            if (!context.serverWorld().spawnNewEntityAndPassengers(entity)) {
                DesolateDungeons.LOGGER.warn("SummonEntityAugmentFunction failed to summon the entity {} because of issues with the UUID", entityTypeIdentifier);
            }
        }

        return AugmentState.ENDED;
    }
}
