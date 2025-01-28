package bpluschatter.task;

import java.time.LocalDateTime;

/**
 * Represents a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task
     *
     * @param description Details of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone Completion status of task.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns completion status of task.
     *
     * @return Completion status of task.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns string to be saved in file.
     *
     * @return String to be saved in file.
     */
    public String toFileFormat() {
        return "";
    }

    /**
     * Returns if task's date is the same as specified date.
     *
     * @param dateTime Date and time to be compared.
     * @return If task's date is the same as specified date.
     */
    public boolean isSameDate(LocalDateTime dateTime) {
        return false;
    }

    /**
     * Returns details of task.
     *
     * @return Details of task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
