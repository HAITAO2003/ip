package duke;

import tasks.Task;

import java.util.ArrayList;

/**
 * Manages a list of tasks and provides operations to add, delete, and mark tasks as done.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Creates a new TaskList with the given list of tasks.
     *
     * @param loadedTasks List of tasks to initialize with
     */
    public TaskList(ArrayList<Task> loadedTasks) {
        this.list = loadedTasks;
    }

    /**
     * Adds a new task to the list.
     *
     * @param item Task to add
     */
    public void addTask(Task item) {
        this.list.add(item);
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param index One-based index of the task to delete
     * @return The deleted task
     * @throws DukeException if the index is invalid
     */
    public Task deleteTask(int index) throws DukeException {
        if (index < 1 || index > list.size()) {
            throw new DukeException("Invalid task number! Please provide a number between 1 and " + list.size());
        }
        return list.remove(index - 1);
    }

    /**
     * Marks a task as done at the specified index.
     *
     * @param idx One-based index of the task to mark as done
     * @return The marked task
     * @throws DukeException if the index is invalid
     */
    public Task markTaskDone(int idx) throws DukeException {
        if (idx < 1 || idx > list.size()) {
            throw new DukeException("Invalid task number! Please provide a number between 1 and " + list.size());
        }
        Task item = this.list.get(idx - 1);
        item.markDone();
        return item;
    }

    /**
     * Gets the list of all tasks.
     *
     * @return ArrayList containing all tasks
     */
    public ArrayList<Task> getTaskList() {
        return list;
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The size of the task list
     */
    public int size() {
        return this.list.size();
    }
}