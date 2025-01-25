public class InvalidOnException extends RuntimeException {
    public String toString() {
        return "\tWRONG FORMAT :(\n " + "\tFormat: on yyyy-MM-dd";
    }
}
