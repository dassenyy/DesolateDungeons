package dev.dassen.desolatedungeons;

import dev.dassen.desolatedungeons.block.ModBlocks;
import dev.dassen.desolatedungeons.entity.ModEntities;
import dev.dassen.desolatedungeons.item.ModItemGroups;
import dev.dassen.desolatedungeons.item.ModItems;
import dev.dassen.desolatedungeons.networking.ModPackets;
import dev.dassen.desolatedungeons.world.gen.ModWorldGeneration;
import dev.dassen.desolatedungeons.world.gen.placementmodifier.ModPlacementModifiers;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DesolateDungeons implements ModInitializer {
	public static final String MOD_ID = "desolate_dungeons";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Desolate Dungeons mod");

		ModPlacementModifiers.register();
		ModWorldGeneration.initialize();

		ModEntities.register();

		ModItemGroups.register();
		ModItems.register();
		ModBlocks.register();

		ModPackets.registerC2S();
	}
}