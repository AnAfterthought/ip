import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toFileFormat() {
        String task = "D |";
        if (this.isDone) {
            task += " 1 | ";
        } else {
            task += " 0 | ";
        }
        return task + this.description + " | " + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ")";
    }
}
