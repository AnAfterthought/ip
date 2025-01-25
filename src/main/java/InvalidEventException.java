public class InvalidEventException extends Exception {

    public String toString() {
        return "\tWRONG FORMAT :(\n " + "\tFormat: event <task> /from <date> <time> /to <date> <time>";
    }
}
