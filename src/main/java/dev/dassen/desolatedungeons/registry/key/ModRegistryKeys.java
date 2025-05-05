package dev.dassen.desolatedungeons.registry.key;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.augment.Augment;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionType;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public final class ModRegistryKeys {
    public static final RegistryKey<Registry<Augment>> AUGMENT = registryKeyOf("augment");
    public static final RegistryKey<Registry<AugmentFunctionType<?>>> AUGMENT_FUNCTION_TYPE = registryKeyOf("augment_function_type");

    private static <T> RegistryKey<Registry<T>> registryKeyOf(String path) {
        return RegistryKey.ofRegistry(Identifier.of(DesolateDungeons.MOD_ID, path));
    }
}
