package duke;

import commands.*;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;

/**
 * Parser class that handles the parsing of user input into commands.
 * Validates input format and creates appropriate command objects.
 */
public class Parser {
    private static final String COMMAND_TYPE_TODO = "todo";
    private static final String COMMAND_TYPE_DEADLINE = "deadline";
    private static final String COMMAND_TYPE_EVENT = "event";
    private static final String COMMAND_TYPE_LIST = "list";
    private static final String COMMAND_TYPE_MARK = "done";
    private static final String COMMAND_TYPE_DELETE = "delete";
    private static final String COMMAND_TYPE_EXIT = "bye";

    /**
     * Parses user input into a Command object.
     *
     * @param input The user input string to parse
     * @return The corresponding Command object
     * @throws DukeException If the input format is invalid or command is not recognized
     */
    public static Command parseCommand(String input) throws DukeException {
        String[] parts = input.trim().split(" ", 2);
        if (parts.length == 0 || parts[0].isEmpty()) {
            throw new DukeException("Empty command!");
        }

        String commandType = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1].trim() : "";

        return switch (commandType) {
            case COMMAND_TYPE_DEADLINE -> parseDeadline(args);
            case COMMAND_TYPE_EVENT -> parseEvent(args);
            case COMMAND_TYPE_TODO -> parseTodo(args);
            case COMMAND_TYPE_DELETE -> parseDelete(args);
            case COMMAND_TYPE_MARK -> parseMark(args);
            case COMMAND_TYPE_LIST -> new ListCommand();
            case COMMAND_TYPE_EXIT -> new ExitCommand();
            default -> throw new DukeException("I don't understand that command. Please try again!");
        };
    }

    /**
     * Parses a deadline command string into a Command object.
     * Expected format: description /by deadline_time
     *
     * @param args The arguments for the deadline command
     * @return A Command object representing the deadline task
     * @throws DukeException If the deadline format is invalid or description is empty
     */
    private static Command parseDeadline(String args) throws DukeException {
        if (!args.contains("/by")) {
            throw new DukeException("Please provide a task description and deadline using /by!");
        }
        String[] parts = args.split("/by");
        String description = parts[0].strip();
        String deadline = parts[1].strip();

        if (description.isEmpty()) {
            throw new DukeException("The task description cannot be empty!");
        }

        return new AddCommand(new Deadline(description, deadline));
    }

    /**
     * Parses an event command string into a Command object.
     * Expected format: description /from start_time /to end_time
     *
     * @param args The arguments for the event command
     * @return A Command object representing the event task
     * @throws DukeException If the event format is invalid or description is empty
     */
    private static Command parseEvent(String args) throws DukeException {
        if (!args.contains("/from") || !args.contains("/to")) {
            throw new DukeException("Please provide event details with /from and /to!");
        }
        String[] parts = args.split("/from|/to");
        String description = parts[0].strip();
        String startTime = parts[1].strip();
        String endTime = parts[2].strip();

        if (description.isEmpty()) {
            throw new DukeException("The event description cannot be empty!");
        }

        return new AddCommand(new Event(description, startTime, endTime));
    }

    /**
     * Parses a todo command string into a Command object.
     *
     * @param args The description for the todo task
     * @return A Command object representing the todo task
     * @throws DukeException If the description is empty
     */
    private static Command parseTodo(String args) throws DukeException {
        if (args.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty!");
        }
        return new AddCommand(new ToDo(args));
    }

    /**
     * Parses a delete command string into a Command object.
     *
     * @param args The index of the task to delete
     * @return A Command object for deleting a task
     * @throws DukeException If the task number is not a valid integer
     */
    private static Command parseDelete(String args) throws DukeException {
        try {
            int index = Integer.parseInt(args.trim());
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide a valid task number!");
        }
    }

    /**
     * Parses a mark command string into a Command object.
     *
     * @param args The index of the task to mark as done
     * @return A Command object for marking a task as done
     * @throws DukeException If the task number is not a valid integer
     */
    private static Command parseMark(String args) throws DukeException {
        try {
            int index = Integer.parseInt(args.trim());
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide a valid task number!");
        }
    }
}