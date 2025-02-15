package duke;

/**
 * Custom exception class for Duke application.
 * Used to handle application-specific errors.
 */
public class DukeException extends Exception {
    /**
     * Creates a new DukeException with the specified error message.
     *
     * @param message The error message
     */
    public DukeException(String message) {
        super(message);
    }
}