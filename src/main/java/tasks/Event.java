package tasks;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that occurs during a specific time period.
 * Event tasks have a description, start time, and end time.
 */
public class Event extends Task {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    /**
     * Creates a new Event task.
     *
     * @param description The description of the event
     * @param startTimeStr The start date and time in format "d/M/yyyy HHmm"
     * @param endTimeStr The end date and time in format "d/M/yyyy HHmm"
     * @throws DukeException If the date format is invalid or end time is before start time
     */
    public Event(String description, String startTimeStr, String endTimeStr) throws DukeException {
        super(description);
        try {
            this.startDateTime = LocalDateTime.parse(startTimeStr.strip(), Task.DATE_TIME_INPUT_FORMAT);
            this.endDateTime = LocalDateTime.parse(endTimeStr.strip(), Task.DATE_TIME_INPUT_FORMAT);
            if (this.endDateTime.isBefore(this.startDateTime)) {
                throw new DukeException("End time cannot be before start time!");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter dates in format: d/M/yyyy HHmm (e.g., 2/12/2019 1800)");
        }
    }

    /**
     * Gets the type icon for event tasks.
     *
     * @return The character "E" representing an event task
     */
    @Override
    public String getTypeIcon() {
        return "E";
    }

    /**
     * Gets the formatted start date and time.
     *
     * @return The start date and time formatted according to DATE_TIME_PRINT_FORMAT
     */
    public String getStartTime() {
        return startDateTime.format(Task.DATE_TIME_PRINT_FORMAT);
    }

    /**
     * Gets the formatted end date and time.
     *
     * @return The end date and time formatted according to DATE_TIME_PRINT_FORMAT
     */
    public String getEndTime() {
        return endDateTime.format(Task.DATE_TIME_PRINT_FORMAT);
    }
}