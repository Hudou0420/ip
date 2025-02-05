public class ChatBot {
    public static final String lineSeparator = "-".repeat(30);


    private TaskList taskList;

    public ChatBot(){
        greeting();
        taskList = new TaskList();
    }

    public static void greeting(){
        String chatbotName = "Hudou";
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        String chatbotGreeting = lineSeparator + '\n' +
                "Hello! I'm " + chatbotName + "\nWhat can I do for you?\n";
        System.out.println(chatbotGreeting);
    }

    public static void endSession(){
        String chatbotExit =  '\n' + lineSeparator + '\n' +
                "Bye. Hope to see you again soon!\n" +
                lineSeparator + '\n';
        System.out.println(chatbotExit);
    }

    public static void echo(String input){
        System.out.println(lineSeparator);
        System.out.println("You said: " + input);
        System.out.println(lineSeparator);
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void reactToInputs(String input){
        String[] inputs = input.split(" ");
        try{
            switch (inputs[0]){ //take the first arg of the inputs
            case "list":
                taskList.listTasks();
                break;
            case "bye":
                endSession();
                break;
            case "todo":
            case "deadline":
            case "event":
                taskList.addEvent(input);
                break;

            case "mark":
                if (isInteger(inputs[1])){
                    taskList.markDone(Integer.parseInt(inputs[1]));
                } else{
//                    int firstSpaceIndex = input.indexOf(" ");
//                    //take the rest of the command and find the event
//                    String eventName = (firstSpaceIndex != -1) ?
//                            input.substring(firstSpaceIndex + 1) : "";
                    String taskName = Task.getSubstringFromSecondWord(input);
                    taskList.markDone(taskName);
                }
                break;
            case "unmark":
                if (isInteger(inputs[1])){
                    taskList.markUndone(Integer.parseInt(inputs[1]));
                } else{
//                    int firstSpaceIndex = input.indexOf(" ");
//                    //take the rest of the command and find the event
//                    String eventName = (firstSpaceIndex != -1) ?
//                            input.substring(firstSpaceIndex + 1) : "";
                    String taskName = Task.getSubstringFromSecondWord(input);
                    taskList.markUndone(taskName);
                }
                break;

            //handle exceptions
            case "":
                System.out.println("You did not say anything.");
                break;
            default:
                echo(input);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: You have entered an invalid input.");
        } catch (NullPointerException e) {
            System.out.println("Error: You have entered an empty input.");
        }
    }
}
