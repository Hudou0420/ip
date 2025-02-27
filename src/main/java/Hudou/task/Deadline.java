package main.java.Hudou.task;

import main.java.Hudou.exception.InvalidDateFormatException;
import main.java.Hudou.parser.DateTimeParser;
import main.java.Hudou.parser.Pair;
import main.java.Hudou.parser.SentenceParser;
import main.java.Hudou.exception.HudouException;

//child class of tasks to store the deadline task only
//the general attributes can be seen in the parent class Task
//the class further includes a deadline to tell user the deadline
//this task should be done by
import java.time.LocalDate;
import java.time.LocalTime;
import main.java.Hudou.parser.DateTime;

public class Deadline extends Task {
    //protected String deadline;
    protected final String TASK_SYMBOL = "[D]";
    protected final String TASK_TYPE = "deadline";
    protected final String TASK_COMMAND = "/by";

    //standard format for deadline task storage in file.
    //first 3 elements in the storage string follows the general Task
    //4th (index 3) contains the deadline, so offset 3
    protected final int DEADLINE_OFFSET = 3;
    protected DateTime deadline;


    private void printAddedTask(){
        System.out.println("Added new deadline: " + this.taskName + ", by: " + this.deadline.printDateTime());
    }

    public Deadline(String input) throws Exception {
        String taskDetail = SentenceParser.getSubstringFromSecondWord(input);
        String[] taskDetails = SentenceParser.splitBySubstringCommands(taskDetail, TASK_COMMAND);
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
        return (TASK_SYMBOL + completionStatus + this.taskName + " (by:" + this.deadline.printDateTime() + ")");
    }

    //method used for storing the deadline task
    public String getTaskInString(){
        String completionStatus = (isCompleted ? completedSymbol : uncompletedSymbol);
        String[] taskAttributes = {TASK_TYPE, completionStatus, taskName, deadline.toString()};
        return String.join("|", taskAttributes);
    }
}
