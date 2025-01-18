public class InvalidToDoException extends Exception {

    public String toString(String separator) {
        return "\tWRONG FORMAT :(\n " + "\tFormat: todo <task>\n" +
                "\t" + separator;
    }
}
