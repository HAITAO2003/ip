public enum Command {
    DEADLINE,
    TODO,
    EVENT,
    LIST,
    MARK,
    DELETE,
    UNKNOWN;

    public static Command fromString(String text) {
        try {
            return valueOf(text.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
