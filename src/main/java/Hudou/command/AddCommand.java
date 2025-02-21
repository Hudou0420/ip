package main.java.Hudou.command;

import main.java.Hudou.task.TaskList;
import main.java.Hudou.exception.HudouException;

public class AddCommand extends Command {
    private String input;
    private boolean isLoaded;

    public AddCommand(String input, boolean isLoaded) {
        this.input = input;
        this.isLoaded = isLoaded;
    }

    @Override
    public void execute(TaskList taskList) {
        if (input.split(" ").length < 2) {
            System.out.println(HudouException.emptyDescription);
            return;
        }
        taskList.addTask(input, isLoaded);
    }
}

