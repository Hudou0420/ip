package main.java.Hudou.task;

import main.java.Hudou.parser.SentenceParser;
import main.java.Hudou.exception.HudouException;

//this is a child class method to store special type of task.
//it contains a start and end time extending from general attributes of Task
public class Event extends Task{
    protected final String TASK_SYMBOL = "[E]";
    protected final String TASK_TYPE = "event";
    protected final String START_TIME_COMMAND = "/from";
    protected final String END_TIME_COMMAND = "/to";

    //when storing the task in file, standard format as follows:
    //first 3 elements follows the general Task format
    //4th (index 3) is the event start time
    //5th (index 4) is the event end time
    protected final int START_TIME_OFFSET = 3;
    protected final int END_TIME_OFFSET = 4;

    protected String startTime;
    protected String endTime;

    private void printAddedTask(){
        System.out.println("Added new event: " + this.taskName +
                " from: " + this.startTime + ", to: " + this.endTime);
    }

    //constructor when the user entered to add a new event
    public Event(String input){
        try{
            //get substring for command description. It will start after the command type, so from 2nd word onwards
            String taskDetail = SentenceParser.getSubstringFromSecondWord(input);
            //further breakdown the task description. To recognise keyword "/from", then split the command into 3 parts
            //1st: taskname
            //2nd: task start time, recognised by
            String[] taskDetails = SentenceParser.splitBySubstringCommands(taskDetail, START_TIME_COMMAND);
            this.taskName = taskDetails[0];
            String[] taskStartAndEndTime = SentenceParser.splitBySubstringCommands(taskDetails[1], END_TIME_COMMAND);
            this.startTime = taskStartAndEndTime[0];
            this.endTime = taskStartAndEndTime[1];
            printAddedTask();
        } catch (NullPointerException e){
            HudouException.handleEmptyTask();
        } catch (ArrayIndexOutOfBoundsException e){
            HudouException.handleInvalidTask();
        }
    }

    //constructure, when the chatbot is initialised and reading from file
    public Event(String[] inputs){
        try{
            this.isCompleted =
                    inputs[COMPLETION_STATUS_OFFSET].equals(completedSymbol);
            this.taskName = inputs[TASK_NAME_OFFSET];
            this.startTime = inputs[START_TIME_OFFSET];
            this.endTime = inputs[END_TIME_OFFSET];
        } catch (NullPointerException e){
            HudouException.handleEmptyTask();
        } catch (ArrayIndexOutOfBoundsException e){
            HudouException.handleInvalidTask();
        }
    }

    public String printTask(){
        String completionStatus = (isCompleted ? completedSymbol : uncompletedSymbol) + " ";
        return (TASK_SYMBOL + completionStatus + this.taskName +
                " (from:"+ this.startTime +  ", to:"  + this.endTime + ")");
    }

    public String getTaskInString(){
        String completionStatus = (isCompleted ? completedSymbol : uncompletedSymbol);
        String[] taskAttributes = {TASK_TYPE, completionStatus, taskName, startTime, endTime};
        return String.join("|", taskAttributes);
    }

}
