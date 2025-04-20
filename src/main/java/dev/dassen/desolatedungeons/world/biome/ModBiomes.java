package dev.dassen.desolatedungeons.world.biome;

import dev.dassen.desolatedungeons.entity.ModEntities;
import dev.dassen.desolatedungeons.registry.key.ModBiomeKeys;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BiomeAdditionsSound;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.biome.*;

public class ModBiomes {
    public static void bootstrap(Registerable<Biome> biomeRegisterable) {
        biomeRegisterable.register(
            ModBiomeKeys.DESOLATE_DUNGEON,
            createDesolateDungeon(biomeRegisterable)
        );
    }

    public static Biome createDesolateDungeon(Registerable<Biome> biomeRegisterable) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.SCARAB_BEETLE, 5, 1, 2));

        GenerationSettings.LookupBackedBuilder generationBuilder =
            new GenerationSettings.LookupBackedBuilder(
                biomeRegisterable.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                biomeRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
            );

        return new Biome.Builder()
            .precipitation(false)
            .temperature(2.0F)
            .downfall(0.0F)
            .effects(
                new BiomeEffects.Builder()
                    .waterColor(4159204)
                    .waterFogColor(329011)
                    .fogColor(3344392)
                    .skyColor(OverworldBiomeCreator.getSkyColor(2.0F))
                    .loopSound(SoundEvents.AMBIENT_NETHER_WASTES_LOOP)
                    .moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_NETHER_WASTES_MOOD, 6000, 8, 2.0))
                    .additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111))
                    .music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_NETHER_WASTES))
                    .build()
            )
            .spawnSettings(spawnBuilder.build())
            .generationSettings(generationBuilder.build())
            .build();
    }
}