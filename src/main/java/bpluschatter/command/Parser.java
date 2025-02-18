package bpluschatter.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import bpluschatter.enumerations.Priority;
import bpluschatter.exception.InvalidDeadlineException;
import bpluschatter.exception.InvalidDeleteException;
import bpluschatter.exception.InvalidEventException;
import bpluschatter.exception.InvalidFileFormatException;
import bpluschatter.exception.InvalidMarkException;
import bpluschatter.exception.InvalidOnException;
import bpluschatter.exception.InvalidToDoException;
import bpluschatter.exception.UnknownCommandException;
import bpluschatter.exception.UnknownPriorityException;
import bpluschatter.task.Deadline;
import bpluschatter.task.Event;
import bpluschatter.task.Task;
import bpluschatter.task.TaskList;
import bpluschatter.task.ToDo;
import bpluschatter.ui.Ui;

/**
 * Represents a parser to make sense of user commands.
 * The parser can also make sense of tasks from save files.
 */

public class Parser {

    private final DateTimeFormatter dateTimeFormatter;

    /**
     * Constructor for Parser.
     * Sets the DateTimeFormatter object for parsing date and time strings.
     */
    public Parser() {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    }

    private String[] getDescriptionAndPriorityAsStrings(String details) throws UnknownPriorityException {
        int indexOfLastSpace = details.lastIndexOf(" ");
        if (indexOfLastSpace == -1) {
            throw new UnknownPriorityException();
        }
        return new String[]{details.substring(0, indexOfLastSpace), details.substring(indexOfLastSpace + 1)};
    }

    private Priority getPriority(String details) throws UnknownPriorityException {
        switch (details.toLowerCase()) {
        case "high" -> {
            return Priority.HIGH;
        }
        case "medium" -> {
            return Priority.MEDIUM;
        }
        case "low" -> {
            return Priority.LOW;
        }
        default -> throw new UnknownPriorityException();
        }
    }

    /**
     * Returns updated list of tasks after adding ToDo task.
     *
     * @param tasks List of tasks.
     * @param ui UI object.
     * @param details Details of todo command.
     * @return Updated list of tasks.
     * @throws InvalidToDoException If details is empty.
     * @throws UnknownPriorityException If priority does not exist.
     */
    private TaskList parseToDo(TaskList tasks, Ui ui, String details) throws InvalidToDoException,
            UnknownPriorityException {
        assert ui != null : "There should be a UI object.";

        if (details.isEmpty()) {
            throw new InvalidToDoException();
        }

        String[] detailParts = getDescriptionAndPriorityAsStrings(details);

        Priority priority = getPriority(detailParts[1]);
        ToDo toDo = new ToDo(detailParts[0], priority);
        TaskList newTasks = tasks.add(toDo);
        ui.setAddMessage(toDo, tasks.getSize());

        assert newTasks != null : "Tasklist object should not be empty";
        return newTasks;
    }

    /**
     * Returns updated list of tasks after adding Deadline task.
     *
     * @param tasks List of tasks.
     * @param ui UI object.
     * @param details Details of deadline command.
     * @return Updated list of tasks.
     * @throws DateTimeParseException If date and time format is wrong.
     * @throws InvalidDeadlineException If deadline command is incomplete.
     * @throws UnknownPriorityException If priority does not exist.
     */
    private TaskList parseDeadline(TaskList tasks, Ui ui, String details) throws DateTimeParseException,
            InvalidDeadlineException, UnknownPriorityException {

        assert ui != null : "There should be a UI object.";
        String[] detailParts = details.split(" /by ", 2);

        if (detailParts.length != 2) {
            throw new InvalidDeadlineException();
        }

        String[] byAndPriorityStrings = getDescriptionAndPriorityAsStrings(detailParts[1]);
        Priority priority = getPriority(byAndPriorityStrings[1]);
        LocalDateTime by = LocalDateTime.parse(byAndPriorityStrings[0], dateTimeFormatter);

        Deadline deadline = new Deadline(detailParts[0], priority, by);
        TaskList newTasks = tasks.add(deadline);
        ui.setAddMessage(deadline, tasks.getSize());

        assert newTasks != null : "Tasklist object should not be empty";

        return newTasks;
    }

