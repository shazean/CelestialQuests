package com.shim.celestialquests.datagen;

import com.shim.celestialquests.CelestialQuests;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = CelestialQuests.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CQDataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
//            generator.addProvider(new Recipes(generator));
//            generator.addProvider(new LootTables(generator));
//            ModBlockTags blockTags = new ModBlockTags(generator, event.getExistingFileHelper());
//            ModFluidTags fluidTags = new ModFluidTags(generator, CelestialExploration.MODID, event.getExistingFileHelper());

//            generator.addProvider(blockTags);
//            generator.addProvider(fluidTags);
//            generator.addProvider(new ModItemTags(generator, blockTags, event.getExistingFileHelper()));
//            generator.addProvider(new ModBiomeTags(generator, CelestialExploration.MODID, event.getExistingFileHelper()));
        }
        if (event.includeClient()) {
//            generator.addProvider(new BlockStates(generator, event.getExistingFileHelper()));
            generator.addProvider(new CQItemModels(generator, event.getExistingFileHelper()));
            generator.addProvider(new CQLangProvider(generator, "en_us"));

        }
    }

}