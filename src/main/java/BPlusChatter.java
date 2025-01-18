import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class BPlusChatter {
    static ArrayList<Task> tasks = new ArrayList<>();
    static int count = 0;

    public static void list() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t"+ (i + 1) + "." + tasks.get(i));
        }
    }

    public static void setIsDone(String userInput, boolean isDone) throws
            NumberFormatException, IndexOutOfBoundsException {
        int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
        tasks.get(taskIndex).setIsDone(isDone);
        if (isDone) {
            System.out.println("\tWell done! This task is done:");
        } else {
            System.out.println("\tOk, this task is not done yet:");
        }
        System.out.println("\t\t" + tasks.get(taskIndex));
    }

    public static void addTask(Task task) {
        tasks.add(task);
        count += 1;
        System.out.println("\tOK. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tYou now have " + count + " task(s)");
    }

    public static void deleteTask(String details) throws NumberFormatException, IndexOutOfBoundsException {
        int taskIndex = Integer.parseInt(details) - 1;
        Task task = tasks.get(taskIndex);
        count -= 1;
        System.out.println("\tOk. I've deleted this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tYou now have " + count + " task(s)");
        tasks.remove(taskIndex);
    }

    public static void handleCommands(String userInput)
            throws UnknownCommandException, InvalidToDoException, InvalidDeadlineException, InvalidEventException,
            InvalidMarkException, InvalidUnmarkException, InvalidDeleteException {
        String separator = "____________________________________________________________";
        System.out.println("\t" + separator);

        String taskParts[] = userInput.split(" ", 2);
        String command = taskParts[0];
        String details = "";
        if (taskParts.length == 2) {
            details = taskParts[1];
        }
        if (command.equals("list")) {
            list();
        } else if (command.equals("mark")) {
            try {
                setIsDone(userInput, true);
            } catch (Exception e) {
                throw new InvalidMarkException();
            }
        } else if (command.equals("unmark")) {
            try {
                setIsDone(userInput, false);
            } catch (Exception e) {
                throw new InvalidUnmarkException();
            }
        } else if (command.equals("todo")) {
            if (details.isEmpty()) {
                throw new InvalidToDoException();
            }
            addTask(new ToDo(taskParts[1]));
        } else if (command.equals("deadline")) {
            String[] detailParts = details.split(" /by ",2);
            if (detailParts.length != 2) {
                throw new InvalidDeadlineException();
            }
            addTask(new Deadline(detailParts[0], detailParts[1]));
        } else if (command.equals("event")) {
            String[] detailParts = details.split(" /from ", 2);
            if (detailParts.length != 2) {
                throw new InvalidEventException();
            }
            String[] duration = detailParts[1].split(" /to ", 2);
            if (duration.length != 2) {
                throw new InvalidEventException();
            }
            addTask(new Event(detailParts[0], duration[0], duration[1]));
        } else if (command.equals("delete")){
            try {
                deleteTask(details);
            } catch (Exception e) {
                throw new InvalidDeleteException();
            }
        } else if (userInput.equals("bye")) {
            count = -1;
            return;
        } else {
            throw new UnknownCommandException();
        }
        System.out.println("\t" + separator);
    }

    public static void main(String[] args) {

        Scanner userInputScanner = new Scanner(System.in);
        String greeting = "Hello! I'm BPlusChatter :)\n\tWhat can I do for you?";
        String exit = "Bye bye. Come back soon!";
        String separator = "____________________________________________________________";
        System.out.println("\t" + separator);
        System.out.println("\t" + greeting);
        System.out.println("\t" + separator);

        while (true) {
            try {
                String userInput = userInputScanner.nextLine();
                handleCommands(userInput);
                if (count == -1) {
                    System.out.println("\t" + exit);
                    System.out.println("\t" + separator);
                    break;
                }
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
            } catch (InvalidMarkException e) {
                System.out.println("\tWRONG FORMAT :(\n " + "\tFormat: mark <task number>");
                System.out.println("\tYou have " + count + " task(s)");
                System.out.println("\t" + separator);
            } catch (InvalidUnmarkException e) {
                System.out.println("\tWRONG FORMAT :(\n " + "\tFormat: unmark <task number>");
                System.out.println("\tYou have " + count + " task(s)");
                System.out.println("\t" + separator);
            } catch (InvalidDeleteException e) {
                System.out.println("\tWRONG FORMAT :(\n " + "\tFormat: delete <task number>");
                System.out.println("\tYou have " + count + " task(s)");
                System.out.println("\t" + separator);
            }
        }
    }
}
