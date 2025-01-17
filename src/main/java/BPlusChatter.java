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
            if (userInput.equals("bye")) {
                System.out.println("\t" + separator);
                System.out.println("\t" + exit);
                System.out.println("\t" + separator);
                break;
            }
            if (userInput.equals("list")) {
                System.out.println("\t" + separator);
                for (int i = 0; i < index; i++) {
                    System.out.println("\t"+ (i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i]);
                }
                System.out.println("\t" + separator);
            } else {
                Task task = new Task(userInput);
                tasks[index] = task;
                index += 1;
                System.out.println("\t" + separator);
                System.out.println("\tadded: " + task);
                System.out.println("\t" + separator);
            }
        }
    }
}
