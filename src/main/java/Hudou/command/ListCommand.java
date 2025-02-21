package main.java.Hudou.command;

import main.java.Hudou.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList) {
        taskList.listTasks();
    }
}
