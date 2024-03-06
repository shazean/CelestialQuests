package com.shim.celestialquests.datagen;

import com.shim.celestialquests.CelestialQuests;
import com.shim.celestialquests.registry.CQItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class CQItemModels extends ItemModelProvider {

    public CQItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, CelestialQuests.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //---- MOON -------------------------------------------------------------------------------
        //---- MARS -------------------------------------------------------------------------------
        //---- VENUS -------------------------------------------------------------------------------
        //---- CELESTIAL OBJECTS -------------------------------------------------------------------------------
        //---- OTHER PLANETS -------------------------------------------------------------------------------
        //---- ORE-RELATED -------------------------------------------------------------------------------
        //---- SPACESHIP -------------------------------------------------------------------------------
        //---- SPACE STATION -------------------------------------------------------------------------------
        //---- MAG LEV -------------------------------------------------------------------------------
        //---- ARMOR -------------------------------------------------------------------------------
        //---- GLASS/CERAMICS -------------------------------------------------------------------------------
        //---- FOOD -------------------------------------------------------------------------------
        //---- MISC -------------------------------------------------------------------------------
        singleTexture(CQItemRegistry.QUEST_BOOK.get().getRegistryName().getPath(), new ResourceLocation("item/generated"), "layer0", new ResourceLocation("item/book"));

    }
}