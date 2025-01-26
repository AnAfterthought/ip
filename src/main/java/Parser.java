import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private final DateTimeFormatter dateTimeFormatter;

    Parser() {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    }

    TaskList parseToDo(String details, TaskList tasks, Ui ui) throws InvalidToDoException {
        if (details.isEmpty()) {
            throw new InvalidToDoException();
        }

        ToDo toDo = new ToDo(details);
        tasks.add(toDo);
        ui.showAdd(toDo, tasks);

        return tasks;
    }

    TaskList parseDeadline(String details, TaskList tasks, Ui ui) throws DateTimeParseException,
            InvalidDeadlineException {
        String[] detailParts = details.split(" /by ", 2);
        if (detailParts.length != 2) {
            throw new InvalidDeadlineException();
        }

        LocalDateTime by = LocalDateTime.parse(detailParts[1], dateTimeFormatter);
        Deadline deadline = new Deadline(detailParts[0], by);
        tasks.add(deadline);
        ui.showAdd(deadline, tasks);

        return tasks;
    }

    TaskList parseEvent(String details, TaskList tasks, Ui ui) throws DateTimeParseException,
            InvalidEventException {
        if (details.isEmpty()) {
            throw new InvalidEventException();
        }

        String[] detailParts = details.split(" /from ", 2);
        if (detailParts.length != 2) {
            throw new InvalidEventException();
        }
        String[] duration = detailParts[1].split(" /to ", 2);
        if (duration.length != 2) {
            throw new InvalidEventException();
        }

        LocalDateTime from = LocalDateTime.parse(duration[0], dateTimeFormatter);
        LocalDateTime to = LocalDateTime.parse(duration[1], dateTimeFormatter);
        Event event = new Event(detailParts[0], from, to);
        tasks.add(event);
        ui.showAdd(event, tasks);

        return tasks;
    }

    TaskList parseCommand(String userInput, TaskList tasks, Ui ui) {
        String[] taskParts = userInput.split(" ", 2);
        String command = taskParts[0];
        String details = "";
        if (taskParts.length == 2) {
            details = taskParts[1];
        }
        try {
            switch (command.toLowerCase()) {
                case "list" -> tasks.list();
                case "todo" -> {
                    return parseToDo(details, tasks, ui);
                }
                case "deadline" -> {
                    return parseDeadline(details, tasks, ui);
                }
                case "event" -> {
                    return parseEvent(details, tasks, ui);
                }
                default -> throw new UnknownCommandException();
            }
        } catch (UnknownCommandException e) {
            ui.showUnknownCommandError();
        } catch (InvalidToDoException e) {
            ui.showToDoError();
        } catch (InvalidDeadlineException e) {
            ui.showDeadlineError();
        } catch (InvalidEventException e) {
            ui.showEventError();
        } catch (DateTimeParseException e) {
            ui.showDateTimeFormatError();
        }
        return tasks;
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
