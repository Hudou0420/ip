package main.java.Hudou.command;

import main.java.Hudou.list.TaskList;

//child class from Command to execute listing of current tasks
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList) {
        taskList.printTasks();
    }
}
