package dev.dassen.desolatedungeons.world.gen.densityfunction;

import com.mojang.serialization.MapCodec;
import dev.dassen.desolatedungeons.DesolateDungeons;
import dev.dassen.desolatedungeons.world.gen.densityfunction.types.LayerChoiceDensityFunction;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.CodecHolder;
import net.minecraft.world.gen.densityfunction.DensityFunction;

public class ModDensityFunctionTypes {
    public static void register() {
        DesolateDungeons.LOGGER.info("Registering Density Functions for " + DesolateDungeons.MOD_ID);

        registerDensityFunction("layer_choice", LayerChoiceDensityFunction.CODEC_HOLDER);
    }

    private static MapCodec<? extends DensityFunction> registerDensityFunction(
        String path,
        CodecHolder<? extends DensityFunction> codecHolder
    ) {
        return Registry.register(Registries.DENSITY_FUNCTION_TYPE, Identifier.of(DesolateDungeons.MOD_ID, path), codecHolder.codec());
    }
}
