package dev.dassen.desolatedungeons.world.gen.structure;

import dev.dassen.desolatedungeons.registry.key.ModStructureKeys;
import dev.dassen.desolatedungeons.registry.key.ModStructureSetKeys;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.StructureSet;
import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.gen.chunk.placement.SpreadType;
import net.minecraft.world.gen.structure.Structure;

public class ModStructureSets {
    public static void bootstrap(Registerable<StructureSet> structureSetRegisterable) {
        RegistryEntryLookup<Structure> structureRegistryEntryLookup = structureSetRegisterable.getRegistryLookup(RegistryKeys.STRUCTURE);

        structureSetRegisterable.register(
            ModStructureSetKeys.SANDSWEPT_RUINS,
            new StructureSet(
                structureRegistryEntryLookup.getOrThrow(ModStructureKeys.SANDSWEPT_RUINS),
                new RandomSpreadStructurePlacement(16, 4, SpreadType.LINEAR, 115115114)
            )
        );
    }
}
