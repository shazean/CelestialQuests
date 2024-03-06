package com.shim.celestialquests.registry;

import com.google.common.collect.ImmutableSet;
import com.shim.celestialexploration.registry.BlockRegistry;
import com.shim.celestialquests.CelestialQuests;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;

public class CQVillagerRegistry {
    public static final DeferredRegister<PoiType> POI_TYPES
            = DeferredRegister.create(ForgeRegistries.POI_TYPES, CelestialQuests.MODID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS
            = DeferredRegister.create(ForgeRegistries.PROFESSIONS, CelestialQuests.MODID);

    public static final RegistryObject<PoiType> ASTRONOMY_POI = POI_TYPES.register("astronomy_poi",
            () -> new PoiType("astronomy_poi", PoiType.getBlockStates(BlockRegistry.OXYGEN_COMPRESSOR.get()), 1, 1)); //FIXME

    public static final RegistryObject<VillagerProfession> ASTRONOMER =
            VILLAGER_PROFESSIONS.register("astronomer",
                    () -> new VillagerProfession("astronomer", ASTRONOMY_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_CARTOGRAPHER));


    public static void registerPOIs() {
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class,
                    "registerBlockStates", PoiType.class).invoke(null, ASTRONOMY_POI.get());
        } catch(InvocationTargetException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}