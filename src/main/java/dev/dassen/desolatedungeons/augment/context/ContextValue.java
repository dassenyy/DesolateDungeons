package dev.dassen.desolatedungeons.augment.context;

import net.minecraft.util.StringIdentifiable;

public enum ContextValue implements StringIdentifiable {
    PLAYER_HEALTH("player_health") {
        @Override
        public float getValue(AugmentExecutionContext context) {
            return context.serverPlayer().getHealth();
        }
    },
    PLAYER_HUNGER_LEVEL("player_hunger_level") {
        @Override
        public float getValue(AugmentExecutionContext context) {
            return (float) context.serverPlayer().getHungerManager().getFoodLevel();
        }
    },
    PLAYER_Y_COORDINATE("player_y_coordinate") {
        @Override
        public float getValue(AugmentExecutionContext context) {
            return (float) context.serverPlayer().getBlockY();
        }
    };

    public static final StringIdentifiable.EnumCodec<ContextValue> CODEC = StringIdentifiable.createCodec(
        ContextValue::values
    );

    private final String name;

    private ContextValue(String name) {
        this.name = name;
    }

    @Override
    public String asString() {
        return this.name;
    }

    public abstract float getValue(AugmentExecutionContext context);
}
