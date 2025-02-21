package main.java.Hudou.command;

import main.java.Hudou.list.TaskList;

public abstract class Command {
    public abstract void execute(TaskList taskList);
}

