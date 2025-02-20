package main.java.Hudou.task;

import main.java.Hudou.exception.HudouException;

public class Event extends Task{
    protected final String taskSymbol = "[E]";
    protected final String taskType = "event";
    protected final String startTimeCommand = "/from";
    protected final String endTimeCommand = "/to";
    protected final int START_TIME_OFFSET = 3;
    protected final int END_TIME_OFFSET = 4;

    protected String startTime;
    protected String endTime;

    private void printAddedTask(){
        System.out.println("Added new event: " + this.taskName +
                " from: " + this.startTime + ", to: " + this.endTime);
    }

    public Event(String input){
        try{
            String taskDetail = getSubstringFromSecondWord(input);
            String[] taskDetails = splitBySubstringCommands(taskDetail, startTimeCommand);
            this.taskName = taskDetails[0];
            String[] taskStartAndEndTime = splitBySubstringCommands(taskDetails[1], endTimeCommand);
            this.startTime = taskStartAndEndTime[0];
            this.endTime = taskStartAndEndTime[1];
            printAddedTask();
        } catch (NullPointerException e){
            HudouException.handleEmptyTask();
        } catch (ArrayIndexOutOfBoundsException e){
            HudouException.handleInvalidTask();
        }
    }

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
        return (taskSymbol + completionStatus + this.taskName +
                " (from:"+ this.startTime +  ", to:"  + this.endTime + ")");
    }

    public String getTaskInString(){
        String completionStatus = (isCompleted ? completedSymbol : uncompletedSymbol);
        String[] taskAttributes = {taskType, completionStatus, taskName, startTime, endTime};
        return String.join("|", taskAttributes);
    }

}
