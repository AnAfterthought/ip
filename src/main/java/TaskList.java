import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    TaskList add(Task task) {
        this.tasks.add(task);
        return new TaskList(this.tasks);
    }

    void list() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t"+ (i + 1) + "." + tasks.get(i));
        }
    }
}
