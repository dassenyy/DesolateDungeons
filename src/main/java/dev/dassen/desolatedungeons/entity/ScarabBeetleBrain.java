package dev.dassen.desolatedungeons.entity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.Set;

public class ScarabBeetleBrain {
    private static final ImmutableList<SensorType<? extends Sensor<? super ScarabBeetleEntity>>> SENSOR_TYPES;
    private static final ImmutableList<MemoryModuleType<?>> MEMORY_MODULE_TYPES;

    static {
        SENSOR_TYPES = ImmutableList.of(
            SensorType.NEAREST_LIVING_ENTITIES,
            SensorType.HURT_BY,
            SensorType.NEAREST_ADULT
        );
        MEMORY_MODULE_TYPES = ImmutableList.of(
            MemoryModuleType.IS_PANICKING,
            MemoryModuleType.HURT_BY,
            MemoryModuleType.HURT_BY_ENTITY,
            MemoryModuleType.WALK_TARGET,
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
            MemoryModuleType.PATH,
            MemoryModuleType.VISIBLE_MOBS,
            MemoryModuleType.TEMPTING_PLAYER,
            MemoryModuleType.TEMPTATION_COOLDOWN_TICKS,
            MemoryModuleType.GAZE_COOLDOWN_TICKS,
            MemoryModuleType.IS_TEMPTED,
            MemoryModuleType.BREED_TARGET,
            MemoryModuleType.NEAREST_VISIBLE_ADULT,
            MemoryModuleType.DANGER_DETECTED_RECENTLY
        );
    }

    public ScarabBeetleBrain() {
    }

    public static Brain.Profile<ScarabBeetleEntity> createBrainProfile() {
        return Brain.createProfile(MEMORY_MODULE_TYPES, SENSOR_TYPES);
    }

    protected static Brain<ScarabBeetleEntity> create(Brain<ScarabBeetleEntity> brain) {
        addCoreActivities(brain);
        addIdleActivities(brain);
        brain.setCoreActivities(Set.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.resetPossibleActivities();
        return brain;
    }

    private static void addCoreActivities(Brain<ScarabBeetleEntity> brain) {
        brain.setTaskList(
            Activity.CORE,
            0,
            ImmutableList.of(
                new StayAboveWaterTask<ScarabBeetleEntity>(0.8F),
                new ScarabBeetleFleeTask(1.0f),
                new UpdateLookControlTask(45, 90),
                new ScarabBeetleMoveToTargetTask(),
                new TickCooldownTask(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS),
                new TickCooldownTask(MemoryModuleType.GAZE_COOLDOWN_TICKS)
            )
        );
    }

    private static void addIdleActivities(Brain<ScarabBeetleEntity> brain) {
        brain.setTaskList(
            Activity.IDLE,
            0,
            ImmutableList.of(
                LookAtMobTask.create(EntityType.PLAYER, 4.0f),
                new LookAroundTask(UniformIntProvider.create(150, 250), 30.0f, 0.0f, 0.0f),
                new CompositeTask<ScarabBeetleEntity>(
                    ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.VALUE_ABSENT),
                    ImmutableSet.of(),
                    CompositeTask.Order.SHUFFLED,
                    CompositeTask.RunMode.TRY_ALL,
                    ImmutableList.of(
                        Pair.of(
                            StrollTask.create(0.5f),
                            0
                        ),
                        Pair.of(
                            new WaitTask(100, 200),
                            0
                        )
                    )
                )
            )
        );
    }

    public static void updateActivities(ScarabBeetleEntity scarabBeetle) {
        scarabBeetle.getBrain().resetPossibleActivities(ImmutableList.of(Activity.IDLE));
    }

    public static class ScarabBeetleFleeTask extends FleeTask<ScarabBeetleEntity> {
        private ScarabBeetleFleeTask(float speed) {
            super(speed);
        }

        @Override
        protected void run(ServerWorld serverWorld, ScarabBeetleEntity scarabBeetleEntity, long tick) {
            super.run(serverWorld, scarabBeetleEntity, tick);
            scarabBeetleEntity.enterFlightMode();
        }
    }

    public static class ScarabBeetleMoveToTargetTask extends MoveToTargetTask {
        private ScarabBeetleMoveToTargetTask() {
            super();
        }

        @Override
        protected void finishRunning(ServerWorld serverWorld, MobEntity mobEntity, long tick) {
            super.finishRunning(serverWorld, mobEntity, tick);

            if (mobEntity instanceof ScarabBeetleEntity scarabBeetleEntity) {
                scarabBeetleEntity.leaveFlightMode();
            } else {
                throw new IllegalStateException("Tried to use ScarabBeetleMoveToTargetTask with Non-ScarabBeetleEntity");
            }
        }
    }
}
