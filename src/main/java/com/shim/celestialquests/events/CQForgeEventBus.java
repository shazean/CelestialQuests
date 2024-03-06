package com.shim.celestialquests.events;

import com.mojang.brigadier.CommandDispatcher;
import com.shim.celestialquests.CelestialQuests;
import com.shim.celestialquests.capabilities.QuestHandler;
import com.shim.celestialquests.commands.QuestCommand;
import com.shim.celestialquests.quests.ExterminateTask;
import com.shim.celestialquests.quests.GatherTask;
import com.shim.celestialquests.registry.CQCapabilityRegistry;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CelestialQuests.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CQForgeEventBus {
    //to future me: make sure these are static!
    //sincerely, past frustrated me

    @SubscribeEvent
    public static void onRegisterCommandEvent(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> commandDispatcher = event.getDispatcher();
        QuestCommand.register(commandDispatcher);
    }

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if (event.getSource() instanceof EntityDamageSource entityDamageSource) {
            if (entityDamageSource.getEntity() instanceof Player player) { //attack source is player
                QuestHandler cap = CelestialQuests.getCapability(player, CQCapabilityRegistry.QUEST_HANDLER_CAPABILITY);

                if (cap != null) {
                    if (cap.getCurrentTask() instanceof ExterminateTask task) { //player has an exterminate task
                        if (event.getEntity().getType() == task.getEntity()) { //source entity is the same
                            if (!task.isComplete()) task.updateProgress();

                            if (task.isComplete()) {
                                player.displayClientMessage(new TextComponent("Task complete!"), true);
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onItemPickup(EntityItemPickupEvent event) {
        if (event.getEntity() instanceof Player player) { //entity is player
            QuestHandler cap = CelestialQuests.getCapability(player, CQCapabilityRegistry.QUEST_HANDLER_CAPABILITY);

            if (cap != null) {
                if (cap.getCurrentTask() instanceof GatherTask task) { //player has gather task
                    if (event.getItem().getItem().getItem() == task.getItem()) { //item is the same as task
                        if (!task.isComplete()) task.updateProgress();

                        if (task.isComplete()) {
                            player.displayClientMessage(new TextComponent("Completed task: " + task.getTitle() + "!"), true);
                        }
                    }
                }

            }
        }
    }
}
