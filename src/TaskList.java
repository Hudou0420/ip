public class TaskList {

    public static final int MAXTASKCOUNT = 100;
    public static final String noTaskNotifier = "You have no task in your list!";
    public static final String nonExistentTaskNotifier = "The task is not in your list!";

    private Task[] tasks;
    private int taskCounter;

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
        tasks = new Task[MAXTASKCOUNT];
        taskCounter = 0;
    }

    //constructor that takes in an already existed task list
    //use later if needed
    public TaskList(Task[] tasks) {
        this.tasks = tasks;
        taskCounter = tasks.length;
    }

    //copy constructor for later use if needed
    public TaskList(TaskList other) {
        this.tasks = other.tasks;
        this.taskCounter = other.taskCounter;
    }


    public void addEvent(String taskInput) {
        //handle exception of too many events being in the list
        if (taskCounter >= MAXTASKCOUNT){
            System.out.println("You have too many events in your list! Complete some before adding new ones.");
            return;
        }
        //process the first arg to get the type of the task
        tasks[taskCounter] = classifyTaskTypes(taskInput);
        taskCounter++;
        listTasks();
    }

    //method to print out all the tasks, whether it is completed or not
    //this method prints the tasks added earliest first.
    public void listTasks(){
        if (taskCounter == 0){
            System.out.println(noTaskNotifier);
            return;
        }
        System.out.println("You have " + taskCounter + " tasks in your list!");
        System.out.println(ChatBot.lineSeparator);
        for (int i = 0; i < taskCounter; i++) {
            System.out.println((i + 1) + "." + tasks[i].printTask());
        }
        System.out.println(ChatBot.lineSeparator);
    }

    public void markDone(String taskName){
        if (taskCounter == 0){
            System.out.println(noTaskNotifier);
            return;
        }
        //iterate through the list to identify the task satisfying the name "taskName"
        for (int i = 0; i < taskCounter; i++) {
            if (tasks[i].getTaskName().equals(taskName)) {
                tasks[i].setCompleted();
                System.out.println("Event " + taskName + " has been marked done!");
                listTasks();
                return;
            }
        }
        System.out.println(nonExistentTaskNotifier);
    }

    public void markDone(int index){
        //handle exception where there is no task in the list
        if (taskCounter == 0){
            System.out.println(noTaskNotifier);
            return;
        }
        //handle exception where the input index is
        //greater than the number of tasks inside the list
        if (index > taskCounter){
            System.out.println(nonExistentTaskNotifier);
            return;
        }
        if (tasks[index - 1].getTaskCompletionStatus()) {
            System.out.println("Task " + tasks[index - 1].getTaskName() + " has already been done!");
            //print task returns string because I need String output
            //in the lisTasks() method
            System.out.println(tasks[index - 1].printTask());
            return;
        }
        tasks[index - 1].setCompleted();
        System.out.println("Task " + tasks[index - 1].getTaskName() + " has been marked done!");
        listTasks();
    }

    public void markUndone(String taskName){
        if (taskCounter == 0){
            System.out.println(noTaskNotifier);
            return;
        }
        //iterate through the list to identify the task satisfying the name "taskName"
        for (int i = 0; i < taskCounter; i++) {
            if (tasks[i].getTaskName().equals(taskName)) {
                tasks[i].setUncompleted();
                System.out.println("Task " + taskName + " has been unmarked!");
                listTasks();
                return;
            }
        }
        System.out.println(nonExistentTaskNotifier);
    }

    public void markUndone(int index){
        if (taskCounter == 0){
            System.out.println(noTaskNotifier);
            return;
        }
        if (index > taskCounter){
            System.out.println(nonExistentTaskNotifier);
            return;
        }
        if (!tasks[index - 1].getTaskCompletionStatus()) {
            System.out.println("Task " + tasks[index - 1].getTaskName() + " has not been done!");
            System.out.println(tasks[index - 1].printTask());
            return;
        }
        tasks[index - 1].setUncompleted();
        System.out.println("Task " + tasks[index - 1].getTaskName() + " has been unmarked!");
        listTasks();
    }
}
