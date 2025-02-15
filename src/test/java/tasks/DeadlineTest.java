package tasks;

import duke.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void constructor_validDateTime_createsDeadline() throws DukeException {
        // Test with a valid date time format
        Deadline deadline = new Deadline("Submit report", "2/12/2024 1800");
        assertEquals("Submit report", deadline.getDescription());
        assertEquals("D", deadline.getTypeIcon());
    }

    @Test
    public void constructor_invalidDateTime_throwsDukeException() {
        // Test with invalid date time format
        Exception exception = assertThrows(DukeException.class, () -> {
            new Deadline("Submit report", "invalid-date");
        });
        assertEquals("Please enter date in format: d/M/yyyy HHmm (e.g., 2/12/2019 1800)",
                exception.getMessage());
    }

    @Test
    public void getDeadline_returnsFormattedDateTime() throws DukeException {
        // Test the formatted output of the deadline
        Deadline deadline = new Deadline("Submit report", "2/12/2024 1800");
        assertEquals("Dec 2 2024, 6:00pm", deadline.getDeadline());
    }

    @Test
    public void constructor_withWhitespace_handlesWhitespaceCorrectly() throws DukeException {
        // Test that whitespace is handled correctly
        Deadline deadline = new Deadline("Submit report", "  2/12/2024 1800  ");
        assertEquals("Dec 2 2024, 6:00pm", deadline.getDeadline());
    }
}