public class InvalidDeleteException extends Exception {

    public String toString(int count) {
        return "\tWRONG FORMAT :(\n " + "\tFormat: delete <task number>\n" +
                "\tYou have " + count + " task(s)";
    }
}
