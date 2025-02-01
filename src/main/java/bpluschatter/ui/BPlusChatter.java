package bpluschatter.ui;

import java.io.IOException;
import java.util.Scanner;

import bpluschatter.command.Parser;
import bpluschatter.storage.Storage;
import bpluschatter.task.TaskList;

/**
 * Creates and runs chatbot
 */
public class BPlusChatter {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for chatbot BPlusChatter.
     *
     * @param filePath Save file for chatbot.
     */
    public BPlusChatter(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Parses user commands and returns results.
     */
    public void run() {
        Parser parser = new Parser();
        Scanner userInputScanner = new Scanner(System.in);
        ui.showWelcome();
        while (true) {
            String userInput = userInputScanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                ui.showGoodbye();
                break;
            }
            tasks = parser.parseCommand(userInput, tasks, ui);
            try {
                storage.save(tasks);
            } catch (IOException e) {
                ui.showSavingError();
            }
        }
    }


    /**
     * Runs chatbot.
     *
     * @param args Command line arguments if any.
     */
    public static void main(String[] args) {
        new BPlusChatter("./data/BPlusChatter.txt").run();
    }
}
