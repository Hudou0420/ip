package main.java.Hudou.task;

public class Task {

    protected final String completedSymbol = "[X]";
    protected final String uncompletedSymbol = "[ ]";
    protected final int COMPLETION_STATUS_OFFSET = 1;
    protected final int TASK_NAME_OFFSET = 2;



    protected String taskName;
    protected boolean isCompleted;

    public Task(){
        this.isCompleted = false;
        this.taskName = null;
    }
    public Task(String taskName){
        this.taskName = taskName;
        this.isCompleted = false;
    }

    public String getTaskName(){ return this.taskName; }
    public boolean getTaskCompletionStatus(){ return this.isCompleted; }
    public String getTaskInString(){
        return taskName;
    }

    public void setCompleted(){ this.isCompleted = true; }
    public void setUncompleted(){ this.isCompleted = false; }

    public String printTask(){
        String completionStatus = (isCompleted ? completedSymbol : uncompletedSymbol) + " ";
        return (completionStatus + " " + this.taskName);
    }
}
