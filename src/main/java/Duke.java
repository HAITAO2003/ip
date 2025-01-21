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
            switch(parts[0]){
                case "deadline":
                    if (parts.length <= 1){
                        Utils.output("invalid input!");
                    } else {
                        String description = parts[1].split("/by")[0].strip();
                        String deadline = parts[1].split("/by")[1];
                        taskList.add_item(new Deadline(description, deadline));
                    }
                    input_str = Utils.get_input(Optional.empty());
                    parts = input_str.split(" ", 2);
                    break;
                case "todo":
                    if (parts.length <= 1){
                        Utils.output("invalid input!");
                    } else {
                        taskList.add_item(new ToDo(parts[1].strip()));
                    }
                    input_str = Utils.get_input(Optional.empty());
                    parts = input_str.split(" ", 2);
                    break;
                case "event":
                    if (parts.length <= 1){
                        Utils.output("invalid input!");
                    } else {
                        String[] temp = parts[1].split("/from|/to");
                        String description = temp[0].strip();
                        String startTime = temp[1];
                        String endTime = temp[2];
                        taskList.add_item(new Event(description, startTime, endTime));
                    }
                    input_str = Utils.get_input(Optional.empty());
                    parts = input_str.split(" ", 2);
                    break;
                case "delete":
                    if (parts.length <= 1){
                        Utils.output("invalid input!");
                    } else {
                        taskList.delete_item(parts[1].strip());
                    }
                    input_str = Utils.get_input(Optional.empty());
                    parts = input_str.split(" ", 2);
                    break;
                case "list":
                    taskList.print();
                    input_str = Utils.get_input(Optional.empty());
                    parts = input_str.split(" ", 2);
                    break;
                case "mark":
                    if (parts.length <= 1){
                        Utils.output("invalid input!");
                    } else {
                        taskList.mark_done_by_index(Integer.parseInt(parts[1]));
                    }
                default:
                    input_str = Utils.get_input(Optional.empty());
                    parts = input_str.split(" ",2);
            }
        }
        Utils.output("Bye. Hope to see you again soon!");
    }

}
