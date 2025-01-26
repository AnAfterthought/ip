import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BPlusChatter {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public BPlusChatter(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            storage.load();
            //tasks = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void run() {
        //...
    }

    public static void main(String[] args) {
        new BPlusChatter("./data/BPlusChatter.txt").run();
    }
    /*static ArrayList<Task> tasks = new ArrayList<>();
    static String filePath = "./data/BPlusChatter.txt";
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static void parseTaskFromFile(String taskString) {
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

    public static void getTasksOnDate(String details) throws DateTimeParseException {
        LocalDateTime dateTime = LocalDateTime.parse(details + " 0000", dateTimeFormatter);
        int counter = 1;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isSameDate(dateTime)) {
                System.out.println("\t"+ counter + "." + tasks.get(i));
                counter += 1;
            }
        }
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
                LocalDateTime by = LocalDateTime.parse(detailParts[1], dateTimeFormatter);
                addTask(new Deadline(detailParts[0], by));
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
                LocalDateTime from = LocalDateTime.parse(duration[0], dateTimeFormatter);
                LocalDateTime to = LocalDateTime.parse(duration[1], dateTimeFormatter);
                addTask(new Event(detailParts[0], from, to));
            }
            case "delete" -> {
                try {
                    deleteTask(details);
                } catch (Exception e) {
                    throw new InvalidDeleteException();
                }
            }
            case "on" -> {
                if (details.isEmpty()) {
                    throw new InvalidOnException();
                }
                try {
                    getTasksOnDate(details);
                } catch (DateTimeParseException e) {
                    System.out.println(e);
                    throw new InvalidOnException();
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
            } catch (UnknownCommandException | InvalidToDoException | InvalidDeadlineException | InvalidEventException |
                     InvalidOnException e) {
                System.out.println(e);
            } catch (InvalidMarkException e) {
                System.out.println(e.toString(tasks.size()));
            } catch (InvalidUnmarkException e) {
                System.out.println(e.toString(tasks.size()));
            } catch (InvalidDeleteException e) {
                System.out.println(e.toString(tasks.size()));
            } catch (DateTimeParseException e) {
                System.out.println("\tWRONG FORMAT :(\n\tDate and time (24-hour) format: YYYY-MM-DD HHmm");
            } catch (IOException e) {
                System.out.println("\tError saving tasks into file!");
            }
        }
    }*/
}
