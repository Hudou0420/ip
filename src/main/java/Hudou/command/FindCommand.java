package main.java.Hudou.command;
import main.java.Hudou.list.*;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList) {
        SearchList matches = taskList.findTask(keyword);
        matches.printTasks(); // Assuming SearchList has a method to print results
    }
}

