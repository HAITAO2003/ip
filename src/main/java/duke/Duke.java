package duke;

import commands.Command;
import java.util.ArrayList;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            // Initialize with empty task list instead of trying to load again
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Gets a response to user input
     * @param input The user input
     * @return Duke's response
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            StringBuilder response = new StringBuilder();

            // Capture the response instead of printing to console
            ui.captureResponse(() -> {
                try {
                    c.execute(tasks, ui, storage);
                } catch (DukeException e) {
                    ui.showError(e.getMessage());
                }
            }, response);

            if (c.isExit()) {
                System.exit(0);
            }

            return response.toString();
        } catch (DukeException e) {
            return "☹ OOPS!!! " + e.getMessage();
        }
    }
}