package com.shim.celestialquests.events;

import com.shim.celestialquests.CelestialQuests;
import com.shim.celestialquests.registry.CQEnchantmentRegistry;
import com.shim.celestialquests.registry.CQItemRegistry;
import com.shim.celestialquests.registry.CQVillagerRegistry;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = CelestialQuests.MODID)
public class CQEventBusEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == CQVillagerRegistry.ASTRONOMER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            ItemStack questToken = new ItemStack(Items.COBBLESTONE, 1);
            ItemStack questBook = new ItemStack(CQItemRegistry.QUEST_BOOK.get(), 1);
            ItemStack returnQuest = new ItemStack(CQItemRegistry.QUEST_BOOK.get(), 1);
            ItemStack rewardItem = new ItemStack(Items.SUGAR, 1);

            int villagerLevel = 1;

            //kill zombies quest
            questBook.setHoverName(new TextComponent("Kill 10 Zombies Quest"));
            returnQuest.setHoverName(new TextComponent("Kill 10 Zombies Quest"));

            trades.get(villagerLevel).add((trader, rand) -> offer(
                    questToken, questBook,1,1,0.02F));

            returnQuest.enchant(CQEnchantmentRegistry.COMPLETED_QUEST.get(), 1);

            trades.get(villagerLevel).add((trader, rand) -> offer(
                    returnQuest, rewardItem,1,1,0.02F));

            //kill zombies quest
            ItemStack skeletonQuestBook = new ItemStack(CQItemRegistry.QUEST_BOOK.get(), 1);
            ItemStack skeletonReturnQuest = new ItemStack(CQItemRegistry.QUEST_BOOK.get(), 1);
            ItemStack skeletonRewardItem = new ItemStack(Items.SUGAR, 1);

            skeletonQuestBook.setHoverName(new TextComponent("Kill 10 Skeletons Quest"));
            skeletonReturnQuest.setHoverName(new TextComponent("Kill 10 Skeletons Quest"));
            skeletonReturnQuest.enchant(CQEnchantmentRegistry.COMPLETED_QUEST.get(), 1);

            trades.get(villagerLevel).add((trader, rand) -> offer(
                    questToken, skeletonQuestBook,1,1,0.02F));

            trades.get(villagerLevel).add((trader, rand) -> offer(
                    skeletonReturnQuest, skeletonRewardItem,1,1,0.02F));
        }
    }

    public static MerchantOffer offer(ItemStack baseCostA, ItemStack result, int maxUses, int xp, float priceMultiplier) {
        return offer(baseCostA, ItemStack.EMPTY, result, maxUses, xp, priceMultiplier);
    }

    public static MerchantOffer offer(ItemStack baseCostA, ItemStack costB, ItemStack result, int maxUses, int xp, float priceMultiplier) {
        return offer(baseCostA, costB, result, 0, maxUses, xp, priceMultiplier);
    }

    public static MerchantOffer offer(ItemStack baseCostA, ItemStack costB, ItemStack result, int uses, int maxUses, int xp, float priceMultiplier) {
        return offer(baseCostA, costB, result, uses, maxUses, xp, priceMultiplier, 0);
    }

    public static MerchantOffer offer(ItemStack baseCostA, ItemStack costB, ItemStack result, int uses, int maxUses, int xp, float priceMultiplier, int demand) {
        return new MerchantOffer(baseCostA, costB, result, uses, maxUses, xp, priceMultiplier, demand);
    }

}
