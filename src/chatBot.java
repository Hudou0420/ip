public class chatBot{
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

    public static void EndSession(){
        String chatbotExit =  "\n-------------------------------------------\n" +
                "Bye. Hope to see you again soon!\n\n" +
                "-------------------------------------------\n";
        System.out.println(chatbotExit);
    }
}
