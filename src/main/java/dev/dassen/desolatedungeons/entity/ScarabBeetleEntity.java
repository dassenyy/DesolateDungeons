package dev.dassen.desolatedungeons.entity;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Dynamic;
import dev.dassen.desolatedungeons.entity.ai.control.ScarabBeetleMoveControl;
import dev.dassen.desolatedungeons.entity.data.DesolateTrackedDataHandlerRegistry;
import dev.dassen.desolatedungeons.registry.tag.DesolateBlockTags;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.profiler.Profilers;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.function.IntFunction;

public class ScarabBeetleEntity extends PassiveEntity implements Flutterer {
    protected Brain<ScarabBeetleEntity> brain;

    private static final TrackedData<ScarabBeetleEntity.ControlState> CONTROL_STATE = DataTracker.registerData(ScarabBeetleEntity.class, DesolateTrackedDataHandlerRegistry.SCARAB_BEETLE_CONTROL_STATE);
    private static final TrackedData<Long> CHANGED_CONTROL_STATE_TICK = DataTracker.registerData(ScarabBeetleEntity.class, TrackedDataHandlerRegistry.LONG);
    public final AnimationState idlingAnimationState = new AnimationState();
    public final AnimationState walkingAnimationState = new AnimationState();
    public final AnimationState ascendingAnimationState = new AnimationState();
    public final AnimationState flyingAnimationState = new AnimationState();
    public final AnimationState descendingAnimationState = new AnimationState();

    private MobNavigation mobNavigation;
    private BirdNavigation birdNavigation;

    // CONSTRUCTION

    public ScarabBeetleEntity(EntityType<? extends ScarabBeetleEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new ScarabBeetleMoveControl(this);
        NbtOps nbtOps = NbtOps.INSTANCE;
        this.brain = this.deserializeBrain(
            new Dynamic<>(
                nbtOps,
                nbtOps.createMap(
                    ImmutableMap.of(
                        nbtOps.createString("memories"),
                        nbtOps.emptyMap()
                    )
                )
            )
        );
    }

    // ENTITY SETTINGS

