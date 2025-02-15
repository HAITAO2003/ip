package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command, showing a goodbye message.
     *
     * @param tasks The task list.
     * @param ui The user interface to show the goodbye message.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showToUser("Bye. Hope to see you again soon!");
    }

    /**
     * Returns true as this is an exit command.
     *
     * @return true to indicate the program should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}