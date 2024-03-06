package com.shim.celestialquests.quests;

import java.util.List;

public class Quest {
    private final int id;
    private final String title;
    private final String description;
    private final List<? extends AbstractTask> tasks;
    boolean isComplete = false;

    public Quest(int id, String title, String description, List<? extends AbstractTask> tasks) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<? extends AbstractTask> getTasks() {
        return tasks;
    }

    public void setComplete() {
        isComplete = true;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public boolean checkComplete() {
        for (AbstractTask task : this.tasks) {
            if (!task.isComplete()) return false;
        }
        setComplete();
        return true;
    }
}