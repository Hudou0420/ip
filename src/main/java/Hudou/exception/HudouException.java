package main.java.Hudou.exception;

public class HudouException {

    public static final String emptyDescription = "You did not enter a description for the task.";
    public static final String unknownInput = "I have no idea what you've said\nSo I am just gonna repeat what you've said.";

    public static final String emptyTask = "Event not added, the input is empty.";
    public static final String invalidTask = "task.Event not added, the input is invalid.";

    public static final String noTaskNotifier = "You have no task in your list!";
    public static final String nonExistentTaskNotifier = "The task is not in your list!";

    public static final String chatBotEmptyInput = "Error: You have entered an empty input.";
    public static final String chatBotInvalidInput = "Error: You have entered an invalid input.";

    //exceptions for the main.java.Hudou.task.Task class
    public static void handleEmptyTask(){ System.out.println(emptyTask); }
    public static void handleInvalidTask(){ System.out.println(invalidTask); }

    //exceptions for the tasklist class
    public static void handleNoTaskNotifier(){ System.out.println(noTaskNotifier); }
    public static void handleNonExistentTaskNotifier(){ System.out.println(nonExistentTaskNotifier); }

    //exceptions for the chatBot class
    public static void handleChatBotEmptyInput(){ System.out.println(chatBotEmptyInput); }
    public static void handleChatBotInvalidInput(){ System.out.println(chatBotInvalidInput); }
}
