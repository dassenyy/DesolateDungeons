package dev.dassen.desolatedungeons;

import dev.dassen.desolatedungeons.block.DesolateBlocks;
import dev.dassen.desolatedungeons.entity.DesolateEntities;
import dev.dassen.desolatedungeons.item.DesolateItemGroups;
import dev.dassen.desolatedungeons.item.DesolateItems;
import dev.dassen.desolatedungeons.world.gen.DesolateWorldGeneration;
import dev.dassen.desolatedungeons.world.gen.placementmodifier.DesolatePlacementModifiers;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DesolateDungeons implements ModInitializer {
	public static final String MOD_ID = "desolate_dungeons";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Desolate Dungeons mod");

		DesolatePlacementModifiers.register();
		DesolateWorldGeneration.initialize();

		DesolateEntities.register();

		DesolateItemGroups.register();
		DesolateItems.register();
		DesolateBlocks.register();
	}
}