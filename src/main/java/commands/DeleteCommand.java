package commands;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.DukeException;
import duke.Ui;
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = tasks.deleteTask(index);
        storage.save(tasks.getTaskList());
        ui.showDeletedTask(deletedTask, tasks.size());
    }
}
