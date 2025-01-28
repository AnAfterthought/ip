package bpluschatter.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructor for Deadline.
     *
     * @param description Details of task.
     * @param by Date and time to complete task by.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns string to be saved in file.
     *
     * @return String to be saved in file.
     */
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

    /**
     * Returns if task's date is the same as specified date.
     *
     * @param dateTime Date and time to be compared.
     * @return If task's date is the same as specified date.
     */
    @Override
    public boolean isSameDate(LocalDateTime dateTime) {
        return this.by.toLocalDate().equals(dateTime.toLocalDate());
    }

    /**
     * Returns details of task.
     *
     * @return Details of task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ")";
    }
}
