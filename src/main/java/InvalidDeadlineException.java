public class InvalidDeadlineException extends Exception {

    public String toString() {
        return "\tWRONG FORMAT :(\n " + "\tFormat: deadline <task> /by <date> <time>";
    }
}
