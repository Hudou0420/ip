package main.java.Hudou.task;

import main.java.Hudou.command.ChatBot;
import main.java.Hudou.exception.HudouException;

import java.util.ArrayList;

public class TaskList {

    public static final int MAXTASKCOUNT = 100;
    public static final String tooManyTasksNotifier = "You have too many events in your list! Complete some before adding new ones.";


    private ArrayList<Task> tasks;
    //private int taskCounter;
    private int unfinishedTaskCounter;

    private Task classifyTaskTypes(String input){
        String[] inputArray = input.split(" ");
        return switch (inputArray[0]) {
            case "todo" -> new Todo(input);
            case "deadline" -> new Deadline(input);
            case "event" -> new Event(input);
            default -> new Task(input);
        };
    }

    //parameterless
    public TaskList() {
        //tasks = new Task[MAXTASKCOUNT];
        tasks = new ArrayList<Task>();
        //taskCounter = 0;
        unfinishedTaskCounter = 0;
    }

    //constructor that takes in an already existed task list
    //use later if needed
    public TaskList(Task[] tasks) {
        //this.tasks = tasks;
        //taskCounter = tasks.length;
    }

    //copy constructor for later use if needed
    public TaskList(TaskList other) {
        this.tasks = other.tasks;
        //this.taskCounter = other.taskCounter;
    }


    public void addTask(String taskInput) {
        //handle exception of too many events being in the list
        if (tasks.size() >= MAXTASKCOUNT){
            System.out.println(tooManyTasksNotifier);
            return;
        }
        //process the first arg to get the type of the task
        //tasks[taskCounter] = classifyTaskTypes(taskInput);
        tasks.add(classifyTaskTypes(taskInput));
        //taskCounter++;
        unfinishedTaskCounter++;
        listTasks();
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
                System.out.println("main.java.Hudou.task.Event " + taskName + " has been marked done!");
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
        if (tasks.get(index - 1).getTaskCompletionStatus()) {
            System.out.println("main.java.Hudou.task.Task " + tasks.get(index - 1).getTaskName() + " has already been done!");
            //print task returns string because I need String output
            //in the lisTasks() method
            System.out.println(tasks.get(index - 1).printTask());
            return;
        }
        tasks.get(index - 1).setCompleted();
        unfinishedTaskCounter--;
        System.out.println("main.java.Hudou.task.Task " + tasks.get(index - 1).getTaskName() + " has been marked done!");
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
                System.out.println("main.java.Hudou.task.Task " + taskName + " has been unmarked!");
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
        tasks.remove(index - 1);
        //taskCounter--;
        unfinishedTaskCounter--;
        System.out.println("The task has been deleted!");
        listTasks();
    }
}
