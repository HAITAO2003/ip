
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Event extends Task{

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Event(String description, String startTimeStr, String endTimeStr) throws DukeException {
        super(description);
        try {
            this.startDateTime = LocalDateTime.parse(startTimeStr.strip(), INPUT_FORMAT);
            this.endDateTime = LocalDateTime.parse(endTimeStr.strip(), INPUT_FORMAT);
            if (this.endDateTime.isBefore(this.startDateTime)) {
                throw new DukeException("End time cannot be before start time!");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter dates in format: d/M/yyyy HHmm (e.g., 2/12/2019 1800)");
        }
    }


    @Override
    protected String getTypeIcon() {
        return "E";
    }
    public String getStartTime() {
        return startDateTime.format(PRINT_FORMAT);
    }

    public String getEndTime() {
        return endDateTime.format(PRINT_FORMAT);
    }
}
