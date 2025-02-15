package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to find tasks containing a specific keyword in their descriptions.
 * The search is case-insensitive.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a new FindCommand with the specified keyword.
     * The keyword is converted to lowercase to enable case-insensitive searching.
     *
     * @param keyword The search term to look for in task descriptions
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Executes the find command. Searches through all tasks and displays those
     * whose descriptions contain the keyword (case-insensitive).
     *
     * @param tasks The task list to search through
     * @param ui The user interface to display results
     * @param storage The storage manager (unused in this command)
     */
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