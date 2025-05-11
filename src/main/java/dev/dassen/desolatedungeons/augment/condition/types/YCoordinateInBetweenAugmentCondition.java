package dev.dassen.desolatedungeons.augment.condition.types;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dassen.desolatedungeons.augment.condition.AugmentCondition;
import dev.dassen.desolatedungeons.augment.condition.AugmentConditionType;
import dev.dassen.desolatedungeons.augment.condition.AugmentConditionTypes;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionContext;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import org.jetbrains.annotations.NotNull;

public class YCoordinateInBetweenAugmentCondition implements AugmentCondition {
    public static final MapCodec<YCoordinateInBetweenAugmentCondition> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
            UniformIntProvider.CODEC.fieldOf("y_coordinate_int_provider").forGetter(augmentCondition -> augmentCondition.yCoordinateIntProvider)
        ).apply(instance, YCoordinateInBetweenAugmentCondition::new)
    );

    private final UniformIntProvider yCoordinateIntProvider;

    public YCoordinateInBetweenAugmentCondition(UniformIntProvider yCoordinateIntProvider) {
        this.yCoordinateIntProvider = yCoordinateIntProvider;
    }

    @Override
    public @NotNull AugmentConditionType<?> getType() {
        return AugmentConditionTypes.Y_COORDINATE_IN_BETWEEN;
    }

    @Override
    public boolean test(AugmentFunctionContext context) {
        return yCoordinateIntProvider.getMin() <= context.player().getBlockY() && yCoordinateIntProvider.getMax() >= context.player().getBlockY();
    }
}
