package dev.dassen.desolatedungeons.impl.entity.player;

import dev.dassen.desolatedungeons.augment.Augment;
import net.minecraft.registry.entry.RegistryEntry;

public interface AugmentImpl {
    void offerAugments(int addedLevels, int currentLevel);
    void pickAugment(RegistryEntry<Augment> augment);
}
