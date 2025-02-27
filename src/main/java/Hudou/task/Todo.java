package main.java.Hudou.task;
import main.java.Hudou.parser.SentenceParser;
import main.java.Hudou.exception.HudouException;

//child class which inherits from parent class Task
//no big difference from Task except different symbols and keywordss
public class Todo extends Task {
    protected final String TASK_SYMBOL = "[T]";
    protected final String TASK_TYPE = "todo";

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
        return (TASK_SYMBOL + completionStatus + this.taskName);
    }

    public String getTaskInString(){
        String completionStatus = (isCompleted ?
                completedSymbol : uncompletedSymbol);
        String[] taskAttributes = {TASK_TYPE, completionStatus, taskName};
        return String.join("|", taskAttributes);
    }

}
