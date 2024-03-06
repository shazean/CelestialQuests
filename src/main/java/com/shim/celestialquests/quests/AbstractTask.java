package com.shim.celestialquests.quests;

public abstract class AbstractTask {
    int id;
    String title;
    String description = null;
    boolean isComplete = false;
    int progress;
    int quantity;

    public AbstractTask(int id, String title, String description, int quantity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.quantity = quantity;
    }

    public AbstractTask(int id, String title, int quantity) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setComplete() {
        isComplete = true;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void updateProgress() {
        if (!this.isComplete) {
            this.progress++;
            if (this.progress >= this.quantity) {
                this.setComplete();
            }
        }
    }

    public int getProgress() {
        return this.progress;
    }
}
