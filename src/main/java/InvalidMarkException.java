public class InvalidMarkException extends Exception {

    public String toString(int count) {
        return "\tWRONG FORMAT :(\n " + "\tFormat: mark <task number>\n" +
                "\tYou have " + count + " task(s)";
    }
}
