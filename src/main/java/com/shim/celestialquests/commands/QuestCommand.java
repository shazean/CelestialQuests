package com.shim.celestialquests.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.shim.celestialquests.CelestialQuests;
import com.shim.celestialquests.capabilities.QuestHandler;
import com.shim.celestialquests.quests.ExterminateTask;
import com.shim.celestialquests.quests.GatherTask;
import com.shim.celestialquests.quests.Quest;
import com.shim.celestialquests.registry.CQCapabilityRegistry;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import java.util.List;

public class QuestCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> questCommand =
                Commands.literal("quest").requires((commandSource) -> commandSource.hasPermission(2))
                .then(Commands.argument("id", IntegerArgumentType.integer())
                // /quest <id> exterminate <entity> <quantity>
                .then(Commands.literal("exterminate")
                    .then(Commands.argument("entity", EntityArgument.entity())
                        .then(Commands.argument("quantity", IntegerArgumentType.integer())
                            .executes(QuestCommand::createExterminateTask))))
                // /quest <id> gather <item> <quantity>
                .then(Commands.literal("gather")
                        .then(Commands.argument("item", ItemArgument.item())
                                .then(Commands.argument("quantity", IntegerArgumentType.integer())
                                        .executes(QuestCommand::createGatherTask)))));


        dispatcher.register(questCommand);
    }

    private static int createGatherTask(CommandContext<CommandSourceStack> commandSourceStackCommandContext) {
        int questId = IntegerArgumentType.getInteger(commandSourceStackCommandContext, "id");
        Item item = ItemArgument.getItem(commandSourceStackCommandContext, "item").getItem();
        int quantity = IntegerArgumentType.getInteger(commandSourceStackCommandContext, "quantity");

        String questTitle = "Quest #" + questId;
        String taskTitle = "Gather " + quantity + " " + item + "s";

        GatherTask task = new GatherTask(1, taskTitle, "task", item, quantity);

        final Quest TEST_QUEST = new Quest(questId, questTitle, "idk", List.of(task));

        Entity entity = commandSourceStackCommandContext.getSource().getEntity();
        if (entity instanceof Player player) {
            QuestHandler cap = CelestialQuests.getCapability(player, CQCapabilityRegistry.QUEST_HANDLER_CAPABILITY);

            CelestialQuests.LOGGER.debug("cap is: " + cap);

            if (cap != null) {
                CelestialQuests.LOGGER.debug("cap is not null");
                cap.setCurrentQuest(TEST_QUEST);
                cap.setCurrentTask(task);
            }
        }

        return 1;
    }

    private static int createExterminateTask(CommandContext<CommandSourceStack> commandSourceStackCommandContext) throws CommandSyntaxException {
        int questId = IntegerArgumentType.getInteger(commandSourceStackCommandContext, "id");
        Entity entity = EntityArgument.getEntity(commandSourceStackCommandContext, "entity");
        int quantity = IntegerArgumentType.getInteger(commandSourceStackCommandContext, "quantity");

        String questTitle = "Quest #" + questId;
        String taskTitle = "Kill " + quantity + " " + entity.getDisplayName() + "s";

        ExterminateTask task = new ExterminateTask(1, taskTitle, "task", (EntityType<? extends LivingEntity>) entity.getType(), quantity);

        final Quest TEST_QUEST = new Quest(questId, questTitle, "idk", List.of(task));

        Entity sourceEntity = commandSourceStackCommandContext.getSource().getEntity();
        if (sourceEntity instanceof Player player) {
            QuestHandler cap = CelestialQuests.getCapability(player, CQCapabilityRegistry.QUEST_HANDLER_CAPABILITY);

            if (cap != null) {
                cap.setCurrentQuest(TEST_QUEST);
                cap.setCurrentTask(task);
            }
        }

        return 1;
    }
}