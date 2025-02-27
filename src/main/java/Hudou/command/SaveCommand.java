package main.java.Hudou.command;

import main.java.Hudou.storage.IOHandler;
import main.java.Hudou.task.TaskList;

//child class of Command to call when user wants to save their progress into the file
//it WILL NOT be called automatically when a user updates something
public class SaveCommand extends Command {
    @Override
    public void execute(TaskList taskList) {
        IOHandler.writeTasksToFile(taskList.getTasks());
    }
}
