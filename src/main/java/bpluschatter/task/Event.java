package bpluschatter.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bpluschatter.enumerations.Priority;

/**
 * Represents an event task.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for Event.
     *
     * @param description Details of task.
     * @param from Start of event.
     * @param to End of event.
     */
    public Event(String description, Priority priority, LocalDateTime from, LocalDateTime to) {
        super(description, priority);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns string to be saved in file.
     *
     * @return String to be saved in file.
     */
    @Override
    public String toFileFormat() {
        String task = "E |";
        task += isDone ? " 1 | " : " 0 | ";
        return task + this.description + " | "
                + this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " | "
                + this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                + " | " + this.priority.toString();
    }

    /**
     * Returns if task's date is the same as specified date.
     *
     * @param dateTime Date and time to be compared.
     * @return If task's date is the same as specified date.
     */
    @Override
    public boolean isSameDate(LocalDateTime dateTime) {
        return this.from.toLocalDate().equals(dateTime.toLocalDate());
    }

    /**
     * Returns details of task.
     *
     * @return Details of task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"))
                .replace("AM", "am")
                .replace("PM", "pm")
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"))
                .replace("AM", "am")
                .replace("PM", "pm") + ")";
    }
}
