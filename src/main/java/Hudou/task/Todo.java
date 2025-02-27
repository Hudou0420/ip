package main.java.Hudou.task;
import main.java.Hudou.parser.SentenceParser;
import main.java.Hudou.exception.HudouException;

public class Todo extends Task {
    protected final String taskSymbol = "[T]";
    protected final String taskType = "todo";

    private void printAddedTask(){
        System.out.println("Added new todo task: " + this.taskName);
    }


    public Todo(String input) throws Exception {
        this.taskName = SentenceParser.getSubstringFromSecondWord(input);
        this.isCompleted = false;
        printAddedTask();
    }

    public Todo(String[] inputs) throws Exception {
        this.isCompleted =
                inputs[COMPLETION_STATUS_OFFSET].equals(completedSymbol);
        this.taskName = inputs[TASK_NAME_OFFSET];
    }

    public String printTask(){
        String completionStatus = (isCompleted ?
                completedSymbol : uncompletedSymbol) + " ";
        return (taskSymbol + completionStatus + this.taskName);
    }

    public String getTaskInString(){
        String completionStatus = (isCompleted ?
                completedSymbol : uncompletedSymbol);
        String[] taskAttributes = {taskType, completionStatus, taskName};
        return String.join("|", taskAttributes);
    }

}
