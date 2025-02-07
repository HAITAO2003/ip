package duke;

import tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> loadedTasks) {
        this.list = loadedTasks;
    }

    public void addTask(Task item) {
        this.list.add(item);

    }

    public Task deleteTask(int index) throws DukeException {
        if (index < 1 || index > list.size()) {
            throw new DukeException("Invalid task number! Please provide a number between 1 and " + list.size());
        }
        return list.remove(index - 1);
    }

    public Task markTaskDone(int idx) throws DukeException {
        if (idx < 1 || idx > list.size()) {
            throw new DukeException("Invalid task number! Please provide a number between 1 and " + list.size());
        }
        Task item = this.list.get(idx - 1);
        item.markDone();
        return item;
    }

    public ArrayList<Task> getTaskList() {
        return list;
    }


    public int size() {
        return this.list.size();
    }
}