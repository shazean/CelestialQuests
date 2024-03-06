package com.shim.celestialquests.registry;

import com.shim.celestialquests.CelestialQuests;
import com.shim.celestialquests.capabilities.QuestHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;

public class CQCapabilityRegistry {

    public static final Capability<QuestHandler> QUEST_HANDLER_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});

    public static void registerCapabilities(RegisterCapabilitiesEvent eventIn) {
        eventIn.register(QuestHandler.QuestCapabilityProvider.class);
    }

    public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> eventIn) {
        if (eventIn.getObject() instanceof Player) {
            if (!eventIn.getObject().getCapability(QuestHandler.QuestCapabilityProvider.QUEST_HANDLER).isPresent()) {
                eventIn.addCapability(new ResourceLocation(CelestialQuests.MODID, "quest_handler"), new QuestHandler.QuestCapabilityProvider());
            }
        }
    }
}