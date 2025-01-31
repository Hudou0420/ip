public class TaskList {

    private Tasks[] tasks;
    private int eventListSize;

    public TaskList() {
        tasks = new Tasks[100];
        eventListSize = 0;
    }

    public TaskList(Tasks[] tasks) {
        this.tasks = tasks;
        eventListSize = tasks.length;
    }

    public TaskList(TaskList other) {
        this.tasks = other.tasks;
        this.eventListSize = other.eventListSize;
    }

    public void addEvent(String event) {
        if (eventListSize >= 100){
            System.out.println("You have too many events in your list! Complete some before adding new ones.");
            return;
        }
        Tasks newTasks = new Tasks(event);
        tasks[eventListSize] = newTasks;
        eventListSize++;
        System.out.println("Added new event: " + newTasks.getEventName());
        System.out.println("You have " + eventListSize + " events in your list!");
    }

    public void listTasks(){
        if (eventListSize == 0){
            System.out.println("You have no events in your list!");
            return;
        }
        for (int i = 0; i < eventListSize; i++) {
            System.out.println((i + 1) + ". " +
                    (tasks[i].getEventStatus() ? "[X] " : "[ ] ")
                            + tasks[i].getEventName());
        }
    }

    public void markDone(String eventName){
        if (eventListSize == 0){
            System.out.println("You have no events in your list!");
            return;
        }
        for (int i = 0; i < eventListSize; i++) {
            if (tasks[i].getEventName().equals(eventName)) {
                tasks[i].setCompleted();
                System.out.println("Event " + eventName + " has been marked done!");
                //System.out.println("You have " + eventListSize + " events in your list!");
                System.out.println("--------------------------------------------------");
                listTasks();
                System.out.println("--------------------------------------------------");
                return;
            }
        }
        System.out.println("The event is not in your list!");
    }

    public void markDone(int index){
        if (eventListSize == 0){
            System.out.println("You have no events in your list!");
            return;
        }
        if (index > eventListSize){
            System.out.println("The event does not exist in your list!");
            return;
        }
        if (tasks[index - 1].getEventStatus()) {
            System.out.println("Event " + tasks[index - 1].getEventName() + " has already been done!");
            return;
        }
        tasks[index - 1].setCompleted();
        System.out.println("Event " + tasks[index - 1].getEventName() + " has been marked done!");
        System.out.println("--------------------------------------------------");
        listTasks();
        System.out.println("--------------------------------------------------");
    }

    public void markUndone(String eventName){
        for (int i = 0; i < eventListSize; i++) {
            if (tasks[i].getEventName().equals(eventName)) {
                tasks[i].setUncompleted();
                System.out.println("Event " + eventName + " has been unmarked!");
                System.out.println("--------------------------------------------------");
                listTasks();
                System.out.println("--------------------------------------------------");
                return;
            }
        }
        System.out.println("The event is not in your list!");
    }

    public void markUndone(int index){
        if (eventListSize == 0){
            System.out.println("You have no events in your list!");
            return;
        }
        if (index > eventListSize){
            System.out.println("The event does not exist in your list!");
            return;
        }
        if (!tasks[index - 1].getEventStatus()) {
            System.out.println("Event " + tasks[index - 1].getEventName() + " has not been done!");
            return;
        }
        tasks[index - 1].setUncompleted();
        System.out.println("Event " + tasks[index - 1].getEventName() + " has been unmarked!");
        System.out.println("--------------------------------------------------");
        listTasks();
        System.out.println("--------------------------------------------------");
    }
}
