package commands;

import duke.Storage;
import tasks.Task;
import duke.TaskList;
import duke.DukeException;
import duke.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Creates a new MarkCommand for the task at the given index.
     *
     * @param taskIndex The index of the task to mark as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark command, marking the specified task as done and saving changes.
     *
     * @param tasks The task list containing the task to mark.
     * @param ui The user interface to show the result.
     * @param storage The storage to save the updated task list.
     * @throws DukeException If the index is invalid or there is an error saving the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task markedTask = tasks.markTaskDone(taskIndex);
        storage.save(tasks.getTaskList());
        ui.showMarkedTask(markedTask);
    }
}