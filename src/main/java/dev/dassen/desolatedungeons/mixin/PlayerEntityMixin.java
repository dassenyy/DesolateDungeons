package dev.dassen.desolatedungeons.mixin;

import dev.dassen.desolatedungeons.augment.AugmentInventory;
import dev.dassen.desolatedungeons.impl.entity.player.PlayerPersistentDataSaver;
import dev.dassen.desolatedungeons.impl.entity.player.AugmentImpl;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("unused")
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements PlayerPersistentDataSaver, AugmentImpl {
    private NbtCompound stashedPlayerData;
    private final AugmentInventory augmentInventory = new AugmentInventory((PlayerEntity) (Object) this);

    @Override
    public AugmentInventory getAugmentInventory() {
        return augmentInventory;
    }

    @Override
    public NbtCompound getStashedPlayerData() {
        if (this.stashedPlayerData == null) {
            this.stashedPlayerData = new NbtCompound();
        }

        return this.stashedPlayerData;
    }

    @Inject(method = "readCustomDataFromNbt(Lnet/minecraft/nbt/NbtCompound;)V", at = @At("HEAD"))
    public void injectReadCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("desolate_dungeons.stashed_player_data", NbtElement.COMPOUND_TYPE)) {
            stashedPlayerData = nbt.getCompound("desolate_dungeons.stashed_player_data");
        }
        this.augmentInventory.readNbt(nbt.getList("desolate_dungeons.augment_inventory", NbtElement.STRING_TYPE));
    }

    @Inject(method = "writeCustomDataToNbt(Lnet/minecraft/nbt/NbtCompound;)V", at = @At("HEAD"))
    public void injectWriteCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        if (this.stashedPlayerData != null) {
            nbt.put("desolate_dungeons.stashed_player_data", this.stashedPlayerData);
        }
        nbt.put("desolate_dungeons.augment_inventory", this.augmentInventory.writeNbt(new NbtList()));
    }

    @Inject(method = "addExperienceLevels(I)V", at = @At("TAIL"))
    public void onPlayerLevelUp(int level, CallbackInfo ci) {
        //noinspection DataFlowIssue
        ServerPlayerEntity thisServerPlayer = ((ServerPlayerEntity) (Object) this);

        augmentInventory.offerAugments(level, thisServerPlayer.experienceLevel);
    }
}