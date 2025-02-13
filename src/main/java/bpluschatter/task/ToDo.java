package bpluschatter.task;

import bpluschatter.enumerations.Priority;

/**
 * Represents todo task.
 */
public class ToDo extends Task {
    public ToDo(String description, Priority priority) {
        super(description, priority);
    }

    /**
     * Returns string to be saved in file.
     *
     * @return String to be saved in file.
     */
    @Override
    public String toFileFormat() {
        String task = "T |";
        task += isDone ? " 1 | " : " 0 | ";
        return task + description + " | " + this.priority.toString();
    }

    /**
     * Returns details of task.
     *
     * @return Details of task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
