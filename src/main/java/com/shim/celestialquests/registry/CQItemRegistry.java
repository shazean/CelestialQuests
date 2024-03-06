package com.shim.celestialquests.registry;

import com.shim.celestialquests.CelestialQuests;
import net.minecraft.world.item.BookItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CQItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CelestialQuests.MODID);

    public static void register(IEventBus event) {
        ITEMS.register(event);
    }


    public static final RegistryObject<Item> QUEST_BOOK = ITEMS.register("quest_book", () -> new BookItem((new Item.Properties()).tab(CreativeModeTab.TAB_MATERIALS)));

}