    /**
     * Returns updated list of tasks after adding Event task.
     *
     * @param tasks List of tasks.
     * @param ui UI object.
     * @param details Details of event command.
     * @return Updated list of tasks.
     * @throws DateTimeParseException If date and time format is wrong.
     * @throws InvalidEventException If event command is incomplete.
     * @throws UnknownPriorityException If priority does not exist.
     */
    private TaskList parseEvent(TaskList tasks, Ui ui, String details) throws DateTimeParseException,
            InvalidEventException, UnknownPriorityException {
        assert ui != null : "There should be a UI object.";

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

        String[] toAndPriorityStrings = getDescriptionAndPriorityAsStrings(duration[1]);
        Priority priority = getPriority(toAndPriorityStrings[1]);
        LocalDateTime from = LocalDateTime.parse(duration[0], dateTimeFormatter);
        LocalDateTime to = LocalDateTime.parse(toAndPriorityStrings[0], dateTimeFormatter);

        Event event = new Event(detailParts[0], priority, from, to);
        TaskList newTasks = tasks.add(event);
        ui.setAddMessage(event, tasks.getSize());

        assert newTasks != null : "Tasklist object should not be empty";

        return newTasks;
    }

    /**
     * Returns updated list of tasks after adding marking/unmarking a task.
     *
     * @param tasks List of tasks.
     * @param ui UI object.
     * @param details Index of task to be marked/unmarked.
     * @param isDone Is true if task is marked as completed, false if unmarked.
     * @return Updated list of tasks.
     * @throws InvalidMarkException If index is not a number or index larger than number of tasks.
     */
    private TaskList parseMark(TaskList tasks, Ui ui, boolean isDone, String details) throws InvalidMarkException {
        assert ui != null : "There should be a UI object.";
        try {
            int taskIndex = Integer.parseInt(details) - 1;
            tasks.get(taskIndex).setIsDone(isDone);
            ui.setMarkMessage(tasks.get(taskIndex));
            return tasks;
        } catch (NumberFormatException e) {
            throw new InvalidMarkException();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidMarkException();
        }
    }

    /**
     * Returns updated list of tasks after adding deleting a task.
     *
     * @param tasks List of tasks.
     * @param ui UI object.
     * @param details Index of task to be deleted.
     * @return Updated list of tasks.
     * @throws InvalidDeleteException If index is not a number or index larger than number of tasks.
     */
    private TaskList parseDelete(TaskList tasks, Ui ui, String details) throws InvalidDeleteException {
        assert ui != null : "There should be a UI object.";
        try {
            int taskIndex = Integer.parseInt(details) - 1;
            Task task = tasks.get(taskIndex);
            TaskList newTasks = tasks.remove(taskIndex);
            ui.setDeleteMessage(task, newTasks.getSize());
            return newTasks;
        } catch (NumberFormatException e) {
            throw new InvalidDeleteException();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDeleteException();
        }
    }

    /**
     * Prints tasks that occur on specific date.
     *
     * @param tasks List of tasks.
     * @param details Date in format yyyy-MM-dd.
     * @throws InvalidOnException If details is empty.
     * @throws DateTimeParseException If date format is wrong or on command is incomplete or incorrect.
     */
    private void parseOn(TaskList tasks, Ui ui, String details) throws InvalidOnException, DateTimeParseException {
        if (details.isEmpty()) {
            throw new InvalidOnException();
        }
        LocalDateTime dateTime = LocalDateTime.parse(details + " 0000", dateTimeFormatter);
        TaskList validTasks = new TaskList(new ArrayList<>(tasks.toStream()
                .filter(x -> x.isSameDate(dateTime))
                .toList()));
        ui.setOnMessage(validTasks);
    }

    /**
     * Returns whether a string contains any string from a given array.
     *
     * @param task Task object to be inspected.
     * @param keywords Keywords to be found.
     * @return True if string contains any keyword, false otherwise.
     */
    private boolean doesStringContainItemFromArray(Task task, String ... keywords) {
        return Arrays.stream(keywords).map(x -> x.toLowerCase())
                .anyMatch(task.toString().toLowerCase()::contains);
    }

