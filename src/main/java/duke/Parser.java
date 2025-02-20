package duke;

import commands.*;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;

public class Parser {
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_FIND = "find";

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