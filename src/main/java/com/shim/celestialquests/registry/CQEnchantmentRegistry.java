package com.shim.celestialquests.registry;

import com.shim.celestialquests.CelestialQuests;
import com.shim.celestialquests.enchantments.CompletedQuestEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CQEnchantmentRegistry {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, CelestialQuests.MODID);

    public static RegistryObject<Enchantment> COMPLETED_QUEST = ENCHANTMENTS.register("completed_quest", () -> new CompletedQuestEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.values()));


    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
