import java.util.Scanner;

public class BPlusChatter {

    public static void list(Task[] tasks, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println("\t"+ (i + 1) + "." + tasks[i].getStatus());
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
        System.out.println("\t\t" + tasks[taskIndex].getStatus());
    }

    public static void addTask(Task task, Task[] tasks, int index) {
        tasks[index] = task;
        System.out.println("\tOK. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tYou now have " + (index + 1) + " tasks");
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
            userInput = userInputScanner.nextLine();
            System.out.println("\t" + separator);
            if (userInput.equals("bye")) {
                System.out.println("\t" + exit);
                System.out.println("\t" + separator);
                break;
            }
            if (userInput.equals("list")) {
                list(tasks, index);
            } else if (userInput.matches("mark \\d")) {
                setIsDone(userInput, true, tasks);
            } else if (userInput.matches("unmark \\d")) {
                setIsDone(userInput, false, tasks);
            } else if (userInput.startsWith("todo ")) {
                String task = userInput.split(" ",2)[1];
                addTask(new ToDo(task), tasks, index);
                index += 1;
            }
            else {
                Task task = new Task(userInput);
                tasks[index] = task;
                index += 1;
                System.out.println("\tadded: " + task);
            }
            System.out.println("\t" + separator);
        }
    }
}
