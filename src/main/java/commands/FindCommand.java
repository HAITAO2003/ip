// FindCommand.java
package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> allTasks = tasks.getTaskList();
        List<Task> matchingTasks = new ArrayList<>();

        for (Task task : allTasks) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        ui.showMatchingTasks(matchingTasks);
    }
}
