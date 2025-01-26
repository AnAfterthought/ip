package bpluschatter.ui;

import bpluschatter.storage.Storage;
import bpluschatter.task.TaskList;
import bpluschatter.command.Parser;

import java.util.Scanner;
import java.io.IOException;

public class BPlusChatter {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public static void main(String[] args) {
        new BPlusChatter("./data/BPlusChatter.txt").run();
    }
}
