package tasks;

import java.time.format.DateTimeFormatter;

/**
 * Abstract base class for all types of tasks.
 * Provides common functionality for task management.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected static final DateTimeFormatter DATE_TIME_INPUT_FORMAT =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected static final DateTimeFormatter DATE_TIME_PRINT_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    /**
     * Gets the type icon specific to each task type.
     *
     * @return A string representing the task type
     */
    public abstract String getTypeIcon();

    /**
     * Creates a new task with the given description.
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Gets the status icon indicating whether the task is done.
     *
     * @return "X" if the task is done, " " if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Gets the description of the task.
     *
     * @return The task description
     */
    public String getDescription() {
        return this.description;
    }
}