package commands;
import duke.Storage;
import tasks.Task;
import duke.TaskList;
import duke.DukeException;
import duke.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Creates a new DeleteCommand for the task at the given index.
     *
     * @param index The index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command, removing the specified task from the list and saving changes.
     *
     * @param tasks The task list to delete from.
     * @param ui The user interface to show the result.
     * @param storage The storage to save the updated task list.
     * @throws DukeException If the index is invalid or there is an error saving the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = tasks.deleteTask(index);
        storage.save(tasks.getTaskList());
        ui.showDeletedTask(deletedTask, tasks.size());
    }
}