package main.java.Hudou.command;

import main.java.Hudou.parser.CommandParser;
import main.java.Hudou.task.TaskList;

import static main.java.Hudou.storage.IOHandler.readTasksFromFile;

public class ChatBot {
    public static final String chatbotName = "Hudou";
    public static final String lineSeparator = "-".repeat(30);

    public static final String chatbotGreeting = lineSeparator + '\n' +
            "Hello! I'm " + chatbotName + "\nWhat can I do for you?\n";

    private TaskList taskList;

    //called when first initialised, it will read all the tasks stored in the "tasks.txt" file
    //and bring them into the program. if the file does not exist, i.e. when the user first
    //uses the program, it will create a file. Details please look at the IOHandler.readTaskFromFile() method
    public ChatBot() {
        greeting();
        taskList = readTasksFromFile();
    }

    public static void greeting() {
        System.out.println(chatbotGreeting);
    }

    //helper function for parsing
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //important method to call when user finished typing a commmand and chatbot
    //is suppose to understand what the user has said
    public void reactToInputs(String input) {
        Command command = CommandParser.parse(input);
        if (command != null) {
            command.execute(taskList);
        }
    }
}
