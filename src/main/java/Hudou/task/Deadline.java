package main.java.Hudou.task;

import main.java.Hudou.exception.InvalidDateFormatException;
import main.java.Hudou.parser.DateTimeParser;
import main.java.Hudou.parser.Pair;
import main.java.Hudou.parser.SentenceParser;
import main.java.Hudou.exception.HudouException;

import java.time.LocalDate;
import java.time.LocalTime;
import main.java.Hudou.parser.DateTime;

public class Deadline extends Task {
    //protected String deadline;
    protected final String taskSymbol = "[D]";
    protected final String taskType = "deadline";
    protected final String taskCommand = "/by";
    protected final int DEADLINE_OFFSET = 3;
    protected DateTime deadline;


    private void printAddedTask(){
        System.out.println("Added new deadline: " + this.taskName + ", by: " + this.deadline.printDateTime());
    }

    public Deadline(String input) throws Exception {
        String taskDetail = SentenceParser.getSubstringFromSecondWord(input);
        String[] taskDetails = SentenceParser.splitBySubstringCommands(taskDetail, taskCommand);
        this.taskName = taskDetails[0];
        Pair<LocalDate, LocalTime> deadlineDateTime = DateTimeParser.extractDateTime(taskDetails[1]);
        this.deadline = new DateTime(deadlineDateTime.getFirst(), deadlineDateTime.getSecond());
        printAddedTask();
    }

    public Deadline(String[] inputs) throws Exception {
        this.isCompleted =
                inputs[COMPLETION_STATUS_OFFSET].equals(completedSymbol);
        this.taskName = inputs[TASK_NAME_OFFSET];
        this.deadline = DateTime.parse(inputs[DEADLINE_OFFSET]);

    }

    public String printTask(){
        String completionStatus = (isCompleted ? completedSymbol : uncompletedSymbol) + " ";
        return (taskSymbol + completionStatus + this.taskName + " (by:" + this.deadline.printDateTime() + ")");
    }

    public String getTaskInString(){
        String completionStatus = (isCompleted ? completedSymbol : uncompletedSymbol);
        String[] taskAttributes = {taskType, completionStatus, taskName, deadline.toString()};
        return String.join("|", taskAttributes);
    }
}
