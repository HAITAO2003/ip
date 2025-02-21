package commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Task;

/**
 * Represents a command to update the details of a task in the task list.
 */
public class UpdateCommand extends Command {
    /**
     * Executes the update command, showing the details of the new updated entry.
     *
     * @param tasks The task list to display.
     * @param ui The user interface to show the task list.
     * @param storage The storage.
     */
    private final int index;
    private final Task newTask;

    public UpdateCommand(int index, Task newTask) {
        this.index = index;
        this.newTask = newTask;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = tasks.deleteTask(index);
        storage.save(tasks.getTaskList());
        tasks.addTask(newTask);
        storage.save(tasks.getTaskList());
        ui.showUpdatedTask(deletedTask, newTask);
    }
}