public class Deadline extends Task{

    private String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline.strip();
    }

    @Override
    protected String getTypeIcon() {
        return "D";
    }
    public String getDeadline(){
        return this.deadline;
    }
}
