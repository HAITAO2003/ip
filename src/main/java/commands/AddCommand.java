package commands;

import duke.Storage;
import tasks.Task;
import duke.TaskList;
import duke.DukeException;
import duke.Ui;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        storage.save(tasks.getTaskList());
        ui.showAddedTask(task, tasks.size());
    }
}