package main.java.Hudou.command;

import main.java.Hudou.task.TaskList;

//the parent class for general commands. called when the parseer has
//recognized a valid command. The child class of the command handles different types of commands
//with different actions.
public abstract class Command {
    public abstract void execute(TaskList taskList);
}

