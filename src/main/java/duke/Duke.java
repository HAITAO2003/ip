package duke;

import commands.Command;

import java.util.ArrayList;

/**
 * Main class for the Duke task management application.
 * Handles the overall program flow and user interactions.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes Duke with the specified storage file path.
     *
     * @param filePath The path to the file for storing tasks
     * @throws DukeException If there is an error loading the tasks
     */
    public Duke(String filePath) throws DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Runs the main program loop, processing user input until exit.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main entry point of the application.
     *
     * @param args Command line arguments (not used)
     * @throws DukeException If there is an error starting the application
     */
    public static void main(String[] args) throws DukeException {
        new Duke("data/tasks.txt").run();
    }
}