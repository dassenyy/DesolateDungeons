package dev.dassen.desolatedungeons.augment.condition;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.augment.condition.types.ValueInBetweenAugmentCondition;
import dev.dassen.desolatedungeons.registry.ModRegistries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AugmentConditionTypes {
    public static final AugmentConditionType<ValueInBetweenAugmentCondition> VALUE_IN_BETWEEN =
        registerAugmentConditionType("value_in_between", new AugmentConditionType<>(ValueInBetweenAugmentCondition.CODEC));

    public static void register() {
        DesolateDungeons.LOGGER.info("Registering Augment Condition Types for " + DesolateDungeons.MOD_ID);
    }

    public static <T extends AugmentCondition> AugmentConditionType<T> registerAugmentConditionType(String path, AugmentConditionType<T> type) {
        return Registry.register(ModRegistries.AUGMENT_CONDITION_TYPE, Identifier.of(DesolateDungeons.MOD_ID, path), type);
    }
}
