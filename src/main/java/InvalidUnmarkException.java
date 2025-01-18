public class InvalidUnmarkException extends Exception {

    public String toString(int count, String separator) {
        return "\tWRONG FORMAT :(\n " + "\tFormat: unmark <task number>\n" +
                "\tYou have " + count + " task(s)\n" +
                "\t" + separator;
    }
}
