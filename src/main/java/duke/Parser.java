package duke;

import commands.*;

public class Parser {
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EXIT = "bye";

    public static Command parseCommand(String input) throws DukeException {
        String[] parts = input.trim().split(" ", 2);
        if (parts.length == 0 || parts[0].isEmpty()) {
            throw new DukeException("Empty command!");
        }

        String commandType = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1].trim() : "";

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
            default:
                throw new DukeException("I don't understand that command. Please try again!");
        }
    }

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

    private static Command parseTodo(String args) throws DukeException {
        if (args.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty!");
        }
        return new AddCommand(new ToDo(args));
    }

    private static Command parseDelete(String args) throws DukeException {
        try {
            int index = Integer.parseInt(args.trim());
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide a valid task number!");
        }
    }

    private static Command parseMark(String args) throws DukeException {
        try {
            int index = Integer.parseInt(args.trim());
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide a valid task number!");
        }
    }
}