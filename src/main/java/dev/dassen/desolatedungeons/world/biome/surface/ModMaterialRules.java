package dev.dassen.desolatedungeons.world.biome.surface;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class ModMaterialRules {
    private static final MaterialRules.MaterialRule BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final MaterialRules.MaterialRule DIRT = makeStateRule(Blocks.DIRT);
    private static final MaterialRules.MaterialRule GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);

    public static MaterialRules.MaterialRule createDesolateDungeon() {
        ImmutableList.Builder<MaterialRules.MaterialRule> materialRuleBuilder = ImmutableList.builder();

        materialRuleBuilder.add(
            MaterialRules.condition(
                MaterialRules.verticalGradient("bedrock_floor", YOffset.getBottom(), YOffset.aboveBottom(3)),
                BEDROCK
            )
        );
        materialRuleBuilder.add(
            MaterialRules.condition(
                MaterialRules.aboveY(YOffset.fixed(128), 0),
                MaterialRules.sequence(
                    MaterialRules.condition(
                        MaterialRules.stoneDepth(0, false, 0, VerticalSurfaceType.FLOOR),
                        MaterialRules.sequence(
                            MaterialRules.condition(
                                MaterialRules.water(0, 0),
                                GRASS_BLOCK
                            ),
                            DIRT
                        )
                    ),
                    MaterialRules.condition(
                        MaterialRules.stoneDepth(0, true,0, VerticalSurfaceType.FLOOR),
                        DIRT
                    )
                )
            )
        );

        return MaterialRules.sequence(materialRuleBuilder.build().toArray(MaterialRules.MaterialRule[]::new));
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}
