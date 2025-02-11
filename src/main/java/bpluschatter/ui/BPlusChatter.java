package bpluschatter.ui;

import java.io.IOException;

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
            ui.setLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Parses user commands and returns string message.
     */
    public String run(String userInput) {
        Parser parser = new Parser();
        if (userInput.equalsIgnoreCase("bye")) {
            ui.setGoodbyeMessage();
            System.exit(0);
        }
        tasks = parser.parseCommand(userInput, tasks, ui);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.setSavingError();
        }
        return ui.toString();
    }

    /**
     * Returns error status.
     *
     * @return Error status.
     */
    public boolean getError() {
        return ui.getIsError();
    }

}
