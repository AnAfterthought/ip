public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toFileFormat() {
        String task = "D |";
        if (isDone) {
            task += " 1 | ";
        } else {
            task += " 0 | ";
        }
        return task + description + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
