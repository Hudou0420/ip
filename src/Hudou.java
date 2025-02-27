import main.java.Hudou.command.ChatBot;

import java.util.Locale;
import java.util.Scanner;

public class Hudou {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);  //some weird things happen when i do not include this line
                                            //because my PC system language is in chinese
        ChatBot chatBot = new ChatBot();
        String input = null;
        Scanner scanner = new Scanner(System.in);
        do{
            input = scanner.nextLine();
            chatBot.reactToInputs(input);
        } while(!input.equalsIgnoreCase("bye"));
    }
}
