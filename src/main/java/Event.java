import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileFormat() {
        String task = "E |";
        if (this.isDone) {
            task += " 1 | ";
        } else {
            task += " 0 | ";
        }
        return task + this.description + " | " +
                this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " | " +
                this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) +
                " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) +")";
    }
}
