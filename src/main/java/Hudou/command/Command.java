package main.java.Hudou.command;

import main.java.Hudou.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList taskList);
}

