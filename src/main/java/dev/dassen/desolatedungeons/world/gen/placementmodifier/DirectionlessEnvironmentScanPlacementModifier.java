package dev.dassen.desolatedungeons.world.gen.placementmodifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class DirectionlessEnvironmentScanPlacementModifier extends PlacementModifier {
    public record WeightedBlockPosition(int x, int y, int z, int weight) {}

    private final BlockPredicate targetPredicate;
    private final int maxTaxicabDistance;
    private final List<WeightedBlockPosition> relativePositionsToCheck;

    public static final MapCodec<DirectionlessEnvironmentScanPlacementModifier> MODIFIER_CODEC =
        RecordCodecBuilder.mapCodec(
            (instance) -> instance.group(
                BlockPredicate.BASE_CODEC.fieldOf("target_condition")
                    .forGetter(DESPlacementModifier -> DESPlacementModifier.targetPredicate),
                Codec.intRange(1, 3).fieldOf("max_taxicab_distance")
                    .forGetter(DESPlacementModifier -> DESPlacementModifier.maxTaxicabDistance)
            )
            .apply(instance, DirectionlessEnvironmentScanPlacementModifier::new)
        );

    private DirectionlessEnvironmentScanPlacementModifier(BlockPredicate targetPredicate, int maxTaxicabDistance) {
        this.targetPredicate = targetPredicate;
        this.maxTaxicabDistance = maxTaxicabDistance;

        this.relativePositionsToCheck = new ArrayList<WeightedBlockPosition>();

        // Compute all relative positions around a block to check in the getPositions() method based on the given maxTaxicabDistance
        for (int x = -this.maxTaxicabDistance; x <= this.maxTaxicabDistance; x++) {
            for (int y = -this.maxTaxicabDistance; y <= this.maxTaxicabDistance; y++) {
                for (int z = -this.maxTaxicabDistance; z <= this.maxTaxicabDistance; z++) {
                    int weight = Math.abs(x) + Math.abs(y) + Math.abs(z);
                    if (weight <= this.maxTaxicabDistance) {
                        relativePositionsToCheck.add(new WeightedBlockPosition(x, y, z, weight));
                    }
                }
            }
        }

        relativePositionsToCheck.sort(Comparator.comparingInt(weightedBlockPosition -> weightedBlockPosition.weight));
    }

    /**
     * @param maxTaxicabDistance This parameter accepts a minimum value of 1 or a maximum value of 3
     */
    public static DirectionlessEnvironmentScanPlacementModifier of(BlockPredicate targetPredicate, int maxTaxicabDistance) {
        if (maxTaxicabDistance < 1 || maxTaxicabDistance > 3) {
            throw new IllegalArgumentException("Parameter maxTaxicabDistance must be between 1 and 3");
        }
        return new DirectionlessEnvironmentScanPlacementModifier(targetPredicate, maxTaxicabDistance);
    }

    @Override
    public Stream<BlockPos> getPositions(FeaturePlacementContext context, Random random, BlockPos blockPosition) {
        BlockPos.Mutable mutableBlockPosition = blockPosition.mutableCopy();
        StructureWorldAccess structureWorldAccess = context.getWorld();

        if (this.targetPredicate.test(structureWorldAccess, mutableBlockPosition)) {
            return Stream.of(mutableBlockPosition);
        }

        for (int i = 0; i < this.relativePositionsToCheck.size(); i++) {
            WeightedBlockPosition relativePosition = this.relativePositionsToCheck.get(i);

            mutableBlockPosition.move(relativePosition.x, relativePosition.y, relativePosition.z);

            if (structureWorldAccess.isOutOfHeightLimit(mutableBlockPosition.getY())) {
                mutableBlockPosition.move(-relativePosition.x, -relativePosition.y, -relativePosition.z);
                continue;
            }

            if (this.targetPredicate.test(structureWorldAccess, mutableBlockPosition)) {
                return Stream.of(mutableBlockPosition);
            }

            mutableBlockPosition.move(-relativePosition.x, -relativePosition.y, -relativePosition.z);
        }

        return Stream.of();
    }

    @Override
    public PlacementModifierType<?> getType() {
        return DesolatePlacementModifiers.DIRECTIONLESS_ENVIRONMENT_SCAN;
    }
}
