package bpluschatter.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList
     *
     * @param tasks ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns updated list of tasks after adding a task.
     *
     * @param task Task to be added.
     * @return Updated list of tasks.
     */
    public TaskList add(Task task) {
        this.tasks.add(task);
        return new TaskList(this.tasks);
    }

    /**
     * Returns updated list of tasks after deleting a task.
     *
     * @param index Index of task.
     * @return Updated list of tasks.
     */
    public TaskList remove(int index) {
        this.tasks.remove(index);
        return new TaskList(this.tasks);
    }

    /**
     * Returns number of tasks.
     *
     * @return Number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns task on specified index.
     *
     * @param index Index of task.
     * @return Task.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Prints all tasks.
     */
    public void list() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.get(i));
        }
    }

    @Override
    public String toString() {
        String tasksString = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksString += (i + 1) + "." + tasks.get(i) + "\n";
        }
        return tasksString;
    }
}
