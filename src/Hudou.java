import java.util.Scanner;

public class Hudou {
    public static void main(String[] args) {
        ChatBot chatBot = new ChatBot();
        String input = null;
        do{
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            chatBot.processInputs(input);
        } while(!input.equals("bye"));
    }
}
