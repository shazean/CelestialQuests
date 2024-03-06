package com.shim.celestialquests.quests;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

public class ExterminateTask extends AbstractTask {
    EntityType<? extends LivingEntity> taskObjective;
    int quantity;
    int progress;

    public ExterminateTask(int id, String title, String description, EntityType<? extends LivingEntity> taskObjective, int quantity) {
        super(id, title, description, quantity);

        this.taskObjective = taskObjective;
    }

    public ExterminateTask(int id, String title, EntityType<? extends LivingEntity> taskObjective, int quantity) {
        super(id, title, quantity);

        this.taskObjective = taskObjective;
    }

    public EntityType<? extends LivingEntity> getEntity() {
        return taskObjective;
    }
}
