public class Event extends Task{
    protected final String taskType = "[E]";
    protected final String startTimeCommand = "/from";
    protected final String endTimeCommand = "/to";

    protected String startTime;
    protected String endTime;


    public Event(String input){
        try{
            String taskDetail = getSubstringFromSecondWord(input);
            String[] taskDetails = splitBySubstringCommands(taskDetail, startTimeCommand);
            this.taskName = taskDetails[0];
            String[] taskStartAndEndTime = splitBySubstringCommands(taskDetails[1], endTimeCommand);
            this.startTime = taskStartAndEndTime[0];
            this.endTime = taskStartAndEndTime[1];
            System.out.println("Added new event: " + this.taskName + " from: " + this.startTime + ", to: " + this.endTime);
        } catch (NullPointerException e){
            Exception.handleEmptyTask();
        } catch (ArrayIndexOutOfBoundsException e){
            Exception.handleInvalidTask();
        }
    }

    public String printTask(){
        String completionStatus = isCompleted ? "[X] " : "[ ] ";
        return (taskType + completionStatus + this.taskName + " (from: "+ this.startTime +  ", to: "  + this.endTime + ")");
    }

}
