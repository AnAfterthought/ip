public class ToDo extends Task {
    ToDo(String description) {
        super(description);
    }

    public String getStatus() {
        return "[T]" + super.getStatus();
    }

    public String toString() {
        return super.toString();
    }
}
