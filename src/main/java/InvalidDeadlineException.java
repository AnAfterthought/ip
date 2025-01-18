public class InvalidDeadlineException extends Exception {

    public String toString(String separator) {
        return "\tWRONG FORMAT :(\n " + "\tFormat: deadline <task> /by <date>\n" +
                "\t" + separator;
    }
}
