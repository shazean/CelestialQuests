package com.shim.celestialquests.quests;

import net.minecraft.world.item.Item;

public class GatherTask extends AbstractTask {
    Item taskObjective;

    public GatherTask(int id, String title, String description, Item taskObjective, int quantity) {
        super(id, title, description, quantity);

        this.taskObjective = taskObjective;
    }

    public GatherTask(int id, String title, Item taskObjective, int quantity) {
        super(id, title, quantity);

        this.taskObjective = taskObjective;
    }

    public Item getItem() {
        return taskObjective;
    }
}
