public class UnknownCommandException extends Exception {

    public String toString() {
        return "\tUNKNOWN COMMAND :(\n " +
                "\tTry starting with todo, deadline, event, mark, unmark, list, delete or bye";
    }
}
