import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    private final DateTimeFormatter dateTimeFormatter;

    Parser() {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    }

    Task parseFromFile(String taskString) {
        String[] taskParts = taskString.split(" \\| ");
        Task newTask = new Task(taskParts[2]);
        if (taskParts[0].equals("T")) {
            newTask = new ToDo(taskParts[2]);
        } else if (taskParts[0].equals("D")) {
            LocalDateTime by = LocalDateTime.parse(taskParts[3], dateTimeFormatter);
            newTask = new Deadline(taskParts[2], by);
        } else if (taskParts[0].equals("E")) {
            LocalDateTime from = LocalDateTime.parse(taskParts[3], dateTimeFormatter);
            LocalDateTime to = LocalDateTime.parse(taskParts[4], dateTimeFormatter);
            newTask = new Event(taskParts[2], from, to);
        }
        if (taskParts[1].equals("1")) {
            newTask.setIsDone(true);
        }
        return newTask;
    }
}
