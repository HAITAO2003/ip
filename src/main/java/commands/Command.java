package commands;
import duke.Storage;
import duke.TaskList;
import duke.DukeException;
import duke.Ui;

/**
 * Represents an abstract command in the task management system.
 * All commands should inherit from this class.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks The task list to perform the command on.
     * @param ui The user interface to show results.
     * @param storage The storage to save any changes.
     * @throws DukeException If there is an error executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether this command should exit the program.
     *
     * @return true if the program should exit after this command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}