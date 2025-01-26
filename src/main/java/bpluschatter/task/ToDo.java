package bpluschatter.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

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

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
