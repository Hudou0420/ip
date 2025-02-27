package main.java.Hudou.list;

import main.java.Hudou.task.Task;

import java.util.ArrayList;

public class SearchList implements List{
    private class searchResult{
        private Task task;
        private int index;

        public searchResult(Task task, int index) {
            this.task = task;
            this.index = index;
        }

        public Task getTask() { return task; }
        public int getIndex() { return index; }
    }

    private ArrayList<searchResult> results;

    public SearchList() {
        results = new ArrayList<searchResult>();
    }

    public void printTasks(){
        if (results.size() == 0) {
            System.out.println("No tasks found, please check your list");
            return;
        }
        for (searchResult r : results){
            System.out.println(r.getIndex() + ". " +r.getTask().printTask());
        }
        System.out.println("To modify or delete tasks above, call the task by its index.");
    }

    public void add(Task task, int index){
        results.add(new searchResult(task, index));
    }

}
