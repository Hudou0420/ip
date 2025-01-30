public class Event {

    private String eventName;
    private String eventDate;
    private boolean isCompleted;


    public Event(String name){
        this.eventName = name;
        this.isCompleted = false;
    }

    public Event(String name, String eventDate){
        this.eventName = name;
        this.eventDate = eventDate;
        this.isCompleted = false;
    }

    public String getEventName(){ return this.eventName; }
    public void changeEventName(String name){ this.eventName = name; }

    public boolean getEventStatus(){ return this.isCompleted; }
    public void setCompleted(){ this.isCompleted = true; }
    public void setUncompleted(){ this.isCompleted = false; }
}
