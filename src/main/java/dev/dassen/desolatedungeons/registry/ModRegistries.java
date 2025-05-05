package dev.dassen.desolatedungeons.registry;

import com.mojang.serialization.Lifecycle;
import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.augment.function.AugmentFunctionType;
import dev.dassen.desolatedungeons.registry.key.ModRegistryKeys;
import net.minecraft.registry.Registry;
import net.minecraft.registry.SimpleRegistry;

public class ModRegistries {
    public static final Registry<AugmentFunctionType<?>> AUGMENT_FUNCTION_TYPE = new SimpleRegistry<>(ModRegistryKeys.AUGMENT_FUNCTION_TYPE, Lifecycle.stable());

    public static void register() {
        DesolateDungeons.LOGGER.info("Registering Registries for " + DesolateDungeons.MOD_ID);
    }
}
