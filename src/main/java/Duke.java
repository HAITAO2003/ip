import utils.Utils;

import java.util.Optional;

public class Duke {
    static TodoList todoList = new TodoList();
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
                case "add":
                    if (parts.length <= 1){
                        Utils.output("invalid input!");
                    } else {
                        todoList.add_item(parts[1]);
                    }
                    input_str = Utils.get_input(Optional.empty());
                    parts = input_str.split(" ", 2);
                    break;
                case "delete":
                    if (parts.length <= 1){
                        Utils.output("invalid input!");
                    } else {
                        todoList.delete_item(parts[1]);
                    }
                    input_str = Utils.get_input(Optional.empty());
                    parts = input_str.split(" ", 2);
                    break;
                case "list":
                    todoList.print();
                    input_str = Utils.get_input(Optional.empty());
                    parts = input_str.split(" ", 2);
                    break;
                default:
                    input_str = Utils.get_input(Optional.empty());
                    parts = input_str.split(" ",2);
            }
        }
        Utils.output("Bye. Hope to see you again soon!");
    }

}