    // This is used in the DesolateDungeons ModInitializer to register the attributes in the AttributeRegistry
    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
            .add(EntityAttributes.MAX_HEALTH, 8d)
            .add(EntityAttributes.FLYING_SPEED, 2d)
            .add(EntityAttributes.MOVEMENT_SPEED, 0.5d)
            .add(EntityAttributes.JUMP_STRENGTH, 0.65d);
    }

    @Override
    @Nullable
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return DesolateEntities.SCARAB_BEETLE.create(world, SpawnReason.BREEDING);
    }

    // SAVE FILE HANDLING

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putString("ControlState", this.getControlState().asString());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setControlState(ScarabBeetleEntity.ControlState.fromName(nbt.getString("ControlState")));
    }

    // AI

    @Override
    protected Brain.Profile<ScarabBeetleEntity> createBrainProfile() {
        return ScarabBeetleBrain.createBrainProfile();
    }

    @Override
    protected Brain<ScarabBeetleEntity> deserializeBrain(Dynamic<?> dynamic) {
        return ScarabBeetleBrain.create(this.createBrainProfile().deserialize(dynamic));
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        MobNavigation mobNavigation = new MobNavigation(this, world);
        mobNavigation.setCanSwim(true);
        this.mobNavigation = mobNavigation;
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanSwim(true);
        this.birdNavigation = birdNavigation;
        return this.mobNavigation;
    }

    private void changeNavigationMode() {
        if (this.getControlState() == ControlState.WALKING) {
            this.navigation = this.mobNavigation;
        } else if (this.getControlState() == ControlState.FLYING) {
            this.navigation = this.birdNavigation;
        }
    }

    // enterFlightMode gets called at the start of the ScarabBeetleFleeTask.
    // Changes the ControlState to FLYING which alters the logic in the ScarabBeetleMoveControl module,
    // and also switches to the BirdNavigation module responsible for flying.
    public void enterFlightMode() {
        setControlState(ScarabBeetleEntity.ControlState.FLYING);
        setChangedControlStateTick(this.getWorld().getTime());
    }

    // leaveFlightMode gets called at the end of the ScarabBeetleMoveToTargetTask, after it finished pathing.
    // If the scarab beetle entity is still in flight mode but not panicking anymore,
    // Changes the ControlState to WALKING which alters the logic in the ScarabBeetleMoveControl module,
    // and also switches to the MobNavigation module responsible for walking.
    public void leaveFlightMode() {
        if(this.getControlState() == ControlState.FLYING && !this.isPanicking()) {
            setControlState(ScarabBeetleEntity.ControlState.WALKING);
            setChangedControlStateTick(this.getWorld().getTime());
        }
    }

    // DATA TRACKER

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(CONTROL_STATE, ScarabBeetleEntity.ControlState.WALKING);
        builder.add(CHANGED_CONTROL_STATE_TICK, 0L);
    }

    @Override
    public void onTrackedDataSet(TrackedData<?> data) {
        if (CONTROL_STATE.equals(data)) {
            this.changeNavigationMode();
        }

        super.onTrackedDataSet(data);
    }

    // HOT LOOPS

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient()) {
            this.updateAnimations();
        }
    }

    @Override
    protected void mobTick(ServerWorld world) {
        Profiler profiler = Profilers.get();
        profiler.push("scarabBeetleBrain");
        this.brain.tick(world, this);
        profiler.pop();
        profiler.push("scarabBeetleActivityUpdate");
        ScarabBeetleBrain.updateActivities(this);
        profiler.pop();

        super.mobTick(world);
    }

   // GETTERS, SETTERS & CHECKS

    public ScarabBeetleEntity.ControlState getControlState() {
        return this.dataTracker.get(CONTROL_STATE);
    }

    public void setControlState(ScarabBeetleEntity.ControlState controlState) {
        this.dataTracker.set(CONTROL_STATE, controlState);
    }

    public long getChangedControlStateTick() {
        return this.dataTracker.get(CHANGED_CONTROL_STATE_TICK);
    }

    public void setChangedControlStateTick(long changedControlStateTick) {
        this.dataTracker.set(CHANGED_CONTROL_STATE_TICK, changedControlStateTick);
    }

    public long getChangedControlStateTickDelta() {
        return this.getWorld().getTime() - Math.abs(getChangedControlStateTick());
    }

    @Override
    public boolean isInvulnerableTo(ServerWorld world, DamageSource source) {
        return source.isOf(DamageTypes.FALL) || super.isInvulnerableTo(world, source);
    }

    @Override
    public boolean isInAir() {
        return !this.isOnGround();
    }

    public static boolean canSpawn(EntityType<ScarabBeetleEntity> entity, WorldAccess world, SpawnReason spawnReason, BlockPos position, Random random) {
        return world.getBlockState(position.down()).isIn(DesolateBlockTags.SCARAB_BEETLES_SPAWNABLE_ON);
    }

    // ANIMATION

    private void updateAnimations() {
        if (this.getChangedControlStateTickDelta() <= 10L) {
            if (this.getControlState() == ControlState.WALKING) {
                this.descendingAnimationState.startIfNotRunning(this.age);
            } else if (this.getControlState() == ControlState.FLYING) {
                this.ascendingAnimationState.startIfNotRunning(this.age);
            }
        } else {
            this.descendingAnimationState.stop();
            this.ascendingAnimationState.stop();
        }

        if (!(this.walkingAnimationState.isRunning() || getControlState() == ControlState.FLYING)) {
            this.idlingAnimationState.start(this.age);
        }
    }

    // SCARAB BEETLE CONTROL STATE

    // This is responsible for entity movement.
    // Movement logic in ScarabBeetleMoveControl depends on which control state the scarab beetle entity currently is in.
    // Navigation logic gets either switched to MobNavigation or BirdNavigation when the control state gets switched.
    // ControlState is also responsible for which of the 2 walking animations gets used.
    public enum ControlState implements StringIdentifiable {
        WALKING("walking", 0),
        FLYING("flying", 1);

        private final String name;
        private final int index;
        // CODEC is for saving the current control state in an NBT file
        private static final StringIdentifiable.EnumCodec<ScarabBeetleEntity.ControlState> CODEC = StringIdentifiable.createCodec(ScarabBeetleEntity.ControlState::values);
        // PACKET_CODEC with its INDEX_TO_VALUE function is used to create a TrackedDataHandler for the TrackedData CONTROL_STATE
        public static final IntFunction<ScarabBeetleEntity.ControlState> INDEX_TO_VALUE =
            ValueLists.createIdToValueFunction(
                ScarabBeetleEntity.ControlState::getIndex, values(), ValueLists.OutOfBoundsHandling.ZERO
            );
        public static final PacketCodec<ByteBuf, ScarabBeetleEntity.ControlState> PACKET_CODEC = PacketCodecs.indexed(INDEX_TO_VALUE, ScarabBeetleEntity.ControlState::getIndex);

        private ControlState(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public static ScarabBeetleEntity.ControlState fromName(String name) {
            return CODEC.byId(name, WALKING);
        }

        @Override
        public String asString() {
            return this.name;
        }

        public int getIndex() {
            return this.index;
        }
    }
}