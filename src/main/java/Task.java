public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatus() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String toString() {
        return this.description;
    }
}
