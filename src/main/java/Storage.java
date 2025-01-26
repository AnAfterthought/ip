import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private final Parser parser;

    Storage(String filePath) {
        this.filePath = filePath;
        this.parser = new Parser();
    }

    ArrayList<Task> load() throws IOException {
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

    void save(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); i++) {
            fileWriter.write(tasks.get(i).toFileFormat() + System.lineSeparator());
        }
        fileWriter.close();
    }
}
