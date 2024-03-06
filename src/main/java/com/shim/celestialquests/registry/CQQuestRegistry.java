package com.shim.celestialquests.registry;

import com.shim.celestialquests.quests.ExterminateTask;
import com.shim.celestialquests.quests.GatherTask;
import com.shim.celestialquests.quests.Quest;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;

import java.util.Arrays;

public class CQQuestRegistry {

    final Quest TEST_QUEST = new Quest(1, "Test first quest", "idk", Arrays.asList(
            new ExterminateTask(1, "Kill 10 zombies", "kill zombies", EntityType.ZOMBIE, 10),
            new GatherTask(2, "Gather 16 cobblestone", "gather cobblestone", Items.COBBLESTONE, 16)));

}
