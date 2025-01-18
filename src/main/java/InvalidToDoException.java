public class InvalidToDoException extends Exception {

    public String toString() {
        return "\tWRONG FORMAT :(\n " + "\tFormat: todo <task>";
    }
}
