
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String DATA_DIR = "data";
    private static final String FILE_PATH = DATA_DIR + File.separator + "duke.txt";

    public static void ensureDirectoryExists() throws IOException {
        Path dirPath = Paths.get(DATA_DIR);
        if (!Files.exists(dirPath)) {
            Files.createDirectory(dirPath);
        }
    }

    public static void saveTasksToFile(List<Task> tasks) {
        try {
            ensureDirectoryExists();
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));

            for (Task task : tasks) {
                writer.write(convertTaskToString(task));
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    public static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            ensureDirectoryExists();
            File file = new File(FILE_PATH);
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
            System.err.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    private static String convertTaskToString(Task task) {
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

    private static Task convertStringToTask(String line) {
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
            }

            if (task != null && isDone) {
                task.markDone();
            }
            return task;
        } catch (Exception e) {
            System.err.println("Error parsing line: " + line);
            return null;
        }
    }
}
