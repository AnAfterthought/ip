public class UnknownCommandException extends Exception {

    public String toString(String separator) {
        return "\tUNKNOWN COMMAND :(\n " +
                "\tTry starting with todo, deadline, event, mark, unmark, list, delete or bye\n" +
                "\t" + separator;
    }
}
