package commands;

import duke.Storage;
import tasks.Task;
import duke.TaskList;
import duke.DukeException;
import duke.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Creates a new AddCommand with the given task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command, adding the task to the task list and saving it.
     *
     * @param tasks The task list to add the task to.
     * @param ui The user interface to show the result.
     * @param storage The storage to save the updated task list.
     * @throws DukeException If there is an error saving the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        storage.save(tasks.getTaskList());
        ui.showAddedTask(task, tasks.size());
    }
}