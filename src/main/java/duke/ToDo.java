package duke;

import duke.DukeException;
import duke.Task;

public class ToDo extends Task {
    public ToDo(String description) throws DukeException {
        super(description);
        if (description.trim().isEmpty()) {
            throw new DukeException("duke.Task description cannot be empty!");
        }
    }
    @Override
    protected String getTypeIcon() {
        return "T";
    }

}
