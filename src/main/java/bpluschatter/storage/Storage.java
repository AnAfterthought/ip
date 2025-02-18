package bpluschatter.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import bpluschatter.command.Parser;
import bpluschatter.exception.InvalidFileFormatException;
import bpluschatter.exception.UnknownPriorityException;
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
        createFile();
    }

    /**
     * Creates file if it does not exist
     */
    private void createFile() {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating file");
        }
    }

    /**
     * Returns tasks from save file.
     *
     * @return List of tasks after retrieving them from save file.
     * @throws IOException If error occurs during file creation.
     */
    public ArrayList<Task> load() throws IOException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                tasks.add(parser.parseFromFile(scanner.nextLine()));
            }
            return tasks;
        } catch (InvalidFileFormatException e) {
            System.out.println("File format corrupted.");
            return new ArrayList<Task>();
        } catch (UnknownPriorityException e) {
            System.out.println("Invalid priority detected.");
            return new ArrayList<Task>();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Number of fields is incorrect.");
            return new ArrayList<Task>();
        } catch (DateTimeParseException e) {
            System.out.println("Date and time format is incorrect");
            return new ArrayList<Task>();
        }
    }

    /**
     * Saves tasks into save file.
     *
     * @param tasks List of tasks.
     * @throws IOException If error occurs while writing to file.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (int i = 0; i < tasks.getSize(); i++) {
            fileWriter.write(tasks.get(i).toFileFormat() + System.lineSeparator());
        }
        fileWriter.close();
    }
}
