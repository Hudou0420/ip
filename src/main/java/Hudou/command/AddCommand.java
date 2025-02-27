package main.java.Hudou.command;

import main.java.Hudou.task.TaskList;
import main.java.Hudou.exception.HudouException;

//Child class of command to execute adding task commands
public class AddCommand extends Command {
    private String input;
    private boolean isReadFromFile;

    public AddCommand(String input, boolean isReadFromFile) {
        this.input = input;
        this.isReadFromFile = isReadFromFile;
    }

    @Override
    public void execute(TaskList taskList) {
        if (input.split(" ").length < 2) {
            System.out.println(HudouException.emptyDescription);
            return;
        }
        taskList.addTask(input, isReadFromFile);
    }
}

