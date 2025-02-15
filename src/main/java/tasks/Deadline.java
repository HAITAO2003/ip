package tasks;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a specific deadline.
 * Deadline tasks have a description and a date/time by which they must be completed.
 */
public class Deadline extends Task {
    private LocalDateTime dateTime;

    /**
     * Creates a new Deadline task.
     *
     * @param description The description of the deadline task
     * @param deadlineStr The deadline date and time in format "d/M/yyyy HHmm"
     * @throws DukeException If the deadline date format is invalid
     */
    public Deadline(String description, String deadlineStr) throws DukeException {
        super(description);
        try {
            this.dateTime = LocalDateTime.parse(deadlineStr.strip(), Task.DATE_TIME_INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter date in format: d/M/yyyy HHmm (e.g., 2/12/2019 1800)");
        }
    }

    /**
     * Gets the type icon for deadline tasks.
     *
     * @return The character "D" representing a deadline task
     */
    @Override
    public String getTypeIcon() {
        return "D";
    }

    /**
     * Gets the formatted deadline date and time.
     *
     * @return The deadline date and time formatted according to DATE_TIME_PRINT_FORMAT
     */
    public String getDeadline() {
        return dateTime.format(Task.DATE_TIME_PRINT_FORMAT);
    }
}