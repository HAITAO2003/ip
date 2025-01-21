
public class Event extends Task{
    private String startTime;
    private String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime.strip();
        this.endTime = endTime.strip();
    }

    @Override
    protected String getTypeIcon() {
        return "E";
    }
    public String getStartTime(){
        return this.startTime;
    }
    public String getEndTime(){
        return this.endTime;
    }
}
