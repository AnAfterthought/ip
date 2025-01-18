public class InvalidEventException extends Exception {

    public String toString(String separator) {
        return "\tWRONG FORMAT :(\n " + "\tFormat: event <task> /from <date> /to <date>\n" +
                "\t" + separator;
    }
}
