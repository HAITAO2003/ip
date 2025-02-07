package commands;

import duke.Storage;
import tasks.Task;
import duke.TaskList;
import duke.DukeException;
import duke.Ui;

public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task markedTask = tasks.markTaskDone(taskIndex);
        storage.save(tasks.getTaskList());
        ui.showMarkedTask(markedTask);
    }
}
