package com.shim.celestialquests.capabilities;

import com.shim.celestialquests.quests.AbstractTask;
import com.shim.celestialquests.quests.Quest;
import net.minecraft.nbt.CompoundTag;

public interface IQuest {
    Quest getCurrentQuest();
    void setCurrentQuest(Quest quest);
    boolean needsNewQuest();

    AbstractTask getCurrentTask();
    void setCurrentTask(AbstractTask task);
    boolean needsNewTask();
    void updateTaskProgression();

    boolean checkQuestCompletion();
    boolean checkTaskCompletion();

    CompoundTag getData();
    void setData(CompoundTag nbt);
}
