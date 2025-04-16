package dev.dassen.desolatedungeons.entity;

import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.registry.key.DesolateEntityKeys;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DesolateEntities {
    public static final EntityType<ScarabBeetleEntity> SCARAB_BEETLE;

    static {
        SCARAB_BEETLE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(DesolateDungeons.MOD_ID, "scarab_beetle"),
            EntityType.Builder.create(ScarabBeetleEntity::new, SpawnGroup.CREATURE)
                .dimensions(0.5f, 0.5f)
                .eyeHeight(0.22f)
                .build(DesolateEntityKeys.SCARAB_BEETLE)
        );
    }

    public static void register() {
        DesolateDungeons.LOGGER.info("Registering Entities for " + DesolateDungeons.MOD_ID);

        FabricDefaultAttributeRegistry.register(DesolateEntities.SCARAB_BEETLE, ScarabBeetleEntity.createAttributes());
    }
}
