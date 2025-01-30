import java.util.Scanner;

public class Hudou {
    public static void main(String[] args) {
        chatBot chatBot = new chatBot();
        String input = null;
        do{
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            chatBot.processInputs(input);
        } while(!input.equals("bye"));
    }
}
