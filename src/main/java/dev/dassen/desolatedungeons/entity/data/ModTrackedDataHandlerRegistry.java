package dev.dassen.desolatedungeons.entity.data;

import dev.dassen.desolatedungeons.entity.ScarabBeetleEntity;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;

public class ModTrackedDataHandlerRegistry {
    public static final TrackedDataHandler<ScarabBeetleEntity.ControlState> SCARAB_BEETLE_CONTROL_STATE;

    static {
        SCARAB_BEETLE_CONTROL_STATE = TrackedDataHandler.create(ScarabBeetleEntity.ControlState.PACKET_CODEC);
        TrackedDataHandlerRegistry.register(SCARAB_BEETLE_CONTROL_STATE);
    }
}
