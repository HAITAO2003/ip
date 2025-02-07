package duke;// duke.Ui.java

import tasks.Deadline;
import tasks.Event;
import tasks.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DIVIDER = "____________________________________________________________";

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        showToUser("Hello from\n" + logo);
        showToUser("Hello! I'm duke.Duke\nWhat can I do for you?");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showError(String message) {
        System.out.println("☹ OOPS!!! " + message);
    }

    public void showLoadingError() {
        showError("Problem loading tasks from file.");
    }

    public void showToUser(String... messages) {
        for (String message : messages) {
            System.out.println(message);
        }
    }

    public void showAddedTask(Task task, int totalTasks) {
        showToUser(
                "Got it. I've added this task:",
                formatTask(task),
                String.format("Now you have %d tasks in the list.", totalTasks)
        );
    }

    public void showDeletedTask(Task task, int totalTasks) {
        showToUser(
                "Noted. I've removed this task:",
                formatTask(task),
                String.format("Now you have %d tasks in the list.", totalTasks)
        );
    }

    public void showMarkedTask(Task task) {
        showToUser(
                "Nice! I've marked this task as done:",
                formatTask(task)
        );
    }

    public void showTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            showToUser("No tasks in your list!");
            return;
        }

        showToUser("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            showToUser(String.format("%d.%s", i + 1, formatTask(tasks.get(i))));
        }
    }

    private String formatTask(Task task) {
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return String.format("     [%s][%s] %s (by: %s)",
                    deadline.getTypeIcon(), deadline.getStatusIcon(),
                    deadline.getDescription(), deadline.getDeadline());
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return String.format("     [%s][%s] %s (from: %s to: %s)",
                    event.getTypeIcon(), event.getStatusIcon(),
                    event.getDescription(), event.getStartTime(), event.getEndTime());
        } else {
            return String.format("     [%s][%s] %s",
                    task.getTypeIcon(), task.getStatusIcon(),
                    task.getDescription());
        }
    }
}