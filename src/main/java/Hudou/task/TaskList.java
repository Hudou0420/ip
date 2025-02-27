package main.java.Hudou.task;

import main.java.Hudou.command.ChatBot;
import main.java.Hudou.exception.HudouException;

import java.util.ArrayList;

//a class to contain all single Task,
public class TaskList {

    //I still set a limit to the number of tasks can be set, so user won't be drowned by tasks
    public static final int MAXTASKCOUNT = 100;
    public static final String tooManyTasksNotifier =
            "You have too many events in your list! Complete some before adding new ones.";


    private ArrayList<Task> tasks;
    private int unfinishedTaskCounter;

    public ArrayList<Task> getTasks() {return tasks;}

    //to split the command depending on whether the input is read from
    //user in CLI or read from file
    private String[] splitStringByInputType(String input, boolean isReadFromFile){
        String splitter = isReadFromFile ? "\\|" : " ";
        String[] splitted = input.split(splitter);
        return splitted;
    }

    //depending on whether the task is read from file or from user input
    //the method is called when the chatbot wants to add a new task
    private Task classifyTaskTypes(String input, Boolean isReadFromFile){
        String[] inputArray = splitStringByInputType(input, isReadFromFile);
        if (isReadFromFile){
            return switch (inputArray[0]) {
                case "todo" -> new Todo(inputArray);
                case "deadline" -> new Deadline(inputArray);
                case "event" -> new Event(inputArray);
                default -> new Task(input);
            };
        } else{
            return switch (inputArray[0]) {
                case "todo" -> new Todo(input);
                case "deadline" -> new Deadline(input);
                case "event" -> new Event(input);
                default -> new Task(input);
            };
        }
    }

    //parameterless constructor, for inheritance purposes
    public TaskList() {
        tasks = new ArrayList<Task>();
        unfinishedTaskCounter = 0;
    }


    //The methods below is stored in TaskList instead of differnt command
    //This is because it is easier to handle, especially it needs to access
    //private variables in taskList. It will be more difficult to monitor and
    //modify if I place it inside different commands

    public void addTask(String taskInput, boolean isReadFromFile) {
        //handle exception of too many events being in the list
        if (tasks.size() >= MAXTASKCOUNT){
            System.out.println(tooManyTasksNotifier);
            return;
        }
        //process the first arg to get the type of the task
        Task currentTask = classifyTaskTypes(taskInput, isReadFromFile);
        tasks.add(currentTask);
        if (!isReadFromFile || !currentTask.getTaskCompletionStatus()){
            unfinishedTaskCounter++;
        }
        if (!isReadFromFile){ listTasks(); }
    }


    //method to print out all the tasks, whether it is completed or not
    //this method prints the tasks added earliest first.
    public void listTasks(){
        if (tasks.isEmpty()){
            HudouException.handleNoTaskNotifier();
            return;
        }
        System.out.println("You have " + unfinishedTaskCounter + " unfinished tasks in your list!");
        System.out.println(ChatBot.lineSeparator);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).printTask());
        }
        System.out.println(ChatBot.lineSeparator);
    }

    public void markDone(String taskName){
        if (tasks.isEmpty()){
            HudouException.handleNoTaskNotifier();
            return;
        }
        //iterate through the list to identify the task satisfying the name "taskName"
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskName().equals(taskName)) {
                tasks.get(i).setCompleted();
                unfinishedTaskCounter--;
                System.out.println("Task " + taskName + " has been marked done!");
                listTasks();
                return;
            }
        }
        HudouException.handleNonExistentTaskNotifier();
    }

    public void markDone(int index){
        if (HudouException.handleTaskListExceptions(index, tasks.size(), unfinishedTaskCounter)
                == HudouException.taskListErrors.errorCaught) {
            return;
        }
        //handle exception where the input index is
        //greater than the number of tasks inside the list
        if (index > tasks.size()){
            HudouException.handleNonExistentTaskNotifier();
            return;
        }
        if (tasks.get(index - 1).getTaskCompletionStatus()) {
            System.out.println("Task " + tasks.get(index - 1).getTaskName() + " has already been done!");
            //print task returns string because I need String output
            //in the lisTasks() method
            System.out.println(tasks.get(index - 1).printTask());
            return;
        }
        tasks.get(index - 1).setCompleted();
        unfinishedTaskCounter--;
        System.out.println("Task " + tasks.get(index - 1).getTaskName() + " has been marked done!");
        listTasks();
    }

    public void markUndone(String taskName){
        if (tasks.isEmpty()){
            HudouException.handleNoTaskNotifier();
            return;
        }
        //iterate through the list to identify the task satisfying the name "taskName"
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskName().equals(taskName)) {
                tasks.get(i).setUncompleted();
                unfinishedTaskCounter++;
                System.out.println("Task " + taskName + " has been unmarked!");
                listTasks();
                return;
            }
        }
        HudouException.handleNonExistentTaskNotifier();
    }

    public void markUndone(int index){
        if (HudouException.handleTaskListExceptions(index, tasks.size(), unfinishedTaskCounter)
                == HudouException.taskListErrors.errorCaught) {
            return;
        }
        if (!tasks.get(index - 1).getTaskCompletionStatus()) {
            System.out.println("main.java.Hudou.task.Task " + tasks.get(index - 1).getTaskName() + " has not been done!");
            System.out.println(tasks.get(index - 1).printTask());
            return;
        }
        tasks.get(index - 1).setUncompleted();
        unfinishedTaskCounter++;
        System.out.println("main.java.Hudou.task.Task " + tasks.get(index - 1).getTaskName() + " has been unmarked!");
        listTasks();
    }

    public void deleteTask(int index){
        if (HudouException.handleTaskListExceptions(index, tasks.size(), unfinishedTaskCounter)
                == HudouException.taskListErrors.errorCaught) {
            return;
        }
        if (!tasks.get(index - 1).getTaskCompletionStatus()) {
            unfinishedTaskCounter--;
        }
        tasks.remove(index - 1);
        System.out.println("The task has been deleted!");
        listTasks();
    }
}
