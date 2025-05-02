package dev.dassen.desolatedungeons.mixin;

import dev.dassen.desolatedungeons.augment.Augment;
import dev.dassen.desolatedungeons.impl.entity.PersistentDataSaver;
import dev.dassen.desolatedungeons.impl.entity.player.AugmentImpl;
import dev.dassen.desolatedungeons.networking.packet.OfferAugmentsPayload;
import dev.dassen.desolatedungeons.registry.key.ModDynamicRegistryKeys;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements PersistentDataSaver, AugmentImpl {
    private NbtCompound persistentData;
    final private RegistryEntry<Augment>[] offeredAugments = new RegistryEntry[3];
    final private List<Augment> augments = new ArrayList<>();

    @Override
    public NbtCompound getPersistentData() {
        if (this.persistentData == null) {
            this.persistentData = new NbtCompound();
        }

        return this.persistentData;
    }

    @Override
    public NbtCompound getOrCreateNbtCompound(NbtCompound parentNbt, String key) {
        if (!parentNbt.contains(key)) {
            NbtCompound nbt = new NbtCompound();
            parentNbt.put(key, nbt);
            return nbt;
        }

        return parentNbt.getCompound(key);
    }

    @Inject(method = "readCustomDataFromNbt(Lnet/minecraft/nbt/NbtCompound;)V", at = @At("HEAD"))
    public void injectReadCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("DesolateDungeon", NbtElement.COMPOUND_TYPE)) {
            persistentData = nbt.getCompound("DesolateDungeon");
        }
    }

    @Inject(method = "writeCustomDataToNbt(Lnet/minecraft/nbt/NbtCompound;)V", at = @At("HEAD"))
    public void injectWriteCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        if (this.persistentData != null) {
            nbt.put("DesolateDungeon", this.persistentData);
        }
    }

    @Override
    public void offerAugments(int addedLevel, int currentLevel) {
        //noinspection DataFlowIssue
        ServerPlayerEntity thisServerPlayer = ((ServerPlayerEntity) (Object) this);

        Registry<Augment> augmentRegistry = thisServerPlayer.getWorld().getRegistryManager().getOrThrow(ModDynamicRegistryKeys.AUGMENT);

        List<RegistryKey<Augment>> augmentKeys = new ArrayList<>(augmentRegistry.getKeys());
        Collections.shuffle(augmentKeys);

        for (int i = 0; i < 3; i++) {
            this.offeredAugments[i] = augmentRegistry.getOrThrow(augmentKeys.get(i));
        }

        ServerPlayNetworking.send(thisServerPlayer, new OfferAugmentsPayload(addedLevel, currentLevel, offeredAugments[0], offeredAugments[1], offeredAugments[2]));
    }

    @Override
    public void pickAugment(RegistryEntry<Augment> augment) {
        //noinspection DataFlowIssue
        PlayerEntity thisPlayer = ((PlayerEntity) (Object) this);

        Registry<Augment> augmentRegistry = thisPlayer.getWorld().getRegistryManager().getOrThrow(ModDynamicRegistryKeys.AUGMENT);

        if (Arrays.stream(offeredAugments).noneMatch(offeredAugment -> offeredAugment == augment)) {
            return;
        }

        augments.add(augment.value());
    }

    @Inject(method = "addExperienceLevels(I)V", at = @At("TAIL"))
    public void onPlayerLevelUp(int level, CallbackInfo ci) {
        //noinspection DataFlowIssue
        ServerPlayerEntity thisServerPlayer = ((ServerPlayerEntity) (Object) this);

        offerAugments(level, thisServerPlayer.experienceLevel);
    }
}