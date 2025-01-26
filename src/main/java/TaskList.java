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
}
