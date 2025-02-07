package tasks;

import duke.DukeException;
import tasks.Task;

public class ToDo extends Task {
    public ToDo(String description) throws DukeException {
        super(description);
        if (description.trim().isEmpty()) {
            throw new DukeException("tasks.Task description cannot be empty!");
        }
    }
    @Override
    public String getTypeIcon() {
        return "T";
    }

}
