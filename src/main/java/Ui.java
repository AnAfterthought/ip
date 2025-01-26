public class Ui {
    void showWelcome() {
        String greeting = "\tHello! I'm BPlusChatter :)\n\tWhat can I do for you?";
        System.out.println(greeting);
    }

    void showGoodbye() {
        String exit = "\tBye bye. Come back soon!";
        System.out.println(exit);
    }

    void showAdd(Task task, TaskList tasks) {
        System.out.println("\tOK. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tYou now have " + tasks.size() + " task(s)");
    }

    void showMark(boolean isDone, Task task) {
        if (isDone) {
            System.out.println("\tWell done! This task is done:");
        } else {
            System.out.println("\tOk, this task is not done yet:");
        }
        System.out.println("\t\t" + task);
    }

    void showDelete(Task task, int count) {
        System.out.println("\tOk. I've deleted this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tYou now have " + count + " task(s)");
    }

    void showToDoError() {
        System.out.println("\tWRONG FORMAT :(\n " + "\tFormat: todo <task>");
    }

    void showDeadlineError() {
        System.out.println("\tWRONG FORMAT :(\n " + "\tFormat: deadline <task> /by <date> <time>");
    }

    void showEventError() {
        System.out.println("\tWRONG FORMAT :(\n " + "\tFormat: event <task> /from <date> <time> /to <date> <time>");
    }

    void showOnError() {
        System.out.println("\tWRONG FORMAT :(\n " + "\tFormat: on yyyy-MM-dd");
    }

    void showLoadingError() {
        System.out.println("Error encountered loading tasks!");
    }

    void showSavingError() {
        System.out.println("Error encountered saving tasks!");
    }

    void showUnknownCommandError() {
        System.out.println("\tUNKNOWN COMMAND :(\n " +
                "\tTry starting with todo, deadline, event, mark, unmark, list, delete, on or bye");
    }

    void showDateTimeFormatError() {
        System.out.println("\tWRONG FORMAT :(\n\tDate and time (24-hour) format: YYYY-MM-DD HHmm");
    }

    void showMarkError(int count) {
        System.out.println("\tWRONG FORMAT :(\n " + "\tFormat: mark/unmark <task number>\n" +
                "\tYou have " + count + " task(s)");
    }

    void showDeleteError(int count) {
        System.out.println("\tWRONG FORMAT :(\n\tFormat: delete <task number>\n" +
                "\tYou have " + count + " task(s)");
    }
}
