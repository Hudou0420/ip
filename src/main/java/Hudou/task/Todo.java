package main.java.Hudou.task;
import main.java.Hudou.parser.SentenceParser;
import main.java.Hudou.exception.HudouException;

public class Todo extends Task {
    protected final String taskSymbol = "[T]";
    protected final String taskType = "todo";

    private void printAddedTask(){
        System.out.println("Added new todo task: " + this.taskName);
    }


    public Todo(String input){
        try{
            this.taskName = SentenceParser.getSubstringFromSecondWord(input);
            this.isCompleted = false;
            printAddedTask();
        } catch (NullPointerException e){
            HudouException.handleEmptyTask();
        } catch (ArrayIndexOutOfBoundsException e){
            HudouException.handleInvalidTask();
        }
    }

    public Todo(String[] inputs){
        try{
            this.isCompleted =
                    inputs[COMPLETION_STATUS_OFFSET].equals(completedSymbol);
            this.taskName = inputs[TASK_NAME_OFFSET];
        } catch (NullPointerException e){
            HudouException.handleEmptyTask();
        } catch (ArrayIndexOutOfBoundsException e){
            HudouException.handleInvalidTask();
        }
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
