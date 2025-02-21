package duke;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;

import java.util.List;

public class Ui {
    private StringBuilder responseCapture;
    private static final String DIVIDER = "____________________________________________________________";

    public void captureResponse(Runnable action, StringBuilder response) {
        this.responseCapture = response;
        action.run();
        this.responseCapture = null;
    }

    private void appendResponse(String... messages) {
        if (responseCapture != null) {
            for (String message : messages) {
                responseCapture.append(message).append("\n");
            }
        }
    }

    public void showLine() {
        appendResponse(DIVIDER);
    }

    public void showError(String message) {
        appendResponse("☹ OOPS!!! " + message);
    }

    public void showLoadingError() {
        showError("Problem loading tasks from file.");
    }

    public void showToUser(String... messages) {
        for (String message : messages) {
            appendResponse(message);
        }
    }

    public void showAddedTask(Task task, int totalTasks) {
        showToUser(
                "Got it. I've added this task:",
                formatTask(task),
                String.format("Now you have %d tasks in the list.", totalTasks)
        );
    }
    public void showUpdatedTask(Task oldTask, Task newTask) {
        showToUser(
                "Got it. I've updated this task:",
                formatTask(oldTask),
                "to this new task:",
                formatTask(newTask)
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
    public void showMatchingTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            showToUser("No matching tasks found!");
            return;
        }

        showToUser("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            showToUser(String.format("%d.%s", i + 1, formatTask(tasks.get(i))));
        }
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