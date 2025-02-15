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

/**
 * Handles the loading and saving of task data to and from persistent storage.
 * Manages file operations and data conversion between Task objects and their string representation.
 */
public class Storage {
    private final String filePath;
    private static final String DATA_DIR = "data";

    /**
     * Creates a new Storage instance with the specified file path.
     *
     * @param filePath The path where tasks will be saved to and loaded from
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the given list of tasks to the storage file.
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
     * Loads tasks from the storage file.
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
                return tasks;
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
     * Format: [Type]|[Done]|[Description]|[Additional Details]
     *
     * @param task Task to convert
     * @return String representation of the task
     */
    private String convertTaskToString(Task task) {
        StringBuilder sb = new StringBuilder();

        if (task instanceof ToDo) {
            sb.append("T");
        } else if (task instanceof Deadline) {
            sb.append("D");
        } else if (task instanceof Event) {
            sb.append("E");
        }

        sb.append(" | ").append(task.isDone ? "1" : "0")
                .append(" | ").append(task.getDescription());

        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            sb.append(" | ").append(deadline.getDeadline());
        } else if (task instanceof Event) {
            Event event = (Event) task;
            sb.append(" | ").append(event.getStartTime())
                    .append(" | ").append(event.getEndTime());
        }

        return sb.toString();
    }

    /**
     * Converts a string from storage back to a Task object.
     * Handles ToDo, Deadline, and Event task types.
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