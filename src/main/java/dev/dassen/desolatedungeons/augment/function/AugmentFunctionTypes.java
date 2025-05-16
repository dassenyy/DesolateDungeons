package dev.dassen.desolatedungeons.augment.function;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.augment.function.types.*;
import dev.dassen.desolatedungeons.registry.ModRegistries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AugmentFunctionTypes {
    public static final AugmentFunctionType<AttributeModificationAugmentFunction> ATTRIBUTE_MODIFICATION =
        registerAugmentFunctionType("attribute_modification", new AugmentFunctionType<>(AttributeModificationAugmentFunction.CODEC));
    public static final AugmentFunctionType<ConditionAugmentFunction> CONDITION =
        registerAugmentFunctionType("condition", new AugmentFunctionType<>(ConditionAugmentFunction.CODEC));
    public static final AugmentFunctionType<SequenceAugmentFunction> SEQUENCE =
        registerAugmentFunctionType("sequence", new AugmentFunctionType<>(SequenceAugmentFunction.CODEC));
    public static final AugmentFunctionType<StatusEffectAugmentFunction> STATUS_EFFECT =
        registerAugmentFunctionType("status_effect", new AugmentFunctionType<>(StatusEffectAugmentFunction.CODEC));
    public static final AugmentFunctionType<SummonEntityAugmentFunction> SUMMON_ENTITY =
        registerAugmentFunctionType("summon_entity", new AugmentFunctionType<>(SummonEntityAugmentFunction.CODEC));

    public static void register() {
        DesolateDungeons.LOGGER.info("Registering Augment Function Types for " + DesolateDungeons.MOD_ID);
    }

    public static <T extends AugmentFunction> AugmentFunctionType<T> registerAugmentFunctionType(String path, AugmentFunctionType<T> type) {
        return Registry.register(ModRegistries.AUGMENT_FUNCTION_TYPE, Identifier.of(DesolateDungeons.MOD_ID, path), type);
    }
}
