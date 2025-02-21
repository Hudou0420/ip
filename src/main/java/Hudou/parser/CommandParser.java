package main.java.Hudou.parser;

import main.java.Hudou.command.*;
import main.java.Hudou.exception.HudouException;

public class CommandParser {

    private static void echo(String input){
        System.out.println(input);
    }

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
