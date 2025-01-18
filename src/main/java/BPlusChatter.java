import java.awt.dnd.InvalidDnDOperationException;
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

    public static int handleCommands(String userInput, Task[] tasks, int index)
            throws UnknownCommandException, InvalidToDoException, InvalidDeadlineException, InvalidEventException {
        String separator = "____________________________________________________________";
        System.out.println("\t" + separator);
        /*if (userInput.equals("bye")) {
            System.out.println("\t" + exit);
            System.out.println("\t" + separator);
            break;
        }*/
        String taskParts[] = userInput.split(" ", 2);
        String command = taskParts[0];
        String details = "";
        if (taskParts.length == 2) {
            details = taskParts[1];
        }
        if (command.equals("list")) {
            list(tasks, index);
        } else if (userInput.matches("mark \\d")) {
            setIsDone(userInput, true, tasks);
        } else if (userInput.matches("unmark \\d")) {
            setIsDone(userInput, false, tasks);
        } else if (command.equals("todo")) {
            if (details.isEmpty()) {
                throw new InvalidToDoException();
            }
            addTask(new ToDo(taskParts[1]), tasks, index);
            index += 1;
        } else if (command.equals("deadline")) {
            String[] detailParts = details.split(" /by ",2);
            if (detailParts.length != 2) {
                throw new InvalidDeadlineException();
            }
            addTask(new Deadline(detailParts[0], detailParts[1]), tasks, index);
            index += 1;
        } else if (command.equals("event")) {
            String[] detailParts = details.split(" /from ", 2);
            if (detailParts.length != 2) {
                throw new InvalidEventException();
            }
            String[] duration = detailParts[1].split(" /to ", 2);
            if (duration.length != 2) {
                throw new InvalidEventException();
            }
            addTask(new Event(detailParts[0], duration[0], duration[1]), tasks, index);
            index += 1;
        }
        else {
            throw new UnknownCommandException();
        }
        System.out.println("\t" + separator);
        return index;
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
                index = handleCommands(userInput, tasks, index);
            } catch (UnknownCommandException e) {
                System.out.println("\tUNKNOWN COMMAND :(\n " +
                        "\tTry starting with todo, deadline, event, mark, unmark, list or bye");
                System.out.println("\t" + separator);
            } catch (InvalidToDoException e) {
                System.out.println("\tWRONG FORMAT :(\n " + "\tFormat: todo <task>");
                System.out.println("\t" + separator);
            } catch (InvalidDeadlineException e) {
                System.out.println("\tWRONG FORMAT :(\n " + "\tFormat: deadline <task> /by <date>");
                System.out.println("\t" + separator);
            } catch (InvalidEventException e) {
                System.out.println("\tWRONG FORMAT :(\n " + "\tFormat: event <task> /from <date> /to <date>");
                System.out.println("\t" + separator);
            }
        }
    }
}
