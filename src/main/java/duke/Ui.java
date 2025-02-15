package duke;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;

import java.util.List;
import java.util.Scanner;

/**
 * Handles all user interface operations including input and output.
 */
public class Ui {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DIVIDER = "____________________________________________________________";

    /**
     * Shows the welcome message when the program starts.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        showToUser("Hello from\n" + logo);
        showToUser("Hello! I'm duke.Duke\nWhat can I do for you?");
    }

    /**
     * Reads the next line of user input.
     *
     * @return The user's input string
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows a horizontal line divider.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Shows an error message to the user.
     *
     * @param message The error message to show
     */
    public void showError(String message) {
        System.out.println("☹ OOPS!!! " + message);
    }

    /**
     * Shows an error message when loading tasks fails.
     */
    public void showLoadingError() {
        showError("Problem loading tasks from file.");
    }

    /**
     * Shows one or more messages to the user.
     *
     * @param messages Variable number of messages to show
     */
    public void showToUser(String... messages) {
        for (String message : messages) {
            System.out.println(message);
        }
    }

    /**
     * Shows a message when a task is added.
     *
     * @param task The task that was added
     * @param totalTasks The total number of tasks in the list
     */
    public void showAddedTask(Task task, int totalTasks) {
        showToUser(
                "Got it. I've added this task:",
                formatTask(task),
                String.format("Now you have %d tasks in the list.", totalTasks)
        );
    }

    /**
     * Shows a message when a task is deleted.
     *
     * @param task The task that was deleted
     * @param totalTasks The total number of tasks in the list
     */
    public void showDeletedTask(Task task, int totalTasks) {
        showToUser(
                "Noted. I've removed this task:",
                formatTask(task),
                String.format("Now you have %d tasks in the list.", totalTasks)
        );
    }

    /**
     * Shows a message when a task is marked as done.
     *
     * @param task The task that was marked as done
     */
    public void showMarkedTask(Task task) {
        showToUser(
                "Nice! I've marked this task as done:",
                formatTask(task)
        );
    }

    /**
     * Shows the list of all tasks.
     *
     * @param tasks List of tasks to show
     */
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

    /**
     * Formats a task for display.
     * Different formatting is applied based on the task type.
     *
     * @param task The task to format
     * @return Formatted string representation of the task
     */
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