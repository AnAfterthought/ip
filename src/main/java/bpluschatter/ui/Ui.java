package bpluschatter.ui;

import bpluschatter.task.Task;
import bpluschatter.task.TaskList;

/**
 * Prints out success and error messages after each command.
 */
public class Ui {
    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        String greeting = "\tHello! I'm BPlusChatter :)\n\tWhat can I do for you?";
        System.out.println(greeting);
    }

    /**
     * Prints goodbye message.
     */
    public void showGoodbye() {
        String exit = "\tBye bye. Come back soon!";
        System.out.println(exit);
    }

    /**
     * Prints message after addition of a task.
     *
     * @param task Task that was added.
     * @param count Number of tasks after task was added.
     */
    public void showAdd(Task task, int count) {
        System.out.println("\tOK. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tYou now have " + count + " task(s)");
    }

    /**
     * Prints message after marking/unmarking a task.
     *
     * @param isDone Completion status of task.
     * @param task Task that was marked/unmarked.
     */
    public void showMark(Task task) {
        if (task.getIsDone()) {
            System.out.println("\tWell done! This task is done:");
        } else {
            System.out.println("\tOk, this task is not done yet:");
        }
        System.out.println("\t\t" + task);
    }

    /**
     * Prints message after deleting a task.
     *
     * @param task Task that was deleted.
     * @param count Number of tasks in list.
     */
    public void showDelete(Task task, int count) {
        System.out.println("\tOk. I've deleted this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tYou now have " + count + " task(s)");
    }

    /**
     * Prints error message for todo command.
     */
    public void showToDoError() {
        System.out.println("\tWRONG FORMAT :(\n " + "\tFormat: todo <task>");
    }

    /**
     * Prints error message for deadline command.
     */
    public void showDeadlineError() {
        System.out.println("\tWRONG FORMAT :(\n " + "\tFormat: deadline <task> /by <date> <time>");
    }

    /**
     * Prints error message for event command.
     */
    public void showEventError() {
        System.out.println("\tWRONG FORMAT :(\n " + "\tFormat: event <task> /from <date> <time> /to <date> <time>");
    }

    /**
     * Prints error message for on command.
     */
    public void showOnError() {
        System.out.println("\tWRONG FORMAT :(\n " + "\tFormat: on yyyy-MM-dd");
    }

    /**
     * Prints error message when loading tasks from save file.
     */
    public void showLoadingError() {
        System.out.println("Error encountered loading tasks!");
    }

    /**
     * Prints error message when saving tasks.
     */
    public void showSavingError() {
        System.out.println("Error encountered saving tasks!");
    }

    /**
     * Prints error message for unknown commands.
     */
    public void showUnknownCommandError() {
        System.out.println("\tUNKNOWN COMMAND :(\n " +
                "\tTry starting with todo, deadline, event, mark, unmark, list, delete, on, find or bye");
    }

    /**
     * Prints error message if date and time format is wrong.
     */
    public void showDateTimeFormatError() {
        System.out.println("\tWRONG FORMAT :(\n\tDate and time (24-hour) format: YYYY-MM-DD HHmm");
    }

    /**
     * Prints error message for mark/unmark command.
     */
    public void showMarkError(int count) {
        System.out.println("\tWRONG FORMAT :(\n " + "\tFormat: mark/unmark <task number>\n" +
                "\tYou have " + count + " task(s)");
    }

    /**
     * Prints error message for delete command.
     */
    public void showDeleteError(int count) {
        System.out.println("\tWRONG FORMAT :(\n\tFormat: delete <task number>\n" +
                "\tYou have " + count + " task(s)");
    }

    /**
     * Prints tasks containing a keyword.
     *
     * @param tasks List of tasks containing a keyword.
     */
    public void showFind(TaskList tasks) {
        System.out.println("\tHere are the tasks I found:");
        tasks.list();
    }
}
