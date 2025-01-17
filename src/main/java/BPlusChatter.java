import java.util.Scanner;

public class BPlusChatter {
    public static void main(String[] args) {
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
            System.out.println("\t" + separator);
            System.out.println("\t" + userInput);
            System.out.println("\t" + separator);
        }
    }
}
