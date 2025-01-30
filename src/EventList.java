public class EventList {

    private Event[] events;
    private int eventListSize;

    public EventList() {
        events = new Event[100];
        eventListSize = 0;
    }

    public EventList(Event[] events) {
        this.events = events;
        eventListSize = events.length;
    }

    public EventList(EventList other) {
        this.events = other.events;
        this.eventListSize = other.eventListSize;
    }

    public void addEvent(String event) {
        if (eventListSize >= 100){
            System.out.println("You have too many events in your list! Complete some before adding new ones.");
            return;
        }
        Event newEvent = new Event(event);
        events[eventListSize] = newEvent;
        eventListSize++;
        System.out.println("Added new event: " + newEvent.getEventName());
        System.out.println("You have " + eventListSize + " events in your list!");
    }

    public void listEvents(){
        if (eventListSize == 0){
            System.out.println("You have no events in your list!");
            return;
        }
        for (int i = 0; i < eventListSize; i++) {
            System.out.println((i + 1) + ". " +
                    (events[i].getEventStatus() ? "[X] " : "[ ] ")
                            + events[i].getEventName());
        }
    }

    public void markDone(String eventName){
        if (eventListSize == 0){
            System.out.println("You have no events in your list!");
            return;
        }
        for (int i = 0; i < eventListSize; i++) {
            if (events[i].getEventName().equals(eventName)) {
                events[i].setCompleted();
                System.out.println("Event " + eventName + " has been marked done!");
                //System.out.println("You have " + eventListSize + " events in your list!");
                System.out.println("--------------------------------------------------");
                listEvents();
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
        if (events[index - 1].getEventStatus()) {
            System.out.println("Event " + events[index - 1].getEventName() + " has already been done!");
            return;
        }
        events[index - 1].setCompleted();
        System.out.println("Event " + events[index - 1].getEventName() + " has been marked done!");
        System.out.println("--------------------------------------------------");
        listEvents();
        System.out.println("--------------------------------------------------");
    }

    public void markUndone(String eventName){
        for (int i = 0; i < eventListSize; i++) {
            if (events[i].getEventName().equals(eventName)) {
                events[i].setUncompleted();
                System.out.println("Event " + eventName + " has been unmarked!");
                System.out.println("--------------------------------------------------");
                listEvents();
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
        if (!events[index - 1].getEventStatus()) {
            System.out.println("Event " + events[index - 1].getEventName() + " has not been done!");
            return;
        }
        events[index - 1].setUncompleted();
        System.out.println("Event " + events[index - 1].getEventName() + " has been unmarked!");
        System.out.println("--------------------------------------------------");
        listEvents();
        System.out.println("--------------------------------------------------");
    }
}
