package main.java.Hudou.exception;

/**
 * @file    HudouException.java
 * @author  Hu Hongheng
 * @date    2025-02-27
 * @brief   Defines custom exception handling for the Hudou application.
 *
 * This class contains predefined error messages and static methods to handle
 * different types of exceptions in the application, including input validation
 * errors and missing files. It also provides utility methods to manage exceptions
 * related to task management and chatbot interactions.
 */
public class HudouException {

    public static final String emptyDescription = "You did not enter a description for the task.";
    public static final String unknownInput = "I have no idea what you've said\n" +
            "So I am just gonna repeat what you've said.";

    public static final String emptyTask = "Task not added, the input is empty.";
    public static final String invalidTask = "Task not added, the input is invalid.";

    public static final String noTaskNotifier = "You have no task in your list!";
    public static final String nonExistentTaskNotifier = "The task is not in your list!";

    public static final String chatBotEmptyInput = "Error: You have entered an empty input.";
    public static final String chatBotInvalidInput = "Error: You have entered an invalid input.";

    public static final String fileNotFound = "File not found! Creating a new tasks.txt...";


    public static void handleEmptyTask(){ System.out.println(emptyTask); }
    public static void handleInvalidTask(){ System.out.println(invalidTask); }
    public static void handleNoTaskNotifier(){ System.out.println(noTaskNotifier); }
    public static void handleNonExistentTaskNotifier(){ System.out.println(nonExistentTaskNotifier); }

    public static void handleChatBotInvalidInput(){ System.out.println(chatBotInvalidInput); }

    /**
     * @enum taskListErrors
     * @brief Enum representing possible task list errors.
     */
    public static enum taskListErrors {noError, errorCaught}

    /**
     * @brief Handles exceptions related to task list management.
     *
     * @param index The task index being accessed.
     * @param taskCounter The total number of tasks in the list.
     * @param unfinishedTaskCounter The number of unfinished tasks.
     * @return taskListErrors Enum indicating if an error was caught.
     */
    public static taskListErrors handleTaskListExceptions(int index, int taskCounter, int unfinishedTaskCounter){
        if (taskCounter == 0){
            handleNoTaskNotifier();
            return taskListErrors.errorCaught;
        }
        if (index > taskCounter){
            handleNonExistentTaskNotifier();
            return taskListErrors.errorCaught;
        }
        return taskListErrors.noError;
    }

    public static void JARFullNotifier() {
        System.err.println("Error: JAR directory is null. Cannot write tasks to file.");
    }

    public static void handleException(Exception e) {
        if (e instanceof NullPointerException) {
            handleEmptyTask();
        } else if (e instanceof ArrayIndexOutOfBoundsException) {
            handleInvalidTask();
        } else if (e instanceof InvalidDateFormatException) {
            System.err.println(e.getMessage());
        } else {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}
