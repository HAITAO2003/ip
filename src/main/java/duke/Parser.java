package duke;

import commands.*;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;

/**
 * The Parser class handles the parsing of user input commands for the Duke task manager.
 * It converts string input into appropriate Command objects that can be executed by the system.
 * This class supports various commands including todo, deadline, event, list, mark, delete,
 * exit, and find operations.
 */
public class Parser {
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_FIND = "find";

    /**
     * Parses a user input string into a Command object.
     * The input string is expected to contain a command type followed by arguments (if required).
     *
     * @param input The user input string to be parsed
     * @return A Command object corresponding to the user input
     * @throws DukeException If the input is empty, invalid, or missing required arguments
     */
    public static Command parseCommand(String input) throws DukeException {
        // Assert input is not null
        assert input != null : "Input string cannot be null";

        String[] parts = input.trim().split(" ", 2);
        // Assert that after trimming and splitting, we have at least one part
        assert parts.length > 0 : "Split result cannot be empty after trimming";

        if (parts.length == 0 || parts[0].isEmpty()) {
            throw new DukeException("Empty command!");
        }

        String commandType = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1].trim() : "";

        // Assert commandType is lowercase and not empty
        assert !commandType.isEmpty() : "Command type cannot be empty after processing";
        assert commandType.equals(commandType.toLowerCase()) : "Command type must be lowercase";

        switch (commandType) {
            case COMMAND_DEADLINE:
                return parseDeadline(args);
            case COMMAND_EVENT:
                return parseEvent(args);
            case COMMAND_TODO:
                return parseTodo(args);
            case COMMAND_DELETE:
                return parseDelete(args);
            case COMMAND_MARK:
                return parseMark(args);
            case COMMAND_LIST:
                return new ListCommand();
            case COMMAND_EXIT:
                return new ExitCommand();
            case COMMAND_FIND:
                return parseFind(args);
            default:
                throw new DukeException("I don't understand that command. Please try again!");
        }
    }

    /**
     * Parses a deadline task from the given arguments.
     * Expected format: description /by deadline_time
     *
     * @param args The string containing task description and deadline
     * @return AddCommand containing the new Deadline task
     * @throws DukeException If the format is invalid or description is empty
     */
    private static Command parseDeadline(String args) throws DukeException {
        // Assert args is not null
        assert args != null : "Deadline arguments cannot be null";

        if (!args.contains("/by")) {
            throw new DukeException("Please provide a task description and deadline using /by!");
        }
        String[] parts = args.split("/by");
        // Assert we have exactly two parts after splitting by "/by"
        assert parts.length == 2 : "Deadline must have exactly one /by delimiter";

        String description = parts[0].strip();
        String deadline = parts[1].strip();

        if (description.isEmpty()) {
            throw new DukeException("The task description cannot be empty!");
        }

        // Assert both description and deadline are not empty after stripping
        assert !description.isEmpty() && !deadline.isEmpty() : "Description and deadline must not be empty after stripping";
        return new AddCommand(new Deadline(description, deadline));
    }

    /**
     * Parses a find command from the given arguments.
     * The argument should contain a non-empty search keyword.
     *
     * @param args The search keyword
     * @return FindCommand with the specified search keyword
     * @throws DukeException If the search keyword is empty
     */
    private static Command parseFind(String args) throws DukeException {
        // Assert args is not null
        assert args != null : "Find arguments cannot be null";

        if (args.isEmpty()) {
            throw new DukeException("The search keyword cannot be empty!");
        }

        // Assert search keyword is not empty after validation
        assert !args.isEmpty() : "Search keyword cannot be empty after validation";
        return new FindCommand(args);
    }

    /**
     * Parses an event task from the given arguments.
     * Expected format: description /from start_time /to end_time
     *
     * @param args The string containing event description and timing details
     * @return AddCommand containing the new Event task
     * @throws DukeException If the format is invalid or description is empty
     */
    private static Command parseEvent(String args) throws DukeException {
        // Assert args is not null
        assert args != null : "Event arguments cannot be null";

        if (!args.contains("/from") || !args.contains("/to")) {
            throw new DukeException("Please provide event details with /from and /to!");
        }
        String[] parts = args.split("/from|/to");
        // Assert we have exactly three parts after splitting
        assert parts.length == 3 : "Event must have exactly one /from and one /to delimiter";

        String description = parts[0].strip();
        String startTime = parts[1].strip();
        String endTime = parts[2].strip();

        if (description.isEmpty()) {
            throw new DukeException("The event description cannot be empty!");
        }

        // Assert all parts are not empty after stripping
        assert !description.isEmpty() && !startTime.isEmpty() && !endTime.isEmpty() :
                "Description, start time, and end time must not be empty after stripping";
        return new AddCommand(new Event(description, startTime, endTime));
    }

    /**
     * Parses a todo task from the given arguments.
     * The argument should contain a non-empty task description.
     *
     * @param args The task description
     * @return AddCommand containing the new ToDo task
     * @throws DukeException If the description is empty
     */
    private static Command parseTodo(String args) throws DukeException {
        // Assert args is not null
        assert args != null : "Todo arguments cannot be null";

        if (args.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty!");
        }

        // Assert description is not empty after validation
        assert !args.isEmpty() : "Todo description cannot be empty after validation";
        return new AddCommand(new ToDo(args));
    }

    /**
     * Parses a delete command from the given arguments.
     * The argument should contain a valid positive integer representing the task index.
     *
     * @param args The string containing the task index
     * @return DeleteCommand with the specified task index
     * @throws DukeException If the index is invalid or not a number
     */
    private static Command parseDelete(String args) throws DukeException {
        // Assert args is not null
        assert args != null : "Delete arguments cannot be null";

        try {
            int index = Integer.parseInt(args.trim());
            // Assert index is positive
            assert index > 0 : "Delete index must be positive";
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide a valid task number!");
        }
    }

    /**
     * Parses a mark command from the given arguments.
     * The argument should contain a valid positive integer representing the task index.
     *
     * @param args The string containing the task index
     * @return MarkCommand with the specified task index
     * @throws DukeException If the index is invalid, not positive, or not a number
     */
    private static Command parseMark(String args) throws DukeException {
        // Assert args is not null
        assert args != null : "Mark arguments cannot be null";

        try {
            if (args.isEmpty()) {
                throw new DukeException("Please specify which task to mark as done!");
            }
            int index = Integer.parseInt(args.trim());
            if (index <= 0) {
                throw new DukeException("Task number must be positive!");
            }

            // Assert index is positive after validation
            assert index > 0 : "Mark index must be positive after validation";
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide a valid task number!");
        }
    }
}