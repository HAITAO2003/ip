import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected static final DateTimeFormatter PRINT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    protected abstract String getTypeIcon();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone(){
        this.isDone = true;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String getDescription() {
        return this.description; // mark done task with X
    }
}