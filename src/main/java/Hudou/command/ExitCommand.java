package main.java.Hudou.command;

import main.java.Hudou.list.TaskList;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList) {
        ChatBot.endSession();
    }
}

