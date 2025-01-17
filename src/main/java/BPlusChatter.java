import java.util.Scanner;

public class BPlusChatter {

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
                for (int i = 0; i < index; i++) {
                    System.out.println("\t"+ (i + 1) + "." + tasks[i].getStatus());
                }
            } else if (userInput.matches("mark \\d")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[taskIndex].setIsDone(true);
                System.out.println("\tWell done! This task is done:");
                System.out.println("\t\t" + tasks[taskIndex].getStatus());
            } else if (userInput.matches("unmark \\d")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[taskIndex].setIsDone(false);
                System.out.println("\tOk, this task is not done yet:");
                System.out.println("\t\t" + tasks[taskIndex].getStatus());
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
