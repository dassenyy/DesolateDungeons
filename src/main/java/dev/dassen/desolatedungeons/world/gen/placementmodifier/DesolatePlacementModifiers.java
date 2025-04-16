package dev.dassen.desolatedungeons.world.gen.placementmodifier;

import dev.dassen.desolatedungeons.DesolateDungeons;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

public class DesolatePlacementModifiers {
    public static final PlacementModifierType<DirectionlessEnvironmentScanPlacementModifier> DIRECTIONLESS_ENVIRONMENT_SCAN;

    static {
        DIRECTIONLESS_ENVIRONMENT_SCAN = Registry.register(
            Registries.PLACEMENT_MODIFIER_TYPE,
            Identifier.of(DesolateDungeons.MOD_ID, "directionless_environment_scan"),
            () -> DirectionlessEnvironmentScanPlacementModifier.MODIFIER_CODEC
        );
    }

    public static void register() {
        DesolateDungeons.LOGGER.info("Registering Placement Modifiers for " + DesolateDungeons.MOD_ID);
    }
}
