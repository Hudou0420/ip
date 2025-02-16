package main.java.Hudou.task;

import main.java.Hudou.exception.HudouException;

public class Todo extends Task {
    protected final String taskType = "[T]";


    public Todo(String input){
        try{
            this.taskName = getSubstringFromSecondWord(input);
            this.isCompleted = false;
            System.out.println("Added new todo task: " + this.taskName);
        } catch (NullPointerException e){
            HudouException.handleEmptyTask();
        } catch (ArrayIndexOutOfBoundsException e){
            HudouException.handleInvalidTask();
        }
    }

    public String printTask(){
        String completionStatus = isCompleted ? "[X] " : "[ ] ";
        return (taskType + completionStatus + this.taskName);
    }

}
