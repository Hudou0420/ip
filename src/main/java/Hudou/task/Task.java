package main.java.Hudou.task;

//the parent class of all different types of tasks.
//has the general attribute of completion status, and task name
public class Task {

    protected final String completedSymbol = "[X]";
    protected final String uncompletedSymbol = "[ ]";

    //standard format for storing the task to file:
    //1th element (index 0): the task type, whether it is Todo, Deadline or Event
    //2nd element (index 1): whether the task is completed or not
    //3td element (index 2): the task name
    //4th element and onwards: special attributes that is different based on different type of tasks
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
