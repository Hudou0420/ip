package main.java.Hudou.command;

import main.java.Hudou.storage.IOHandler;
import main.java.Hudou.task.TaskList;

public class SaveCommand extends Command {
    @Override
    public void execute(TaskList taskList) {
        IOHandler.writeTasksToFile(taskList.getTasks());
    }
}
