package main.java.Hudou.parser;

import main.java.Hudou.command.*;
import main.java.Hudou.exception.HudouException;

/**
 * @file    CommandParser.java
 * @author  Hu Hongheng
 * @date    2025-02-27
 * @brief   Parses user input commands for the Hudou chatbot.
 *
 * This class processes user input commands and maps them to
 * corresponding command objects. It determines the command type
 * based on the first keyword and creates an instance of the
 * appropriate command class. If the input is unknown, it returns
 * an error message and echoes back the user's input.
 */
public class CommandParser {

    /**
     * @brief Echoes the input back to the user when the command is not understood.
     *
     * @param input The user input string to be echoed.
     */
    private static void echo(String input){
        System.out.println(input);
    }

    /**
     * @brief Parses the user input and returns the corresponding Command object.
     *
     * This method analyzes the first keyword of the input and maps it to
     * a command class. It handles known commands like "list", "bye", "todo",
     * "deadline", "event", "mark", "unmark", "delete", and "save".
     *
     * @param input The user input string containing a command.
     * @return The appropriate Command object, or null if the input is invalid.
     */
    public static Command parse(String input) {
        String[] inputs = input.split(" ");
        switch (inputs[0].toLowerCase()) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(input, false);
        case "mark":
            return new MarkCommand(input, true);
        case "unmark":
            return new MarkCommand(input, false);
        case "delete":
            return new DeleteCommand(Integer.parseInt(inputs[1]));
        case "find":
            return new FindCommand(inputs[1]);
        case "save":
            return new SaveCommand();
        case "":
            System.out.println("You did not say anything.");
            return null;
        default:
            System.out.println(HudouException.unknownInput);
            echo(input);
            return null;
        }
    }
}
