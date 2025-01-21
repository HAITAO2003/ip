import utils.Utils;
import java.util.Optional;

public class Duke {
    static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Utils.output("Hello from\n" + logo);
        Utils.output("Hello! I'm Duke \nWhat can I do for you? ");

        String input_str = Utils.get_input(Optional.empty());
        String[] parts = input_str.split(" ", 2);

        while (!input_str.equals("bye")) {
            try {
                switch(parts[0]) {
                    case "deadline":
                        if (parts.length <= 1 || !parts[1].contains("/by")) {
                            throw new DukeException("Please provide a task description and deadline using /by!");
                        }
                        String description = parts[1].split("/by")[0].strip();
                        String deadline = parts[1].split("/by")[1];
                        if (description.isEmpty()) {
                            throw new DukeException("The task description cannot be empty!");
                        }
                        taskList.add_item(new Deadline(description, deadline));
                        break;

                    case "todo":
                        if (parts.length <= 1) {
                            throw new DukeException("The description of a todo cannot be empty!");
                        }
                        taskList.add_item(new ToDo(parts[1].strip()));
                        break;

                    case "event":
                        if (parts.length <= 1 || !parts[1].contains("/from") || !parts[1].contains("/to")) {
                            throw new DukeException("Please provide event details with /from and /to!");
                        }
                        String[] temp = parts[1].split("/from|/to");
                        description = temp[0].strip();
                        if (description.isEmpty()) {
                            throw new DukeException("The event description cannot be empty!");
                        }
                        String startTime = temp[1];
                        String endTime = temp[2];
                        taskList.add_item(new Event(description, startTime, endTime));
                        break;

                    case "delete":
                        if (parts.length <= 1) {
                            throw new DukeException("Please specify what to delete!");
                        }
                        taskList.delete_item(parts[1].strip());
                        break;

                    case "list":
                        taskList.print();
                        break;

                    case "mark":
                        if (parts.length <= 1) {
                            throw new DukeException("Please specify which task to mark as done!");
                        }
                        try {
                            int taskNum = Integer.parseInt(parts[1]);
                            taskList.mark_done_by_index(taskNum);
                        } catch (NumberFormatException e) {
                            throw new DukeException("Please provide a valid task number!");
                        }
                        break;

                    default:
                        throw new DukeException("I don't understand that command. Please try again!");
                }
            } catch (DukeException e) {
                Utils.output(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                Utils.output("Something went wrong! Please check your command format.");
            } catch (Exception e) {
                Utils.output("An unexpected error occurred: " + e.getMessage());
            }

            input_str = Utils.get_input(Optional.empty());
            parts = input_str.split(" ", 2);
        }
        Utils.output("Bye. Hope to see you again soon!");
    }
}