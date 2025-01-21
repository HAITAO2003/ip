import utils.Utils;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    public TaskList() {
        this.list = new ArrayList<>();
    }
    public void add_item(Task item) {
        this.list.add(item);
        System.out.println("Got it. I've added this task:");
        if (item instanceof Deadline) {
            Deadline deadline = (Deadline) item;
            System.out.printf("     [%s] [%s] %s (by: %s)\n",
                    deadline.getTypeIcon(),deadline.getStatusIcon(), deadline.getDescription(), deadline.getDeadline());
        }else if (item instanceof ToDo) {
            ToDo todo = (ToDo) item;
            System.out.printf("     [%s] [%s] %s\n", todo.getTypeIcon(), todo.getStatusIcon(), todo.getDescription());
        }else if (item instanceof Event){
            Event event = (Event) item;
            System.out.printf("     [%s] [%s] %s (from:%s to:%s)\n",
                    event.getTypeIcon(),event.getStatusIcon(), event.getDescription(), event.getStartTime(), event.getEndTime());
        }else{
            Utils.output("Task type not supported");
        }
        Utils.output("Now you have " + this.list.size() + " tasks in the list.");
    }

    public void delete_item(String description){
        this.list.removeIf(task -> task.getDescription().equals(description));
        Utils.output("Deleted: " + description);
    }
    public void mark_done_by_index(int idx) {
        if (idx > this.list.size()){
            Utils.output("Task number does not exist!");
        }else{
            System.out.println("Nice! I've marked this task as done:");
            Task item = this.list.get(idx - 1);
            item.markDone();
            Utils.output("  " +idx + ".[" + item.getStatusIcon() +"]" + item.getDescription());
        }
    }
    public void print() {
        int idx = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task item : this.list) {
            if (item instanceof Deadline) {
                Deadline deadline = (Deadline) item;
                System.out.printf("%d.[%s] [%s] %s (by: %s)\n",
                        idx, deadline.getTypeIcon(), deadline.getStatusIcon(),
                        deadline.getDescription(), deadline.getDeadline());
            } else if (item instanceof ToDo) {
                ToDo todo = (ToDo) item;
                System.out.printf("%d.[%s] [%s] %s\n",
                        idx, todo.getTypeIcon(), todo.getStatusIcon(),
                        todo.getDescription());
            } else if (item instanceof Event) {
                Event event = (Event) item;
                System.out.printf("%d.[%s] [%s] %s (from: %s to: %s)\n",
                        idx, event.getTypeIcon(), event.getStatusIcon(),
                        event.getDescription(), event.getStartTime(), event.getEndTime());
            }
            idx = idx + 1;
        }
        Utils.output("");
    }
}
