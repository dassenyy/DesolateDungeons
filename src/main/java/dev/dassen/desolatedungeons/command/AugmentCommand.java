package dev.dassen.desolatedungeons.command;

import com.mojang.brigadier.CommandDispatcher;
import dev.dassen.desolatedungeons.augment.Augment;
import dev.dassen.desolatedungeons.impl.entity.player.AugmentImpl;
import dev.dassen.desolatedungeons.registry.key.ModRegistryKeys;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.RegistryEntryReferenceArgumentType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Collection;

public class AugmentCommand {
    public static void register(
        CommandDispatcher<ServerCommandSource> dispatcher,
        CommandRegistryAccess registryAccess,
        CommandManager.RegistrationEnvironment environment
    ) {
        dispatcher.register(
            CommandManager.literal("augment")
                .requires(source -> source.hasPermissionLevel(2))
                .then(
                    CommandManager.literal("add")
                        .then(
                            CommandManager.argument("target", EntityArgumentType.players())
                                .then(
                                    CommandManager.argument("augment", RegistryEntryReferenceArgumentType.registryEntry(registryAccess, ModRegistryKeys.AUGMENT))
                                        .executes(
                                            context -> executeAdd(
                                                context.getSource(),
                                                EntityArgumentType.getPlayers(context, "target"),
                                                RegistryEntryReferenceArgumentType.getRegistryEntry(context, "augment", ModRegistryKeys.AUGMENT)
                                            )
                                        )
                                )
                        )
                )
                .then(
                    CommandManager.literal("remove")
                        .then(
                            CommandManager.argument("target", EntityArgumentType.players())
                                .then(
                                    CommandManager.argument("augment", RegistryEntryReferenceArgumentType.registryEntry(registryAccess, ModRegistryKeys.AUGMENT))
                                        .executes(
                                            context -> executeRemove(
                                                context.getSource(),
                                                EntityArgumentType.getPlayers(context, "target"),
                                                RegistryEntryReferenceArgumentType.getRegistryEntry(context, "augment", ModRegistryKeys.AUGMENT)
                                            )
                                        )
                                )
                        )
                )
                .then(
                    CommandManager.literal("query")
                        .then(
                            CommandManager.argument("target", EntityArgumentType.player())
                                .executes(
                                    context -> executeQuery(
                                        context.getSource(),
                                        EntityArgumentType.getPlayer(context, "target")
                                    )
                                )
                        )
                )
        );
    }

    private static int executeAdd(ServerCommandSource source, Collection<? extends ServerPlayerEntity> targets, RegistryEntry.Reference<Augment> augment) {
        for (ServerPlayerEntity target : targets) {
            ((AugmentImpl) target).getAugmentInventory().add(augment);
        }

        source.sendFeedback(
            () -> Text.translatable(
                "commands.desolate_dungeons.augment.add.success." + (targets.size() == 1 ? "single" : "multiple"),
                augment.value().name,
                targets.size() == 1 ? targets.iterator().next().getDisplayName() : targets.size()
            ),
            true
        );

        return 1;
    }

    private static int executeRemove(ServerCommandSource source, Collection<? extends ServerPlayerEntity> targets, RegistryEntry.Reference<Augment> augment) {
        for (ServerPlayerEntity target : targets) {
            ((AugmentImpl) target).getAugmentInventory().remove(augment.registryKey());
        }

        source.sendFeedback(
            () -> Text.translatable(
                "commands.desolate_dungeons.augment.remove.success." + (targets.size() == 1 ? "single" : "multiple"),
                augment.value().name,
                targets.size() == 1 ? targets.iterator().next().getDisplayName() : targets.size()
            ),
            true
        );

        return 1;
    }

    private static int executeQuery(ServerCommandSource source, ServerPlayerEntity target) {
        source.sendFeedback(
            () -> Text.translatable(
                "commands.desolate_dungeons.augment.query",
                target.getDisplayName(),
                ((AugmentImpl) target).getAugmentInventory().getSize(),
                ((AugmentImpl) target).getAugmentInventory().toString()
            ),
            false
        );
        return 1;
    }
}
