package dev.dassen.desolatedungeons.world.gen.structure;

import com.mojang.datafixers.util.Pair;
import dev.dassen.desolatedungeons.registry.key.DesolateStructurePoolKeys;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;

import java.util.List;

public class DesolateStructurePools {
    public static void bootstrap(Registerable<StructurePool> structurePoolRegisterable) {
        RegistryEntryLookup<StructurePool> structurePoolRegistryEntryLookup = structurePoolRegisterable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL);

        structurePoolRegisterable.register(
            DesolateStructurePoolKeys.SANDSWEPT_RUINS,
            new StructurePool(
                structurePoolRegistryEntryLookup.getOrThrow(StructurePools.EMPTY),
                List.of(
                    Pair.of(StructurePoolElement.ofSingle("desolate_dungeons:sandswept_ruins"), 1)
                ),
                StructurePool.Projection.RIGID
            )
        );
    }
}
