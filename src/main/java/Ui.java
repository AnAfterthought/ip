public class Ui {
    void showWelcome() {
        String greeting = "Hello! I'm BPlusChatter :)\n\tWhat can I do for you?";
        System.out.println(greeting);
    }

    void showGoodbye() {
        String exit = "Bye bye. Come back soon!";
        System.out.println(exit);
    }

    void showAdd(Task task, TaskList tasks) {
        System.out.println("\tOK. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tYou now have " + tasks.size() + " task(s)");
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

    void showLoadingError() {
        System.out.println("Error encountered creating file!");
    }

    void showUnknownCommandError() {
        System.out.println("\tUNKNOWN COMMAND :(\n " +
                "\tTry starting with todo, deadline, event, mark, unmark, list, delete, on or bye");
    }

    void showDateTimeFormatError() {
        System.out.println("\tWRONG FORMAT :(\n\tDate and time (24-hour) format: YYYY-MM-DD HHmm");
    }
}
