package com.shim.celestialquests;

import com.shim.celestialquests.registry.CQCapabilityRegistry;
import com.shim.celestialquests.registry.CQEnchantmentRegistry;
import com.shim.celestialquests.registry.CQItemRegistry;
import com.shim.celestialquests.registry.CQVillagerRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.stream.Collectors;

@Mod("celestialquests")
public class CelestialQuests {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "celestialquests";

    public CelestialQuests() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus bus = MinecraftForge.EVENT_BUS;

        // Register the setup method for modloading
        modEventBus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        modEventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        modEventBus.addListener(this::processIMC);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

//        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
//            modEventBus.addListener(this::clientSetup);
//        });

        CQItemRegistry.register(modEventBus);
//        BlockRegistry.register(modEventBus);
//        ContainerRegistry.register(modEventBus);
//        BlockEntityRegistry.register(modEventBus);
//        MenuRegistry.register(modEventBus);
//        EntityRegistry.register(modEventBus);
//        FeatureRegistry.register(modEventBus);
//        FluidRegistry.register(modEventBus);
//        StructureRegistry.DEFERRED_REGISTRY_STRUCTURE.register(modEventBus);
//        ParticleRegistry.register(modEventBus);
//        RecipeRegistry.register(modEventBus);
        CQVillagerRegistry.register(modEventBus);

        CQEnchantmentRegistry.register(modEventBus);

        modEventBus.addListener(CQCapabilityRegistry::registerCapabilities);
        MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, CQCapabilityRegistry::attachEntityCapabilities);

//        bus.addListener(EventPriority.NORMAL, Structures::addDimensionalSpacing);
//        bus.addListener(EventPriority.NORMAL, Structures::setupStructureSpawns);

//        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CelestialClientConfig.SPEC, "celestialexploration-client.toml");
//        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CelestialCommonConfig.SPEC, "celestialexploration-common.toml");

//        GeckoLib.initialize();

    }


    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

        event.enqueueWork(() -> {

            CQVillagerRegistry.registerPOIs();

        });
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo(MODID, "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.messageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    @Nullable
    public static <T> T getCapability(Entity entity, Capability<T> capability) {
        if (entity == null) return null;
        return entity.getCapability(capability).isPresent() ? entity.getCapability(capability).orElseThrow(() -> new IllegalArgumentException("Lazy optional must not be empty")) : null;
    }
}
