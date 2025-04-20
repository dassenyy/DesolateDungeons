package dev.dassen.desolatedungeons.world.biome.surface;

import com.google.common.collect.ImmutableList;
import dev.dassen.desolatedungeons.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class ModMaterialRules {
    private static final MaterialRules.MaterialRule BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final MaterialRules.MaterialRule LATERITE_BRICKS = makeStateRule(ModBlocks.LATERITE_BRICKS);
    private static final MaterialRules.MaterialRule LIMESTONE = makeStateRule(ModBlocks.LIMESTONE);
    private static final MaterialRules.MaterialRule PERIDOTITE = makeStateRule(ModBlocks.PERIDOTITE);

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
                MaterialRules.aboveY(YOffset.fixed(256), 0),
                MaterialRules.condition(
                    MaterialRules.stoneDepth(0, true, 3, VerticalSurfaceType.FLOOR),
                    PERIDOTITE
                )
            )
        );
        materialRuleBuilder.add(
            MaterialRules.condition(
                MaterialRules.verticalGradient("laterite_bricks", YOffset.aboveBottom(127), YOffset.fixed(133)),
                LATERITE_BRICKS
            )
        );
        materialRuleBuilder.add(
            MaterialRules.condition(
                MaterialRules.aboveY(YOffset.fixed(128), 0),
                LIMESTONE
            )
        );

        return MaterialRules.sequence(materialRuleBuilder.build().toArray(MaterialRules.MaterialRule[]::new));
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}
