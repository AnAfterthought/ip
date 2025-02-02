package bpluschatter.ui;

import bpluschatter.task.Task;
import bpluschatter.task.TaskList;

/**
 * Prints out success and error messages after each command.
 */
public class Ui {
    private String message;
    private boolean isError = false;

    /**
     * Sets welcome message.
     */
    public void setWelcome() {
        this.message = "Hello! I'm BPlusChatter :)\nWhat can I do for you?";
        isError = false;
    }

    /**
     * Sets goodbye message.
     */
    public void setGoodbye() {
        this.message = "Bye bye. Come back soon!";
        isError = false;
    }

    /**
     * Sets message after addition of a task.
     *
     * @param task Task that was added.
     * @param count Number of tasks after task was added.
     */
    public void setAdd(Task task, int count) {
        this.message = "OK. I've added this task:\n";
        this.message += task + "\n";
        this.message += "You now have " + count + " task(s)";
        isError = false;
    }

    /**
     * Sets message after marking/unmarking a task.
     *
     * @param task Task that was marked/unmarked.
     */
    public void setMark(Task task) {
        if (task.getIsDone()) {
            this.message = "Well done! This task is done:\n";
        } else {
            this.message = "Ok, this task is not done yet:\n";
        }
        this.message += task;
        isError = false;
    }

    /**
     * Sets message after deleting a task.
     *
     * @param task Task that was deleted.
     * @param count Number of tasks in list.
     */
    public void setDelete(Task task, int count) {
        this.message = "Ok. I've deleted this task:\n";
        this.message += task + "\n";
        this.message += "You now have " + count + " task(s)";
        isError = false;
    }

    /**
     * Sets error message for todo command.
     */
    public void setToDoError() {
        this.message = "WRONG FORMAT :(\n " + "Format: todo <task>";
        isError = true;
    }

    /**
     * Sets error message for deadline command.
     */
    public void setDeadlineError() {
        this.message = "WRONG FORMAT :(\n " + "Format: deadline <task> /by <date> <time>";
        isError = true;
    }

    /**
     * Sets error message for event command.
     */
    public void setEventError() {
        this.message = "WRONG FORMAT :(\n " + "Format: event <task> /from <date> <time> /to <date> <time>";
        isError = true;
    }

    /**
     * Sets error message for on command.
     */
    public void setOnError() {
        this.message = "WRONG FORMAT :(\n " + "Format: on yyyy-MM-dd";
        isError = true;
    }

    /**
     * Sets error message when loading tasks from save file.
     */
    public void setLoadingError() {
        this.message = "Error encountered loading tasks!";
        isError = true;
    }

    /**
     * Sets error message when saving tasks.
     */
    public void setSavingError() {
        this.message = "Error encountered saving tasks!";
        isError = true;
    }

    /**
     * Sets error message for unknown commands.
     */
    public void setUnknownCommandError() {
        this.message = "UNKNOWN COMMAND :(\n"
                + "Try starting with todo, deadline, event, mark, unmark, list, delete, on, find or bye";
        isError = true;
    }

    /**
     * Sets error message if date and time format is wrong.
     */
    public void setDateTimeFormatError() {
        this.message = "WRONG FORMAT :(\nDate and time (24-hour) format: YYYY-MM-DD HHmm";
        isError = true;
    }

    /**
     * Sets error message for mark/unmark command.
     */
    public void setMarkError(int count) {
        this.message = "WRONG FORMAT :(\n " + "Format: mark/unmark <task number>\n"
                + "You have " + count + " task(s)";
        isError = true;
    }

    /**
     * Sets error message for delete command.
     */
    public void setDeleteError(int count) {
        this.message = "WRONG FORMAT :(\nFormat: delete <task number>\n"
                + "You have " + count + " task(s)";
        isError = true;
    }

    /**
     * Sets message to contain tasks containing a keyword.
     *
     * @param tasks List of tasks containing a keyword.
     */
    public void setFind(TaskList tasks) {
        this.message = "Here are the tasks I found:\n";
        this.message += tasks.toString();
        isError = false;
    }

    /**
     * Sets message to contain tasks.
     *
     * @param tasks List of tasks.
     */
    public void setList(TaskList tasks) {
        this.message = tasks.toString();
        isError = false;
    }

    /**
     * Returns error status.
     *
     * @return Error status.
     */
    public boolean getIsError() {
        return this.isError;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
