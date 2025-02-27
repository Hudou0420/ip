package main.java.Hudou.task;

import main.java.Hudou.parser.SentenceParser;
import main.java.Hudou.exception.HudouException;

//child class of tasks to store the deadline task only
//the general attributes can be seen in the parent class Task
//the class further includes a deadline to tell user the deadline
//this task should be done by
public class Deadline extends Task {
    protected String deadline;
    protected final String TASK_SYMBOL = "[D]";
    protected final String TASK_TYPE = "deadline";
    protected final String TASK_COMMAND = "/by";

    //standard format for deadline task storage in file.
    //first 3 elements in the storage string follows the general Task
    //4th (index 3) contains the deadline, so offset 3
    protected final int DEADLINE_OFFSET = 3;


    private void printAddedTask(){
        System.out.println("Added new deadline: " + this.taskName + ", by:" + this.deadline);
    }

    public Deadline(String input){
        try{
            String taskDetail = SentenceParser.getSubstringFromSecondWord(input);   //get the substring after the command
            //further split the string up by getting the task name and task deadline
            //the details of the command format is written in UG
            String[] taskDetails = SentenceParser.splitBySubstringCommands(taskDetail, TASK_COMMAND);
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
        return (TASK_SYMBOL + completionStatus + this.taskName + " (by:" + this.deadline + ")");
    }

    //method used for storing the deadline task
    public String getTaskInString(){
        String completionStatus = (isCompleted ? completedSymbol : uncompletedSymbol);
        String[] taskAttributes = {TASK_TYPE, completionStatus, taskName, deadline};
        return String.join("|", taskAttributes);
    }
}
