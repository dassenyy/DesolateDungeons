package dev.dassen.desolatedungeons.augment.function;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.registry.ModRegistries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AugmentFunctionTypes {
    public static final AugmentFunctionType<IncreaseDamageAugmentFunction> INCREASE_DAMAGE = registerAugmentFunctionType("increase_damage", new AugmentFunctionType<>(IncreaseDamageAugmentFunction.CODEC));

    public static void register() {
        DesolateDungeons.LOGGER.info("Registering Augment Function Types for " + DesolateDungeons.MOD_ID);
    }

    public static <T extends AugmentFunction> AugmentFunctionType<T> registerAugmentFunctionType(String path, AugmentFunctionType<T> type) {
        return Registry.register(ModRegistries.AUGMENT_FUNCTION_TYPE, Identifier.of(DesolateDungeons.MOD_ID, path), type);
    }
}
