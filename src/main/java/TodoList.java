import utils.Utils;

import java.util.ArrayList;
import java.util.Optional;

public class TodoList {
    private ArrayList<Task> list;

    public TodoList() {
        this.list = new ArrayList<>();
    }
    public void add_item(Task item) {
        this.list.add(item);
        Utils.output("Added: " + item.getDescription());
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
        for (Task item : this.list) {
            System.out.println(idx + ".[" +item.getStatusIcon() + "] "+ item.getDescription());
            idx = idx + 1;
        }
        Utils.output("");
    }
}