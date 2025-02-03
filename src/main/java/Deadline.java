
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
public class Deadline extends Task{

    private LocalDateTime dateTime;
    public Deadline(String description, String deadlineStr) throws DukeException {
        super(description);
        try {
            this.dateTime = LocalDateTime.parse(deadlineStr.strip(), INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter date in format: d/M/yyyy HHmm (e.g., 2/12/2019 1800)");
        }
    }

    @Override
    protected String getTypeIcon() {
        return "D";
    }

    public String getDeadline() {
        return dateTime.format(PRINT_FORMAT);
    }
}
