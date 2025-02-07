package duke;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;
    private static final String DATA_DIR = "data";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks to file.
     * Creates the data directory if it doesn't exist.
     *
     * @param tasks List of tasks to save
     * @throws DukeException if there's an error saving the tasks
     */
    public void save(List<Task> tasks) throws DukeException {
        try {
            ensureDirectoryExists();
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            for (Task task : tasks) {
                writer.write(convertTaskToString(task));
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            throw new DukeException("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from file.
     * Creates the data directory if it doesn't exist.
     *
     * @return ArrayList of tasks loaded from file
     * @throws DukeException if there's an error loading the tasks
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            ensureDirectoryExists();
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;  // Return empty list if file doesn't exist
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = convertStringToTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new DukeException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Creates the data directory if it doesn't exist.
     *
     * @throws IOException if there's an error creating the directory
     */
    private void ensureDirectoryExists() throws IOException {
        Path dirPath = Paths.get(DATA_DIR);
        if (!Files.exists(dirPath)) {
            Files.createDirectory(dirPath);
        }
    }

    /**
     * Converts a task object to its string representation for storage.
     *
     * @param task Task to convert
     * @return String representation of the task
     */
    private String convertTaskToString(Task task) {
        StringBuilder sb = new StringBuilder();

        // Add task type
        if (task instanceof ToDo) {
            sb.append("T");
        } else if (task instanceof Deadline) {
            sb.append("D");
        } else if (task instanceof Event) {
            sb.append("E");
        }

        // Add completion status and description
        sb.append(" | ")
                .append(task.isDone ? "1" : "0")
                .append(" | ")
                .append(task.getDescription());

        // Add type-specific details
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            sb.append(" | ").append(deadline.getDeadline());
        } else if (task instanceof Event) {
            Event event = (Event) task;
            sb.append(" | ")
                    .append(event.getStartTime())
                    .append(" | ")
                    .append(event.getEndTime());
        }

        return sb.toString();
    }

    /**
     * Converts a string from storage back to a Task object.
     *
     * @param line String representation of task
     * @return Task object
     * @throws DukeException if there's an error parsing the line
     */
    private Task convertStringToTask(String line) throws DukeException {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task = null;
            switch (type) {
                case "T":
                    task = new ToDo(description);
                    break;
                case "D":
                    task = new Deadline(description, parts[3]);
                    break;
                case "E":
                    task = new Event(description, parts[3], parts[4]);
                    break;
                default:
                    throw new DukeException("Unknown task type: " + type);
            }

            if (task != null && isDone) {
                task.markDone();
            }
            return task;
        } catch (Exception e) {
            throw new DukeException("Error parsing line: " + line + "\n" + e.getMessage());
        }
    }
}