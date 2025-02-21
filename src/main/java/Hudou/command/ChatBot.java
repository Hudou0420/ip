package main.java.Hudou.command;

import main.java.Hudou.parser.CommandParser;
import main.java.Hudou.list.TaskList;

import static main.java.Hudou.storage.IOHandler.readTasksFromFile;

public class ChatBot {
    public static final String chatbotName = "Hudou";
    public static final String lineSeparator = "-".repeat(30);

    public static final String chatbotGreeting = lineSeparator + '\n' +
            "Hello! I'm " + chatbotName + "\nWhat can I do for you?\n";
    public static final String chatbotExit =  '\n' + lineSeparator + '\n' +
            "Bye. Hope to see you again soon!\n" +
            lineSeparator + '\n';

    private TaskList taskList;

    public ChatBot() {
        greeting();
        taskList = readTasksFromFile();
    }

    public static void greeting() {
        System.out.println(chatbotGreeting);
    }

    public static void endSession() {
        System.out.println(chatbotExit);
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void reactToInputs(String input) {
        Command command = CommandParser.parse(input);
        if (command != null) {
            command.execute(taskList);
        }
    }
}
