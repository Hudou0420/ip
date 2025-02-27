package main.java.Hudou.task;

import main.java.Hudou.parser.DateTime;
import main.java.Hudou.parser.DateTimeParser;
import main.java.Hudou.parser.Pair;
import main.java.Hudou.parser.SentenceParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    protected DateTime startTime;
    protected DateTime endTime;

    private void printAddedTask(){
        System.out.println("Added new event: " + this.taskName +
                " from: " + this.startTime + ", to: " + this.endTime);
    }

    public Event(String input) throws Exception {
        String taskDetail = SentenceParser.getSubstringFromSecondWord(input);
        String[] taskDetails = SentenceParser.splitBySubstringCommands(taskDetail, START_TIME_COMMAND);
        this.taskName = taskDetails[0];
        String[] taskStartAndEndTime = SentenceParser.splitBySubstringCommands(taskDetails[1], END_TIME_COMMAND);
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
        return (TASK_SYMBOL + completionStatus + this.taskName +
                " (from: "+ this.startTime.printDateTime() +  ", to: "
                + this.endTime.printDateTime() + ")");
    }

    public String getTaskInString(){
        String completionStatus = (isCompleted ? completedSymbol : uncompletedSymbol);
        String[] taskAttributes = {TASK_TYPE, completionStatus, taskName,
                startTime.toString(), endTime.toString()};
        return String.join("|", taskAttributes);
    }

}
