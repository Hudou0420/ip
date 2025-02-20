package main.java.Hudou.command;

import main.java.Hudou.task.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.deleteTask(index);
    }
}
