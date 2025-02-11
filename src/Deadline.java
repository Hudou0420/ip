public class Deadline extends Task {
    protected String deadline;
    protected final String taskType = "[D]";
    protected final String taskCommand = "/by";

    public Deadline(String input){
        try{
            String taskDetail = getSubstringFromSecondWord(input);
            String[] taskDetails = splitBySubstringCommands(taskDetail, taskCommand);
            this.taskName = taskDetails[0];
            this.deadline = taskDetails[1];
            System.out.println("Added new deadline: " + this.taskName + ", by: " + this.deadline);
        } catch (NullPointerException e){
            Exception.handleEmptyTask();
        } catch (ArrayIndexOutOfBoundsException e){
            Exception.handleInvalidTask();
        }
    }

    public String printTask(){
        String completionStatus = isCompleted ? "[X] " : "[ ] ";
        return (taskType + completionStatus + this.taskName + " (by: " + this.deadline + ")");
    }
}
