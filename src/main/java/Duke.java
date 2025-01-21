import utils.Utils;

import java.util.Optional;


public class Duke {
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Utils.output("Hello from\n" + logo);
        Utils.output("Hello! I'm Duke \nWhat can I do for you? ");
        String input_str = Utils.get_input(Optional.empty());
        while (!input_str.equals("bye")) {
            Utils.output(input_str);
            input_str = Utils.get_input(Optional.empty());
        }
        Utils.output("Bye. Hope to see you again soon!");
    }

}
