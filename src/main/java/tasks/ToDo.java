package tasks;

import duke.DukeException;

/**
 * Represents a simple todo task without any specific timing requirements.
 * Todo tasks only have a description.
 */
public class ToDo extends Task {
    /**
     * Creates a new ToDo task.
     *
     * @param description The description of the todo task
     * @throws DukeException If the description is empty
     */
    public ToDo(String description) throws DukeException {
        super(description);
        if (description.trim().isEmpty()) {
            throw new DukeException("Task description cannot be empty!");
        }
    }

    /**
     * Gets the type icon for todo tasks.
     *
     * @return The character "T" representing a todo task
     */
    @Override
    public String getTypeIcon() {
        return "T";
    }
}