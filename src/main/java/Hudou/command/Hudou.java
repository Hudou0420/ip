package main.java.Hudou.command;

import java.util.Scanner;

public class Hudou {
    public static void main(String[] args) {
        ChatBot chatBot = new ChatBot();
        String input = null;
        Scanner scanner = new Scanner(System.in);
        do{
            input = scanner.nextLine();
            chatBot.reactToInputs(input);
        } while(!input.equalsIgnoreCase("bye"));
    }
}
