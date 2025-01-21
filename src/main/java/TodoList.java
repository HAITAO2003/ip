import utils.Utils;

import java.util.ArrayList;
import java.util.Optional;

public class TodoList {
    private ArrayList<String> list;

    public TodoList() {
        this.list = new ArrayList<>();
    }
    public void add_item(String item) {
        this.list.add(item);
        Utils.output("Added: " + item);
    }
    public void delete_item(String item){
        this.list.remove(item);
        Utils.output("Deleted: " + item);
    }
    public void print() {
        int idx = 0;
        for (String item : this.list) {
            System.out.println(idx + " : " + item);
            idx = idx + 1;
        }
        Utils.output("");
    }
}