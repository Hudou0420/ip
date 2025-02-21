package main.java.Hudou.task;

import main.java.Hudou.parser.SentenceParser;
import main.java.Hudou.exception.HudouException;

public class Deadline extends Task {
    protected String deadline;
    protected final String taskSymbol = "[D]";
    protected final String taskType = "deadline";
    protected final String taskCommand = "/by";
    protected final int DEADLINE_OFFSET = 3;


    private void printAddedTask(){
        System.out.println("Added new deadline: " + this.taskName + ", by:" + this.deadline);
    }

    public Deadline(String input){
        try{
            String taskDetail = SentenceParser.getSubstringFromSecondWord(input);
            String[] taskDetails = SentenceParser.splitBySubstringCommands(taskDetail, taskCommand);
            this.taskName = taskDetails[0];
            this.deadline = taskDetails[1];
            printAddedTask();
        } catch (NullPointerException e){
            HudouException.handleEmptyTask();
        } catch (ArrayIndexOutOfBoundsException e){
            HudouException.handleInvalidTask();
        }
    }

    public Deadline(String[] inputs){
        try{
            this.isCompleted =
                    inputs[COMPLETION_STATUS_OFFSET].equals(completedSymbol);
            this.taskName = inputs[TASK_NAME_OFFSET];
            this.deadline = inputs[DEADLINE_OFFSET];
        } catch (NullPointerException e){
            HudouException.handleEmptyTask();
        } catch (ArrayIndexOutOfBoundsException e){
            HudouException.handleInvalidTask();
        }
    }

    public String printTask(){
        String completionStatus = (isCompleted ? completedSymbol : uncompletedSymbol) + " ";
        return (taskSymbol + completionStatus + this.taskName + " (by:" + this.deadline + ")");
    }

    public String getTaskInString(){
        String completionStatus = (isCompleted ? completedSymbol : uncompletedSymbol);
        String[] taskAttributes = {taskType, completionStatus, taskName, deadline};
        return String.join("|", taskAttributes);
    }
}
