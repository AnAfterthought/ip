package bpluschatter.task;

/**
 * Represents todo task.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns string to be saved in file.
     *
     * @return String to be saved in file.
     */
    @Override
    public String toFileFormat() {
        String task = "T |";
        if (isDone) {
            task += " 1 | ";
        } else {
            task += " 0 | ";
        }
        return task + description;
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
