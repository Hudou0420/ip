package main.java.Hudou.task;

import main.java.Hudou.parser.DateTime;
import main.java.Hudou.parser.DateTimeParser;
import main.java.Hudou.parser.Pair;
import main.java.Hudou.parser.SentenceParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Event extends Task{
    protected final String taskSymbol = "[E]";
    protected final String taskType = "event";
    protected final String startTimeCommand = "/from";
    protected final String endTimeCommand = "/to";
    protected final int START_TIME_OFFSET = 3;
    protected final int END_TIME_OFFSET = 4;

    protected DateTime startTime;
    protected DateTime endTime;

    private void printAddedTask(){
        System.out.println("Added new event: " + this.taskName +
                " from: " + this.startTime + ", to: " + this.endTime);
    }

    public Event(String input) throws Exception {
        String taskDetail = SentenceParser.getSubstringFromSecondWord(input);
        String[] taskDetails = SentenceParser.splitBySubstringCommands(taskDetail, startTimeCommand);
        this.taskName = taskDetails[0];
        String[] taskStartAndEndTime = SentenceParser.splitBySubstringCommands(taskDetails[1], endTimeCommand);
        Pair<LocalDate, LocalTime> startDateTime = DateTimeParser.extractDateTime(taskStartAndEndTime[0]);
        this.startTime = new DateTime(startDateTime.getFirst(), startDateTime.getSecond());
        Pair<LocalDate, LocalTime> endDateTime = DateTimeParser.extractDateTime(taskStartAndEndTime[1]);
        this.endTime = new DateTime(endDateTime.getFirst(), endDateTime.getSecond());
        printAddedTask();
    }

    public Event(String[] inputs) throws Exception {
        this.isCompleted =
                inputs[COMPLETION_STATUS_OFFSET].equals(completedSymbol);
        this.taskName = inputs[TASK_NAME_OFFSET];
        this.startTime = DateTime.parse(inputs[START_TIME_OFFSET]);
        this.endTime = DateTime.parse(inputs[END_TIME_OFFSET]);
    }

    public String printTask(){
        String completionStatus = (isCompleted ? completedSymbol : uncompletedSymbol) + " ";
        return (taskSymbol + completionStatus + this.taskName +
                " (from: "+ this.startTime.printDateTime() +  ", to: "
                + this.endTime.printDateTime() + ")");
    }

    public String getTaskInString(){
        String completionStatus = (isCompleted ? completedSymbol : uncompletedSymbol);
        String[] taskAttributes = {taskType, completionStatus, taskName,
                startTime.toString(), endTime.toString()};
        return String.join("|", taskAttributes);
    }

}
