import java.util.Scanner;

public class BPlusChatter {

    public static void list(Task[] tasks, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println("\t"+ (i + 1) + "." + tasks[i]);
        }
    }

    public static void setIsDone(String userInput, boolean isDone, Task[] tasks) {
        int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
        tasks[taskIndex].setIsDone(isDone);
        if (isDone) {
            System.out.println("\tWell done! This task is done:");
        } else {
            System.out.println("\tOk, this task is not done yet:");
        }
        System.out.println("\t\t" + tasks[taskIndex]);
    }

    public static void addTask(Task task, Task[] tasks, int index) {
        tasks[index] = task;
        System.out.println("\tOK. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tYou now have " + (index + 1) + " tasks");
    }

    public static void handleCommands(String userInput, Task[] tasks, int index) throws UnknownCommandException {
        String separator = "____________________________________________________________";
        System.out.println("\t" + separator);
        /*if (userInput.equals("bye")) {
            System.out.println("\t" + exit);
            System.out.println("\t" + separator);
            break;
        }*/
        if (userInput.equals("list")) {
            list(tasks, index);
        } else if (userInput.matches("mark \\d")) {
            setIsDone(userInput, true, tasks);
        } else if (userInput.matches("unmark \\d")) {
            setIsDone(userInput, false, tasks);
        } else if (userInput.startsWith("todo ")) {
            String task = userInput.split(" ",2)[1];
            addTask(new ToDo(task), tasks, index);
        } else if (userInput.startsWith("deadline ") && userInput.contains(" /by ")) {
            String task = userInput.split(" ",2)[1];
            String[] taskParts = task.split(" /by ");
            addTask(new Deadline(taskParts[0], taskParts[1]), tasks, index);
        } else if (userInput.startsWith("event ") && userInput.contains(" /from") && userInput.contains(" /to ")) {
            String task = userInput.split(" ",2)[1];
            String[] taskParts = task.split(" /from ");
            String[] duration = taskParts[1].split(" /to ");
            addTask(new Event(taskParts[0], duration[0], duration[1]), tasks, index);
        }
        else {
            throw new UnknownCommandException();
        }
        System.out.println("\t" + separator);
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int index = 0;

        Scanner userInputScanner = new Scanner(System.in);
        String userInput;
        String greeting = "Hello! I'm BPlusChatter :)\n\tWhat can I do for you?";
        String exit = "Bye bye. Come back soon!";
        String separator = "____________________________________________________________";
        System.out.println("\t" + separator);
        System.out.println("\t" + greeting);
        System.out.println("\t" + separator);

        while (true) {
            try {
                userInput = userInputScanner.nextLine();
                handleCommands(userInput, tasks, index);
                index += 1;
            } catch (UnknownCommandException e) {
                System.out.println("\tI do not know this command :(\n " +
                        "\tTry starting with todo, deadline, event, mark, unmark, list or bye");
                System.out.println("\t" + separator);
            }
        }
    }
}
