package bpluschatter.task;

import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String toFileFormat() {
        return "";
    }

    public boolean isSameDate(LocalDateTime dateTime) {
        return false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
