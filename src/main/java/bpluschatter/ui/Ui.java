package bpluschatter.ui;

import bpluschatter.task.Task;
import bpluschatter.task.TaskList;

public class Ui {
    public void showWelcome() {
        String greeting = "\tHello! I'm BPlusChatter :)\n\tWhat can I do for you?";
        System.out.println(greeting);
    }

    public void showGoodbye() {
        String exit = "\tBye bye. Come back soon!";
        System.out.println(exit);
    }

    public void showAdd(Task task, TaskList tasks) {
        System.out.println("\tOK. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tYou now have " + tasks.size() + " task(s)");
    }

    public void showMark(boolean isDone, Task task) {
        if (isDone) {
            System.out.println("\tWell done! This task is done:");
        } else {
            System.out.println("\tOk, this task is not done yet:");
        }
        System.out.println("\t\t" + task);
    }

    public void showDelete(Task task, int count) {
        System.out.println("\tOk. I've deleted this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tYou now have " + count + " task(s)");
    }

    public void showToDoError() {
        System.out.println("\tWRONG FORMAT :(\n " + "\tFormat: todo <task>");
    }

    public void showDeadlineError() {
        System.out.println("\tWRONG FORMAT :(\n " + "\tFormat: deadline <task> /by <date> <time>");
    }

    public void showEventError() {
        System.out.println("\tWRONG FORMAT :(\n " + "\tFormat: event <task> /from <date> <time> /to <date> <time>");
    }

    public void showOnError() {
        System.out.println("\tWRONG FORMAT :(\n " + "\tFormat: on yyyy-MM-dd");
    }

    public void showLoadingError() {
        System.out.println("Error encountered loading tasks!");
    }

    public void showSavingError() {
        System.out.println("Error encountered saving tasks!");
    }

    public void showUnknownCommandError() {
        System.out.println("\tUNKNOWN COMMAND :(\n " +
                "\tTry starting with todo, deadline, event, mark, unmark, list, delete, on, find or bye");
    }

    public void showDateTimeFormatError() {
        System.out.println("\tWRONG FORMAT :(\n\tDate and time (24-hour) format: YYYY-MM-DD HHmm");
    }

    public void showMarkError(int count) {
        System.out.println("\tWRONG FORMAT :(\n " + "\tFormat: mark/unmark <task number>\n" +
                "\tYou have " + count + " task(s)");
    }

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
