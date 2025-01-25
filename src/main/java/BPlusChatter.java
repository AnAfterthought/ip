import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BPlusChatter {
    static ArrayList<Task> tasks = new ArrayList<>();
    static String filePath = "./data/BPlusChatter.txt";

    public static void parseTaskFromFile(String taskString) {
        String[] taskParts = taskString.split(" \\| ");
        Task newTask = new Task(taskParts[2]);
        if (taskParts[0].equals("T")) {
            newTask = new ToDo(taskParts[2]);
        } else if (taskParts[0].equals("D")) {
            newTask = new Deadline(taskParts[2], taskParts[3]);
        } else if (taskParts[0].equals("E")) {
            newTask = new Event(taskParts[2], taskParts[3], taskParts[4]);
        }
        if (taskParts[1].equals("1")) {
            newTask.setIsDone(true);
        }
        tasks.add(newTask);
    }

    public static void getTasks() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } else {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                parseTaskFromFile(scanner.nextLine());
            }
        }
    }

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
        System.out.println("\tOK. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tYou now have " + tasks.size() + " task(s)");
    }

    public static void deleteTask(String details) throws NumberFormatException, IndexOutOfBoundsException {
        int taskIndex = Integer.parseInt(details) - 1;
        Task task = tasks.get(taskIndex);
        System.out.println("\tOk. I've deleted this task:");
        System.out.println("\t\t" + task);
        tasks.remove(taskIndex);
        System.out.println("\tYou now have " + tasks.size() + " task(s)");
    }

    public static void handleCommands(String userInput)
            throws UnknownCommandException, InvalidToDoException, InvalidDeadlineException, InvalidEventException,
            InvalidMarkException, InvalidUnmarkException, InvalidDeleteException {

        String[] taskParts = userInput.split(" ", 2);
        String command = taskParts[0];
        String details = "";
        if (taskParts.length == 2) {
            details = taskParts[1];
        }
        switch (command) {
            case "list" -> list();
            case "mark" -> {
                try {
                    setIsDone(userInput, true);
                } catch (Exception e) {
                    throw new InvalidMarkException();
                }
            }
            case "unmark" -> {
                try {
                    setIsDone(userInput, false);
                } catch (Exception e) {
                    throw new InvalidUnmarkException();
                }
            }
            case "todo" -> {
                if (details.isEmpty()) {
                    throw new InvalidToDoException();
                }
                addTask(new ToDo(taskParts[1]));
            }
            case "deadline" -> {
                String[] detailParts = details.split(" /by ", 2);
                if (detailParts.length != 2) {
                    throw new InvalidDeadlineException();
                }
                addTask(new Deadline(detailParts[0], detailParts[1]));
            }
            case "event" -> {
                String[] detailParts = details.split(" /from ", 2);
                if (detailParts.length != 2) {
                    throw new InvalidEventException();
                }
                String[] duration = detailParts[1].split(" /to ", 2);
                if (duration.length != 2) {
                    throw new InvalidEventException();
                }
                addTask(new Event(detailParts[0], duration[0], duration[1]));
            }
            case "delete" -> {
                try {
                    deleteTask(details);
                } catch (Exception e) {
                    throw new InvalidDeleteException();
                }
            }
            default -> throw new UnknownCommandException();
        }
    }

    public static void saveTasks() throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); i++) {
            fileWriter.write(tasks.get(i).toFileFormat() + System.lineSeparator());
        }
        fileWriter.close();
    }

    public static void main(String[] args) {

        Scanner userInputScanner = new Scanner(System.in);
        String greeting = "Hello! I'm BPlusChatter :)\n\tWhat can I do for you?";
        String exit = "Bye bye. Come back soon!";
        System.out.println("\t" + greeting);

        try {
            getTasks();
        } catch (IOException e) {
            System.out.println("Error encountered creating file!");
        }

        while (true) {
            try {
                String userInput = userInputScanner.nextLine();
                if (userInput.equals("bye")) {
                    System.out.println("\t" + exit);
                    break;
                }
                handleCommands(userInput);
                saveTasks();
            } catch (UnknownCommandException | InvalidToDoException | InvalidDeadlineException |
                     InvalidEventException e) {
                System.out.println(e);
            } catch (InvalidMarkException e) {
                System.out.println(e.toString(tasks.size()));
            } catch (InvalidUnmarkException e) {
                System.out.println(e.toString(tasks.size()));
            } catch (InvalidDeleteException e) {
                System.out.println(e.toString(tasks.size()));
            } catch (IOException e) {
                System.out.println("Error saving tasks into file!");
            }
        }
    }
}
