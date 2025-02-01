package bpluschatter.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import bpluschatter.command.Parser;
import bpluschatter.task.Task;
import bpluschatter.task.TaskList;

/**
 * Saves tasks into a file.
 * Loads tasks from a save file.
 */
public class Storage {
    private final String filePath;
    private final Parser parser;

    /**
     * Constructor for Storage.
     *
     * @param filePath Path of save file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.parser = new Parser();
    }

    /**
     * Returns tasks from save file.
     *
     * @return List of tasks after retrieving them from save file.
     * @throws IOException If error occurs during file creation.
     */
    public ArrayList<Task> load() throws IOException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } else {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                tasks.add(parser.parseFromFile(scanner.nextLine()));
            }
        }
        return tasks;
    }

    /**
     * Saves tasks into save file.
     *
     * @param tasks List of tasks.
     * @throws IOException If error occurs while writing to file.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); i++) {
            fileWriter.write(tasks.get(i).toFileFormat() + System.lineSeparator());
        }
        fileWriter.close();
    }
}
