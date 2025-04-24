package dev.dassen.desolatedungeons.mixin;

import dev.dassen.desolatedungeons.util.PersistentDataSaver;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements PersistentDataSaver {
    private NbtCompound persistentData;

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
}