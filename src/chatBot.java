import java.util.Scanner;

public class chatBot{

    private EventList eventList;

    public chatBot(){
        greeting();
        eventList = new EventList();
    }

    public static void greeting(){
        String chatbotName = "Hudou";
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        String chatbotGreeting = "-------------------------------------------\n" +
                "Hello! I'm " + chatbotName + "\nWhat can I do for you?\n";
        System.out.println(chatbotGreeting);
    }

    public static void endSession(){
        String chatbotExit =  "\n-------------------------------------------\n" +
                "Bye. Hope to see you again soon!\n" +
                "-------------------------------------------\n";
        System.out.println(chatbotExit);
    }

    public static void echo(){
        String user = null;
        do {
            Scanner cin = new Scanner(System.in);
            user = cin.nextLine();
            if (!user.equals("bye")) {
                System.out.println("------------------------------------------");
                System.out.println("You said: " + user);
                System.out.println("------------------------------------------");
            }
        } while(!user.equals("bye"));
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void processInputs(String input){
        String[] inputs = input.split(" ");
        switch (inputs[0]){ //take the first arg of the inputs
        case "list":
            eventList.listEvents();
            break;
        case "bye":
            endSession();
            break;
        case "mark":
            if (isInteger(inputs[1])){
                eventList.markDone(Integer.parseInt(inputs[1]));
            } else{
                int firstSpaceIndex = input.indexOf(" ");
                //take the rest of the command and find the event
                String eventName = (firstSpaceIndex != -1) ? input.substring(firstSpaceIndex + 1) : "";
                eventList.markDone(eventName);
            }
            break;
        case "unmark":
            if (isInteger(inputs[1])){
                eventList.markUndone(Integer.parseInt(inputs[1]));
            } else{
                int firstSpaceIndex = input.indexOf(" ");
                //take the rest of the command and find the event
                String eventName = (firstSpaceIndex != -1) ? input.substring(firstSpaceIndex + 1) : "";
                eventList.markUndone(eventName);
            }
            break;
        default:
            eventList.addEvent(input);
        }
    }
}