    /**
     * Prints tasks containing specified keyword.
     *
     * @param tasks List of tasks currently available.
     * @param ui UI object.
     * @param keywords Keywords to be found.
     */
    private void parseFind(TaskList tasks, Ui ui, String ... keywords) {
        assert ui != null : "There should be a UI object.";
        TaskList validTasks = new TaskList(new ArrayList<>(tasks.toStream()
                .filter(x -> doesStringContainItemFromArray(x, keywords))
                .toList()));
        ui.setFindMessage(validTasks);
    }

    /**
     * Returns updated TaskList after modification commands.
     * Returns same TaskList after commands that do not modify TaskList.
     *
     * @param userInput Command.
     * @param tasks List of tasks.
     * @param ui UI object.
     * @return Updated list of tasks.
     */
    public TaskList parseCommand(String userInput, TaskList tasks, Ui ui) {
        String[] taskParts = userInput.split(" ", 2);
        String command = taskParts[0];
        String details = "";
        if (taskParts.length == 2) {
            details = taskParts[1];
        }
        try {
            switch (command.toLowerCase()) {
            case "list" -> {
                ui.setList(tasks);
                return tasks;
            }
            case "todo" -> {
                return parseToDo(tasks, ui, details);
            }
            case "deadline" -> {
                return parseDeadline(tasks, ui, details);
            }
            case "event" -> {
                return parseEvent(tasks, ui, details);
            }
            case "mark" -> {
                return parseMark(tasks, ui, true, details);
            }
            case "unmark" -> {
                return parseMark(tasks, ui, false, details);
            }
            case "delete" -> {
                return parseDelete(tasks, ui, details);
            }
            case "on" -> {
                parseOn(tasks, ui, details);
                return tasks;
            }
            case "find" -> {
                parseFind(tasks, ui, details.split(","));
                return tasks;
            }
            default -> throw new UnknownCommandException();
            }
        } catch (UnknownCommandException e) {
            ui.setUnknownCommandError();
        } catch (InvalidToDoException e) {
            ui.setToDoError();
        } catch (InvalidDeadlineException e) {
            ui.setDeadlineError();
        } catch (InvalidEventException e) {
            ui.setEventError();
        } catch (DateTimeParseException e) {
            ui.setDateTimeFormatError();
        } catch (InvalidMarkException e) {
            ui.setMarkError(tasks.getSize());
        } catch (InvalidDeleteException e) {
            ui.setDeleteError(tasks.getSize());
        } catch (InvalidOnException e) {
            ui.setOnError();
        } catch (UnknownPriorityException e) {
            ui.setPriorityError();
        }
        return tasks;
    }

    /**
     * Returns task parsed from a line in the save file.
     *
     * @param taskString String from save file.
     * @return Task.
     * @throws InvalidFileFormatException If format of string is incorrect.
     * @throws UnknownPriorityException If priority is invalid.
     */
    public Task parseFromFile(String taskString) throws InvalidFileFormatException,
            UnknownPriorityException {
        String[] taskParts = taskString.split(" \\| ");
        Priority priority = getPriority(taskParts[taskParts.length - 1]);
        Task newTask;
        switch (taskParts[0]) {
        case "T" -> newTask = new ToDo(taskParts[2], priority);
        case "D" -> {
            LocalDateTime by = LocalDateTime.parse(taskParts[3], dateTimeFormatter);
            newTask = new Deadline(taskParts[2], priority, by);
        }
        case "E" -> {
            LocalDateTime from = LocalDateTime.parse(taskParts[3], dateTimeFormatter);
            LocalDateTime to = LocalDateTime.parse(taskParts[4], dateTimeFormatter);
            newTask = new Event(taskParts[2], priority, from, to);
        }
        default -> {
            throw new InvalidFileFormatException();
        }
        }
        if (taskParts[1].equals("1")) {
            newTask.setIsDone(true);
        } else if (taskParts[1].equals("0")) {
            return newTask;
        } else {
            throw new InvalidFileFormatException();
        }
        return newTask;
    }
}
