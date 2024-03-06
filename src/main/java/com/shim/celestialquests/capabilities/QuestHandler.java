package com.shim.celestialquests.capabilities;

import com.shim.celestialquests.quests.AbstractTask;
import com.shim.celestialquests.quests.Quest;
import com.shim.celestialquests.registry.CQCapabilityRegistry;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class QuestHandler implements IQuest{
    Quest currentQuest;
    AbstractTask currentTask;

    @Override
    public Quest getCurrentQuest() {
        return currentQuest;
    }

    @Override
    public void setCurrentQuest(Quest quest) {
        currentQuest = quest;
    }

    @Override
    public boolean needsNewQuest() {
        return currentQuest == null;
    }

    @Override
    public AbstractTask getCurrentTask() {
        return currentTask;
    }

    @Override
    public void setCurrentTask(AbstractTask task) {
        currentTask = task;
    }

    @Override
    public boolean needsNewTask() {
        return currentTask == null;
    }

    @Override
    public void updateTaskProgression() {
        currentTask.updateProgress();
    }

    @Override
    public boolean checkQuestCompletion() {
        return currentQuest.checkComplete();
    }

    @Override
    public boolean checkTaskCompletion() {
        return currentTask.isComplete();
    }

    @Override
    public CompoundTag getData() {
        //TODO
        CompoundTag nbt = new CompoundTag();
        return nbt;
    }

    @Override
    public void setData(CompoundTag nbt) {
        //TODO
    }

    public static class QuestCapabilityProvider implements ICapabilityProvider, ICapabilitySerializable<CompoundTag> {

        public static Capability<QuestHandler> QUEST_HANDLER = CapabilityManager.get(new CapabilityToken<>(){});
        private QuestHandler questHandler = null;
        private final LazyOptional<QuestHandler> lazyQuestHandler = LazyOptional.of(this::createQuestHandler);

        @Nonnull
        private QuestHandler createQuestHandler() {
            if (questHandler == null) {
                questHandler = new QuestHandler();
            }
            return questHandler;
        }

        @NotNull
        @Override
        public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            return CQCapabilityRegistry.QUEST_HANDLER_CAPABILITY.orEmpty(cap, lazyQuestHandler.cast());
        }

        @Override
        public CompoundTag serializeNBT() {
            return lazyQuestHandler.orElseThrow(NullPointerException::new).getData();
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            lazyQuestHandler.orElseThrow(NullPointerException::new).setData(nbt);
        }
    }
}
