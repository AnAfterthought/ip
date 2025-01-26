import java.io.File;
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
}
