package main.java.Hudou.task;

import main.java.Hudou.exception.InvalidDateFormatException;
import main.java.Hudou.parser.DateTimeParser;
import main.java.Hudou.parser.SentenceParser;
import main.java.Hudou.exception.HudouException;

public class Deadline extends Task {
    protected String deadline;
    protected final String taskSymbol = "[D]";
    protected final String taskType = "deadline";
    protected final String taskCommand = "/by";
    protected final int DEADLINE_OFFSET = 3;
    //protected LocalDate deadline;


    private void printAddedTask(){
        System.out.println("Added new deadline: " + this.taskName + ", by: " + this.deadline);
    }

    public Deadline(String input) throws Exception {
        String taskDetail = SentenceParser.getSubstringFromSecondWord(input);
        String[] taskDetails = SentenceParser.splitBySubstringCommands(taskDetail, taskCommand);
        this.taskName = taskDetails[0];
        this.deadline = DateTimeParser.extractDateTime(taskDetails[1]);
        printAddedTask();
    }

    public Deadline(String[] inputs) throws Exception {
        this.isCompleted =
                inputs[COMPLETION_STATUS_OFFSET].equals(completedSymbol);
        this.taskName = inputs[TASK_NAME_OFFSET];
        this.deadline = inputs[DEADLINE_OFFSET];

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
