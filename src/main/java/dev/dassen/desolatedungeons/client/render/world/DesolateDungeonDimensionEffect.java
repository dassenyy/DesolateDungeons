package dev.dassen.desolatedungeons.client.render.world;

import dev.dassen.desolatedungeons.DesolateDungeons;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public class DesolateDungeonDimensionEffect extends DimensionEffects {
    public static final Identifier IDENTIFIER = Identifier.of(DesolateDungeons.MOD_ID, "desolate_dungeon");

    public DesolateDungeonDimensionEffect() {
        super(Float.NaN, true, SkyType.NONE, false, false);
    }

    @Override
    public Vec3d adjustFogColor(Vec3d color, float sunHeight) {
        return color;
    }

    @Override
    public boolean useThickFog(int camX, int camY) {
        return true;
    }
}
