package dev.dassen.desolatedungeons.command;

import dev.dassen.desolatedungeons.DesolateDungeons;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class ModCommandManager {
    public static void register() {
        DesolateDungeons.LOGGER.info("Registering Commands for " + DesolateDungeons.MOD_ID);
        CommandRegistrationCallback.EVENT.register(AugmentCommand::register);
    }
}
