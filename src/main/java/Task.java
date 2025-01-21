public abstract class Task {
    protected String description;
    protected boolean isDone;
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