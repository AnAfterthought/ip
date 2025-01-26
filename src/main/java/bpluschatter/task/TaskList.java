package bpluschatter.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList add(Task task) {
        this.tasks.add(task);
        return new TaskList(this.tasks);
    }

    public TaskList remove(int index) {
        this.tasks.remove(index);
        return new TaskList(this.tasks);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void list() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t"+ (i + 1) + "." + tasks.get(i));
        }
    }
}
